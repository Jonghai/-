package com.bjworld.groupware.menu.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class MenuVO extends DefaultVO {
	private String seq;
	private String menuTitle;
	private String parentSeq;
	private String sort;
	private String menuLink;
	private String iconName;
	private String menuType;
	private String menuTypeDesc;
	private String menuCode;
	private String isWrite;
	private String isReplay;
	private String isAttach;
	private String maxAttachSize;
	private String unitAttachSize;
	private String isUsed;
	private String isUsedDesc;
	private String paths;
	private String menuContent;
	
	public String getMenuContent() {
		return menuContent;
	}

	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}

	public String getIsUsedDesc() {
		return isUsedDesc;
	}

	public void setIsUsedDesc(String isUsedDesc) {
		this.isUsedDesc = isUsedDesc;
	}

	public String getMenuTypeDesc() {
		return menuTypeDesc;
	}

	public void setMenuTypeDesc(String menuTypeDesc) {
		this.menuTypeDesc = menuTypeDesc;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getMenuTitle() {
		return menuTitle;
	}

	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
	}

	public String getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(String parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getIsWrite() {
		return isWrite;
	}

	public void setIsWrite(String isWrite) {
		this.isWrite = isWrite;
	}

	public String getIsReplay() {
		return isReplay;
	}

	public void setIsReplay(String isReplay) {
		this.isReplay = isReplay;
	}

	public String getIsAttach() {
		return isAttach;
	}

	public void setIsAttach(String isAttach) {
		this.isAttach = isAttach;
	}

	public String getMaxAttachSize() {
		return maxAttachSize;
	}

	public void setMaxAttachSize(String maxAttachSize) {
		this.maxAttachSize = maxAttachSize;
	}

	public String getUnitAttachSize() {
		return unitAttachSize;
	}

	public void setUnitAttachSize(String unitAttachSize) {
		this.unitAttachSize = unitAttachSize;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

}
