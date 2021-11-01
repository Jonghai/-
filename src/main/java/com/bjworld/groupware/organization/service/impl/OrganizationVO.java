package com.bjworld.groupware.organization.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class OrganizationVO extends DefaultVO {
	private String seq;
	private String orgName;
	private String parentSeq;
	private String orgField;
	private String orgDirection;
	private String orgSort;
	private String paths;
	private String fullPaths;
	private String sorts;

	
	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public String getFullPaths() {
		return fullPaths;
	}

	public void setFullPaths(String fullPaths) {
		this.fullPaths = fullPaths;
	}

	public String getSorts() {
		return sorts;
	}

	public void setSorts(String sorts) {
		this.sorts = sorts;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(String parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getOrgField() {
		return orgField;
	}

	public void setOrgField(String orgField) {
		this.orgField = orgField;
	}

	public String getOrgDirection() {
		return orgDirection;
	}

	public void setOrgDirection(String orgDirection) {
		this.orgDirection = orgDirection;
	}

	public String getOrgSort() {
		return orgSort;
	}

	public void setOrgSort(String orgSort) {
		this.orgSort = orgSort;
	}

}
