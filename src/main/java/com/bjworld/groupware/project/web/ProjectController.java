package com.bjworld.groupware.project.web;

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
import com.bjworld.groupware.project.service.ProjectService;
import com.bjworld.groupware.project.service.impl.ProjectVO;
import com.bjworld.groupware.project.web.ProjectController;

@Controller
@RequestMapping("/admin")
public class ProjectController {
	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	

	@RequestMapping("/project.do")
	 public String project(HttpServletRequest request
	            , Model model) throws Exception{
		List<?> getCsList = customerService.selectCustomerList();
		model.addAttribute("getCsList", getCsList);
	        return "project/project.at";
	    }

	@RequestMapping(value = "/getProjectListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getProjectListAjax(HttpServletRequest request, ProjectVO paramVO)
			throws Exception {

		// 테이블에 바인딩 할 데이터
		List<?> dataList = projectService.selectProjectList(paramVO);
		// Total Count
		Integer total = projectService.selectProjectListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}


	@RequestMapping(value = "/mergeProjectAjax.do")
	@ResponseBody
	public AjaxResult<String>  mergeBuyerCompanyAjax(HttpServletRequest request, ProjectVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {
        		
            if(EgovStringUtil.isEmpty(paramVO.getSeq())){
            	//seq 가 공백이면 insert
            	projectService.mergeProject(paramVO);
            }
            else {
            	//seq 가 공백이 아니면 update
            	projectService.updateProject(paramVO);
            	
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

	
	@RequestMapping(value = "/selectProjectAjax.do")
	@ResponseBody
	public AjaxResult<ProjectVO> selectProjectAjax(HttpServletRequest request, ProjectVO paramVO) throws Exception {

		AjaxResult<ProjectVO> result = new AjaxResult<>();
		try {

			// select 방식
			// projectService.selectProject(paramVO);
			ProjectVO viewVO =  projectService.selectProject(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "시스템관리자를 불러 중"));
		}

		return result;
	}
	
	 @RequestMapping(value = "/deleteProjectAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteProjectAjax(HttpServletRequest request
	            , ProjectVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();

	        try
	        {
	        	projectService.deleteProject(paramVO);
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
