package com.bjworld.groupware.customer.web;

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

import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.customer.service.impl.CustomerVO;
import com.bjworld.groupware.customer.service.customerService;
/*import com.bjworld.groupware.customer.web.CustomerController;*/
import com.bjworld.groupware.organization.service.OrganizationService;
import com.bjworld.groupware.organization.service.impl.OrganizationVO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/admin")
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Resource(name = "customerService")
	private customerService customerService;

	@RequestMapping("/customer.do")
	public String customer(HttpServletRequest request, Model model) throws Exception {
		return "customer/customer.at";
	}

	@RequestMapping(value = "/getCustomerListAjax.do")
	@ResponseBody
	// https://hongku.tistory.com/118
	public HashMap<String, Object> getCustomerListAjax(HttpServletRequest request, CustomerVO paramVO)
			throws Exception {
		// 테이블에 바인딩 할 데이터
		List<?> dataList = customerService.selectCustomerList(paramVO);
		// Total Count
		Integer total = 0;

		HashMap<String, Object> listMap = new HashMap<String, Object>();

		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}

	// 등록
	@RequestMapping(value = "/mergeCustomerAjax.do")
	@ResponseBody
	public AjaxResult<String> mergeCustomerAjax(HttpServletRequest request, CustomerVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {

			if (EgovStringUtil.isEmpty(paramVO.getSeq())) {
				// seq 가 공백이면 insert
				customerService.mergeCustomer(paramVO);
			} else {
				// seq 가 공백이 아니면 update
				customerService.updateCustomer(paramVO);
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

	@RequestMapping(value = "/selectCustomerAjax.do")
	@ResponseBody
	public AjaxResult<Object> selectCustomerAjax(HttpServletRequest request, CustomerVO paramVO) throws Exception {

		AjaxResult<Object> result = new AjaxResult<>();
		try {
			// select 방식
			// employeeService.selectEmployee(paramVO);
			System.out.println(paramVO);
			CustomerVO viewVO = customerService.selectCustomer(paramVO);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "시스템관리자를 불러 중"));
		}
		return result;
	}
	
	@RequestMapping(value = "/deleteCustomerAjax.do")
	@ResponseBody
	public AjaxResult<String> deleteCustomerAjax(HttpServletRequest request, CustomerVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();

		try {
			customerService.deleteCustomer(paramVO);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData("");
			result.setMsg("시스템관리자를 삭제하였습니다.");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 시스템관리자를 삭제 하는 중"));
		}
		return result;
	}
}