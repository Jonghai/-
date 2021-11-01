package com.bjworld.groupware.eventnotice.service;

import com.bjworld.groupware.eventnotice.service.impl.EventNoticeVO;
import java.util.List;

public interface EventNoticeService {

    List<EventNoticeVO> selectEventNoticeList(EventNoticeVO paramVO) throws Exception;

    Integer selectEventNoticeListTotCnt(EventNoticeVO paramVO) throws Exception;

    EventNoticeVO selectEventNotice(EventNoticeVO paramVO) throws Exception;

    void saveEventNotice(EventNoticeVO paramVO) throws Exception;

    void mergeEventNotice(EventNoticeVO paramVO) throws Exception;

    void insertEventNotice(EventNoticeVO paramVO) throws Exception;

    void updateEventNotice(EventNoticeVO paramVO) throws Exception;

    void deleteEventNotice(EventNoticeVO paramVO) throws Exception;
}
