package com.bjworld.groupware.emergencynumber.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.department.service.DepartmentService;
import com.bjworld.groupware.emergencynumber.service.EmergencynumberService;
import com.bjworld.groupware.emergencynumber.service.impl.EmergencynumberVO;
import com.bjworld.groupware.employee.service.EmployeeService;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;

@Controller
@RequestMapping("/admin")
public class EmergencynumberController {
	Logger logger = LoggerFactory.getLogger( EmergencynumberController.class);
	
	@Resource(name = "emergencynumberService")
	private EmergencynumberService emergencynumberService;

	@Resource(name = "employeeService")
	private EmployeeService employeeService;
	
	@RequestMapping("/emergencynumber.do")
	public String emergencynumber(HttpServletRequest request, Model model, EmployeeVO paramVO) throws Exception {
		List<?> getEmployeeList = employeeService.selectEmployeeList(paramVO);
		model.addAttribute("getEmployeeList", getEmployeeList);
		return "emergencynumber/emergencynumber.at";
	}

	@RequestMapping(value = "/getEmergencynumberListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getEmergencynumberListAjax(HttpServletRequest request
			, EmergencynumberVO paramVO) throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = emergencynumberService.selectEmergencynumberList(paramVO);
		// Total Count
		Integer total = emergencynumberService.selectEmergencynumberListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}

	@RequestMapping(value = "/mergeEmergencynumberAjax.do")
	@ResponseBody
	public AjaxResult<String> mergeEmergencynumberAjax(HttpServletRequest request, EmergencynumberVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {
			 if(EgovStringUtil.isEmpty(paramVO.getSeq())){
	            	//seq 가 공백이면 insert
	            	emergencynumberService.mergeEmergencynumber(paramVO);
	            }
	            else {
	            	//seq 가 공백이 아니면 update
	            	emergencynumberService.updateEmergencynumber(paramVO);
	            	
	            }
			// merge 방식
			// adminuserService.mergeAdminUser(paramVO);
			
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("비상연락망을 저장하였습니다.");

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "비상연락망 저장 중"));
		}

		return result;
	}
	
	
	@RequestMapping(value = "/selectEmergencynumberAjax.do")
	@ResponseBody
	public AjaxResult<EmergencynumberVO> selectEmergencynumberAjax(HttpServletRequest request, EmergencynumberVO paramVO) throws Exception {

		AjaxResult<EmergencynumberVO> result = new AjaxResult<>();
		try {

			EmergencynumberVO viewVO =  emergencynumberService.selectEmergencynumber(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "시스템관리자를 불러오는 중"));
		}

		return result;
	}
	
	  @RequestMapping(value = "/deleteEmergencynumberAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteEmergencynumberAjax(HttpServletRequest request
	            , EmergencynumberVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();

	        try
	        {
	        	emergencynumberService.deleteEmergencynumber(paramVO);
	        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("");
	        	result.setMsg("비상연락망을 삭제하였습니다.");
	        }
	        catch(Exception e) {
	            logger.error(e.getMessage());
	        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
	        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 비상연락망을 삭제하는 중"));
	        }
	        
	        return result;
	    }
	    
}
