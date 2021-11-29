package com.bjworld.groupware.server.service.impl;

public class ServerVO {
	private String seq;
	private String customerSeq;
	private String os;
	private String serverAddress;
	private String serverIp;
	private String serverPort;	
	private String serverLocation;
	private String serverStart;
	private String serverEnd;
	
	
	public String getSeq() 	
	{
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCustomerSeq() {
		return customerSeq;
	}
	public void setCustomerSeq(String customerSeq) {
		this.customerSeq = customerSeq;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getServerAddress() {
		return serverAddress;
	}
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getServerLocation() {
		return serverLocation;
	}
	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}
	public String getServerStart() {
		return serverStart;
	}
	public void setServerStart(String serverStart) {
		this.serverStart = serverStart;
	}
	public String getServerEnd() {
		return serverEnd;
	}
	public void setServerEnd(String serverEnd) {
		this.serverEnd = serverEnd;
	}	
	
	
}
