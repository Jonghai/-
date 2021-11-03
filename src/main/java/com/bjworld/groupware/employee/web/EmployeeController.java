package com.bjworld.groupware.employee.web;

/*import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovDateUtil;
import com.bjworld.groupware.common.util.EgovFileScrty;
import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.employee.service.EmployeeService;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.File;
import java.util.HashMap;*/

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.adminuser.service.AdminUserService;
import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import com.bjworld.groupware.adminuser.web.AdminUserController;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovDateUtil;
import com.bjworld.groupware.common.util.EgovFileScrty;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.employee.service.EmployeeService;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;


@Controller
@RequestMapping("/admin")
public class EmployeeController {
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Resource(name = "employeeService")
	private EmployeeService employeeService;

	@RequestMapping("/employee.do")
	public String employee(HttpServletRequest request, Model model) throws Exception {
		return "employee/employee.at";
	}

	@RequestMapping(value = "/getEmployeeListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getEmployeeListAjax(HttpServletRequest request, AdminUserVO paramVO)
			throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = employeeService.selectEmployeeList();
		// Total Count
		Integer total = 0;

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("draw", paramVO.getDraw());
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}


	@RequestMapping(value = "/mergeEmployeeAjax.do")
	@ResponseBody
	public AjaxResult<String> mergeEmployeeAjax(HttpServletRequest request, EmployeeVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {

			// merge 방식
			// adminuserService.mergeAdminUser(paramVO);
			employeeService.mergeEmployee(paramVO);
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("시스템관리자를 저장하였습니다.");


		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "시스템관리자를 저장 중"));
		}

		return result;
	}

}