package com.bjworld.groupware.companyhanin.service;

import com.bjworld.groupware.companyhanin.service.impl.CompanyHaninVO;
import java.util.List;

public interface CompanyHaninService {

    List<CompanyHaninVO> selectCompanyHaninList(CompanyHaninVO paramVO) throws Exception;

    Integer selectCompanyHaninListTotCnt(CompanyHaninVO paramVO) throws Exception;

    CompanyHaninVO selectCompanyHanin(CompanyHaninVO paramVO) throws Exception;

    void mergeCompanyHanin(CompanyHaninVO paramVO) throws Exception;

    void insertCompanyHanin(CompanyHaninVO paramVO) throws Exception;

    void updateCompanyHanin(CompanyHaninVO paramVO) throws Exception;

    void deleteCompanyHanin(CompanyHaninVO paramVO) throws Exception;
}
