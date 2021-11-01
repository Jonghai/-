package com.bjworld.groupware.sitecompanyhistory.service.impl;

import com.bjworld.groupware.sitecompanyhistory.service.SiteCompanyHistoryService;
import com.bjworld.groupware.sitecompanyhistory.service.impl.SiteCompanyHistoryMapper;
import com.bjworld.groupware.sitecompanyhistory.service.impl.SiteCompanyHistoryVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("sitecompanyhistoryService")
public class SiteCompanyHistoryServiceImpl extends EgovAbstractServiceImpl implements SiteCompanyHistoryService {
    @Resource(name="sitecompanyhistoryMapper")
    private SiteCompanyHistoryMapper sitecompanyhistoryMapper;

    @Override
    public List<SiteCompanyHistoryVO> selectSiteCompanyHistoryList(SiteCompanyHistoryVO paramVO) throws Exception {
        return sitecompanyhistoryMapper.selectSiteCompanyHistoryList(paramVO);
    }

    @Override
    public Integer selectSiteCompanyHistoryListTotCnt(SiteCompanyHistoryVO paramVO) throws Exception {
        return sitecompanyhistoryMapper.selectSiteCompanyHistoryListTotCnt(paramVO);
    }

    @Override
    public SiteCompanyHistoryVO selectSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception {
        return sitecompanyhistoryMapper.selectSiteCompanyHistory(paramVO);
    }

    @Override
    public void mergeSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception {
        sitecompanyhistoryMapper.mergeSiteCompanyHistory(paramVO);
    }

    @Override
    public void insertSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception {
        sitecompanyhistoryMapper.insertSiteCompanyHistory(paramVO);
    }

    @Override
    public void updateSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception {
        sitecompanyhistoryMapper.updateSiteCompanyHistory(paramVO);
    }

    @Override
    public void deleteSiteCompanyHistory(SiteCompanyHistoryVO paramVO) throws Exception {
        sitecompanyhistoryMapper.deleteSiteCompanyHistory(paramVO);
    }

	@Override
	public List<SiteCompanyHistoryVO> selectSiteCompanyHistoryYear(SiteCompanyHistoryVO paramVO) throws Exception {
		return sitecompanyhistoryMapper.selectSiteCompanyHistoryYear(paramVO);
	}
}
