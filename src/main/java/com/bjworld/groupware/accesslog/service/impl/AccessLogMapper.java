package com.bjworld.groupware.accesslog.service.impl;

import com.bjworld.groupware.accesslog.service.impl.AccessLogVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("accesslogMapper")
public interface AccessLogMapper{

	List<AccessLogVO> selectAccessLogList(AccessLogVO paramVO) throws Exception;
	
	Integer selectAccessLogListTotCnt(AccessLogVO paramVO) throws Exception;
	
	void mergeAccessLog(AccessLogVO paramVO) throws Exception;

    void insertAccessLog(AccessLogVO paramVO) throws Exception;

    void updateAccessLog(AccessLogVO paramVO) throws Exception;
	
	AccessLogVO selectAccessLog(AccessLogVO paramVO) throws Exception;
	
	void deleteAccessLog(AccessLogVO paramVO) throws Exception;
}
