package com.bjworld.groupware.projectattach.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("projectattachMapper")
public interface ProjectAttachMapper {

	void insertProjectAttach(ProjectAttachVO projectAttachVO);

	List<ProjectAttachVO> selectprojectattachtlist(ProjectAttachVO VO);

	void deleteprojectattach(ProjectAttachVO paramVO);

	ProjectAttachVO selectprojectattach(ProjectAttachVO paramVO);

}
