package com.bjworld.groupware.board.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjworld.groupware.adminuser.service.impl.AdminUserVO;
import com.bjworld.groupware.board.service.BoardService;
import com.bjworld.groupware.board.service.impl.BoardVO;
import com.bjworld.groupware.boardattach.service.BoardAttachService;
import com.bjworld.groupware.boardattach.service.impl.BoardAttachVO;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.exception.ProjectException;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.menu.service.MenuService;
import com.bjworld.groupware.menu.service.impl.MenuVO;

@Controller
@RequestMapping("/admin")
public class BoardController {
	Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Value("${Globals.fileStorePath}")
	private String attachFileSavePath;
    
    @Resource(name="boardService")
    private BoardService boardService;
    
    @Resource(name="boardattachService")
    private BoardAttachService boardattachService;
    
    @Resource(name="menuService")
    private MenuService menuService;
    
    @RequestMapping("/board.do")
	public String board(HttpServletRequest request, BoardVO paramVO, Model m) throws Exception{
    	if(EgovStringUtil.isEmpty(paramVO.getBoardCode())) {
    		
    		return "";
    	}
    	
    	MenuVO menuParamVO = new MenuVO();
    	menuParamVO.setMenuCode(paramVO.getBoardCode());
    	MenuVO menuViewVO = menuService.selectMenuByCode(menuParamVO);
    	if(menuViewVO == null) {
    		return "";
    	}
    	
    	m.addAttribute("menuViewVO", menuViewVO);
		return "board/board.at";
    }
    
    @RequestMapping("/getBoardListAjax.do")
    @ResponseBody
    public HashMap<String, Object> getBoardListAjax(HttpServletRequest request, BoardVO paramVO) throws Exception {
    	List<?> datalist = boardService.selectBoardList(paramVO);
    	
    	Integer total = boardService.selectBoardListTotCnt(paramVO);
    	
    	HashMap<String, Object> listMap = new HashMap<String, Object>();
    	
    	listMap.put("draw", paramVO.getDraw());
    	listMap.put("recordsTotal", total);
    	listMap.put("recordsFiltered", total);
    	listMap.put("data", datalist);
    	
    	return listMap;
    }
    
