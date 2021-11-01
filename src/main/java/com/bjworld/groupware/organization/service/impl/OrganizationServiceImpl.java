package com.bjworld.groupware.organization.service.impl;

import com.bjworld.groupware.organization.service.OrganizationService;
import com.bjworld.groupware.organization.service.impl.OrganizationMapper;
import com.bjworld.groupware.organization.service.impl.OrganizationVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("organizationService")
public class OrganizationServiceImpl extends EgovAbstractServiceImpl implements OrganizationService {
    @Resource(name="organizationMapper")
    private OrganizationMapper organizationMapper;

    @Override
    public List<OrganizationVO> selectOrganizationList(OrganizationVO paramVO) throws Exception {
        return organizationMapper.selectOrganizationList(paramVO);
    }

    @Override
    public Integer selectOrganizationListTotCnt(OrganizationVO paramVO) throws Exception {
        return organizationMapper.selectOrganizationListTotCnt(paramVO);
    }

    @Override
    public OrganizationVO selectOrganization(OrganizationVO paramVO) throws Exception {
        return organizationMapper.selectOrganization(paramVO);
    }

    @Override
    public void mergeOrganization(OrganizationVO paramVO) throws Exception {
        organizationMapper.mergeOrganization(paramVO);
    }

    @Override
    public void insertOrganization(OrganizationVO paramVO) throws Exception {
        organizationMapper.insertOrganization(paramVO);
    }

    @Override
    public void updateOrganization(OrganizationVO paramVO) throws Exception {
        organizationMapper.updateOrganization(paramVO);
    }

    @Override
    public void deleteOrganization(OrganizationVO paramVO) throws Exception {
        organizationMapper.deleteOrganization(paramVO);
    }

	@Override
	public List<OrganizationVO> selectOrganizationTreeList(OrganizationVO paramVO) throws Exception {
		return organizationMapper.selectOrganizationTreeList(paramVO);
	}
}
