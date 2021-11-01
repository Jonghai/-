package com.bjworld.groupware.accesslog.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.accesslog.service.AccessLogService;
import com.bjworld.groupware.accesslog.service.impl.AccessLogVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("/accesslog")
public class AccessLogController {

    Logger logger = LoggerFactory.getLogger(AccessLogController.class);

    @Resource(name="accesslogService")
    private AccessLogService accesslogService;

    @RequestMapping("/getAccessLogList.do")
    public String getAccessLogList(HttpServletRequest request
            , Model model) throws Exception{
        return "accesslog/getAccessLogList.tiles";
    }

    @RequestMapping(value = "/getAccessLogListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getAccessLogListAjax(HttpServletRequest request
            , AccessLogVO paramVO) throws Exception{

    	List<?> dataList = accesslogService.selectAccessLogList(paramVO);
		// Total Count
		Integer total = accesslogService.selectAccessLogListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeAccessLogAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , AccessLogVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	accesslogService.mergeAccessLog(paramVO);
        	
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
    
    @RequestMapping(value = "/selectAccessLogAjax.do")
    @ResponseBody
    public AjaxResult<AccessLogVO> selectAccessLogAjax(HttpServletRequest request
            , AccessLogVO vo) throws Exception{

    	AjaxResult<AccessLogVO> result = new AjaxResult<>();

        try
        {
        	AccessLogVO viewVO =  accesslogService.selectAccessLog(vo);
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

    @RequestMapping(value = "/deleteAccessLogAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteAccessLogAjax(HttpServletRequest request
            , AccessLogVO vo) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	accesslogService.deleteAccessLog(vo);
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
}
