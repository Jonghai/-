package com.bjworld.groupware.adminuser.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovDateUtil;
import com.bjworld.groupware.common.util.EgovFileScrty;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.adminuser.service.AdminUserService;
import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.File;
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    Logger logger = LoggerFactory.getLogger(AdminUserController.class);
    
    

    @Resource(name="adminuserService")
    private AdminUserService adminuserService;
    

    @RequestMapping("/adminuser.do")
    public String adminuser(HttpServletRequest request
            , Model model) throws Exception{
        return "adminuser/adminuser.at";
    }

    @RequestMapping(value = "/getAdminUserListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getAdminUserListAjax(HttpServletRequest request
            , AdminUserVO paramVO) throws Exception{

    	//테이블에 바인딩 할 데이터
    	List<?> dataList = adminuserService.selectAdminUserList(paramVO);
		// Total Count
		Integer total = adminuserService.selectAdminUserListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeAdminUserAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , AdminUserVO paramVO ) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	if(!EgovStringUtil.isEmpty(paramVO.getUserPwd()))
        		paramVO.setUserPwd(EgovFileScrty.encryptPassword(paramVO.getUserPwd(), paramVO.getUserId()));
        	
            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);
            paramVO.setUserDeptSeq("0");
            paramVO.setIsChangePwd("1");
            paramVO.setPwdUpdateDate(EgovDateUtil.getCurrentDate("yyyyMMdd"));
            
            if(EgovStringUtil.isEmpty(paramVO.getSeq())){
            	//seq 가 공백이면 insert
            }
            else {
            	//seq 가 공백이 아니면 update
            }
            
            // merge 방식
        	adminuserService.mergeAdminUser(paramVO);
        	
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
    
    @RequestMapping(value = "/selectAdminUserAjax.do")
    @ResponseBody
    public AjaxResult<AdminUserVO> selectAdminUserAjax(HttpServletRequest request
            , AdminUserVO paramVO) throws Exception{

    	AjaxResult<AdminUserVO> result = new AjaxResult<>();

        try
        {
        	AdminUserVO viewVO =  adminuserService.selectAdminUser(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 시스템관리자를 불러 오는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/deleteAdminUserAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteAdminUserAjax(HttpServletRequest request
            , AdminUserVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	adminuserService.deleteAdminUser(paramVO);
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
    
    @RequestMapping("/aldomgiinn.do")
	public String login(HttpServletRequest request, Model m) throws Exception
	{
		return "admin/login/login";
	}
	
	@RequestMapping("/loginProcessAjax.do")
	@ResponseBody
	public AjaxResult<String> loginProcess(HttpServletRequest request, AdminUserVO paramVO) throws Exception {
		AjaxResult<String> result = new AjaxResult<>();
		try
		{
			String encryptPassword = EgovFileScrty.encryptPassword(paramVO.getUserPwd(), paramVO.getUserId());

			paramVO.setUserPwd(encryptPassword);
			
			AdminUserVO viewVO = adminuserService.selectAdminUserLogin(paramVO);
			
			if(viewVO != null) {
				//String loginAfterURL = "/admin/company.do";
				viewVO.setUserPwd("");
				viewVO.setPwdUpdateDate("");
				viewVO.setUserEmail("");
				viewVO.setUserPhone("");
				
				EgovSessionCookieUtil.setSessionAttribute(request, SystemConstant.ADMIN_USER_LOGIN_SESSION_KEY, viewVO);
				
				result.setData(SystemConstant.ADMIN_LOGIN_AFTER_URL);
				result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			}else {
				result.setMsg("아이디 또는 비밀번호를 확인해 주십시오");
				result.setIsSuccess(SystemConstant.AJAX_FAIL);
			}
		}
		catch(Exception ex)
		{
			EgovBasicLogger.info(ex.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "로그인 처리 중"));
		}
		
		return result;
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) throws Exception
	{
		try
		{
		}
		catch(Exception ex)
		{
			EgovBasicLogger.info(ex.getMessage());
		}
		
		
		EgovSessionCookieUtil.removeSessionAttribute(request, SystemConstant.ADMIN_USER_LOGIN_SESSION_KEY);
		return "redirect:" + SystemConstant.AdminLoginUrl;
	}	
}
