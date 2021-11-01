package com.bjworld.groupware.common.web;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.imgscalr.Scalr;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.SpringProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.FileVO;
import com.bjworld.groupware.common.vo.SessionVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;

@Controller
@PropertySource("classpath:/egovProps/globals-${spring.profiles.active}.properties")
public class CommonController {

	Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Value("${Globals.fileStorePath}")
	private String attachFileSavePath;
	
	@RequestMapping(value = "/sitemap/map.do")
	public String siteMap()
	{
		return "sitemap/map";
	}
	
	@RequestMapping(value="/admin/dragDropUploadAjax.do")
	@ResponseBody
	public Object dragDropUpload(@RequestParam("upload") MultipartFile file, HttpServletRequest req) throws Exception
	{
		HashMap<String, Object> map = new HashMap<>();
		OutputStream out = null;
        try {
        	String fileName = file.getOriginalFilename();
        	String extension = fileName.substring(fileName.lastIndexOf("."));
			byte[] bytes = file.getBytes();
			String uploadPath = attachFileSavePath;
			String type = req.getParameter("type");
			
			ProjectUtility.fileExtensionCheck(extension, file);
			
			if(!StringUtils.isEmpty(type))
			{
				uploadPath += File.separator + req.getParameter("type");
				
				if(type.equals("projectcompanyfile")) {
					String spSeq  = req.getParameter("spseq");
					uploadPath += File.separator + spSeq;
				}
			}
			
			File uploadFile = new File(uploadPath);
			if(!uploadFile.exists()){
				uploadFile.mkdirs();
			}
           
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + extension;
            
			uploadPath = uploadPath + File.separator + fileName;
			out = new FileOutputStream(new File(uploadPath));
            out.write(bytes);
            out.flush();
            out.close();
            out = null;
         
            map.put("uploaded", 1);
            if(!StringUtils.isEmpty(type)) {
            	map.put("url", req.getContextPath() + "/upload/" + type + "/" + fileName);
            }
            else
            	map.put("url", req.getContextPath() + "/upload/" + fileName);
            map.put("fileName", file.getOriginalFilename());
            map.put("saveFileName", fileName);
            map.put("filesize", String.valueOf(file.getSize()));

            return map;
        } catch (Exception e) {
            map.put("uploaded", 0);
            map.put("error", e.getMessage());
            return map;
        }     
        finally
        {
        	if(out != null){
                out.close();
            }
        }
	}
	
	@RequestMapping(value="/common/ckEditorImageUpload.do")
	public String fileUpload(HttpServletRequest req, HttpServletResponse resp, 
                 MultipartHttpServletRequest multiFile) throws Exception {
		PrintWriter printWriter = null;
		OutputStream out = null;
		MultipartFile file = multiFile.getFile("upload");
		if(file != null){
			if(file.getSize() > 0 && !EgovStringUtil.isEmpty(file.getName())){
				if(file.getContentType().toLowerCase().startsWith("image/")){
					try{
						String fileName = file.getOriginalFilename();
						String extension = fileName.substring(fileName.lastIndexOf("."));
						byte[] bytes = file.getBytes();
						String uploadPath = attachFileSavePath + File.separator + SystemConstant.UPLOAD_PATH_BOARD;
						File uploadFile = new File(uploadPath);
						if(!uploadFile.exists()){
							uploadFile.mkdirs();
						}
						
						String callback ="CKEditorFuncNum"; //req.getParameter("CKEditorFuncNum");
						
						fileName = UUID.randomUUID().toString() + extension;
						uploadPath = uploadPath + File.separator + fileName;
						out = new FileOutputStream(new File(uploadPath));
                        out.write(bytes);
                        
                        printWriter = resp.getWriter();
                        resp.setContentType("text/html");
                        String fileUrl = req.getContextPath() + "/upload/board/" + fileName;
                        
                        printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
                                + callback
                                + ",'"
                                + fileUrl
                                + "'"
                                + ")</script>");
                        printWriter.flush();
                    }catch(IOException e){
                        EgovBasicLogger.info(e.getMessage());
                    }finally{
                        if(out != null){
                            out.close();
                        }
                        if(printWriter != null){
                            printWriter.close();
                        }		
					}
				}
			}
		}
		return null;
	}	
	
	@RequestMapping("/tilestest.do")
	public String tilestest()
	{
		return "tilestest/test.tiles";
	}		
	
	private boolean makeThumbnail(String path, String filePath, String fileName, String fileExt, int width) throws IOException {

		// 원본 이미지 입니다.
		BufferedImage srcImg = ImageIO.read(new File(filePath));

		// 리사이즈(썸네일 생성)
		BufferedImage destImg = Scalr.resize(srcImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, width);

		// 이미지 출력
		String thumbName = path + File.separator + fileName + fileExt;
		File thumbFile = new File(thumbName);
		return ImageIO.write(destImg, fileExt.replace(".",  ""), thumbFile);
	}
	
	private String requestReplace (String paramValue, String gubun) {

        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
        		paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
  }
}
