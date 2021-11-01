package com.bjworld.groupware.relatedinstitution.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class RelatedInstitutionVO extends DefaultVO {
	private String seq;
	private String institutionName;
	private String institutionUrl;
	private String institutionCategory;
	private String institutionCategoryDesc;
	private String logoOriFilename;
	private String logoSaveFilename;
	private String regDate;

	
	public String getInstitutionCategoryDesc() {
		return institutionCategoryDesc;
	}

	public void setInstitutionCategoryDesc(String institutionCategoryDesc) {
		this.institutionCategoryDesc = institutionCategoryDesc;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getInstitutionUrl() {
		return institutionUrl;
	}

	public void setInstitutionUrl(String institutionUrl) {
		this.institutionUrl = institutionUrl;
	}

	public String getInstitutionCategory() {
		return institutionCategory;
	}

	public void setInstitutionCategory(String institutionCategory) {
		this.institutionCategory = institutionCategory;
	}

	public String getLogoOriFilename() {
		return logoOriFilename;
	}

	public void setLogoOriFilename(String logoOriFilename) {
		this.logoOriFilename = logoOriFilename;
	}

	public String getLogoSaveFilename() {
		return logoSaveFilename;
	}

	public void setLogoSaveFilename(String logoSaveFilename) {
		this.logoSaveFilename = logoSaveFilename;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
