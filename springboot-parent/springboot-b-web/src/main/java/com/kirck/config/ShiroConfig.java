package com.kirck.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.kirck.commen.constants.SysConstants;
import com.kirck.commen.exception.BusinessException;
import com.kirck.handler.MyExceptionHandler;
import com.kirck.shiro.MyShiroRealm;
import com.kirck.shiro.RedisSessionDao;

//@Configuration
public class ShiroConfig {
	
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

	private static final String AUTHC = "authc"; // 表示 请求需要认证才能使用
	private static final String ANON = "anon"; // 表示请求 可以匿名使用
	
	@Value("${url.nologin}")
	private String noLoginUrl;
	
	/**
	 * session管理
	 */
	@Bean(name = "sessionManager")
	public SessionManager sessionManager(RedisSessionDao redisSessionDao) {
		// CustomWebSessionManager
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(SysConstants.Token.CACHE_TIME_SECOND * 1000);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setSessionDAO(redisSessionDao);
		return sessionManager;
	}

	@Bean
	public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
		shiroFilterFactoryBean.setLoginUrl("/tips/noLogin");
		// 登录成功后要跳转的链接
//		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面
//		shiroFilterFactoryBean.setUnauthorizedUrl("/tips/unauth"); // 权限控制提示已经被全局异常拦截，并处理返回了， 因此未经过这里

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 注意过滤器配置顺序 不能颠倒
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
//		filterChainDefinitionMap.put("/logout", "logout");
		
		// 配置不会被拦截的链接 顺序判断
		// swagger 的配置
		filterChainDefinitionMap.put("/swagger-ui.html", ANON);
		filterChainDefinitionMap.put("/configuration/ui", ANON);
		filterChainDefinitionMap.put("/configuration/security", ANON);
		filterChainDefinitionMap.put("/swagger-resources/**", ANON);
		filterChainDefinitionMap.put("/v2/api-docs", ANON);
		filterChainDefinitionMap.put("/webjars/**", ANON);

		// 添加 coders 配置的链接
		initConfigDefinition(filterChainDefinitionMap, noLoginUrl);
		
		// 静态资源，以及特殊接口如 “登录” 等
		filterChainDefinitionMap.put("/static/**", ANON);
		filterChainDefinitionMap.put("/durid2/**", ANON);
		filterChainDefinitionMap.put("/api/**", ANON);
		filterChainDefinitionMap.put("/null/**", ANON);
		filterChainDefinitionMap.put("/login", ANON);
		filterChainDefinitionMap.put("/login.html", ANON);
		filterChainDefinitionMap.put("/captcha", ANON);
		filterChainDefinitionMap.put("/captcha.jpg", ANON);
		filterChainDefinitionMap.put("/**", ANON); // TODO 为了方便本地测试，可以直接放开这一行，所有接口无需要认证
//		filterChainDefinitionMap.put("/**", AUTHC); // 表示要对所有的请求做认证；要保证这一条放在最后生效
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("sessionManager") SessionManager sessionManager,@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setSessionManager(sessionManager);
		securityManager.setRealm(myShiroRealm);
		return securityManager;
	}

	/**
	 * 注册全局异常处理
	 * 
	 * @return
	 */
	@Bean(name = "exceptionHandler")
	public HandlerExceptionResolver handlerExceptionResolver() {
		return new MyExceptionHandler();
	}
	
	private void initConfigDefinition(Map<String, String> filterChainDefinitionMap, String noLoginUrl) {
		if(StringUtils.isNotBlank(noLoginUrl)) {
			String[]  noLoginUrlArr = StringUtils.split(noLoginUrl, SysConstants.Symbol.COMMA);
			for(String url : noLoginUrlArr) {
				if(filterChainDefinitionMap.containsKey(url)) {
					throw new BusinessException("加载登录校验白名单配置失败, 重复的 url 配置: " + url);
				}
				if(StringUtils.isNotBlank(url)) {
					filterChainDefinitionMap.put(url, ANON);
				}
			}
		} else {
			logger.error("注意： 未配置任何认证白名单, 未配置任何认证白名单, 未配置任何认证白名单");
		}
	}
}
