package com.bjworld.groupware.project.service;

import java.util.List;

import com.bjworld.groupware.project.service.impl.ProjectVO;

public interface ProjectService {

	void mergeProject(ProjectVO paramVO);
	
	ProjectVO selectProject(ProjectVO paramVO) throws Exception;
	

	List<ProjectVO> selectProjectList(ProjectVO paramVO) throws Exception;

	void deleteProject(ProjectVO paramVO) throws Exception;


	void updateProject(ProjectVO paramVO) throws Exception;

	Integer selectProjectListTotCnt(ProjectVO paramVO) throws Exception;

}
