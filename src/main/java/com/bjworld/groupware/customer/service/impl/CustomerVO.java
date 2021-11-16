package com.bjworld.groupware.customer.service.impl;

//import com.bjworld.groupware.common.vo.DefaultVO;

public class CustomerVO {//extends DefaultVO {
	private String seq;
	private String customerName;
	private String customerManager;
    private String customerRank;
	private String customerPhone;
	private String customerEmail;
	
		
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerRank() {
		return customerRank;
	}
	public void setCustomerRank(String customerRank) {
		this.customerRank = customerRank;
	}
	
	/*@Override
	public String toString() {
		return "CustomerVO [seq=" + seq + ", customerName=" + customerName + ", customerManager=" + customerManager
				+ ", customerRank=" + customerRank + ", customerPhone=" + customerPhone + ", customerEmail="
				+ customerEmail + "]";
	}*/
	
	
}
