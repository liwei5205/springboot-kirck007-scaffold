package com.kirck.vo;

import java.io.Serializable;
import java.util.List;

public class TokenVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5280577268310264295L;
	// 注释： company_id
	private String companyId;
	// 注释： user_id
	private String userId;
	// 角色 id
	private List<String> roleIdList;
	
	private List<String> roleNameList;
	
	private List<String> permissionList;
	

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public List<String> getRoleNameList() {
		return roleNameList;
	}

	public void setRoleNameList(List<String> roleNameList) {
		this.roleNameList = roleNameList;
	}

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	
}
