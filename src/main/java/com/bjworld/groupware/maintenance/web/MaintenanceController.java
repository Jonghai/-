package com.bjworld.groupware.maintenance.web;

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
import com.bjworld.groupware.project.service.ProjectService;
import com.bjworld.groupware.project.service.impl.ProjectVO;
import com.bjworld.groupware.maintenance.service.MaintenanceService;
import com.bjworld.groupware.maintenance.service.impl.MaintenanceVO;
import com.bjworld.groupware.maintenance.web.MaintenanceController;

@Controller
@RequestMapping("/admin")
public class MaintenanceController {
	
Logger logger = LoggerFactory.getLogger(MaintenanceController.class);
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "maintenanceService")
	private MaintenanceService maintenanceService;
	

	@RequestMapping("/maintenance.do")
	 public String maintenance(HttpServletRequest request, Model model, ProjectVO paramVO) throws Exception{
		List<?> getPjList = projectService.selectProjectList(paramVO);
		model.addAttribute("getPjList", getPjList);
	        return "maintenance/maintenance.at";
	    }

	@RequestMapping(value = "/getMaintenanceListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getMaintenanceListAjax(HttpServletRequest request, MaintenanceVO paramVO)
			throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = maintenanceService.selectMaintenanceList(paramVO);
		// Total Count
		Integer total = maintenanceService.selectMaintenanceListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}


	@RequestMapping(value = "/mergeMaintenanceAjax.do")
	@ResponseBody
	public AjaxResult<String>  mergeBuyerCompanyAjax(HttpServletRequest request, MaintenanceVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {
			if (!EgovStringUtil.isEmpty(paramVO.getProResult())) {
        		paramVO.setProState("9");
        	}else {
        		paramVO.setProState("2");
        	}
        		
            if(EgovStringUtil.isEmpty(paramVO.getSeq())){
            	//seq 가 공백이면 insert
            	
            	
            	maintenanceService.mergeMaintenance(paramVO);
            }
            else {
            	//seq 가 공백이 아니면 update
            	
            
            	maintenanceService.updateMaintenance(paramVO);
            	
            }
            
            // merge 방식
            
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("저장하였습니다.");


		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "리스트를 저장 중"));
		}

		return result;
	}

	
	@RequestMapping(value = "/selectMaintenanceAjax.do")
	@ResponseBody
	public AjaxResult<MaintenanceVO> selectMaintenanceAjax(HttpServletRequest request, MaintenanceVO paramVO) throws Exception {

		AjaxResult<MaintenanceVO> result = new AjaxResult<>();
		try {

			// select 방식
			// maintenanceService.selectMaintenance(paramVO);
			MaintenanceVO viewVO =  maintenanceService.selectMaintenance(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "리스트를 불러오는 중"));
		}

		return result;
	}
	
	 @RequestMapping(value = "/deleteMaintenanceAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteMaintenanceAjax(HttpServletRequest request
	            , MaintenanceVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();

	        try
	        {
	        	maintenanceService.deleteMaintenance(paramVO);
	        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("");
	        	result.setMsg("시스템관리자를 삭제하였습니다.");
	        }
	        catch(Exception e) {
	            logger.error(e.getMessage());
	        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
	        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 리스트 삭제 하는 중"));
	        }
	        
	        return result;
	    }
	 @RequestMapping(value="/updateProStateMaintenanceAjax")
	 	@ResponseBody
	 	 public AjaxResult<String> updateProStateMaintenanceAjax(HttpServletRequest request
		            , MaintenanceVO paramVO) throws Exception{
		 
		 AjaxResult<String> result = new AjaxResult<>();
		 
		 try
	        {
			 paramVO.setProState("9");
			 maintenanceService.updateProStateMaintenance(paramVO);
	        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("");
	        	result.setMsg("처리완료 되었습니다.");
	        }
	        catch(Exception e) {
	            logger.error(e.getMessage());
	        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
	        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "처리상태를 업데이트하는 중"));
	        }
		 
		 return result;
	 }

}
