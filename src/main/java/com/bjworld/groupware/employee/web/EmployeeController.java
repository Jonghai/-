package com.bjworld.groupware.employee.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.employee.service.EmployeeService;
import com.bjworld.groupware.employee.service.impl.EmployeeVO;
import com.bjworld.groupware.organization.service.OrganizationService;
import com.bjworld.groupware.organization.service.impl.OrganizationVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Resource(name="employeeService")
    private EmployeeService employeeService;
    
    @Resource(name="organizationService")
    private OrganizationService organizationService;

    @RequestMapping("/employee.do")
    public String getEmployeeList(HttpServletRequest request
            , Model model) throws Exception{
    	List<OrganizationVO> listOrganization = organizationService.selectOrganizationList(null);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String listOrganizationJSON = mapper.writeValueAsString(listOrganization);
    	
    	model.addAttribute("listOrganizationJSON", listOrganizationJSON);
    	
        return "employee/employee.at2";
    }

    @RequestMapping(value = "/getEmployeeListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getEmployeeListAjax(HttpServletRequest request
            , EmployeeVO paramVO) throws Exception{

    	List<?> dataList = employeeService.selectEmployeeList(paramVO);
		// Total Count
		Integer total = employeeService.selectEmployeeListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeEmployeeAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request
            , EmployeeVO paramVO) throws Exception{
    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            //SessionVO sessionVO =  SessionUtils.getCurrentUserSession();        	
        	//paramVO.setRegLoginId(sessionVO.getLoginId());

            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

        	employeeService.mergeEmployee(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("직원 정보를 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "직원 정보를 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/selectEmployeeAjax.do")
    @ResponseBody
    public AjaxResult<EmployeeVO> selectEmployeeAjax(HttpServletRequest request
            , EmployeeVO paramVO) throws Exception{

    	AjaxResult<EmployeeVO> result = new AjaxResult<>();

        try
        {
        	EmployeeVO viewVO =  employeeService.selectEmployee(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 직원 정보를 불러 오는 중"));
        }
        
        return result;
    }

    @RequestMapping(value = "/deleteEmployeeAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteEmployeeAjax(HttpServletRequest request
            , EmployeeVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	employeeService.deleteEmployee(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("직원 정보를 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 직원 정보를 삭제 하는 중"));
        }
        
        return result;
    }
    
    @RequestMapping(value = "/reorderEmployeeAjax.do")
    @ResponseBody
    public AjaxResult<String> reorderEmployeeAjax(HttpServletRequest request
            , String employeeSortInfoJSON) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	ObjectMapper mapper = new ObjectMapper();
        	List<EmployeeVO> listEmployee = mapper.readValue(employeeSortInfoJSON, new TypeReference<List<EmployeeVO>>() {});
        	
        	for (EmployeeVO employeeItem: listEmployee) {
				employeeService.updateEmployeeSort(employeeItem);
			}
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("직원 정보를 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 직원 정보를 삭제 하는 중"));
        }
        
        return result;
    }
    
}
