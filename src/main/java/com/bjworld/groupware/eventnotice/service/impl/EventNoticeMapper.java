package com.bjworld.groupware.eventnotice.service.impl;

import com.bjworld.groupware.eventnotice.service.impl.EventNoticeVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("eventnoticeMapper")
public interface EventNoticeMapper{

	List<EventNoticeVO> selectEventNoticeList(EventNoticeVO paramVO) throws Exception;
	
	Integer selectEventNoticeListTotCnt(EventNoticeVO paramVO) throws Exception;
	
	void mergeEventNotice(EventNoticeVO paramVO) throws Exception;

    void insertEventNotice(EventNoticeVO paramVO) throws Exception;

    void updateEventNotice(EventNoticeVO paramVO) throws Exception;
	
	EventNoticeVO selectEventNotice(EventNoticeVO paramVO) throws Exception;
	
	void deleteEventNotice(EventNoticeVO paramVO) throws Exception;
}
