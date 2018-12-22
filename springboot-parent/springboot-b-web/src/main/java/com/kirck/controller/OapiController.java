package com.kirck.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kirck.commen.result.JsonResult;
import com.kirck.entity.Account;
import com.kirck.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(value = "登录相关controller", tags = { "登录操作接口" })
@RestController
@RequestMapping("oapi")
public class OapiController extends BaseController{
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/oapi/login")
	@ResponseBody
	public JsonResult<Account> login(@ApiParam(value = "用户") @RequestBody(required = true) Account account,
			@ApiParam(value = "验证码") @RequestParam(required = true) String vcode, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("----/oapi/login start");
		JsonResult<Account> result = accountService.login(account,vcode);
		return result;
	}
}
