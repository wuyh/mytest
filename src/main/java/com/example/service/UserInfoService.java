package com.example.service;

import com.example.bean.UserInfo;

public interface UserInfoService {

	UserInfo findByUserName(String userName);
}
