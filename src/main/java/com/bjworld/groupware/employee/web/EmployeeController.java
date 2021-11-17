package com.bjworld.groupware.employee.web;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.employee.service.EmployeeService;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;
import com.bjworld.groupware.employee.web.EmployeeController;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovDateUtil;
import com.bjworld.groupware.common.util.EgovFileScrty;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.department.service.DepartmentService;
import com.bjworld.groupware.department.service.impl.DepartmentVO;



@Controller
@RequestMapping("/admin")
public class EmployeeController {
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Resource(name = "employeeService")
	private EmployeeService employeeService;
	
	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	@RequestMapping("/employee.do")
	public String employee(HttpServletRequest request, Model model) throws Exception {
		List<?> getDeptList = departmentService.selectDepartmentList();
		model.addAttribute("getDeptList", getDeptList);
		return "employee/employee.at";
	}

	@RequestMapping(value = "/getEmployeeListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getEmployeeListAjax(HttpServletRequest request, EmployeeVO paramVO)
			throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = employeeService.selectEmployeeList(paramVO);
		// Total Count
		Integer total = employeeService.selectEmployeeListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}


	@RequestMapping(value = "/mergeEmployeeAjax.do")
	@ResponseBody
	public AjaxResult<String>  mergeBuyerCompanyAjax(HttpServletRequest request, EmployeeVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {
        		
            if(EgovStringUtil.isEmpty(paramVO.getSeq())){
            	//seq 가 공백이면 insert
            	employeeService.mergeEmployee(paramVO);
            }
            else {
            	//seq 가 공백이 아니면 update
            	employeeService.updateEmployee(paramVO);
            	
            }
            
            // merge 방식
            
        	
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

	
	@RequestMapping(value = "/selectEmployeeAjax.do")
	@ResponseBody
	public AjaxResult<EmployeeVO> selectEmployeeAjax(HttpServletRequest request, EmployeeVO paramVO) throws Exception {

		AjaxResult<EmployeeVO> result = new AjaxResult<>();
		try {

			// select 방식
			// employeeService.selectEmployee(paramVO);
			EmployeeVO viewVO =  employeeService.selectEmployee(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "시스템관리자를 불러 중"));
		}

		return result;
	}
	
	 @RequestMapping(value = "/deleteEmployeeAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteEmployeeAjax(HttpServletRequest request
	            , EmployeeVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();

	        try
	        {
	        	employeeService.deleteEmployee(paramVO);
	        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("");
	        	result.setMsg("시스템관리자를 삭제하였습니다.");
	        }
	        catch(Exception e) {
	            logger.error(e.getMessage());
	        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
	        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 시스템관리자를 삭제 하는 중"));
	        }
	        
	        return result;
	    }
	 
	
}