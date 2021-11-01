package com.bjworld.groupware.menu.service;

import com.bjworld.groupware.menu.service.impl.MenuVO;
import java.util.List;

public interface MenuService {

    List<MenuVO> selectMenuList(MenuVO paramVO) throws Exception;

    Integer selectMenuListTotCnt(MenuVO paramVO) throws Exception;

    MenuVO selectMenu(MenuVO paramVO) throws Exception;

    void mergeMenu(MenuVO paramVO) throws Exception;

    void insertMenu(MenuVO paramVO) throws Exception;

    void updateMenu(MenuVO paramVO) throws Exception;

    void deleteMenu(MenuVO paramVO) throws Exception;

	List<MenuVO> selectMenuTreeList(MenuVO paramVO) throws Exception;

	void saveMenu(MenuVO paramVO) throws Exception;

	void removeMenu(MenuVO paramVO) throws Exception;

	MenuVO selectMenuByCode(MenuVO paramVO) throws Exception;
	
	void updateMenuInCode(MenuVO paramVO) throws Exception;
}
