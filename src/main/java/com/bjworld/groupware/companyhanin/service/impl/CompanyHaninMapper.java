package com.bjworld.groupware.companyhanin.service.impl;

import com.bjworld.groupware.companyhanin.service.impl.CompanyHaninVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("companyhaninMapper")
public interface CompanyHaninMapper{

	List<CompanyHaninVO> selectCompanyHaninList(CompanyHaninVO paramVO) throws Exception;
	
	Integer selectCompanyHaninListTotCnt(CompanyHaninVO paramVO) throws Exception;
	
	void mergeCompanyHanin(CompanyHaninVO paramVO) throws Exception;

    void insertCompanyHanin(CompanyHaninVO paramVO) throws Exception;

    void updateCompanyHanin(CompanyHaninVO paramVO) throws Exception;
	
	CompanyHaninVO selectCompanyHanin(CompanyHaninVO paramVO) throws Exception;
	
	void deleteCompanyHanin(CompanyHaninVO paramVO) throws Exception;
}
