package com.bjworld.groupware.customerstaff.web;

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
import com.bjworld.groupware.customer.service.CustomerService;
import com.bjworld.groupware.customerstaff.service.CustomerStaffService;
import com.bjworld.groupware.customerstaff.service.impl.CustomerStaffVO;
import com.bjworld.groupware.customerstaff.web.CustomerStaffController;

@Controller
@RequestMapping("/admin")
public class CustomerStaffController {
	Logger logger = LoggerFactory.getLogger(CustomerStaffController.class);
	
	@Resource(name = "customerstaffService")
	private CustomerStaffService customerstaffService;
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	
	
	@RequestMapping("/customerstaff.do")
	 public String customerstaff(HttpServletRequest request
	            , Model model) throws Exception{
		List<?> getCsList = customerService.selectCustomerList();
		model.addAttribute("getCsList", getCsList);
	        return "customerstaff/customerstaff.at";
	    }
	
	@RequestMapping(value = "/getCustomerStaffListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getCustomerStaffListAjax(HttpServletRequest request, CustomerStaffVO paramVO)
			throws Exception {
	
		// 테이블에 바인딩 할 데이터
		List<?> dataList = customerstaffService.selectCustomerStaffList(paramVO);
		// Total Count
		Integer total = customerstaffService.selectCustomerStaffListTotCnt(paramVO);
	
		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}
	
	
	@RequestMapping(value = "/mergeCustomerStaffAjax.do")
	@ResponseBody
	public AjaxResult<String>  mergeBuyerCompanyAjax(HttpServletRequest request, CustomerStaffVO paramVO) throws Exception {
	
		AjaxResult<String> result = new AjaxResult<>();
		try {
	    		
	        if(EgovStringUtil.isEmpty(paramVO.getSeq())){
	        	//seq 가 공백이면 insert
	        	customerstaffService.mergeCustomerStaff(paramVO);
	        }
	        else {
	        	//seq 가 공백이 아니면 update
	        	customerstaffService.updateCustomerStaff(paramVO);
	        	
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
	
	
	@RequestMapping(value = "/selectCustomerStaffAjax.do")
	@ResponseBody
	public AjaxResult<CustomerStaffVO> selectCustomerStaffAjax(HttpServletRequest request, CustomerStaffVO paramVO) throws Exception {
	
		AjaxResult<CustomerStaffVO> result = new AjaxResult<>();
		try {
	
			// select 방식
			// projecCustomerStaffe.selectCustomerStaff(paramVO);
			CustomerStaffVO viewVO =  customerstaffService.selectCustomerStaff(paramVO);
	    	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	    	result.setData(viewVO);
	
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "시스템관리자를 불러 중"));
		}
	
		return result;
	}
	
	 @RequestMapping(value = "/deleteCustomerStaffAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteCustomerStaffAjax(HttpServletRequest request
	            , CustomerStaffVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();
	
	        try
	        {
	        	customerstaffService.deleteCustomerStaff(paramVO);
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
