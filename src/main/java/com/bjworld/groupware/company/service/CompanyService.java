package com.bjworld.groupware.company.service;

import com.bjworld.groupware.company.service.impl.CompanyVO;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CompanyService {

    List<CompanyVO> selectCompanyList(CompanyVO paramVO) throws Exception;

    Integer selectCompanyListTotCnt(CompanyVO paramVO) throws Exception;

    CompanyVO selectCompany(CompanyVO paramVO) throws Exception;

    void mergeCompany(CompanyVO paramVO) throws Exception;

    void insertCompany(CompanyVO paramVO) throws Exception;

    void updateCompany(CompanyVO paramVO) throws Exception;

    void deleteCompany(CompanyVO paramVO) throws Exception;

	String selectCompanyByBusinessNumber(String companyBusinessNumber) throws Exception;

	void saveCompany(CompanyVO paramVO) throws Exception;

	CompanyVO selectCompanyUserLogin(CompanyVO paramVO);

	CompanyVO selectCompanyForPassword(CompanyVO paramVO);

	void updateCompanyPassword(CompanyVO paramVO) throws Exception;

	CompanyVO selectCompanyBySystemMember(String companyBusinessNumber) throws Exception;
	
	void selectExcelCompany(CompanyVO paramVO, HttpServletRequest request, HttpServletResponse response, String[] paramKey) throws Exception;
	
	List<String> selectCompanyListByHomeTaxStatusCheck(CompanyVO companyParamVO) throws Exception;

	void updateCompanyAtHomeTaxStatus(CompanyVO paramVO) throws Exception;
}
