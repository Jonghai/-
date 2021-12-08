package com.bjworld.groupware.inquiry.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.customer.service.CustomerService;
import com.bjworld.groupware.customer.service.impl.CustomerVO;
import com.bjworld.groupware.inquiry.service.InquiryService;
import com.bjworld.groupware.inquiry.service.impl.InquiryVO;
import com.bjworld.groupware.inquiry.web.InquiryController;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;


@Controller
@RequestMapping("/admin")
public class InquiryController {
	Logger logger = LoggerFactory.getLogger(InquiryController.class);
	
	@Resource(name = "inquiryService")
	private InquiryService inquiryService;
	
	@Resource(name = "customerService")
	private CustomerService customerService;
	

	@RequestMapping("/inquiry.do")
	 public String inquiry(HttpServletRequest request
	            , Model model) throws Exception{
		List<?> getCsList = customerService.selectCustomerList();
		model.addAttribute("getCsList", getCsList);
	        return "inquiry/inquiry.at";
	    }

	@RequestMapping(value = "/getInquiryListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getInquiryListAjax(HttpServletRequest request, InquiryVO paramVO)
			throws Exception {
System.out.println(paramVO);
		// 테이블에 바인딩 할 데이터
		List<?> dataList = inquiryService.selectInquiryList(paramVO);
		// Total Count
		Integer total = inquiryService.selectInquiryListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}
	
    @RequestMapping(value = "/getInquiryTreeAjax.do")
    @ResponseBody
    public JSONArray getInquiryTree(HttpServletRequest request
            , CustomerVO paramVO) throws Exception
    {
    	
    	List<CustomerVO> listCustomer = customerService.selectCustomerList(paramVO);
    	
    	CustomerVO rootVO = new CustomerVO();
    	rootVO.setSeq("0");
    	rootVO.setCustomerName("고객사");
    	
    	JSONArray treeArray = new JSONArray();
    	initTreeData(treeArray, listCustomer, rootVO);
    	
    	return treeArray;
    }

    @SuppressWarnings("unchecked")
 	private void initTreeData(JSONArray treeArray, List<CustomerVO> originListData, CustomerVO parentNode)
     {	
     	JSONObject treeNodeObject = new JSONObject();
     	treeNodeObject.put("key", parentNode.getSeq());
     	treeNodeObject.put("title", parentNode.getCustomerName());
     	treeNodeObject.put("expanded", parentNode.getSeq().equals("0") ? true : false);
     	treeNodeObject.put("folder", true);
     	
     	
     	if(originListData.size() > 0)
     	{
     		JSONArray childArray = new JSONArray();
 			
     		for(CustomerVO childNodeItemVO : originListData)
     		{
     			childrenArray(childArray,childNodeItemVO);
     		}
     		treeNodeObject.put("children", childArray);
     	}
     		
     	treeArray.add(treeNodeObject);    
     }

    
	@SuppressWarnings("unchecked")
	private void childrenArray(JSONArray childArray, CustomerVO childNodeItemVO) {
		 JSONObject treeNodeObject = new JSONObject();
	     treeNodeObject.put("key", childNodeItemVO.getSeq());
	     treeNodeObject.put("title", childNodeItemVO.getCustomerName());
	     treeNodeObject.put("expanded", childNodeItemVO.getSeq().equals("0") ? true : false);
	     treeNodeObject.put("folder", false);
	     
	     childArray.add(treeNodeObject);
	}
    
	@RequestMapping(value = "/mergeInquiryAjax.do")
	@ResponseBody
	public AjaxResult<String>  mergeBuyerCompanyAjax(HttpServletRequest request, InquiryVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {
        		
            if(EgovStringUtil.isEmpty(paramVO.getSeq())){
            	//seq 가 공백이면 insert
            	inquiryService.mergeInquiry(paramVO);
            }
            else {
            	//seq 가 공백이 아니면 update
            	inquiryService.updateInquiry(paramVO);
            	
            }
            
            // merge 방식
            
        	
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("문의를 저장하였습니다.");


		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "문의 저장 중"));
		}

		return result;
	}

	
	@RequestMapping(value = "/selectInquiryAjax.do")
	@ResponseBody
	public AjaxResult<InquiryVO> selectInquiryAjax(HttpServletRequest request, InquiryVO paramVO) throws Exception {

		AjaxResult<InquiryVO> result = new AjaxResult<>();
		try {

			// select 방식
			// inquiryService.selectInquiry(paramVO);
			InquiryVO viewVO =  inquiryService.selectInquiry(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "문의 불러오는 중"));
		}

		return result;
	}
	
	 @RequestMapping(value = "/deleteInquiryAjax.do")
	    @ResponseBody
	    public AjaxResult<String> deleteInquiryAjax(HttpServletRequest request
	            , InquiryVO paramVO) throws Exception{
	        
	        AjaxResult<String> result = new AjaxResult<>();

	        try
	        {
	        	inquiryService.deleteInquiry(paramVO);
	        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
	        	result.setData("");
	        	result.setMsg("문의가 삭제하였습니다.");
	        }
	        catch(Exception e) {
	            logger.error(e.getMessage());
	        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
	        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 문의 삭제 하는 중"));
	        }
	        
	        return result;
	    }
	 
	
}
