package com.bjworld.groupware.server.service;

import java.util.List;

import com.bjworld.groupware.server.service.impl.ServerVO;

public interface ServerService {

	void mergeServer(ServerVO paramVO);
	
	ServerVO selectServer(ServerVO paramVO) throws Exception;
	
	List<ServerVO> selectServerList(ServerVO paramVO) throws Exception;

	void deleteServer(ServerVO paramVO) throws Exception;


	void updateServer(ServerVO paramVO) throws Exception;

	Integer selectServerListTotCnt(ServerVO paramVO) throws Exception;

}
