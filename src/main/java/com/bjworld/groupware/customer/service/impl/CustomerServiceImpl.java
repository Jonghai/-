package com.bjworld.groupware.customer.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.bjworld.groupware.customer.service.customerService;
import com.bjworld.groupware.customer.service.impl.CustomerVO;
import com.bjworld.groupware.customer.service.impl.CustomerMapper;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("customerService")
public class CustomerServiceImpl extends EgovAbstractServiceImpl implements customerService {
	
	@Resource(name = "customerMapper")
	private CustomerMapper customerMapper;

	@Override
	public List<CustomerVO> selectCustomerList(CustomerVO paramVO) throws Exception {
		return customerMapper.selectCustomerList(paramVO);
	}

	@Override
	public void mergeCustomer(CustomerVO paramVO) throws Exception { 
		customerMapper.mergeCustomer(paramVO);		
	}

	@Override
	public CustomerVO selectCustomer(CustomerVO paramVO) throws Exception {
		
		return customerMapper.selectCustomer(paramVO);
	}

	@Override
	public void updateCustomer(CustomerVO paramVO) throws Exception {
		customerMapper.updateCustomer(paramVO);		
	}

	@Override
	public void deleteCustomer(CustomerVO paramVO) throws Exception {
		customerMapper.deleteCustomer(paramVO);		
	}

	@Override
	public Integer selectCustomerListTotCnt(CustomerVO paramVO) throws Exception {
		return customerMapper.selectCustomerListTotCnt(paramVO);		
	}	
}