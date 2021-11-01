package com.bjworld.groupware.commoncode.service.impl;

import java.util.List;

import com.bjworld.groupware.common.vo.DefaultVO;

public class CommonCodeVO extends DefaultVO {
	private String seq;
	private String parentSeq;
	private String codeDesc;
	private String sort;
	private String isUsed;
	private String isUsedDesc;
	private String codeEtc1;
	private String codeEtc2;
	private String codeEtc3;
	private String isEdit;
	private String isEditDesc;
	
	private List<CommonCodeVO> listChildCommonCode;
	
	public String getIsUsedDesc() {
		return isUsedDesc;
	}

	public void setIsUsedDesc(String isUsedDesc) {
		this.isUsedDesc = isUsedDesc;
	}

	
	public List<CommonCodeVO> getListChildCommonCode() {
		return listChildCommonCode;
	}

	public void setListChildCommonCode(List<CommonCodeVO> listChildCommonCode) {
		this.listChildCommonCode = listChildCommonCode;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(String parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getCodeEtc1() {
		return codeEtc1;
	}

	public void setCodeEtc1(String codeEtc1) {
		this.codeEtc1 = codeEtc1;
	}

	public String getCodeEtc2() {
		return codeEtc2;
	}

	public void setCodeEtc2(String codeEtc2) {
		this.codeEtc2 = codeEtc2;
	}

	public String getCodeEtc3() {
		return codeEtc3;
	}

	public void setCodeEtc3(String codeEtc3) {
		this.codeEtc3 = codeEtc3;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getIsEditDesc() {
		return isEditDesc;
	}

	public void setIsEditDesc(String isEditDesc) {
		this.isEditDesc = isEditDesc;
	}
	
	

}
