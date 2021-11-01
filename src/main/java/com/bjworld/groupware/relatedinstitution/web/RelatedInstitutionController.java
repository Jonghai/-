package com.bjworld.groupware.relatedinstitution.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.relatedinstitution.service.RelatedInstitutionService;
import com.bjworld.groupware.relatedinstitution.service.impl.RelatedInstitutionVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.File;
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class RelatedInstitutionController {

    Logger logger = LoggerFactory.getLogger(RelatedInstitutionController.class);
    
    @Value("${Globals.fileStorePath}")
	private String attachFileSavePath;

    @Resource(name="relatedinstitutionService")
    private RelatedInstitutionService relatedinstitutionService;

    @RequestMapping("/relatedinstitution.do")
    public String relatedinstitution(HttpServletRequest request
            , Model model) throws Exception{
        return "relatedinstitution/relatedinstitution.at";
    }

    @RequestMapping(value = "/getRelatedInstitutionListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getRelatedInstitutionListAjax(HttpServletRequest request
            , RelatedInstitutionVO paramVO) throws Exception{

    	List<?> dataList = relatedinstitutionService.selectRelatedInstitutionList(paramVO);
		// Total Count
		Integer total = relatedinstitutionService.selectRelatedInstitutionListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeRelatedInstitutionAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , RelatedInstitutionVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());
            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	relatedinstitutionService.mergeRelatedInstitution(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("관련기관을 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "관련기관을 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/selectRelatedInstitutionAjax.do")
    @ResponseBody
    public AjaxResult<RelatedInstitutionVO> selectRelatedInstitutionAjax(HttpServletRequest request
            , RelatedInstitutionVO paramVO) throws Exception{

    	AjaxResult<RelatedInstitutionVO> result = new AjaxResult<>();

        try
        {
        	RelatedInstitutionVO viewVO =  relatedinstitutionService.selectRelatedInstitution(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 관련기관을 불러 오는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/deleteRelatedInstitutionAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteRelatedInstitutionAjax(HttpServletRequest request
            , RelatedInstitutionVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	relatedinstitutionService.deleteRelatedInstitution(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("관련기관을 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 관련기관을 삭제 하는 중"));
        }
        
        return result;
    }
}
