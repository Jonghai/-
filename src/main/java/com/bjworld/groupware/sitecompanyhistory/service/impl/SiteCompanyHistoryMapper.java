package com.bjworld.groupware.sitecompanyhistory.service.impl;

import com.bjworld.groupware.sitecompanyhistory.service.impl.SiteCompanyHistoryVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("sitecompanyhistoryMapper")
public interface SiteCompanyHistoryMapper{

	List<SiteCompanyHistoryVO> selectSiteCompanyHistoryList(SiteCompanyHistoryVO paramVO) throws Exception;
	
	Integer selectSiteCompanyHistoryListTotCnt(SiteCompanyHistoryVO paramVO) throws Exception;
	
	void mergeSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

    void insertSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

    void updateSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;
	
	SiteCompanyHistoryVO selectSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;
	
	void deleteSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

	List<SiteCompanyHistoryVO> selectSiteCompanyHistoryYear(SiteCompanyHistoryVO paramVO) throws Exception;
}
