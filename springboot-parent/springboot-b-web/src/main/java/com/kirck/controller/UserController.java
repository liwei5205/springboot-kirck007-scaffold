package com.kirck.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kirck.commen.NumberConstants;
import com.kirck.commen.ResultStatus;
import com.kirck.entity.User;
import com.kirck.service.UserService;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kirck007
 * @since 2018-11-28
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/hello")
	@ResponseBody
	@ApiOperation(value = "欢迎", httpMethod = "GET")
	public String login(){
		return "hello";
	}
	
	@GetMapping(value = "/getOne")
	@ResponseBody
	@ApiOperation(value = "获取一个用户", httpMethod = "GET")
	public Map<String,Object> getOne(@RequestParam(required = true)String id){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
		 User user = userService.selectById(id);
		 if(user==null) {
			 result.put("msg", "查询失败");
			 result.put("code", ResultStatus.NULL);
		 }
		 result.put("msg", "查询成功");
		 result.put("code", ResultStatus.SUCCESS);
		 result.put("data", user);
		}catch (Exception e) {
			 logger.error("获取用户出现异常",e);
			 result.put("data", e);
			 result.put("msg", "查询异常");
			 result.put("code", ResultStatus.FAIL);
		}finally{
			return result;
		}
	}
}

