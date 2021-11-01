package com.bjworld.groupware.organization.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.organization.service.OrganizationService;
import com.bjworld.groupware.organization.service.impl.OrganizationVO;

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
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class OrganizationController {

    Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Resource(name="organizationService")
    private OrganizationService organizationService;
    
    @RequestMapping(value = "/getOrganizationTreeAjax.do")
    @ResponseBody
    public JSONArray getOrganizationTree(HttpServletRequest request
            , OrganizationVO paramVO) throws Exception
    {
    	List<OrganizationVO> listOrganization = organizationService.selectOrganizationList(paramVO);
    	
    	JSONArray treeArray = new JSONArray();
    	
    	initTreeData(treeArray, listOrganization, listOrganization.get(0));
    	return treeArray;
    }
    
    @SuppressWarnings("unchecked")
	private JSONArray initTreeData(JSONArray treeArray, List<OrganizationVO> originListData, OrganizationVO menuNode)
    {
    	List<OrganizationVO> childOrganization =  getChildOrganizationVO(originListData, menuNode.getSeq());
    	
    	JSONObject treeNodeObject = new JSONObject();
    	treeNodeObject.put("key", menuNode.getSeq());
    	treeNodeObject.put("title", menuNode.getOrgName());
    	treeNodeObject.put("expanded", true);
    	if(menuNode.getSeq().equals("0"))
    		treeNodeObject.put("active", true);
    	
    	if(childOrganization.size() > 0)
    	{
    		JSONArray childArray = new JSONArray();
			
    		for(OrganizationVO childOrganizationVO : childOrganization)
    		{
    			initTreeData(childArray, originListData, childOrganizationVO );	
    		}
    		treeNodeObject.put("children", childArray);
    	}
    		
    	treeArray.add(treeNodeObject);
    	
    	return null;
    }
    
    public List<OrganizationVO> getChildOrganizationVO(List<OrganizationVO> listOrganization, String seq) {
		List<OrganizationVO> child = new ArrayList<>();

		for (OrganizationVO organizationItem : listOrganization) {
			if (organizationItem.getParentSeq().equals(seq))
				child.add(organizationItem);
		}

		return child;
	}
        
    @RequestMapping(value = "/selectOrganizationTreeListAjax.do")
    @ResponseBody
    public AjaxResult<List<OrganizationVO>> selectOrganizationTreeList(HttpServletRequest request
            , OrganizationVO paramVO) throws Exception
    {
    	
    	AjaxResult<List<OrganizationVO>> result = new AjaxResult<>();
    	try {
        	
    		List<OrganizationVO> listOrganization = organizationService.selectOrganizationTreeList(paramVO);
        	
			result.setData(listOrganization);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "부서 정보를 가져 오는 중"));
		}
        
        return result;
    } 


    @RequestMapping(value = "/mergeOrganizationAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , OrganizationVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());

            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	organizationService.mergeOrganization(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("부서 정보를 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "부서 정보를 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/selectOrganizationAjax.do")
    @ResponseBody
    public AjaxResult<OrganizationVO> selectOrganizationAjax(HttpServletRequest request
            , OrganizationVO paramVO) throws Exception{

    	AjaxResult<OrganizationVO> result = new AjaxResult<>();

        try
        {
        	OrganizationVO viewVO =  organizationService.selectOrganization(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 부서 정보를 불러 오는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/deleteOrganizationAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteOrganizationAjax(HttpServletRequest request
            , OrganizationVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	organizationService.deleteOrganization(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("부서 정보를 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 부서 정보를 삭제 하는 중"));
        }
        
        return result;
    }
}
