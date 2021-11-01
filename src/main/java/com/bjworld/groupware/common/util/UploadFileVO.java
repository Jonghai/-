package com.bjworld.groupware.common.util;

public class UploadFileVO {
	
	private String originalFileName;
	private String saveFileName;
	private String saveFullPath;
	private String fileSize;
	
	
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getSaveFullPath() {
		return saveFullPath;
	}
	public void setSaveFullPath(String saveFullPath) {
		this.saveFullPath = saveFullPath;
	}		
}
