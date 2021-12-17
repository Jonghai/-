package com.bjworld.groupware.project.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.boardattach.service.impl.BoardAttachVO;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.customer.service.CustomerService;
import com.bjworld.groupware.project.service.ProjectService;
import com.bjworld.groupware.project.service.impl.ProjectVO;
import com.bjworld.groupware.project.web.ProjectController;
import com.bjworld.groupware.projectattach.service.ProjectAttachService;
import com.bjworld.groupware.projectattach.service.impl.ProjectAttachVO;

@Controller
@RequestMapping("/admin")
public class ProjectController {
	Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Resource(name = "projectService")
	private ProjectService projectService;
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	
	@Resource(name="projectAttachservice")
	private ProjectAttachService projectattachservice;
	

	@RequestMapping("/project.do")
	 public String project(HttpServletRequest request
	            , Model model) throws Exception{
		List<?> getCsList = customerService.selectCustomerList();
		model.addAttribute("getCsList", getCsList);
		
		ProjectAttachVO VO = new ProjectAttachVO();
		
		List<ProjectAttachVO> getProjectAttachList = projectattachservice.selectprojectattachtlist(VO);
		model.addAttribute("getProjectAttachList", getProjectAttachList);
		
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
			
			ProjectAttachVO VO = new ProjectAttachVO();
			VO.setProjectSeq(paramVO.getSeq());
			
			List<ProjectAttachVO> projectattachlist = projectattachservice.selectprojectattachtlist(VO);
			viewVO.setProjectattachlist(projectattachlist);
			
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
	 
/*	 @RequestMapping(value = "/downloadboardfile.do")
	    @ResponseBody
	    public void downloadboardfile(HttpServletRequest request, HttpServletResponse response
	            , ProjectVO paramVO) throws Exception{
	        
	    	try {
	    		ProjectVO viewVO = projectService.selectProject(paramVO);
	    		if(viewVO != null) {
					String saveFileName = viewVO.getSaveFileName();
					String uploadFolderPath = attachFileSavePath + File.separator + "board";
					EgovFileMngUtil.downFile(request, response, viewVO.getOriFileName(), uploadFolderPath + File.separator + saveFileName);
	    		}
	    		else
	    			throw new Exception("요청한 파일데이터가 존재하지 않습니다.");
	    	}
	    	catch(Exception ex) {
	    		try
				{
					ProjectUtility.writeResponseMessage(response, "<script>alert('다운로드 하려는 파일에 문제가 발생하였습니다.'); history.back(); </script>");
				}
				catch(Exception e)
				{
					EgovBasicLogger.info(e.getMessage());
				}
	    	}
	    }
	 @RequestMapping("/deleteBoardAttachFileAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteAdminBoardAttachFileAjax(HttpServletRequest request, ProjectVO paramVO) throws Exception {
			AjaxResult<String> result = new AjaxResult<>();

			try {
				ProjectVO projectVO = projectService.selectProject(paramVO);
				if(projectVO != null) {
					try {
						String attachFileSavePath;
						File f = new File(attachFileSavePath + File.separator + "board" + File.separator + projectVO.getSaveFileName());
						f.delete();
					} catch (Exception e) {
						// TODO: handle exception
						
					}
				}
				projectService.deleteProject(paramVO);
				result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
				result.setData("");
				result.setMsg("데이터를 삭제하였습니다.");
			} catch (Exception e) {
				// TODO: handle exception
				logger.info(e.getMessage());
				result.setIsSuccess(SystemConstant.AJAX_FAIL);
				result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "데이터를 삭제 하는 중"));
			}
			
			return result;
		}*/
}
