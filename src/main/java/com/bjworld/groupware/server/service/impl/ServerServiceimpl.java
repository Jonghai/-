package com.bjworld.groupware.server.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.bjworld.groupware.server.service.ServerService;
import com.bjworld.groupware.server.service.impl.ServerMapper;
import com.bjworld.groupware.server.service.impl.ServerVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("serverService")
public class ServerServiceimpl extends EgovAbstractServiceImpl implements ServerService {
	@Resource(name = "serverMapper")
	private ServerMapper serverMapper;

	@Override
	public void mergeServer(ServerVO paramVO) {
		serverMapper.mergeServer(paramVO);
	}
	
	public ServerVO selectServer(ServerVO paramVO) throws Exception {
        return serverMapper.selectServer(paramVO);
    }

	@Override
	public List<ServerVO> selectServerList(ServerVO paramVO) throws Exception {		
		return serverMapper.selectServerList(paramVO);
	}

	@Override
	public void deleteServer(ServerVO paramVO) throws Exception {
		serverMapper.deleteServer(paramVO);		
	}
	
	@Override
	public void updateServer(ServerVO paramVO) throws Exception {
		serverMapper.updateServer(paramVO);		
	}
	
	 @Override
	    public Integer selectServerListTotCnt(ServerVO paramVO) throws Exception {
	        return serverMapper.selectServerListTotCnt(paramVO);
	    }
}
