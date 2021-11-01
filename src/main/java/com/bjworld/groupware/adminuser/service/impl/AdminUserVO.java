package com.bjworld.groupware.adminuser.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class AdminUserVO extends DefaultVO {
	private String seq;
	private String userId;
	private String userPwd;
	private String userName;
	private String userDeptSeq;
	private String userDeptName;
	private String userPhone;
	private String userPhone2;
	private String userEmail;
	private String regDate;
	private String pwdUpdateDate;
	private String userPosition;
	private String isChangePwd;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDeptSeq() {
		return userDeptSeq;
	}

	public void setUserDeptSeq(String userDeptSeq) {
		this.userDeptSeq = userDeptSeq;
	}

	public String getUserDeptName() {
		return userDeptName;
	}

	public void setUserDeptName(String userDeptName) {
		this.userDeptName = userDeptName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPhone2() {
		return userPhone2;
	}

	public void setUserPhone2(String userPhone2) {
		this.userPhone2 = userPhone2;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPwdUpdateDate() {
		return pwdUpdateDate;
	}

	public void setPwdUpdateDate(String pwdUpdateDate) {
		this.pwdUpdateDate = pwdUpdateDate;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getIsChangePwd() {
		return isChangePwd;
	}

	public void setIsChangePwd(String isChangePwd) {
		this.isChangePwd = isChangePwd;
	}
}
