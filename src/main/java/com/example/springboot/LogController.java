package com.example.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("log")
@Api(value="日志测试类",tags="日志测试接口")
public class LogController {
	
	private final Logger logger = LoggerFactory.getLogger(LogController.class);

	@RequestMapping(value="writeLog",method={RequestMethod.GET})
	@ApiOperation("测试读写分离")
	public Object writeLog(){
			logger.debug("This is a debug message");  
	        logger.info("This is an info message");  
	        logger.warn("This is a warn message");  
	        logger.error("This is an error message");
	        logger.info("the server restart?");
	        new LogHelper().helpMethod();  
	        return "OK";  
	}
}
