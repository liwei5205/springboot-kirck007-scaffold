package com.kirck.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kirck.commen.constants.ResultStatus;
import com.kirck.commen.result.JsonResult;
import com.kirck.vo.TokenVo;

/**
 * @author kirck007
 */
public class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected TokenVo getCurrentSession() {
		// update by zhouman 2018-12-10
		// redis 将这种数据都是写在缓存里面的，而我们当前用 redis 接管了session, 因此这些都是存储在 redis 里面的
		return (TokenVo) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 全局异常处理
	 * 
	 * @author zhouman
	 */
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	public JsonResult<String> bindException(Exception e) {
		JsonResult<String> res = new JsonResult<>();
		if (e instanceof BindException) {
			res.setCode(ResultStatus.FAIL);
			res.setMsg("接口参数类型异常，请检查接口上送参数");
		} else if (e instanceof AuthenticationException) {
			res.setCode(ResultStatus.FAIL);
			res.setMsg(e.getMessage());
		} else if (e instanceof UnauthenticatedException) {
			res.setCode(ResultStatus.FAIL);
			res.setMsg("身份未认证,请先登录");
		} else if (e instanceof UnauthorizedException || e instanceof AuthorizationException) {
			res.setCode(ResultStatus.FAIL);
			res.setMsg("无访问权限:" + e.getMessage());
		} else if (e instanceof MissingServletRequestParameterException) {
			res.setCode(ResultStatus.FAIL);
			res.setMsg("接口请求缺少参数");
		} else if (e instanceof TypeMismatchException) {
			res.setCode(ResultStatus.FAIL);
			res.setMsg("类型不匹配");
		} else {
			res.setCode(ResultStatus.FAIL);
			res.setMsg("系统异常");
		}
		
		logger.error("全局异常处理返回: {}", JSON.toJSONString(res), e);
		return res;
	}

}
