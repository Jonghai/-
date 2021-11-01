package com.bjworld.groupware.siteuser.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.EgovFileScrty;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.siteuser.service.SiteUserService;
import com.bjworld.groupware.siteuser.service.impl.SiteUserVO;

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
public class SiteUserController {

    Logger logger = LoggerFactory.getLogger(SiteUserController.class);
    
    

    @Resource(name="siteuserService")
    private SiteUserService siteuserService;

    

    @RequestMapping("/siteuser.do")
    public String siteuser(HttpServletRequest request
            , Model model) throws Exception{
        return "siteuser/siteuser.at";
    }

    @RequestMapping(value = "/getSiteUserListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getSiteUserListAjax(HttpServletRequest request
            , SiteUserVO paramVO) throws Exception{

    	List<?> dataList = siteuserService.selectSiteUserList(paramVO);
		// Total Count
		Integer total = siteuserService.selectSiteUserListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeSiteUserAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , SiteUserVO paramVO ) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());
            
        	if(!EgovStringUtil.isEmpty(paramVO.getUserPwd()))
        		paramVO.setUserPwd(EgovFileScrty.encryptPassword(paramVO.getUserPwd(), paramVO.getUserId()));

            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	siteuserService.mergeSiteUser(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("데이터를 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "데이터를 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/selectSiteUserAjax.do")
    @ResponseBody
    public AjaxResult<SiteUserVO> selectSiteUserAjax(HttpServletRequest request
            , SiteUserVO paramVO) throws Exception{

    	AjaxResult<SiteUserVO> result = new AjaxResult<>();

        try
        {
        	SiteUserVO viewVO =  siteuserService.selectSiteUser(paramVO);

            

        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 데이터를 불러 오는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/deleteSiteUserAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteSiteUserAjax(HttpServletRequest request
            , SiteUserVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	siteuserService.deleteSiteUser(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("데이터를 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 데이터를 삭제 하는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/checkSiteUserIdAjax.do")
    @ResponseBody
    public AjaxResult<SiteUserVO> checkSiteUserId(HttpServletRequest request
            , SiteUserVO paramVO) throws Exception{

    	AjaxResult<SiteUserVO> result = new AjaxResult<>();

        try
        {
        	SiteUserVO viewVO =  siteuserService.selectSiteUserByUserId(paramVO);
        	
        	if(viewVO == null) {
        		result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
            	result.setData(null);
            	result.setMsg("사용가능한 아이디 입니다.");
        	}
        	else {
        		result.setIsSuccess(SystemConstant.AJAX_FAIL);
            	result.setData(null);
            	result.setMsg("이미 등록된 아이디 입니다.");
        	}
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 아이디 중복검사 중"));
        }
        
        return result;
    }
    
    
}
