package com.bjworld.groupware.organization.service.impl;

import com.bjworld.groupware.organization.service.impl.OrganizationVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("organizationMapper")
public interface OrganizationMapper{

	List<OrganizationVO> selectOrganizationList(OrganizationVO paramVO) throws Exception;
	
	Integer selectOrganizationListTotCnt(OrganizationVO paramVO) throws Exception;
	
	void mergeOrganization(OrganizationVO paramVO) throws Exception;

    void insertOrganization(OrganizationVO paramVO) throws Exception;

    void updateOrganization(OrganizationVO paramVO) throws Exception;
	
	OrganizationVO selectOrganization(OrganizationVO paramVO) throws Exception;
	
	void deleteOrganization(OrganizationVO paramVO) throws Exception;

	List<OrganizationVO> selectOrganizationTreeList(OrganizationVO paramVO) throws Exception;
}
