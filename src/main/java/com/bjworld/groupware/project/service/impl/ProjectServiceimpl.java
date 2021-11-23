package com.bjworld.groupware.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.project.service.ProjectService;
import com.bjworld.groupware.project.service.impl.ProjectMapper;
import com.bjworld.groupware.project.service.impl.ProjectVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("projectService")

public class ProjectServiceimpl extends EgovAbstractServiceImpl implements ProjectService {
	@Resource(name = "projectMapper")
	private ProjectMapper projectMapper;

	@Override
	public void mergeProject(ProjectVO paramVO) {
		projectMapper.mergeProject(paramVO);// TODO Auto-generated method stub
		
	}
	
	public ProjectVO selectProject(ProjectVO paramVO) throws Exception {
        return projectMapper.selectProject(paramVO);
    }


	@Override
	public List<ProjectVO> selectProjectList(ProjectVO paramVO) throws Exception {
		
		return projectMapper.selectProjectList(paramVO);
	}
	

	@Override
	public void deleteProject(ProjectVO paramVO) throws Exception {
		projectMapper.deleteProject(paramVO);
		
	}

	@Override
	public void updateProject(ProjectVO paramVO) throws Exception {
		projectMapper.updateProject(paramVO);
		
	}
	
	 @Override
	    public Integer selectProjectListTotCnt(ProjectVO paramVO) throws Exception {
	        return projectMapper.selectProjectListTotCnt(paramVO);
	    }

}
