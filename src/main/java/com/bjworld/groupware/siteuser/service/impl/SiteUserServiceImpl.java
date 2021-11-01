package com.bjworld.groupware.siteuser.service.impl;

import com.bjworld.groupware.siteuser.service.SiteUserService;
import com.bjworld.groupware.siteuser.service.impl.SiteUserMapper;
import com.bjworld.groupware.siteuser.service.impl.SiteUserVO;

import com.bjworld.groupware.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;

@Service("siteuserService")
public class SiteUserServiceImpl extends EgovAbstractServiceImpl implements SiteUserService {
    @Resource(name="siteuserMapper")
    private SiteUserMapper siteuserMapper;

    

    @Override
    public List<SiteUserVO> selectSiteUserList(SiteUserVO paramVO) throws Exception {
        return siteuserMapper.selectSiteUserList(paramVO);
    }

    @Override
    public Integer selectSiteUserListTotCnt(SiteUserVO paramVO) throws Exception {
        return siteuserMapper.selectSiteUserListTotCnt(paramVO);
    }

    @Override
    public SiteUserVO selectSiteUser(SiteUserVO paramVO) throws Exception {
        return siteuserMapper.selectSiteUser(paramVO);
    }

    @Override
    @Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void saveSiteUser(SiteUserVO paramVO) throws Exception {
        
    }

    @Override
    public void mergeSiteUser(SiteUserVO paramVO) throws Exception {
        siteuserMapper.mergeSiteUser(paramVO);
    }

    @Override
    public void insertSiteUser(SiteUserVO paramVO) throws Exception {
        siteuserMapper.insertSiteUser(paramVO);
    }

    @Override
    public void updateSiteUser(SiteUserVO paramVO) throws Exception {
        siteuserMapper.updateSiteUser(paramVO);
    }

    @Override
    public void deleteSiteUser(SiteUserVO paramVO) throws Exception {
        siteuserMapper.deleteSiteUser(paramVO);
    }

	@Override
	public SiteUserVO selectSiteUserByUserId(SiteUserVO paramVO) throws Exception {
		return siteuserMapper.selectSiteUserByUserId(paramVO);
	}
}
