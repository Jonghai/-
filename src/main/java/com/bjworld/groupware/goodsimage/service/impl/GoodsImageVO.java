package com.bjworld.groupware.goodsimage.service.impl;

import java.util.List;
import com.bjworld.groupware.common.vo.DefaultVO;

public class GoodsImageVO extends DefaultVO {
	private String seq;
	private String goodsSeq;
	private String oriFilename;
	private String saveFilename;
	private String filesize;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getGoodsSeq() {
		return goodsSeq;
	}

	public void setGoodsSeq(String goodsSeq) {
		this.goodsSeq = goodsSeq;
	}

	public String getOriFilename() {
		return oriFilename;
	}

	public void setOriFilename(String oriFilename) {
		this.oriFilename = oriFilename;
	}

	public String getSaveFilename() {
		return saveFilename;
	}

	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

}
