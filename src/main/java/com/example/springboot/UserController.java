package com.example.springboot;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/userInfo")
@Api(tags="user info interface")
public class UserController {

	@RequestMapping(value="getUser",method={RequestMethod.POST})
	@ApiOperation(value="根据名字查询用户",notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息",produces = "application/json")
	public String user(@ApiParam(name="name",value="用户姓名",required=true)
					   @RequestParam String name){
		return null;
	}
	
	@RequestMapping("/userList")
	public ModelAndView userInfo(Model model){
		model.addAttribute("xname", "ddd");
		return new ModelAndView("userList");
	}
	
	@RequestMapping("/userAdd")
	@RequiresPermissions("userInfo:add")
	public String userInfoAdd(){
		return "userInfoAdd";
	}
	
	@RequestMapping("/userDel")
	@RequiresPermissions("userInfo:del")
	public String userInfoDel(){
		return "userInfoDel";
	}
}
