package com.bjworld.groupware.accesslog.service;

import com.bjworld.groupware.accesslog.service.impl.AccessLogVO;
import java.util.List;

public interface AccessLogService {

    List<AccessLogVO> selectAccessLogList(AccessLogVO paramVO) throws Exception;

    Integer selectAccessLogListTotCnt(AccessLogVO paramVO) throws Exception;

    AccessLogVO selectAccessLog(AccessLogVO paramVO) throws Exception;

    void mergeAccessLog(AccessLogVO paramVO) throws Exception;

    void insertAccessLog(AccessLogVO paramVO) throws Exception;

    void updateAccessLog(AccessLogVO paramVO) throws Exception;

    void deleteAccessLog(AccessLogVO paramVO) throws Exception;
}
