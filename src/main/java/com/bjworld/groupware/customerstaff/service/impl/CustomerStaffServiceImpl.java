package com.bjworld.groupware.customerstaff.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.customerstaff.service.CustomerStaffService;
import com.bjworld.groupware.customerstaff.service.impl.CustomerStaffMapper;
import com.bjworld.groupware.customerstaff.service.impl.CustomerStaffVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("customerstaffService")
public class CustomerStaffServiceImpl extends EgovAbstractServiceImpl implements CustomerStaffService {
	@Resource(name = "customerstaffMapper")
	private CustomerStaffMapper customerstaffMapper;

	@Override
	public void mergeCustomerStaff(CustomerStaffVO paramVO) {
		customerstaffMapper.mergeCustomerStaff(paramVO);// TODO Auto-generated method stub
		
	}
	
	public CustomerStaffVO selectCustomerStaff(CustomerStaffVO paramVO) throws Exception {
        return customerstaffMapper.selectCustomerStaff(paramVO);
    }


	@Override
	public List<CustomerStaffVO> selectCustomerStaffList(CustomerStaffVO paramVO) throws Exception {
		
		return customerstaffMapper.selectCustomerStaffList(paramVO);
	}
	

	@Override
	public void deleteCustomerStaff(CustomerStaffVO paramVO) throws Exception {
		customerstaffMapper.deleteCustomerStaff(paramVO);
		
	}

	@Override
	public void updateCustomerStaff(CustomerStaffVO paramVO) throws Exception {
		customerstaffMapper.updateCustomerStaff(paramVO);
		
	}
	
	 @Override
	    public Integer selectCustomerStaffListTotCnt(CustomerStaffVO paramVO) throws Exception {
	        return customerstaffMapper.selectCustomerStaffListTotCnt(paramVO);
	    }


}
