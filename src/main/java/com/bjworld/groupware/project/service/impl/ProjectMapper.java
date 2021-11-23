package com.bjworld.groupware.project.service.impl;

import java.util.List;

import com.bjworld.groupware.project.service.impl.ProjectVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("projectMapper")
public interface ProjectMapper {
	void mergeProject(ProjectVO paramVO);
	
	ProjectVO selectProject(ProjectVO paramVO) throws Exception;
	
	void updateProject(ProjectVO paramVO) throws Exception;
	
	List<ProjectVO> selectProjectList(ProjectVO paramVO) throws Exception;
	
	Integer selectProjectListTotCnt(ProjectVO paramVO) throws Exception;

	
	void deleteProject(ProjectVO paramVO) throws Exception;

}
