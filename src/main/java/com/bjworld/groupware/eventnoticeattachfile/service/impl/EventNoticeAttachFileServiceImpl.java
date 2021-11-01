package com.bjworld.groupware.eventnoticeattachfile.service.impl;

import com.bjworld.groupware.eventnoticeattachfile.service.EventNoticeAttachFileService;
import com.bjworld.groupware.eventnoticeattachfile.service.impl.EventNoticeAttachFileMapper;
import com.bjworld.groupware.eventnoticeattachfile.service.impl.EventNoticeAttachFileVO;

import com.bjworld.groupware.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;

@Service("eventnoticeattachfileService")
public class EventNoticeAttachFileServiceImpl extends EgovAbstractServiceImpl implements EventNoticeAttachFileService {
    @Resource(name="eventnoticeattachfileMapper")
    private EventNoticeAttachFileMapper eventnoticeattachfileMapper;

    

    @Override
    public List<EventNoticeAttachFileVO> selectEventNoticeAttachFileList(EventNoticeAttachFileVO paramVO) throws Exception {
        return eventnoticeattachfileMapper.selectEventNoticeAttachFileList(paramVO);
    }

    @Override
    public Integer selectEventNoticeAttachFileListTotCnt(EventNoticeAttachFileVO paramVO) throws Exception {
        return eventnoticeattachfileMapper.selectEventNoticeAttachFileListTotCnt(paramVO);
    }

    @Override
    public EventNoticeAttachFileVO selectEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception {
        return eventnoticeattachfileMapper.selectEventNoticeAttachFile(paramVO);
    }

    @Override
    @Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void saveEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception {
        
    }

    @Override
    public void mergeEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception {
        eventnoticeattachfileMapper.mergeEventNoticeAttachFile(paramVO);
    }

    @Override
    public void insertEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception {
        eventnoticeattachfileMapper.insertEventNoticeAttachFile(paramVO);
    }

    @Override
    public void updateEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception {
        eventnoticeattachfileMapper.updateEventNoticeAttachFile(paramVO);
    }

    @Override
    public void deleteEventNoticeAttachFile(EventNoticeAttachFileVO paramVO) throws Exception {
        eventnoticeattachfileMapper.deleteEventNoticeAttachFile(paramVO);
    }
}
