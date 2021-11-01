package com.bjworld.groupware.goodscategory.service.impl;

import java.util.List;
import com.bjworld.groupware.common.vo.DefaultVO;

public class GoodsCategoryVO extends DefaultVO {
	private String seq;
	private String categoryName;
	private String parentSeq;
	private String categorySort;
	private String isUsed;
	private String isUsedDesc;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(String parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getCategorySort() {
		return categorySort;
	}

	public void setCategorySort(String categorySort) {
		this.categorySort = categorySort;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getIsUsedDesc() {
		return isUsedDesc;
	}

	public void setIsUsedDesc(String isUsedDesc) {
		this.isUsedDesc = isUsedDesc;
	}

}
