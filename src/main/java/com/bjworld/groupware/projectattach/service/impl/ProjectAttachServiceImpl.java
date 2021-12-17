package com.bjworld.groupware.projectattach.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bjworld.groupware.projectattach.service.ProjectAttachService;

@Service("projectAttachservice")
public class ProjectAttachServiceImpl implements ProjectAttachService{
	
	@Resource(name="projectattachMapper")
	private ProjectAttachMapper projectattachMapper;

	@Override
	public void insertProjectAttach(ProjectAttachVO projectAttachVO) {
		projectattachMapper.insertProjectAttach(projectAttachVO);
	}

	@Override
	public List<ProjectAttachVO> selectprojectattachtlist(ProjectAttachVO VO) {
		
		return projectattachMapper.selectprojectattachtlist(VO);
	}

	@Override
	public void deleteProjectAttach(ProjectAttachVO paramVO) {
		projectattachMapper.deleteprojectattach(paramVO);
	}

	@Override
	public ProjectAttachVO selectProjectAttach(ProjectAttachVO paramVO) {
		
		return projectattachMapper.selectprojectattach(paramVO);
	}

}
