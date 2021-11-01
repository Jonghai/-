package com.bjworld.groupware.userpage.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bjworld.groupware.board.service.BoardService;
import com.bjworld.groupware.board.service.impl.BoardVO;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.employee.service.EmployeeService;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;
import com.bjworld.groupware.organization.service.OrganizationService;
import com.bjworld.groupware.organization.service.impl.OrganizationVO;
import com.bjworld.groupware.relatedinstitution.service.RelatedInstitutionService;
import com.bjworld.groupware.relatedinstitution.service.impl.RelatedInstitutionVO;
import com.bjworld.groupware.sitecompanyhistory.service.SiteCompanyHistoryService;
import com.bjworld.groupware.sitecompanyhistory.service.impl.SiteCompanyHistoryVO;

@Controller
public class UserPageController {
	
	@Value("${Globals.fileStorePath}")
	private String attachFileSavePath;
	
	@RequestMapping("/main.do")
	public String main(Model model) throws Exception {
		return "main/main.ut";
	}
	
	@RequestMapping("/introduce/introduce.do")
	public String introduce() {
		
		return "introduce/introduce.ut";
	}
	
	@RequestMapping("/board/board.do")
	public String board() {
		
		return "board/board.ut";
	}
}