    @RequestMapping("/mergeBoardAjax.do")
    @ResponseBody
    public AjaxResult<String> mergetBoardAjax(HttpServletRequest request, BoardVO paramVO) throws Exception {
    	
    	AjaxResult<String> result = new AjaxResult<String>();
    	
    	try {
			AdminUserVO sessionVO = ProjectUtility.getSessionAdminUser();
			//paramVO.setRegUserId(sessionVO.getUserId());
			paramVO.setRegUserId("admin");
			paramVO.setRegType("1");
			paramVO.setRegRemoteIP(ProjectUtility.getRemoteIp(request));
			
			MenuVO menuParamVO = new MenuVO();
        	menuParamVO.setMenuCode(paramVO.getBoardCode());
        	MenuVO menuViewVO = menuService.selectMenuByCode(menuParamVO);
        	
        	 
            if(menuViewVO.getMenuType().equals("3")) {
             	//이미지 게시판
             	String imgSrc = ProjectUtility.getImgSrc(EgovStringUtil.getHtmlStrCnvr(paramVO.getBoardContent()));
             	if(EgovStringUtil.isEmpty(imgSrc)) {
             		throw new ProjectException("이미지를 등록해 주세요.", 0);
             	}
             	paramVO.setThumbnailAddress(imgSrc);
            }
            else if(menuViewVO.getMenuType().equals("5")) {
            	//유튜브 동영상 게시판
            	String link = paramVO.getBoardTitleLink();
            	if(EgovStringUtil.isEmpty(link)) {
             		throw new ProjectException("유튜브 링크를 등록해 주세요.", 0);
             	}
            	if(!EgovStringUtil.isEmpty(link)) {
            		String movieCode = link.substring(link.lastIndexOf("/"));
            		paramVO.setThumbnailAddress("https://i.ytimg.com/vi"+movieCode+"/hq720.jpg");
            	}
            	
            	paramVO.setBoardContent("");
            }
            else if(menuViewVO.getMenuType().equals("4")) {
            	
            	if(paramVO.getAttachSaveFileName() == null || paramVO.getAttachSaveFileName().length  == 0) {
            		throw new ProjectException("PDF 파일을 등록해 주세요.", 1);
            	}
            	
            	String attachFullPath = attachFileSavePath + File.separator + SystemConstant.UPLOAD_PATH_BOARD + File.separator + paramVO.getAttachSaveFileName()[0];
            	
            	File file = new File(attachFullPath);
            	
            	try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){ 
            	
	            	List<String> listSaveFileName = ProjectUtility.conversionPdf2Img(bis, attachFileSavePath + File.separator + SystemConstant.UPLOAD_PATH_BOARD);
	            	if(listSaveFileName.size() > 0) {
	            		paramVO.setThumbnailAddress("/upload/" + SystemConstant.UPLOAD_PATH_BOARD + "/" + listSaveFileName.get(0));
	            	}
	            	
	            	for (int i = 0; i < listSaveFileName.size(); i++) {
						if(i == 0)
							continue;
						
						try {
							File deleteFile = new File(attachFileSavePath + File.separator + SystemConstant.UPLOAD_PATH_BOARD + File.separator + listSaveFileName.get(i));
							deleteFile.delete();
						}
						catch(Exception ex) {
							EgovBasicLogger.info(ex.getMessage());
						}
					}
            	}
            	catch(Exception ex) {
            		logger.info(ex.getMessage());
            		throw new ProjectException("처리중 오류가 발생하였습니다.", 1);
            	}
            	
            	paramVO.setBoardContent("");
            	
            	if(!EgovStringUtil.isEmpty(paramVO.getSeq())) {
	            	BoardAttachVO boardAttachParamVO = new BoardAttachVO();
	            	boardAttachParamVO.setBoardCode(paramVO.getBoardCode());
	            	boardAttachParamVO.setBoardSeq(paramVO.getSeq());
	            	boardattachService.deleteBoardAttachByBoardSeq(boardAttachParamVO);
            	}
            }
            
			if(EgovStringUtil.isEmpty(paramVO.getSeq()))
				paramVO.setSeq(null);
			
			boardService.saveBoard(paramVO);
			
			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("게시글을 저장하였습니다.");
		} 
    	catch (ProjectException pe) {
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(pe.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "게시글 저장 중"));
		}
    	
    	return result;
    }
    
    @RequestMapping("/selectBoardAjax.do")
    @ResponseBody
    public AjaxResult<BoardVO> selectBoardAjax(HttpServletRequest request, BoardVO paramVO) throws Exception {
    	AjaxResult<BoardVO> result = new AjaxResult<>();
    	
    	try {
			BoardVO viewVO = boardService.selectBoard(paramVO);
			viewVO.setBoardContent(EgovStringUtil.getHtmlStrCnvr(viewVO.getBoardContent()));
			
			BoardAttachVO attachParamVO = new BoardAttachVO();
			attachParamVO.setBoardSeq(viewVO.getSeq());
			attachParamVO.setBoardCode(paramVO.getBoardCode());
			List<BoardAttachVO> listBoardAttach = boardattachService.selectBoardAttachList(attachParamVO);
			
			viewVO.setListBoardAttach(listBoardAttach);
			
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData(viewVO);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 게시글을 불러 오는 중"));
		}
    	
    	return result;
    }
    
    @RequestMapping("/deleteBoardAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteBoardAjax(HttpServletRequest request, BoardVO paramVO) throws Exception {
    	AjaxResult<String> result = new AjaxResult<String>();
    	
    	try {
			BoardAttachVO attachParamVO = new BoardAttachVO();
			attachParamVO.setBoardSeq(paramVO.getSeq());
			attachParamVO.setBoardCode(paramVO.getBoardCode());
			
			List<BoardAttachVO> listBoardAttach = boardattachService.selectBoardAttachList(attachParamVO);
			
			boardService.deleteBoard(paramVO);
			
			String uploadFolderPath = attachFileSavePath + File.separator + "board";
			for(BoardAttachVO attachItemVO : listBoardAttach) {
        		try {
        			File f = new File(uploadFolderPath + File.separator + attachItemVO.getSaveFileName());
        			f.delete();
        		}
        		catch(Exception ex) {
        		}
        	}
			
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
        	result.setData("");
        	result.setMsg("게시글을 삭제하였습니다.");
		} catch (Exception e) {
			logger.error(e.getMessage());
        	result.setIsSuccess(SystemConstant.AJAX_FAIL);
        	result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 게시글을 삭제 하는 중"));
		}
    	
    	return result;
    }
    
    @RequestMapping(value = "/downloadboardfile.do")
    @ResponseBody
    public void downloadboardfile(HttpServletRequest request, HttpServletResponse response
            , BoardAttachVO paramVO) throws Exception{
        
    	try {
    		BoardAttachVO viewVO = boardattachService.selectBoardAttach(paramVO);
    		if(viewVO != null) {
				String saveFileName = viewVO.getSaveFileName();
				String uploadFolderPath = attachFileSavePath + File.separator + "board";
				EgovFileMngUtil.downFile(request, response, viewVO.getOriFileName(), uploadFolderPath + File.separator + saveFileName);
    		}
    		else
    			throw new Exception("요청한 파일데이터가 존재하지 않습니다.");
    	}
    	catch(Exception ex) {
    		try
			{
				ProjectUtility.writeResponseMessage(response, "<script>alert('다운로드 하려는 파일에 문제가 발생하였습니다.'); history.back(); </script>");
			}
			catch(Exception e)
			{
				EgovBasicLogger.info(e.getMessage());
			}
    	}
    }
    
  
    @RequestMapping("/deleteBoardAttachFileAjax.do")
    @ResponseBody
    public AjaxResult<String> deleteAdminBoardAttachFileAjax(HttpServletRequest request, BoardAttachVO paramVO) throws Exception {
		AjaxResult<String> result = new AjaxResult<>();

		try {
			paramVO.setBoardCode(paramVO.getBoardCode());
			BoardAttachVO boardAttachVO = boardattachService.selectBoardAttach(paramVO);
			if(boardAttachVO != null) {
				try {
					File f = new File(attachFileSavePath + File.separator + "board" + File.separator + boardAttachVO.getSaveFileName());
					f.delete();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			boardattachService.deleteBoardAttach(paramVO);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData("");
			result.setMsg("데이터를 삭제하였습니다.");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "데이터를 삭제 하는 중"));
		}
		
		return result;
	}
}
