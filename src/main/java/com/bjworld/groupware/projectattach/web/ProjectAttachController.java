package com.bjworld.groupware.projectattach.web;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.project.service.impl.ProjectVO;
import com.bjworld.groupware.project.web.ProjectController;
import com.bjworld.groupware.projectattach.service.ProjectAttachService;
import com.bjworld.groupware.projectattach.service.impl.ProjectAttachVO;

@Controller
@RequestMapping("/admin")
public class ProjectAttachController {
	Logger logger = LoggerFactory.getLogger(ProjectAttachController.class);
	
	@Value("${Globals.fileStorePath}")
	private String attachFileSavePath;
	
	@Resource(name="projectAttachservice")
	private ProjectAttachService projectattachservice;

	@RequestMapping("/deleteProjectAttachAjax.do")
	@ResponseBody
	public AjaxResult<String> deleteProjectAttachAjax(HttpServletRequest request, ProjectAttachVO paramVO) {
		AjaxResult<String> result = new AjaxResult<>();
		
		try {
			projectattachservice.deleteProjectAttach(paramVO);
			
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "첨부파일을 삭제하는 중"));
		}
		
		return result;
	}
	
	@RequestMapping(value = "/downloadprojectfile.do")
    @ResponseBody
    public void downloadboardfile(HttpServletRequest request, HttpServletResponse response
            , ProjectAttachVO paramVO) throws Exception{
        
    	try {
    		ProjectAttachVO viewVO = projectattachservice.selectProjectAttach(paramVO);
    		if(viewVO != null) {
				String saveFileName = viewVO.getSaveFilename();
				String uploadFolderPath = attachFileSavePath + File.separator + "project";
				EgovFileMngUtil.downFile(request, response, viewVO.getOriFilename(), uploadFolderPath + File.separator + saveFileName);
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
}
