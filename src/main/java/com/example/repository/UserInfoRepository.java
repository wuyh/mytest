package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.bean.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {

	 UserInfo findByUserName(String userName);
}
