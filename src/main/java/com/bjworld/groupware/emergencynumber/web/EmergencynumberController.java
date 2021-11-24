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
import com.bjworld.groupware.emergencynumber.service.EmergencynumberService;
import com.bjworld.groupware.emergencynumber.service.impl.EmergencynumberVO;

@Controller
@RequestMapping("/admin")
public class EmergencynumberController {
	Logger logger = LoggerFactory.getLogger( EmergencynumberController.class);
	
	@Resource(name = "emergencynumberService")
	private EmergencynumberService emergencynumberService;

	@RequestMapping("/emergencynumber.do")
	public String emergencynumber(HttpServletRequest request, Model model) throws Exception {
		return "emergencynumber/emergencynumber.at";
	}

	@RequestMapping(value = "/getEmergencynumberListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getEmergencynumberListAjax(HttpServletRequest request
			, EmergencynumberVO paramVO) throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = emergencynumberService.selectEmergencynumberList();
		// Total Count
		Integer total = dataList.size();

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("draw", paramVO.getDraw());
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

			// merge 방식
			// adminuserService.mergeAdminUser(paramVO);
			emergencynumberService.mergeEmergencynumber(paramVO);
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
