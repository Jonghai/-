package com.bjworld.groupware.menu.service.impl;

import com.bjworld.groupware.menu.service.impl.MenuVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("menuMapper")
public interface MenuMapper{

	List<MenuVO> selectMenuList(MenuVO paramVO) throws Exception;
	
	Integer selectMenuListTotCnt(MenuVO paramVO) throws Exception;
	
	void mergeMenu(MenuVO paramVO) throws Exception;

    void insertMenu(MenuVO paramVO) throws Exception;

    void updateMenu(MenuVO paramVO) throws Exception;
	
	MenuVO selectMenu(MenuVO paramVO) throws Exception;
	
	void deleteMenu(MenuVO paramVO) throws Exception;
	
	List<MenuVO> selectMenuTreeList(MenuVO paramVO) throws Exception;

	void createBoardTable(MenuVO paramVO) throws Exception;

	void dropBoardTable(MenuVO paramVO) throws Exception;

	void createBoardAttachTable(MenuVO paramVO) throws Exception;

	void dropBoardAttachTable(MenuVO paramVO) throws Exception;
	
	MenuVO selectMenuByCode(MenuVO paramVO) throws Exception;

	void updateMenuInCode(MenuVO paramVO) throws Exception;
}
