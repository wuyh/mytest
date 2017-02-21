package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDAO;
import com.example.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("home")
@Api(tags="user info interface")
public class UserController {

	@Autowired
	private UserDAO userMapper;
	
	@RequestMapping(value="getUser",method={RequestMethod.POST})
	@ApiOperation(value="根据名字查询用户",notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息",produces = "application/json")
	public String user(@ApiParam(name="name",value="用户姓名",required=true)
					   @RequestParam String name){
		User user = userMapper.findUserByName("王小二");
		return user.getName()+"===="+ user.getAge();
	}
}
