package com.kirck.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author kirck007
 * @since 2018-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "account对象", description = "账户对象account")
public class Account extends Model<Account> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id", hidden = true)
	private String id;

	/**
	 * 公司id
	 */
	@ApiModelProperty(value = "companyId", hidden = true)
	private String companyId;

	/**
	 * 人员id
	 */
	@ApiModelProperty(value = "userId", hidden = true)
	private String userId;

	/**
	 * 登录账号
	 */
	@ApiModelProperty(value = "登录账号", name = "loginId", required = true)
	private String loginId;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "登录密码", name = "pwd", required = true)
	private String pwd;

	/**
	 * 盐
	 */
	@ApiModelProperty(value = "salt", hidden = true)
	private String salt;

	/**
	 * 账号名称（展示用）
	 */
	@ApiModelProperty(value = "登录名", name = "accountName")
	private String accountName;

	/**
	 * 状态：0正常
	 */
	@ApiModelProperty(value = "status", hidden = true)
	private Integer status;

	/**
	 * 微信的uid
	 */
	@ApiModelProperty(value = "wxUid", name = "微信Id")
	private String wxUid;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "createDate", hidden = true)
	private LocalDateTime createDate;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "createUid", hidden = true)
	private String createUid;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "updateDate", hidden = true)
	private LocalDateTime updateDate;

	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "updateUid", hidden = true)
	private String updateUid;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWxUid() {
		return wxUid;
	}

	public void setWxUid(String wxUid) {
		this.wxUid = wxUid;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
