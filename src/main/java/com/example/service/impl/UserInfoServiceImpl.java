package com.example.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.bean.UserInfo;
import com.example.repository.UserInfoRepository;
import com.example.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	UserInfoRepository userInfoRespository;

	@Override
	public UserInfo findByUserName(String userName) {
		log.info("UserInfoServiceImpl.findByUserName");
		
		return userInfoRespository.findByUserName(userName);
	}

}
