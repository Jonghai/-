package com.bjworld.groupware.goodscategory.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.goodscategory.service.GoodsCategoryService;
import com.bjworld.groupware.goodscategory.service.impl.GoodsCategoryVO;

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
public class GoodsCategoryController {

    Logger logger = LoggerFactory.getLogger(GoodsCategoryController.class);
    
    

    @Resource(name="goodscategoryService")
    private GoodsCategoryService goodscategoryService;

    

    @RequestMapping("/goodscategory.do")
    public String goodscategory(HttpServletRequest request
            , Model model) throws Exception{
        return "goodscategory/goodscategory.at";
    }

    @RequestMapping(value = "/getGoodsCategoryListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getGoodsCategoryListAjax(HttpServletRequest request
            , GoodsCategoryVO paramVO) throws Exception{

    	List<?> dataList = goodscategoryService.selectGoodsCategoryList(paramVO);
		// Total Count
		Integer total = goodscategoryService.selectGoodsCategoryListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeGoodsCategoryAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , GoodsCategoryVO paramVO ) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());
            

            

            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	goodscategoryService.mergeGoodsCategory(paramVO);
        	
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
    
    @RequestMapping(value = "/selectGoodsCategoryAjax.do")
    @ResponseBody
    public AjaxResult<GoodsCategoryVO> selectGoodsCategoryAjax(HttpServletRequest request
            , GoodsCategoryVO paramVO) throws Exception{

    	AjaxResult<GoodsCategoryVO> result = new AjaxResult<>();

        try
        {
        	GoodsCategoryVO viewVO =  goodscategoryService.selectGoodsCategory(paramVO);

            

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

    @RequestMapping(value = "/deleteGoodsCategoryAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteGoodsCategoryAjax(HttpServletRequest request
            , GoodsCategoryVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	goodscategoryService.deleteGoodsCategory(paramVO);
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
