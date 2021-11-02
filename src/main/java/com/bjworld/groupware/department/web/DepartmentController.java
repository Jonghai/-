package com.bjworld.groupware.department.web;

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
import com.bjworld.groupware.department.service.DepartmentService;

@Controller
@RequestMapping("/admin")
public class DepartmentController {
	Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	@RequestMapping("/department.do")
	public String department(HttpServletRequest request, Model model) throws Exception {
		return "department/department.at";
	}

	@RequestMapping(value = "/getDepartmentListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getDepartmentListAjax(HttpServletRequest request, AdminUserVO paramVO)
			throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = departmentService.selectDepartmentList();
		// Total Count
		Integer total = 0;

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("draw", paramVO.getDraw());
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}

	@RequestMapping(value = "/mergeDepartmentAjax.do")
	@ResponseBody
	public AjaxResult<String> mergeDepartmentAjax(HttpServletRequest request, AdminUserVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {

			// merge 방식
			// adminuserService.mergeAdminUser(paramVO);
			departmentService.mergeDepartment(request.getParameter("deptName"));
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
