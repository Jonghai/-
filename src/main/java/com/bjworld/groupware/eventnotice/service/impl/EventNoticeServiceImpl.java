package com.bjworld.groupware.eventnotice.service.impl;

import com.bjworld.groupware.eventnotice.service.EventNoticeService;
import com.bjworld.groupware.eventnotice.service.impl.EventNoticeMapper;
import com.bjworld.groupware.eventnotice.service.impl.EventNoticeVO;

import com.bjworld.groupware.eventnoticeattachfile.service.EventNoticeAttachFileService;
import com.bjworld.groupware.eventnoticeattachfile.service.impl.EventNoticeAttachFileVO;

import com.bjworld.groupware.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;

@Service("eventnoticeService")
public class EventNoticeServiceImpl extends EgovAbstractServiceImpl implements EventNoticeService {
	@Resource(name = "eventnoticeMapper")
	private EventNoticeMapper eventnoticeMapper;

	@Resource(name = "eventnoticeattachfileService")
	private EventNoticeAttachFileService eventnoticeattachfileService;

	@Override
	public List<EventNoticeVO> selectEventNoticeList(EventNoticeVO paramVO) throws Exception {
		return eventnoticeMapper.selectEventNoticeList(paramVO);
	}

	@Override
	public Integer selectEventNoticeListTotCnt(EventNoticeVO paramVO) throws Exception {
		return eventnoticeMapper.selectEventNoticeListTotCnt(paramVO);
	}

	@Override
	public EventNoticeVO selectEventNotice(EventNoticeVO paramVO) throws Exception {
		return eventnoticeMapper.selectEventNotice(paramVO);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void saveEventNotice(EventNoticeVO paramVO) throws Exception {

		String seq = paramVO.getSeq();

		if (EgovStringUtil.isEmpty(seq))
			paramVO.setSeq(null);

		mergeEventNotice(paramVO);

		seq = paramVO.getSeq();

		if (paramVO.getAttachSaveFileName() != null) {
			for (int i = 0; i < paramVO.getAttachSaveFileName().length; i++) {
				EventNoticeAttachFileVO fileVO = new EventNoticeAttachFileVO();
				fileVO.setEventSeq(seq);
				fileVO.setEventSaveFilename(paramVO.getAttachSaveFileName()[i]);
				fileVO.setEventOriFilename(paramVO.getAttachOriFileName()[i]);
				fileVO.setEventFilesize(paramVO.getAttachFileSize()[i]);
				eventnoticeattachfileService.insertEventNoticeAttachFile(fileVO);
			}
		}

	}

	@Override
	public void mergeEventNotice(EventNoticeVO paramVO) throws Exception {
		eventnoticeMapper.mergeEventNotice(paramVO);
	}

	@Override
	public void insertEventNotice(EventNoticeVO paramVO) throws Exception {
		eventnoticeMapper.insertEventNotice(paramVO);
	}

	@Override
	public void updateEventNotice(EventNoticeVO paramVO) throws Exception {
		eventnoticeMapper.updateEventNotice(paramVO);
	}

	@Override
	public void deleteEventNotice(EventNoticeVO paramVO) throws Exception {
		eventnoticeMapper.deleteEventNotice(paramVO);
	}
}
