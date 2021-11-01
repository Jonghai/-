package com.bjworld.groupware.company.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovDateUtil;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.EgovFileScrty;
import com.bjworld.groupware.common.util.EgovNumberUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.commoncode.service.CommonCodeService;
import com.bjworld.groupware.commoncode.service.impl.CommonCodeVO;
import com.bjworld.groupware.company.service.CompanyService;
import com.bjworld.groupware.company.service.impl.CompanyVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class CompanyController {

    Logger logger = LoggerFactory.getLogger(CompanyController.class);
    
    @Value("${Globals.fileStorePath}")
	private String attachFileSavePath;
    
    @Resource(name="companyService")
    private CompanyService companyService;
    
    @Resource(name="commoncodeService")
    private CommonCodeService commonCodeService;
  
    @Resource(name="commoncodeService")
    private CommonCodeService commoncodeService;
    
    
    @RequestMapping("/company.do")
    public String company(HttpServletRequest request
            , Model model
            , CompanyVO paramVO) throws Exception{
    	
        return "company/company.at";
    }
    
    @RequestMapping(value = "/getCompanyListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getCompanyListAjax(HttpServletRequest request
            , CompanyVO paramVO) throws Exception{
    	
    	List<?> dataList = companyService.selectCompanyList(paramVO);
		// Total Count
		Integer total = companyService.selectCompanyListTotCnt(paramVO);
		
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", dataList);
    	return listMap;
    }

    @RequestMapping(value = "/mergeCompanyAjax.do")
    @ResponseBody
    public AjaxResult<String> mergeCompanyAjax(HttpServletRequest request
            , CompanyVO paramVO) throws Exception{
    	    	
        AjaxResult<String> result = new AjaxResult<>();
        try {
        	
            if(StringUtils.isEmpty(paramVO.getSeq()))
        		paramVO.setSeq(null);

            if(!EgovStringUtil.isEmpty(paramVO.getCompanyPwd()))
        		paramVO.setCompanyPwd(EgovFileScrty.encryptPassword(paramVO.getCompanyPwd(), paramVO.getCompanyBusinessNumber()));
            
        	companyService.saveCompany(paramVO);
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("기업정보를 저장하였습니다.");
        	
		} catch (Exception e) {
            logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "기업정보를 저장 중"));
		}
        
        return result;
    }
    
    @RequestMapping(value = "/selectCompanyAjax.do")
    @ResponseBody
    public AjaxResult<CompanyVO> selectCompanyAjax(HttpServletRequest request
            , CompanyVO vo) throws Exception{

    	AjaxResult<CompanyVO> result = new AjaxResult<>();

        try
        {
        	CompanyVO viewVO =  companyService.selectCompany(vo);
        	
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 기업정보를 불러 오는 중"));
        }
        
        return result;
    }  
    
    @RequestMapping(value = "/deleteCompanyAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteCompanyAjax(HttpServletRequest request
            , CompanyVO vo) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	companyService.deleteCompany(vo);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("기업정보를 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 기업정보를 삭제 하는 중"));
        }
        
        return result;
    }
    
    @RequestMapping(value = "/checkBusinessNumberAjax.do")
    @ResponseBody
    public AjaxResult<String> checkBusinessNumber(HttpServletRequest request
            , CompanyVO vo) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	String seq = companyService.selectCompanyByBusinessNumber(vo.getCompanyBusinessNumber());
        	
        	if(EgovStringUtil.isEmpty(seq)) {
        		result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("1");
	        	result.setMsg("해당 사업자 번호로 등록된 기업정보가 존재하지 않습니다.");
        	}
        	else
        	{
	        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("2");
	        	result.setMsg("해당 사업자 번호로 등록된 기업정보가 이미 존재합니다.");
        	}
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 기업 정보를 조회 하는 중"));
        }
        
        return result;
    }
    
    @RequestMapping(value = "/initCompanyPasswordAjax.do")
    @ResponseBody
    public AjaxResult<String> initCompanyPassword(HttpServletRequest request
            , CompanyVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	String newpassword = "";
        	for (int i = 1; i <= 6; i++) {
     		   // 영자
     		   if (i % 3 != 0) {
     		      newpassword += EgovStringUtil.getRandomStr('a', 'z');
     		   // 숫자
     		   } else {
     		      newpassword += EgovNumberUtil.getRandomNum(0, 9);
     		   }
     		}
     		
        	newpassword += "!#";
     		paramVO.setCompanyPwd(EgovFileScrty.encryptPassword(newpassword, paramVO.getCompanyBusinessNumber().replaceAll("-","")));
     		
        	companyService.updateCompanyPassword(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(newpassword);
        	result.setMsg("기업 비밀번호를 초기화 화였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 기업 비밀번호를 초기화 하는 중"));
        }
        
        return result;
    }
    
    @RequestMapping(value = "/companyExceldownload.do")
    public void downloadCompanyExcel(HttpServletRequest request, HttpServletResponse response, String selectedColumn, CompanyVO paramVO) throws Exception {
    	
    	String [] splits = selectedColumn.split(",");

    	try {
    		companyService.selectExcelCompany(paramVO, request, response, splits);
		} catch (Exception ex) {
			try
			{
				String responseMessage = "<script>alert('엑셀 파일 생성 중 오류가 발생하였습니다.'); history.back(); </script>";
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.write(responseMessage);
				out.flush();
				out.close();
			}
			catch(Exception e)
			{
				EgovBasicLogger.info(e.getMessage());
			}
		}
    }
    
    @RequestMapping(value = "/getHomeTaxCompanyStatusAjax.do")
    @ResponseBody
    public AjaxResult<String> getHomeTaxCompanyStatus(HttpServletRequest request
            , CompanyVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	String searchResult = ProjectUtility.getHomeTaxCompanyStatus(paramVO.getCompanyBusinessNumber());
        	
        	paramVO.setHomeTaxStatus(searchResult);
        	companyService.updateCompanyAtHomeTaxStatus(paramVO);
        	
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(searchResult);
        	result.setMsg("홈텍스 사업자 등록상태 조회를 완료하였습니다.");
        }
        catch(RuntimeException re) {
            logger.error(re.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(re.getMessage());
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 홈텍스 사업자 등록상태를 조회 하는 중"));
        }
        
        return result;
    }
   
}
