package com.bjworld.groupware.inquiry.service;

import java.util.List;

import com.bjworld.groupware.inquiry.service.impl.InquiryVO;

public interface InquiryService {
	
	void mergeInquiry(InquiryVO paramVO);
	
	InquiryVO selectInquiry(InquiryVO paramVO) throws Exception;
	

	List<InquiryVO> selectInquiryList(InquiryVO paramVO) throws Exception;

	void deleteInquiry(InquiryVO paramVO) throws Exception;


	void updateInquiry(InquiryVO paramVO) throws Exception;

	Integer selectInquiryListTotCnt(InquiryVO paramVO) throws Exception;
	

}
