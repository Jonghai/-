package com.bjworld.groupware.sitecompanyhistory.service;

import com.bjworld.groupware.sitecompanyhistory.service.impl.SiteCompanyHistoryVO;
import java.util.List;

public interface SiteCompanyHistoryService {

    List<SiteCompanyHistoryVO> selectSiteCompanyHistoryList(SiteCompanyHistoryVO paramVO) throws Exception;

    Integer selectSiteCompanyHistoryListTotCnt(SiteCompanyHistoryVO paramVO) throws Exception;

    SiteCompanyHistoryVO selectSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

    void mergeSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

    void insertSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

    void updateSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

    void deleteSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception;

	List<SiteCompanyHistoryVO> selectSiteCompanyHistoryYear(SiteCompanyHistoryVO paramVO) throws Exception;
}
