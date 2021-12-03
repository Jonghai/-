package com.bjworld.groupware.server.web;

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
import com.bjworld.groupware.server.service.ServerService;
import com.bjworld.groupware.server.service.impl.*;
import com.bjworld.groupware.server.web.ServerController;

@Controller
@RequestMapping("/admin")
public class ServerController {
	Logger logger = LoggerFactory.getLogger(ServerController.class);
	
	@Resource(name = "serverService")
	private ServerService serverService;
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	

	@RequestMapping("/server.do")
	 public String Server(HttpServletRequest request
	            , Model model) throws Exception{
		List<?> getCsList = customerService.selectCustomerList();
		model.addAttribute("getCsList", getCsList);
	        return "server/server.at";
	    }

	@RequestMapping(value = "/getServerListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getServerListAjax(HttpServletRequest request, ServerVO paramVO)
			throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = serverService.selectServerList(paramVO);
		// Total Count
		Integer total = serverService.selectServerListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}


	@RequestMapping(value = "/mergeServerAjax.do")
	@ResponseBody
	public AjaxResult<String>  mergeServerAjax(HttpServletRequest request, ServerVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {        		
            if(EgovStringUtil.isEmpty(paramVO.getSeq())){
            	//seq 가 공백이면 insert
            	serverService.mergeServer(paramVO);
            }
            else {
            	//seq 가 공백이 아니면 update
            	serverService.updateServer(paramVO);            	
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

	
	@RequestMapping(value = "/selectServerAjax.do")
	@ResponseBody
	public AjaxResult<ServerVO> selectServerAjax(HttpServletRequest request, ServerVO paramVO) throws Exception {

		AjaxResult<ServerVO> result = new AjaxResult<>();
		try {

			// select 방식
			// ServerService.selectServer(paramVO);
			ServerVO viewVO =  serverService.selectServer(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "시스템관리자를 불러 중"));
		}

		return result;
	}
	
	 @RequestMapping(value = "/deleteServerAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteServerAjax(HttpServletRequest request
	            , ServerVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();

	        try
	        {
	        	serverService.deleteServer(paramVO);
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
