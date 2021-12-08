package com.bjworld.groupware.department.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.department.service.DepartmentService;
import com.bjworld.groupware.department.service.impl.DepartmentVO;

@Controller
@RequestMapping("/admin")
public class DepartmentController {
	Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	@RequestMapping("/department.do")
	public String department(HttpServletRequest request, Model model, DepartmentVO paramVO) throws Exception {
		paramVO.setParentSeq("0");
		List<?> dataList = departmentService.selectDepartmentList(paramVO);
		
		model.addAttribute("dataList", dataList);
		
		return "department/department.at";
	}

	@RequestMapping(value = "/getDepartmentListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getDepartmentListAjax(HttpServletRequest request, DepartmentVO paramVO)
			throws Exception {
		
		// 테이블에 바인딩 할 데이터
		List<?> dataList = departmentService.selectDepartmentList(paramVO);
		// Total Count
		Integer total = departmentService.selectDepartmentListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
		}
		
		
	

	@RequestMapping(value = "/mergeDepartmentAjax.do")
	@ResponseBody
	public AjaxResult<String> mergeDepartmentAjax(HttpServletRequest request, DepartmentVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {
			
            if(EgovStringUtil.isEmpty(paramVO.getSeq())){
            	//seq 가 공백이면 insert
            	departmentService.mergeDepartment(paramVO);
            }
            else {
            	//seq 가 공백이 아니면 update
            	departmentService.updateDepartment(paramVO);
            	
            }
			// merge 방식
			// adminuserService.mergeAdminUser(paramVO);
			
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("부서를 저장하였습니다.");

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "부서를 저장 중"));
		}

		return result;
	}
	@RequestMapping(value = "/selectDepartmentAjax.do")
	@ResponseBody
	public AjaxResult<DepartmentVO> selectDepartmentAjax(HttpServletRequest request, DepartmentVO paramVO) throws Exception {

		AjaxResult<DepartmentVO> result = new AjaxResult<>();
		try {

			DepartmentVO viewVO =  departmentService.selectDepartment(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "부서를 불러오는 중"));
		}

		return result;
	}
	
	 @RequestMapping(value = "/deleteDepartmentAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteDepartmentAjax(HttpServletRequest request
	            , DepartmentVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();

	        try
	        {
	        	departmentService.deleteDepartment(paramVO);
	        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("");
	        	result.setMsg("부서를 삭제하였습니다.");
	        }
	        catch(Exception e) {
	            logger.error(e.getMessage());
	        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
	        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "부서를 삭제하는 중"));
	        }
	        
	        return result;
	    }

}

