package com.bjworld.groupware.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.property.EgovPropertyService;

public class FileUtil {

	public static UploadFileVO uploadFile(HttpServletRequest request) {
		UploadFileVO fileVO = null;

		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iterator = multipartRequest.getFileNames();

			while (iterator.hasNext()) {
				MultipartFile multipartFile = multipartRequest.getFile(iterator.next());

				if (multipartFile == null)
					continue;

				String originalFileName = multipartFile.getOriginalFilename();
				String saveFileName = multipartFile.getOriginalFilename(); 
				

				String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				
				saveFileName = saveFileName.replace(originalFileExtension, "");
				String checkFileName = saveFileName;

				byte[] data = multipartFile.getBytes();
				String uploadFolderPath = request.getSession().getServletContext().getRealPath("/upload");

				int fileIdx = 0;

				while(true)
				{
					File f = new File(uploadFolderPath + "\\" + saveFileName + originalFileExtension);
					if(!f.exists())
						break;
					
					saveFileName = checkFileName + "_" + String.valueOf(++fileIdx);
				}

				try (FileOutputStream fos = new FileOutputStream(uploadFolderPath + "\\" + saveFileName + originalFileExtension)) {
					fos.write(data);
					fos.close();
				} catch (Exception ex) {
					EgovBasicLogger.info(ex.getMessage());
				}

				fileVO = new UploadFileVO();
				fileVO.setOriginalFileName(originalFileName);
				fileVO.setSaveFileName(saveFileName + originalFileExtension);
				fileVO.setSaveFullPath(uploadFolderPath + "\\" + saveFileName + originalFileExtension);
			}

		} catch (Exception ex) {
			EgovBasicLogger.info(ex.getMessage());
		}

		return fileVO;
	}
}
