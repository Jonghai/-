package com.bjworld.groupware.inquiry.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.inquiry.service.InquiryService;
import com.bjworld.groupware.inquiry.service.impl.InquiryMapper;
import com.bjworld.groupware.inquiry.service.impl.InquiryVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("inquiryService")

public class InquiryServiceimpl extends EgovAbstractServiceImpl implements InquiryService {
	@Resource(name = "inquiryMapper")
	private InquiryMapper inquiryMapper;

	@Override
	public void mergeInquiry(InquiryVO paramVO) {
		inquiryMapper.mergeInquiry(paramVO);// TODO Auto-generated method stub
		
	}
	
	public InquiryVO selectInquiry(InquiryVO paramVO) throws Exception {
        return inquiryMapper.selectInquiry(paramVO);
    }


	@Override
	public List<InquiryVO> selectInquiryList(InquiryVO paramVO) throws Exception {
		
		return inquiryMapper.selectInquiryList(paramVO);
	}
	

	@Override
	public void deleteInquiry(InquiryVO paramVO) throws Exception {
		inquiryMapper.deleteInquiry(paramVO);
		
	}

	@Override
	public void updateInquiry(InquiryVO paramVO) throws Exception {
		inquiryMapper.updateInquiry(paramVO);
		
	}
	
	 @Override
	    public Integer selectInquiryListTotCnt(InquiryVO paramVO) throws Exception {
	        return inquiryMapper.selectInquiryListTotCnt(paramVO);
	    }


}
