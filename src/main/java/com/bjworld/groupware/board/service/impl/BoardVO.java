package com.bjworld.groupware.board.service.impl;

import java.util.List;

import com.bjworld.groupware.boardattach.service.impl.BoardAttachVO;
import com.bjworld.groupware.common.vo.DefaultVO;

public class BoardVO extends DefaultVO{
	private String seq;
	private String boardSeq;
	private String menuSeq;
	private String boardCode;
	private String boardNavigator;
	private String boardTitle;
	private String boardTitleLink;
	private String boardContent;
	private String boardContentHtml;
	private String regUserId;
	private String regUserName;
	private String regType;
	private String regRemoteIP;
	private String regDate;
	private String readCount;
	private String thumbnailAddress;
	private String thumbnailImageFilename;
	private String thumbnailImageSavefilename;
	
	private String [] attachSaveFileName;
	private String [] attachOriFileName;
	private String [] attachFileSize;
	

	public String getThumbnailAddress() {
		return thumbnailAddress;
	}

	public void setThumbnailAddress(String thumbnailAddress) {
		this.thumbnailAddress = thumbnailAddress;
	}

	private List<BoardAttachVO> listBoardAttach;
	
	private String fileCount;
	private String nextTitle;
	private String nextSeq;
	private String prevTitle;
	private String prevSeq;
	
	public String getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoardNavigator() {
		return boardNavigator;
	}

	public void setBoardNavigator(String boardNavigator) {
		this.boardNavigator = boardNavigator;
	}

	public String getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getBoardContentHtml() {
		return boardContentHtml;
	}

	public void setBoardContentHtml(String boardContentHtml) {
		this.boardContentHtml = boardContentHtml;
	}

	public String[] getAttachSaveFileName() {
		return attachSaveFileName;
	}

	public void setAttachSaveFileName(String[] attachSaveFileName) {
		this.attachSaveFileName = attachSaveFileName;
	}

	public String[] getAttachOriFileName() {
		return attachOriFileName;
	}

	public void setAttachOriFileName(String[] attachOriFileName) {
		this.attachOriFileName = attachOriFileName;
	}

	public String[] getAttachFileSize() {
		return attachFileSize;
	}

	public void setAttachFileSize(String[] attachFileSize) {
		this.attachFileSize = attachFileSize;
	}

	public List<BoardAttachVO> getListBoardAttach() {
		return listBoardAttach;
	}

	public void setListBoardAttach(List<BoardAttachVO> listBoardAttach) {
		this.listBoardAttach = listBoardAttach;
	}

	public String getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardTitleLink() {
		return boardTitleLink;
	}

	public void setBoardTitleLink(String boardTitleLink) {
		this.boardTitleLink = boardTitleLink;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}

	public String getRegUserName() {
		return regUserName;
	}

	public void setRegUserName(String regUserName) {
		this.regUserName = regUserName;
	}

	public String getRegType() {
		return regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getRegRemoteIP() {
		return regRemoteIP;
	}

	public void setRegRemoteIP(String regRemoteIP) {
		this.regRemoteIP = regRemoteIP;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getReadCount() {
		return readCount;
	}

	public void setReadCount(String readCount) {
		this.readCount = readCount;
	}

	public String getThumbnailImageFilename() {
		return thumbnailImageFilename;
	}

	public void setThumbnailImageFilename(String thumbnailImageFilename) {
		this.thumbnailImageFilename = thumbnailImageFilename;
	}

	public String getThumbnailImageSavefilename() {
		return thumbnailImageSavefilename;
	}

	public void setThumbnailImageSavefilename(String thumbnailImageSavefilename) {
		this.thumbnailImageSavefilename = thumbnailImageSavefilename;
	}

	public String getFileCount() {
		return fileCount;
	}

	public void setFileCount(String fileCount) {
		this.fileCount = fileCount;
	}

	public String getNextTitle() {
		return nextTitle;
	}

	public void setNextTitle(String nextTitle) {
		this.nextTitle = nextTitle;
	}

	public String getNextSeq() {
		return nextSeq;
	}

	public void setNextSeq(String nextSeq) {
		this.nextSeq = nextSeq;
	}

	public String getPrevTitle() {
		return prevTitle;
	}

	public void setPrevTitle(String prevTitle) {
		this.prevTitle = prevTitle;
	}

	public String getPrevSeq() {
		return prevSeq;
	}

	public void setPrevSeq(String prevSeq) {
		this.prevSeq = prevSeq;
	}
	
}
