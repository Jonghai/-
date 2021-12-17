package com.bjworld.groupware.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bjworld.groupware.project.service.ProjectService;
import com.bjworld.groupware.project.service.impl.ProjectMapper;
import com.bjworld.groupware.project.service.impl.ProjectVO;
import com.bjworld.groupware.projectattach.service.ProjectAttachService;
import com.bjworld.groupware.projectattach.service.impl.ProjectAttachVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("projectService")

public class ProjectServiceimpl extends EgovAbstractServiceImpl implements ProjectService {
	@Resource(name = "projectMapper")
	private ProjectMapper projectMapper;
	
	@Resource(name = "projectAttachservice")
	private ProjectAttachService projectAttachservice;

	@Override
	//@Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
	public void mergeProject(ProjectVO paramVO) {
		projectMapper.mergeProject(paramVO);// TODO Auto-generated method stub
		
		String ProjectSeq = paramVO.getSeq();
		
		if(!StringUtils.isEmpty(ProjectSeq)&&
				paramVO.getAttachOriFileName()!=null && 
				paramVO.getAttachOriFileName().length >0) {
			
			for(int i = 0; i<paramVO.getAttachOriFileName().length; i++) {
				ProjectAttachVO projectAttachVO = new ProjectAttachVO();
				
				projectAttachVO.setProjectSeq(ProjectSeq);
				projectAttachVO.setSaveFilename(paramVO.getAttachSaveFileName()[i]);
				projectAttachVO.setOriFilename(paramVO.getAttachOriFileName()[i]);
				
				projectAttachservice.insertProjectAttach(projectAttachVO);
			}
			
		}
		
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
		
		String ProjectSeq = paramVO.getSeq();
		
		if(!StringUtils.isEmpty(ProjectSeq)&&
				paramVO.getAttachOriFileName()!=null && 
				paramVO.getAttachOriFileName().length >0) {
			
			for(int i = 0; i<paramVO.getAttachOriFileName().length; i++) {
				ProjectAttachVO projectAttachVO = new ProjectAttachVO();
				
				projectAttachVO.setProjectSeq(ProjectSeq);
				projectAttachVO.setSaveFilename(paramVO.getAttachSaveFileName()[i]);
				projectAttachVO.setOriFilename(paramVO.getAttachOriFileName()[i]);
				
				projectAttachservice.insertProjectAttach(projectAttachVO);
			}
			
		}
		
	}
	
	 @Override
	    public Integer selectProjectListTotCnt(ProjectVO paramVO) throws Exception {
	        return projectMapper.selectProjectListTotCnt(paramVO);
	    }


}
