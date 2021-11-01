package com.bjworld.groupware.login.web;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.bjworld.groupware.accesslog.service.AccessLogService;
import com.bjworld.groupware.accesslog.service.impl.AccessLogVO;
import com.bjworld.groupware.adminuser.service.AdminUserService;
import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileScrty;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;

@Controller
@RequestMapping("/admin")
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@Resource(name="accesslogService")
	private AccessLogService accessLogService;
	
	@RequestMapping("/adminlayout.do")
	public String adminlayout(HttpServletRequest request, Model m) throws Exception
	{
		return "admin/adminlayout";
	}	
}
