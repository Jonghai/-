package com.bjworld.groupware.menu.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.commoncode.service.impl.CommonCodeVO;
import com.bjworld.groupware.menu.service.MenuService;
import com.bjworld.groupware.menu.service.impl.MenuVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
public class MenuController {

    Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Resource(name="menuService")
    private MenuService menuService;

    @RequestMapping("/menu.do")
    public String menu(HttpServletRequest request
            , Model model) throws Exception{
        return "menu/menu.at";
    }
    
    @RequestMapping(value = "/getMenuListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getMenuListAjax(HttpServletRequest request
            , MenuVO paramVO) throws Exception{

    	List<?> dataList = menuService.selectMenuList(paramVO);
		// Total Count
		Integer total = menuService.selectMenuListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }
    
    
    @RequestMapping(value = "/getMenuTreeAjax.do")
    @ResponseBody
    public JSONArray getMenuTree(HttpServletRequest request
            , MenuVO paramVO) throws Exception
    {
    	paramVO.setStartIndex(0);
    	paramVO.setEndIndex(1000);
    	paramVO.setSortColumn("sort");
    	paramVO.setSortColumnDir("asc");
    	List<MenuVO> listMenu = menuService.selectMenuList(paramVO);
    	
    	MenuVO rootVO = new MenuVO();
    	rootVO.setSeq("0");
    	rootVO.setMenuTitle("시스템 메뉴");
    	
    	JSONArray treeArray = new JSONArray();
    	initTreeData(treeArray, listMenu, rootVO);
    	
    	return treeArray;
    }
    
    @SuppressWarnings("unchecked")
	private void initTreeData(JSONArray treeArray, List<MenuVO> originListData, MenuVO parentNode)
    {
    	List<MenuVO> childNode =  getChildNodeVO(originListData, parentNode.getSeq());
    	
    	JSONObject treeNodeObject = new JSONObject();
    	treeNodeObject.put("key", parentNode.getSeq());
    	treeNodeObject.put("title", parentNode.getMenuTitle());
    	treeNodeObject.put("expanded", parentNode.getSeq().equals("0") ? true : false);
    	treeNodeObject.put("folder", childNode.size() > 0 ? true : false);
    	
    	if(childNode.size() > 0)
    	{
    		JSONArray childArray = new JSONArray();
			
    		for(MenuVO childNodeItemVO : childNode)
    		{
    			initTreeData(childArray, originListData, childNodeItemVO );	
    		}
    		treeNodeObject.put("children", childArray);
    	}
    		
    	treeArray.add(treeNodeObject);    
    }
    
    private List<MenuVO> getChildNodeVO(List<MenuVO> listNode, String seq) {
		List<MenuVO> child = new ArrayList<>();

		for (MenuVO nodeVO : listNode) {
			if (nodeVO.getParentSeq().equals(seq))
				child.add(nodeVO);
		}

		return child;
	}
    
    @RequestMapping(value = "/mergeMenuAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeMenuAjax(HttpServletRequest request
            , MenuVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
        	menuService.saveMenu(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("메뉴를 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "메뉴를 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/updateMenuAjax.do")
    @ResponseBody
    public AjaxResult<String> updateMenu(HttpServletRequest request
            , MenuVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
        	menuService.updateMenu(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("메뉴를 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "메뉴를 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/selectMenuAjax.do")
    @ResponseBody
    public AjaxResult<MenuVO> selectMenu(HttpServletRequest request
            , MenuVO vo) throws Exception{

    	AjaxResult<MenuVO> result = new AjaxResult<>();

        try
        {
        	MenuVO viewVO =  menuService.selectMenu(vo);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 메뉴를 불러 오는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/deleteMenuAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteMenu(HttpServletRequest request
            , MenuVO vo) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	menuService.removeMenu(vo);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("메뉴를 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 메뉴를 삭제 하는 중"));
        }
        
        return result;
    }
    
   /* 
   
    
    @RequestMapping(value = "/getCommunityTreeMenuAjax.do")
    @ResponseBody
    public JSONArray getCommunityTreeMenu(HttpServletRequest request
            , MenuVO paramVO) throws Exception
    {
    	paramVO.setParentSeq("8");
    	List<MenuVO> listMenu = menuService.selectMenuTreeList(paramVO);
    	
    	JSONArray treeArray = new JSONArray();
    	
    	for(MenuVO childMenuVO : listMenu)
		{
    		JSONObject treeNodeObject = new JSONObject();
        	treeNodeObject.put("key", childMenuVO.getSeq());
        	treeNodeObject.put("title", childMenuVO.getMenuTitle());
        	treeArray.add(treeNodeObject);
		}
    	
    	return treeArray;
    }*/
    
   /* private JSONArray initTreeData(JSONArray treeArray, List<MenuVO> originListData, MenuVO menuNode)
    {
    	//List<MenuVO> childMenu =  CepaUtility.getChildMenuVO(originListData, menuNode.getSeq());
    	
    	JSONObject treeNodeObject = new JSONObject();
    	treeNodeObject.put("key", menuNode.getSeq());
    	treeNodeObject.put("title", menuNode.getMenuTitle());
    	treeNodeObject.put("expanded", true);
    	if(childMenu.size() > 0)
    	{
    		JSONArray childArray = new JSONArray();
			
    		for(MenuVO childMenuVO : childMenu)
    		{
    			initTreeData(childArray, originListData, childMenuVO );	
    		}
    		treeNodeObject.put("children", childArray);
    	}
    		
    	treeArray.add(treeNodeObject);
    	
    	return null;
    }*/
}
