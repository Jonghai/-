package com.bjworld.groupware.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

public class SystemConstant {

	public final static String AJAX_SUCCESS = "1";
	public final static String AJAX_FAIL = "0";
	public final static String AJAX_ERROR_MESSAGE = "%s 오류가 발생하였습니다. 같은 문제가 반복되면 관리자에게 문의하십시오.";
	public final static String ADMIN_USER_LOGIN_SESSION_KEY = "loginUserSession";
	public final static String USER_LOGIN_SESSION_KEY = "userPageSession";
	
	public final static String AdminLoginUrl = "/admin/aldomgiinn.do";
	public final static String ADMIN_LOGIN_AFTER_URL = "/admin/board.do?boardCode=1";
	
	
	public final static String UPLOAD_PATH_FACILITY = "facility";
	public final static String UPLOAD_PATH_BOARD = "board";
	public final static String UPLOAD_PATH_COMPANYSUPPORT = "companysupport";
	public final static String UPLOAD_PATH_REPORT = "report";
	public final static String UPLOAD_PATH_EQUIPMENT= "equipment";
	public final static String UPLOAD_PATH_RELATEDINSTITUTION = "relatedinstitution";
	
}
