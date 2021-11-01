package com.bjworld.groupware.company.service.impl;

import com.bjworld.groupware.company.service.impl.CompanyVO;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("companyMapper")
public interface CompanyMapper{

	List<CompanyVO> selectCompanyList(CompanyVO paramVO) throws Exception;
	
	Integer selectCompanyListTotCnt(CompanyVO paramVO) throws Exception;
	
	void mergeCompany(CompanyVO paramVO) throws Exception;

    void insertCompany(CompanyVO paramVO) throws Exception;

    void updateCompany(CompanyVO paramVO) throws Exception;
	
	CompanyVO selectCompany(CompanyVO paramVO) throws Exception;
	
	void deleteCompany(CompanyVO paramVO) throws Exception;
	
	String selectCompanyByBusinessNumber(String companyBusinessNumber) throws Exception;

	CompanyVO selectCompanyUserLogin(CompanyVO paramVO);

	CompanyVO selectCompanyForPassword(CompanyVO paramVO);
	
	void updateCompanyPassword(CompanyVO paramVO) throws Exception;
	
	CompanyVO selectCompanyBySystemMember(String companyBusinessNumber) throws Exception;
	
	List<HashMap<String, String>> selectExcelCompany(CompanyVO paramVO);
	
	List<String> selectCompanyListByHomeTaxStatusCheck(CompanyVO companyParamVO) throws Exception;
	
	void updateCompanyAtHomeTaxStatus(CompanyVO paramVO) throws Exception;
}
