package com.bjworld.groupware.organization.service;

import com.bjworld.groupware.organization.service.impl.OrganizationVO;
import java.util.List;

public interface OrganizationService {

    List<OrganizationVO> selectOrganizationList(OrganizationVO paramVO) throws Exception;

    Integer selectOrganizationListTotCnt(OrganizationVO paramVO) throws Exception;

    OrganizationVO selectOrganization(OrganizationVO paramVO) throws Exception;

    void mergeOrganization(OrganizationVO paramVO) throws Exception;

    void insertOrganization(OrganizationVO paramVO) throws Exception;

    void updateOrganization(OrganizationVO paramVO) throws Exception;

    void deleteOrganization(OrganizationVO paramVO) throws Exception;

	List<OrganizationVO> selectOrganizationTreeList(OrganizationVO paramVO) throws Exception;
}
