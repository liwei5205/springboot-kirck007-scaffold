package com.kirck.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyShiroRealm extends AuthorizingRealm{
	 	
	 	/**
	 	 * 执行授权逻辑
	 	 */
	    @Override
	    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//	        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
	        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	       
	        return authorizationInfo;
	    }
	 
	    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
	    @Override
	    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
	            throws AuthenticationException {
//	        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
	        //获取用户的输入的账号.
	        String username = (String) token.getPrincipal();
//	        System.out.println(token.getCredentials());
	        //通过username从数据库中查找 User对象，如果找到，没找到.
	        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法


	        AuthenticationInfo authenticationInfo = null;
			return authenticationInfo;
	    }

}
