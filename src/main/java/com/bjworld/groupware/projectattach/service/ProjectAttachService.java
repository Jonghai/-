package com.bjworld.groupware.projectattach.service;

import java.util.List;

import com.bjworld.groupware.projectattach.service.impl.ProjectAttachVO;

public interface ProjectAttachService {

	void insertProjectAttach(ProjectAttachVO projectAttachVO);

	List<ProjectAttachVO> selectprojectattachtlist(ProjectAttachVO VO);

	void deleteProjectAttach(ProjectAttachVO paramVO);

	ProjectAttachVO selectProjectAttach(ProjectAttachVO paramVO);

}
