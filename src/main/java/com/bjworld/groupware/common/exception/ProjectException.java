package com.bjworld.groupware.common.exception;

public class ProjectException extends Exception {

	private final int ERR_CODE;

	public ProjectException(String msg, int errCode)
	{
		super(msg);
		ERR_CODE = errCode;
	}

	public int getErrCode() {
		return this.ERR_CODE;
	}
}
