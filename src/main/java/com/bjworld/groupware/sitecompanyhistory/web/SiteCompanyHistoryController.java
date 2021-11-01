package com.bjworld.groupware.sitecompanyhistory.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.sitecompanyhistory.service.SiteCompanyHistoryService;
import com.bjworld.groupware.sitecompanyhistory.service.impl.SiteCompanyHistoryVO;

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
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class SiteCompanyHistoryController {

    Logger logger = LoggerFactory.getLogger(SiteCompanyHistoryController.class);

    @Resource(name="sitecompanyhistoryService")
    private SiteCompanyHistoryService sitecompanyhistoryService;

    @RequestMapping("/sitecompanyhistory.do")
    public String sitecompanyhistory(HttpServletRequest request
            , Model model) throws Exception{
        return "sitecompanyhistory/sitecompanyhistory.at";
    }

    @RequestMapping(value = "/getSiteCompanyHistoryListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getSiteCompanyHistoryListAjax(HttpServletRequest request
            , SiteCompanyHistoryVO paramVO) throws Exception{

    	List<?> dataList = sitecompanyhistoryService.selectSiteCompanyHistoryList(paramVO);
		// Total Count
		Integer total = sitecompanyhistoryService.selectSiteCompanyHistoryListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeSiteCompanyHistoryAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , SiteCompanyHistoryVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());

            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	sitecompanyhistoryService.mergeSiteCompanyHistory(paramVO);
        	
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
    
    @RequestMapping(value = "/selectSiteCompanyHistoryAjax.do")
    @ResponseBody
    public AjaxResult<SiteCompanyHistoryVO> selectSiteCompanyHistoryAjax(HttpServletRequest request
            , SiteCompanyHistoryVO paramVO) throws Exception{

    	AjaxResult<SiteCompanyHistoryVO> result = new AjaxResult<>();

        try
        {
        	SiteCompanyHistoryVO viewVO =  sitecompanyhistoryService.selectSiteCompanyHistory(paramVO);
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

    @RequestMapping(value = "/deleteSiteCompanyHistoryAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteSiteCompanyHistoryAjax(HttpServletRequest request
            , SiteCompanyHistoryVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	sitecompanyhistoryService.deleteSiteCompanyHistory(paramVO);
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
