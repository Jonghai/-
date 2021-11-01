package com.bjworld.groupware.eventnoticeattachfile.service.impl;

import java.util.List;
import com.bjworld.groupware.common.vo.DefaultVO;

public class EventNoticeAttachFileVO extends DefaultVO {
    private String seq;
private String eventSeq;
private String eventSaveFilename;
private String eventOriFilename;
private String eventFilesize;


    public String getSeq() { return seq; }
public void setSeq(String seq) { this.seq = seq; }
public String getEventSeq() { return eventSeq; }
public void setEventSeq(String eventSeq) { this.eventSeq = eventSeq; }
public String getEventSaveFilename() { return eventSaveFilename; }
public void setEventSaveFilename(String eventSaveFilename) { this.eventSaveFilename = eventSaveFilename; }
public String getEventOriFilename() { return eventOriFilename; }
public void setEventOriFilename(String eventOriFilename) { this.eventOriFilename = eventOriFilename; }
public String getEventFilesize() { return eventFilesize; }
public void setEventFilesize(String eventFilesize) { this.eventFilesize = eventFilesize; }

}
