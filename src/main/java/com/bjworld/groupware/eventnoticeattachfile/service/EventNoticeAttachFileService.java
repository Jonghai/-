package com.bjworld.groupware.eventnoticeattachfile.service;

import com.bjworld.groupware.eventnoticeattachfile.service.impl.EventNoticeAttachFileVO;
import java.util.List;

public interface EventNoticeAttachFileService {

    List<EventNoticeAttachFileVO> selectEventNoticeAttachFileList(EventNoticeAttachFileVO paramVO) throws Exception;

    Integer selectEventNoticeAttachFileListTotCnt(EventNoticeAttachFileVO paramVO) throws Exception;

    EventNoticeAttachFileVO selectEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception;

    void saveEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception;

    void mergeEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception;

    void insertEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception;

    void updateEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception;

    void deleteEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception;
}
