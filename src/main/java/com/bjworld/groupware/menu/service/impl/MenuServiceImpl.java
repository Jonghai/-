package com.bjworld.groupware.menu.service.impl;

import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.menu.service.MenuService;
import com.bjworld.groupware.menu.service.impl.MenuMapper;
import com.bjworld.groupware.menu.service.impl.MenuVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.annotation.Resource;

@Service("menuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl implements MenuService {
    @Resource(name="menuMapper")
    private MenuMapper menuMapper;

    @Override
    public List<MenuVO> selectMenuList(MenuVO paramVO) throws Exception {
        return menuMapper.selectMenuList(paramVO);
    }

    @Override
    public Integer selectMenuListTotCnt(MenuVO paramVO) throws Exception {
        return menuMapper.selectMenuListTotCnt(paramVO);
    }

    @Override
    public MenuVO selectMenu(MenuVO paramVO) throws Exception {
        return menuMapper.selectMenu(paramVO);
    }

    @Override
    public void mergeMenu(MenuVO paramVO) throws Exception {
        menuMapper.mergeMenu(paramVO);
    }

    @Override
    public void insertMenu(MenuVO paramVO) throws Exception {
        menuMapper.insertMenu(paramVO);
    }

    @Override
    public void updateMenu(MenuVO paramVO) throws Exception {
        menuMapper.updateMenu(paramVO);
    }

    @Override
    public void deleteMenu(MenuVO paramVO) throws Exception {
        menuMapper.deleteMenu(paramVO);
    }

	@Override
	public List<MenuVO> selectMenuTreeList(MenuVO paramVO) throws Exception {
		return menuMapper.selectMenuTreeList(paramVO);
	}

	@Override
	public void saveMenu(MenuVO paramVO) throws Exception {
		if(EgovStringUtil.isEmpty(paramVO.getSeq())) {
			menuMapper.insertMenu(paramVO);
		
			if(!EgovStringUtil.isEmpty(paramVO.getMenuType()) 
					&& (paramVO.getMenuType().equals("2")
					|| paramVO.getMenuType().equals("3")
					|| paramVO.getMenuType().equals("4")
					|| paramVO.getMenuType().equals("5"))) {
				menuMapper.createBoardTable(paramVO);
				menuMapper.createBoardAttachTable(paramVO);
				
				paramVO.setMenuCode(paramVO.getSeq());
				updateMenuInCode(paramVO);
			}
		}
		else {
			menuMapper.updateMenu(paramVO);
		}
	}

	@Override
	public void removeMenu(MenuVO paramVO) throws Exception {
		MenuVO viewVO = selectMenu(paramVO);
		
		menuMapper.deleteMenu(paramVO);
		
		if(!EgovStringUtil.isEmpty(viewVO.getMenuType()) 
				&& (viewVO.getMenuType().equals("2")
				|| viewVO.getMenuType().equals("3")
				|| viewVO.getMenuType().equals("4")
				|| viewVO.getMenuType().equals("5"))) {
			menuMapper.dropBoardAttachTable(paramVO);
			menuMapper.dropBoardTable(paramVO);
		}
	}

	@Override
	public MenuVO selectMenuByCode(MenuVO paramVO) throws Exception {
		return menuMapper.selectMenuByCode(paramVO);
	}

	@Override
	public void updateMenuInCode(MenuVO paramVO) throws Exception {
		menuMapper.updateMenuInCode(paramVO);
	}
}
