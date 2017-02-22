package com.example.dao.impl;

import org.springframework.stereotype.Component;

import com.example.dao.UserDAO;
import com.example.entity.User;

@Component
public class UserDAOImpl implements UserDAO {

	@Override
	public User findUserByName(String name) {
		
		return null;
	}

}
