package com.bjworld.groupware.inquiry.service.impl;

import java.util.List;

import com.bjworld.groupware.inquiry.service.impl.InquiryVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("inquiryMapper")
public interface InquiryMapper {
	
	void mergeInquiry(InquiryVO paramVO);
	
	InquiryVO selectInquiry(InquiryVO paramVO) throws Exception;
	
	void updateInquiry(InquiryVO paramVO) throws Exception;
	
	List<InquiryVO> selectInquiryList(InquiryVO paramVO) throws Exception;
	
	Integer selectInquiryListTotCnt(InquiryVO paramVO) throws Exception;

	void deleteInquiry(InquiryVO paramVO) throws Exception;
	

}
