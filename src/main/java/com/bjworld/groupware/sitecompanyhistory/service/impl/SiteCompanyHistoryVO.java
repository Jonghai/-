package com.bjworld.groupware.sitecompanyhistory.service.impl;

import com.bjworld.groupware.common.vo.DefaultVO;

public class SiteCompanyHistoryVO extends DefaultVO {
    private String seq;
private String standardYear;
private String historyDate;
private String historyContents;


    public String getSeq() { return seq; }
public void setSeq(String seq) { this.seq = seq; }
public String getStandardYear() { return standardYear; }
public void setStandardYear(String standardYear) { this.standardYear = standardYear; }
public String getHistoryDate() { return historyDate; }
public void setHistoryDate(String historyDate) { this.historyDate = historyDate; }
public String getHistoryContents() { return historyContents; }
public void setHistoryContents(String historyContents) { this.historyContents = historyContents; }

}
