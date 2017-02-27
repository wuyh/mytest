package com.example.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		log.info("ShiroConfiguration.shiroFilter");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		
	    //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		
		//配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
		
		//<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		
		return shiroFilterFactoryBean;
	}
	
	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		securityManager.setRealm(getShiroRealm());
		
		//注入缓存
		securityManager.setCacheManager(ehCacheManager());
		
		//cookie
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}
	 
	@Bean
	public ShiroRealm getShiroRealm(){
		ShiroRealm shiroRealm = new ShiroRealm();
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return shiroRealm;
	}
	
	/**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     *  所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * @return
     */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}
	
	/**
     *  开启shiro aop注解支持.
     *  使用代理方式;所以需要开启代码支持;
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
       AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
       authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
       return authorizationAttributeSourceAdvisor;
    }
    
    @Bean
    public EhCacheManager ehCacheManager(){
    	log.info("ShiroConfiguration.ehCacheManager()");
    	EhCacheManager ehCacheManager = new EhCacheManager();
    	ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
    	return ehCacheManager;
    }
    
    /**
     * cookie
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
    	log.info("ShiroConfiguration.rememberMeCookie()");
    	//这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
    	SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
    	simpleCookie.setMaxAge(600);
    	return simpleCookie;
    }
    
    /**
     * cookie manager 
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
    	log.info("ShiroConfiguration.rememberMeManager()");
    	CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    	cookieRememberMeManager.setCookie(rememberMeCookie());
    	return cookieRememberMeManager;
    }
}
