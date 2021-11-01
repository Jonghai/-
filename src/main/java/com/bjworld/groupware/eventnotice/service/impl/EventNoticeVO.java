package com.bjworld.groupware.eventnotice.service.impl;

import java.util.List;
import com.bjworld.groupware.common.vo.DefaultVO;
import com.bjworld.groupware.eventnoticeattachfile.service.impl.EventNoticeAttachFileVO;

public class EventNoticeVO extends DefaultVO {
	private String seq;
	private String eventTitle;
	private String eventContent;
	private String eventStartdate;
	private String eventEnddate;
	private String eventLocation;
	private String eventScale;
	private String eventCategory;
	private String eventItem;
	private String eventHosting;
	private String eventCharge;
	private String eventPhone;
	private String eventEmail;
	private String eventHomepage;
	private String regDate;
	private String[] attachSaveFileName;
	private String[] attachOriFileName;
	private String[] attachFileSize;
	private List<EventNoticeAttachFileVO> listAttachFile;
	
	

	public List<EventNoticeAttachFileVO> getListAttachFile() {
		return listAttachFile;
	}

	public void setListAttachFile(List<EventNoticeAttachFileVO> listAttachFile) {
		this.listAttachFile = listAttachFile;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public String getEventStartdate() {
		return eventStartdate;
	}

	public void setEventStartdate(String eventStartdate) {
		this.eventStartdate = eventStartdate;
	}

	public String getEventEnddate() {
		return eventEnddate;
	}

	public void setEventEnddate(String eventEnddate) {
		this.eventEnddate = eventEnddate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventScale() {
		return eventScale;
	}

	public void setEventScale(String eventScale) {
		this.eventScale = eventScale;
	}

	public String getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	public String getEventItem() {
		return eventItem;
	}

	public void setEventItem(String eventItem) {
		this.eventItem = eventItem;
	}

	public String getEventHosting() {
		return eventHosting;
	}

	public void setEventHosting(String eventHosting) {
		this.eventHosting = eventHosting;
	}

	public String getEventCharge() {
		return eventCharge;
	}

	public void setEventCharge(String eventCharge) {
		this.eventCharge = eventCharge;
	}

	public String getEventPhone() {
		return eventPhone;
	}

	public void setEventPhone(String eventPhone) {
		this.eventPhone = eventPhone;
	}

	public String getEventEmail() {
		return eventEmail;
	}

	public void setEventEmail(String eventEmail) {
		this.eventEmail = eventEmail;
	}

	public String getEventHomepage() {
		return eventHomepage;
	}

	public void setEventHomepage(String eventHomepage) {
		this.eventHomepage = eventHomepage;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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

}
