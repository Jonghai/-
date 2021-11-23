package com.bjworld.groupware.inquiry.service.impl;

public class InquiryVO {
	private String seq;
	private String csSeq;
	private String inquiryDate;
	private String inquiryTitle;
	private String inquiryContent;
	private String answerDate;
	private String answerContent;
	private String csName;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCsSeq() {
		return csSeq;
	}
	public void setCsSeq(String csSeq) {
		this.csSeq = csSeq;
	}
	public String getInquiryDate() {
		return inquiryDate;
	}
	public void setInquiryDate(String inquiryDate) {
		this.inquiryDate = inquiryDate;
	}
	public String getInquiryTitle() {
		return inquiryTitle;
	}
	public void setInquiryTitle(String inquiryTitle) {
		this.inquiryTitle = inquiryTitle;
	}
	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}
	public String getInquiryContent() {
		return inquiryContent;
	}
	public String getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getCsName() {
		return csName;
	}
	public void setCsName(String csName) {
		this.csName = csName;
	}

}
