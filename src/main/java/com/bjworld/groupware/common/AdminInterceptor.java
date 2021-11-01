package com.bjworld.groupware.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.util.EgovDateUtil;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.menu.service.MenuService;
import com.bjworld.groupware.menu.service.impl.MenuVO;

public class AdminInterceptor extends WebContentInterceptor {

	@Resource(name="menuService")
	private MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException {
		String path = request.getServletPath();

		try {

			if (path.endsWith("Ajax.do")) {
				return true;
			}
			
			AdminUserVO sessionVO = ProjectUtility.getSessionAdminUser();
			if (sessionVO != null && sessionVO.getUserId() != null) {
				return true;
			} else {
				ModelAndView modelAndView = new ModelAndView("redirect:" + SystemConstant.AdminLoginUrl);
				throw new ModelAndViewDefiningException(modelAndView);
			}
		} catch (Exception e) {
			ModelAndView modelAndView = new ModelAndView("redirect:" + SystemConstant.AdminLoginUrl);
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView m)
			throws Exception {
		String path = request.getServletPath();
		String code = request.getParameter("code");
		if (path.endsWith("Ajax.do"))
			return;

		if (path.endsWith("download.do"))
			return;
		
		MenuVO menuParamVO = new MenuVO();
		menuParamVO.setStartIndex(0);
		menuParamVO.setEndIndex(1000);
		
		List<MenuVO> listMenu = menuService.selectMenuList(menuParamVO);
		
		String menuItemFormat = "<li class='nav-item %s'><a href='%s' class='nav-link'><i class='%s'></i> %s</a>%s</li>";
		
		StringBuffer sbMenu = new StringBuffer();
		
		List<MenuVO> parentMenuList = getChildMenu(listMenu, "0");
		
		for(MenuVO parentMenuItem: parentMenuList) {
			List<MenuVO> childMenuList = getChildMenu(listMenu,parentMenuItem.getSeq());
			
			StringBuffer childMenuHtml = new StringBuffer();
			String parentClass = "";
			
			if(childMenuList.size() > 0) {
				for(MenuVO childMenuItem: childMenuList) {
					String menuLink = childMenuItem.getMenuLink();
					if(!childMenuItem.getMenuType().equals("1"))
						menuLink += "?boardCode=" + childMenuItem.getMenuCode();
					
					String childClass = "";
					if(!childMenuItem.getMenuType().equals("1") && !EgovStringUtil.isEmpty(code) && code.equals(childMenuItem.getMenuCode())) {
						parentClass = "nav-item-open";
						childClass = "nav-item-open";
					}
					else {
						if(childMenuItem.getMenuType().equals("1") && path.equals(childMenuItem.getMenuLink())) {
							parentClass = "nav-item-open";
							childClass = "nav-item-open";
						}
					}
					
					childMenuHtml.append(String.format(menuItemFormat
							, childClass
							, menuLink
							, childMenuItem.getIconName()
							, childMenuItem.getMenuTitle()
							, ""));
				}
			}
			else {
				if(path.equals(parentMenuItem.getMenuLink()))
					parentClass = "nav-item-open";
			}
			
			sbMenu.append(String.format(menuItemFormat
					, "nav-item-submenu" + " " + parentClass
					, parentMenuItem.getMenuLink()
					, parentMenuItem.getIconName()
					, parentMenuItem.getMenuTitle()
					, childMenuHtml.length() != 0 ? "<ul class='nav nav-group-sub' style='display: "+(EgovStringUtil.isEmpty(parentClass) ? "" : "block") +";'>" + childMenuHtml + "</ul>" : ""));
		}
		
		m.getModel().put("leftMenuHtml", sbMenu);
	}
	
	private List<MenuVO> getChildMenu(List<MenuVO> listMenu, String parentSeq){
		List<MenuVO> child = new ArrayList<>();

		for (MenuVO menuVO : listMenu) {
			if (menuVO.getParentSeq().equals(parentSeq))
				child.add(menuVO);
		}

		return child;
	}
}
