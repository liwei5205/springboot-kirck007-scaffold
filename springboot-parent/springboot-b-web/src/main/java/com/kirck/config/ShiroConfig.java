package com.kirck.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.kirck.handler.MyExceptionHandler;
import com.kirck.shiro.MyShiroRealm;

@Configuration
public class ShiroConfig {


	@Bean
	public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		//Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 注意过滤器配置顺序 不能颠倒
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
		//filterChainDefinitionMap.put("/logout", "logout");
		// 配置不会被拦截的链接 顺序判断
		//filterChainDefinitionMap.put("/static/**", "anon");
		//filterChainDefinitionMap.put("/ajaxLogin", "anon");
		//filterChainDefinitionMap.put("/login", "anon");
		//filterChainDefinitionMap.put("/**", "authc");
		// 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
		//shiroFilterFactoryBean.setLoginUrl("/unauth");
		// 登录成功后要跳转的链接
		// shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		// shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
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
}
