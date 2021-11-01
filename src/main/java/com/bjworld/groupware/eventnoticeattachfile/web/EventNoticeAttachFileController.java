package com.bjworld.groupware.eventnoticeattachfile.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.eventnoticeattachfile.service.EventNoticeAttachFileService;
import com.bjworld.groupware.eventnoticeattachfile.service.impl.EventNoticeAttachFileVO;

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
public class EventNoticeAttachFileController {

    Logger logger = LoggerFactory.getLogger(EventNoticeAttachFileController.class);
    
    

    /*@Resource(name="eventnoticeattachfileService")
    private EventNoticeAttachFileService eventnoticeattachfileService;

    @RequestMapping("/eventnoticeattachfile.do")
    public String eventnoticeattachfile(HttpServletRequest request
            , Model model) throws Exception{
        return "eventnoticeattachfile/eventnoticeattachfile.at";
    }

    @RequestMapping(value = "/getEventNoticeAttachFileListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getEventNoticeAttachFileListAjax(HttpServletRequest request
            , EventNoticeAttachFileVO paramVO) throws Exception{

    	List<?> dataList = eventnoticeattachfileService.selectEventNoticeAttachFileList(paramVO);
		// Total Count
		Integer total = eventnoticeattachfileService.selectEventNoticeAttachFileListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeEventNoticeAttachFileAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , EventNoticeAttachFileVO paramVO ) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());
            
            

            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	eventnoticeattachfileService.mergeEventNoticeAttachFile(paramVO);
        	
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
    
    @RequestMapping(value = "/selectEventNoticeAttachFileAjax.do")
    @ResponseBody
    public AjaxResult<EventNoticeAttachFileVO> selectEventNoticeAttachFileAjax(HttpServletRequest request
            , EventNoticeAttachFileVO paramVO) throws Exception{

    	AjaxResult<EventNoticeAttachFileVO> result = new AjaxResult<>();

        try
        {
        	EventNoticeAttachFileVO viewVO =  eventnoticeattachfileService.selectEventNoticeAttachFile(paramVO);
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

    @RequestMapping(value = "/deleteEventNoticeAttachFileAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteEventNoticeAttachFileAjax(HttpServletRequest request
            , EventNoticeAttachFileVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	eventnoticeattachfileService.deleteEventNoticeAttachFile(paramVO);
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
    }*/
}
