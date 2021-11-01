package com.bjworld.groupware.eventnotice.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.eventnotice.service.EventNoticeService;
import com.bjworld.groupware.eventnotice.service.impl.EventNoticeVO;
import com.bjworld.groupware.eventnoticeattachfile.service.EventNoticeAttachFileService;
import com.bjworld.groupware.eventnoticeattachfile.service.impl.EventNoticeAttachFileVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.File;
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class EventNoticeController {

	Logger logger = LoggerFactory.getLogger(EventNoticeController.class);

	@Value("${Globals.fileStorePath}")
	private String attachFileSavePath;

	@Resource(name = "eventnoticeService")
	private EventNoticeService eventnoticeService;

	@Resource(name = "eventnoticeattachfileService")
	private EventNoticeAttachFileService eventnoticeattachfileService;

	@RequestMapping("/eventnotice.do")
	public String eventnotice(HttpServletRequest request, Model model) throws Exception {
		return "eventnotice/eventnotice.at";
	}

	@RequestMapping(value = "/getEventNoticeListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getEventNoticeListAjax(HttpServletRequest request, EventNoticeVO paramVO)
			throws Exception {

		List<?> dataList = eventnoticeService.selectEventNoticeList(paramVO);
		// Total Count
		Integer total = eventnoticeService.selectEventNoticeListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("draw", paramVO.getDraw());
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}

	@RequestMapping(value = "/mergeEventNoticeAjax.do")
	@ResponseBody
	public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request, EventNoticeVO paramVO)
			throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {

			// SessionVO sessionVO = SessionUtils.getCurrentUserSession();
			// paramVO.setRegLoginId(sessionVO.getLoginId());

			if (StringUtils.isEmpty(paramVO.getSeq()))
				paramVO.setSeq(null);

			eventnoticeService.saveEventNotice(paramVO);

			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("데이터를 저장하였습니다.");

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "데이터를 저장 중"));
		}

		return result;
	}

	@RequestMapping(value = "/selectEventNoticeAjax.do")
	@ResponseBody
	public AjaxResult<EventNoticeVO> selectEventNoticeAjax(HttpServletRequest request, EventNoticeVO paramVO)
			throws Exception {

		AjaxResult<EventNoticeVO> result = new AjaxResult<>();

		try {
			EventNoticeVO viewVO = eventnoticeService.selectEventNotice(paramVO);

			if (viewVO != null) {
				EventNoticeAttachFileVO fileParamVO = new EventNoticeAttachFileVO();
				fileParamVO.setEventSeq(viewVO.getSeq());

				List<EventNoticeAttachFileVO> listAttachFile = eventnoticeattachfileService
						.selectEventNoticeAttachFileList(fileParamVO);
				viewVO.setListAttachFile(listAttachFile);
			}

			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData(viewVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 데이터를 불러 오는 중"));
		}

		return result;
	}

	@RequestMapping(value = "/deleteEventNoticeAjax.do")
	@ResponseBody
	public AjaxResult<String> deleteEventNoticeAjax(HttpServletRequest request, EventNoticeVO paramVO)
			throws Exception {

		AjaxResult<String> result = new AjaxResult<>();

		try {
			eventnoticeService.deleteEventNotice(paramVO);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData("");
			result.setMsg("데이터를 삭제하였습니다.");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 데이터를 삭제 하는 중"));
		}

		return result;
	}

	@RequestMapping(value = "/downloadeventnoticefile.do")
	@ResponseBody
	public void downloadeventnoticefile(HttpServletRequest request, HttpServletResponse response,
			EventNoticeAttachFileVO paramVO) throws Exception {

		try {
			EventNoticeAttachFileVO viewVO = eventnoticeattachfileService.selectEventNoticeAttachFile(paramVO);
			if (viewVO != null) {
				String saveFileName = viewVO.getEventSaveFilename();
				String uploadFolderPath = attachFileSavePath + File.separator + "eventnotice";
				EgovFileMngUtil.downFile(request, response, viewVO.getEventOriFilename(),
						uploadFolderPath + File.separator + saveFileName);
			} else
				throw new Exception("요청한 파일데이터가 존재하지 않습니다.");
		} catch (Exception ex) {
			try {
				ProjectUtility.writeResponseMessage(response,
						"<script>alert('다운로드 하려는 파일에 문제가 발생하였습니다.'); history.back(); </script>");
			} catch (Exception e) {
				EgovBasicLogger.info(e.getMessage());
			}
		}
	}

	@RequestMapping(value = "/deleteEventNoticeAttachFileAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteEventNoticeAttachFile(HttpServletRequest request
            , EventNoticeAttachFileVO paramVO) throws Exception{
        
        AjaxResult<String> result = new AjaxResult<>();

        try
        {
        	EventNoticeAttachFileVO viewFile =  eventnoticeattachfileService.selectEventNoticeAttachFile(paramVO);
        	
        	if(viewFile != null) {
        		String saveFileName = viewFile.getEventSaveFilename();
        		if(!EgovStringUtil.isEmpty(saveFileName)) {
	        		String saveFullPath = attachFileSavePath + File.separator + "eventnotice" + File.separator + saveFileName;
	        		
	        		try {
	        			File f = new File(saveFullPath);
	        			f.delete();
	        		}
	        		catch(Exception ex) {
	        			EgovBasicLogger.info(ex.getMessage());
	        		}
        		}
        	}
        	
        	eventnoticeattachfileService.deleteEventNoticeAttachFile(paramVO);
        	result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("첨부파일을 삭제하였습니다.");
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 첨부파일을 삭제 하는 중"));
        }
        
        return result;
    }
}
