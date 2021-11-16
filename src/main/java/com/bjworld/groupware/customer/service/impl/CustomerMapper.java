package com.bjworld.groupware.customer.service.impl;

import java.util.List;

import com.bjworld.groupware.customer.service.impl.CustomerVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("customerMapper")
public interface CustomerMapper {	
	
	CustomerVO selectCustomer(CustomerVO paramVO) throws Exception;

	List<CustomerVO> selectCustomerList(CustomerVO paramVO) throws Exception;

	void mergeCustomer(CustomerVO paramVO) throws Exception;
	
	void updateCustomer(CustomerVO paramVO) throws Exception;
	
	void deleteCustomer(CustomerVO paramVO) throws Exception;
	
	Integer selectCustomerListTotCnt(CustomerVO paramVO) throws Exception;
}

/*@Mapper("customerMapper")
public interface CustomerMapper {
	
	void mergeCustomer(String customerName) throws Exception;

    void insertCustomer(CustomerUserVO paramVO) throws Exception;

    void updateCustomer(CustomerUserVO paramVO) throws Exception;
	
	CustomerUserVO selectCustomer(CustomerUserVO paramVO) throws Exception;
	
	void deleteCustomer(CustomerUserVO paramVO) throws Exception;
	
	List<?> selectCustomerList();
	
}*/
