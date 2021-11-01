package com.bjworld.groupware.accesslog.service.impl;

import com.bjworld.groupware.accesslog.service.AccessLogService;
import com.bjworld.groupware.accesslog.service.impl.AccessLogMapper;
import com.bjworld.groupware.accesslog.service.impl.AccessLogVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("accesslogService")
public class AccessLogServiceImpl extends EgovAbstractServiceImpl implements AccessLogService {
    @Resource(name="accesslogMapper")
    private AccessLogMapper accesslogMapper;

    @Override
    public List<AccessLogVO> selectAccessLogList(AccessLogVO paramVO) throws Exception {
        return accesslogMapper.selectAccessLogList(paramVO);
    }

    @Override
    public Integer selectAccessLogListTotCnt(AccessLogVO paramVO) throws Exception {
        return accesslogMapper.selectAccessLogListTotCnt(paramVO);
    }

    @Override
    public AccessLogVO selectAccessLog(AccessLogVO paramVO) throws Exception {
        return accesslogMapper.selectAccessLog(paramVO);
    }

    @Override
    public void mergeAccessLog(AccessLogVO paramVO) throws Exception {
        accesslogMapper.mergeAccessLog(paramVO);
    }

    @Override
    public void insertAccessLog(AccessLogVO paramVO) throws Exception {
        accesslogMapper.insertAccessLog(paramVO);
    }

    @Override
    public void updateAccessLog(AccessLogVO paramVO) throws Exception {
        accesslogMapper.updateAccessLog(paramVO);
    }

    @Override
    public void deleteAccessLog(AccessLogVO paramVO) throws Exception {
        accesslogMapper.deleteAccessLog(paramVO);
    }
}
