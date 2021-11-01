package com.bjworld.groupware.commoncode.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.commoncode.service.CommonCodeService;
import com.bjworld.groupware.commoncode.service.impl.CommonCodeVO;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;
import com.bjworld.groupware.menu.service.impl.MenuVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class CommonCodeController {

    Logger logger = LoggerFactory.getLogger(CommonCodeController.class);

    @Resource(name="commoncodeService")
    private CommonCodeService commoncodeService;

    @RequestMapping("/commoncode.do")
    public String getCommonCodeList(HttpServletRequest request
            , Model model) throws Exception{
        return "commoncode/commoncode.at";
    }

    @RequestMapping(value = "/getCommonCodeListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getCommonCodeListAjax(HttpServletRequest request
            , CommonCodeVO paramVO) throws Exception{
    	List<?> dataList =  new ArrayList<CommonCodeVO>();
    	Integer total = 0;
    	
    	if(!EgovStringUtil.isEmpty(paramVO.getParentSeq())) {
	    	dataList = commoncodeService.selectCommonCodeList(paramVO);
			total = commoncodeService.selectCommonCodeListTotCnt(paramVO);
    	}
    	
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeCommonCodeAjax.do")
    @ResponseBody
    public AjaxResult<CommonCodeVO> mergeCommonCodeAjax(HttpServletRequest request
            , CommonCodeVO paramVO) throws Exception{
    	
        AjaxResult<CommonCodeVO> result = new AjaxResult<>();
        try {
        	
            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	commoncodeService.mergeCommonCode(paramVO);
        	
        	CommonCodeVO commonCodeViewVO = commoncodeService.selectCommonCode(paramVO);
        	
			result.setData(commonCodeViewVO);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("코드를 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "코드를 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/updateCommonCodeAjax.do")
    @ResponseBody
    public AjaxResult<String> updateCommonCode(HttpServletRequest request
            , CommonCodeVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
        	commoncodeService.updateCommonCode(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("코드를 수정하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "코드 수정 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/selectCommonCodeAjax.do")
    @ResponseBody
    public AjaxResult<CommonCodeVO> selectCommonCodeAjax(HttpServletRequest request
            , CommonCodeVO paramVO) throws Exception{

    	AjaxResult<CommonCodeVO> result = new AjaxResult<>();

        try
        {
        	CommonCodeVO viewVO =  commoncodeService.selectCommonCode(paramVO);
        	
        	CommonCodeVO childParamVO = new CommonCodeVO();
        	childParamVO.setStartIndex(0);
        	childParamVO.setEndIndex(100);
        	childParamVO.setParentSeq(paramVO.getSeq());
        	childParamVO.setSortColumn("sort");
        	childParamVO.setSortColumnDir("asc");
        	List<CommonCodeVO> listChildCommonCode = commoncodeService.selectCommonCodeList(childParamVO);
        	
        	viewVO.setListChildCommonCode(listChildCommonCode);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	
        	viewVO.setCodeDesc(EgovStringUtil.getHtmlStrCnvr(viewVO.getCodeDesc()));
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 데이터를 불러 오는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/deleteCommonCodeAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteCommonCodeAjax(HttpServletRequest request
            , CommonCodeVO vo) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	commoncodeService.deleteCommonCode(vo);
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
    
    @RequestMapping(value = "/getCommonCodeTreeAjax.do")
    @ResponseBody
    public JSONArray getCommonCodeTree(HttpServletRequest request
            , CommonCodeVO paramVO) throws Exception
    {
    	paramVO.setStartIndex(0);
    	paramVO.setEndIndex(1000);
    	paramVO.setParentSeq("0");
    	paramVO.setSortColumn("sort");
    	paramVO.setSortColumnDir("asc");
    	List<CommonCodeVO> listCommonCode = commoncodeService.selectCommonCodeList(paramVO);
    	
    	CommonCodeVO rootCodeVO = new CommonCodeVO();
    	rootCodeVO.setSeq("0");
    	rootCodeVO.setCodeDesc("공통코드");
    	
    	JSONArray treeArray = new JSONArray();
    	initTreeData(treeArray, listCommonCode, rootCodeVO);
    	
    	return treeArray;
    }
    
    @SuppressWarnings("unchecked")
	private void initTreeData(JSONArray treeArray, List<CommonCodeVO> originListData, CommonCodeVO categoryNode)
    {
    	List<CommonCodeVO> childNode =  getChildCommonCodeVO(originListData, categoryNode.getSeq());
    	
    	JSONObject treeNodeObject = new JSONObject();
    	treeNodeObject.put("key", categoryNode.getSeq());
    	treeNodeObject.put("title", categoryNode.getCodeDesc());
    	treeNodeObject.put("expanded", categoryNode.getSeq().equals("0") ? true : false);
    	treeNodeObject.put("folder", childNode.size() > 0 ? true : false);
    	
    	if(childNode.size() > 0)
    	{
    		JSONArray childArray = new JSONArray();
			
    		for(CommonCodeVO childNodeItemVO : childNode)
    		{
    			initTreeData(childArray, originListData, childNodeItemVO );	
    		}
    		treeNodeObject.put("children", childArray);
    	}
    		
    	treeArray.add(treeNodeObject);    
    }
    
    private List<CommonCodeVO> getChildCommonCodeVO(List<CommonCodeVO> listCommonCode, String seq) {
		List<CommonCodeVO> child = new ArrayList<>();

		for (CommonCodeVO codeVO : listCommonCode) {
			if (codeVO.getParentSeq().equals(seq))
				child.add(codeVO);
		}

		return child;
	}
    
    @RequestMapping(value = "/reorderCommonCodeAjax.do")
    @ResponseBody
    public AjaxResult<String> reorderCommonCode(HttpServletRequest request
            , String commonCodeSortArray) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	ObjectMapper mapper = new ObjectMapper();
        	List<CommonCodeVO> listCommonCode = mapper.readValue(commonCodeSortArray, new TypeReference<List<CommonCodeVO>>() {});
        	
        	for (CommonCodeVO codeItem: listCommonCode) {
				commoncodeService.updateCommonCodeSort(codeItem);
			}
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("정렬 완료");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 정렬을 수정 하는 중"));
        }
        
        return result;
    }
    
}
