package com.bjworld.groupware.siteuser.service.impl;

import com.bjworld.groupware.siteuser.service.impl.SiteUserVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("siteuserMapper")
public interface SiteUserMapper{

	List<SiteUserVO> selectSiteUserList(SiteUserVO paramVO) throws Exception;
	
	Integer selectSiteUserListTotCnt(SiteUserVO paramVO) throws Exception;
	
	void mergeSiteUser(SiteUserVO paramVO) throws Exception;

    void insertSiteUser(SiteUserVO paramVO) throws Exception;

    void updateSiteUser(SiteUserVO paramVO) throws Exception;
	
	SiteUserVO selectSiteUser(SiteUserVO paramVO) throws Exception;
	
	void deleteSiteUser(SiteUserVO paramVO) throws Exception;
	
	SiteUserVO selectSiteUserByUserId(SiteUserVO paramVO) throws Exception;
}
