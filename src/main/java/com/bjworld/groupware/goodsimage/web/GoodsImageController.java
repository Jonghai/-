package com.bjworld.groupware.goodsimage.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.goodsimage.service.GoodsImageService;
import com.bjworld.groupware.goodsimage.service.impl.GoodsImageVO;

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
public class GoodsImageController {

    Logger logger = LoggerFactory.getLogger(GoodsImageController.class);
    
    

    /*@Resource(name="goodsimageService")
    private GoodsImageService goodsimageService;

    

    @RequestMapping("/goodsimage.do")
    public String goodsimage(HttpServletRequest request
            , Model model) throws Exception{
        return "goodsimage/goodsimage.at";
    }

    @RequestMapping(value = "/getGoodsImageListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getGoodsImageListAjax(HttpServletRequest request
            , GoodsImageVO paramVO) throws Exception{

    	List<?> dataList = goodsimageService.selectGoodsImageList(paramVO);
		// Total Count
		Integer total = goodsimageService.selectGoodsImageListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeGoodsImageAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , GoodsImageVO paramVO ) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());
            

            

            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	goodsimageService.mergeGoodsImage(paramVO);
        	
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
    
    @RequestMapping(value = "/selectGoodsImageAjax.do")
    @ResponseBody
    public AjaxResult<GoodsImageVO> selectGoodsImageAjax(HttpServletRequest request
            , GoodsImageVO paramVO) throws Exception{

    	AjaxResult<GoodsImageVO> result = new AjaxResult<>();

        try
        {
        	GoodsImageVO viewVO =  goodsimageService.selectGoodsImage(paramVO);

            

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

    @RequestMapping(value = "/deleteGoodsImageAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteGoodsImageAjax(HttpServletRequest request
            , GoodsImageVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	goodsimageService.deleteGoodsImage(paramVO);
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
