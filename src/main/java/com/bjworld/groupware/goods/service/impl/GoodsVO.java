package com.bjworld.groupware.goods.service.impl;

import java.util.List;
import com.bjworld.groupware.common.vo.DefaultVO;
import com.bjworld.groupware.goodsimage.service.impl.GoodsImageVO;

public class GoodsVO extends DefaultVO {
	private String seq;
	private String companySeq;
	private String goodsCode;
	private String goodsName;
	private String gcSeq1;
	private String gcSeq2;
	private String goodsGuide;
	private String goodsContents;
	private String regDate;
	private String isUsed;
	private String thumnailImage;
	private String[] attachSaveFileName;
	private String[] attachOriFileName;
	private String[] attachFileSize;
	private List<GoodsImageVO> listGoodsImage;
	private String companyName;
	private String gcName1;
	private String gcName2;

	public List<GoodsImageVO> getListGoodsImage() {
		return listGoodsImage;
	}

	public void setListGoodsImage(List<GoodsImageVO> listGoodsImage) {
		this.listGoodsImage = listGoodsImage;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCompanySeq() {
		return companySeq;
	}

	public void setCompanySeq(String companySeq) {
		this.companySeq = companySeq;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGcSeq1() {
		return gcSeq1;
	}

	public void setGcSeq1(String gcSeq1) {
		this.gcSeq1 = gcSeq1;
	}

	public String getGcSeq2() {
		return gcSeq2;
	}

	public void setGcSeq2(String gcSeq2) {
		this.gcSeq2 = gcSeq2;
	}

	public String getGoodsGuide() {
		return goodsGuide;
	}

	public void setGoodsGuide(String goodsGuide) {
		this.goodsGuide = goodsGuide;
	}

	public String getGoodsContents() {
		return goodsContents;
	}

	public void setGoodsContents(String goodsContents) {
		this.goodsContents = goodsContents;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getThumnailImage() {
		return thumnailImage;
	}

	public void setThumnailImage(String thumnailImage) {
		this.thumnailImage = thumnailImage;
	}

	public String[] getAttachSaveFileName() {
		return this.attachSaveFileName;
	}

	public void setAttachSaveFileName(String[] attachSaveFileName) {
		this.attachSaveFileName = attachSaveFileName;
	}

	public String[] getAttachOriFileName() {
		return this.attachOriFileName;
	}

	public void setAttachOriFileName(String[] attachOriFileName) {
		this.attachOriFileName = attachOriFileName;
	}

	public String[] getAttachFileSize() {
		return this.attachFileSize;
	}

	public void setAttachFileSize(String[] attachFileSize) {
		this.attachFileSize = attachFileSize;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getGcName1() {
		return gcName1;
	}

	public void setGcName1(String gcName1) {
		this.gcName1 = gcName1;
	}

	public String getGcName2() {
		return gcName2;
	}

	public void setGcName2(String gcName2) {
		this.gcName2 = gcName2;
	}

}
