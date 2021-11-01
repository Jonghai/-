package com.bjworld.groupware.commoncode.service.impl;

import com.bjworld.groupware.commoncode.service.impl.CommonCodeVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("commoncodeMapper")
public interface CommonCodeMapper{

	List<CommonCodeVO> selectCommonCodeList(CommonCodeVO paramVO) throws Exception;
	
	Integer selectCommonCodeListTotCnt(CommonCodeVO paramVO) throws Exception;
	
	void mergeCommonCode(CommonCodeVO paramVO) throws Exception;

    void insertCommonCode(CommonCodeVO paramVO) throws Exception;

    void updateCommonCode(CommonCodeVO paramVO) throws Exception;
	
	CommonCodeVO selectCommonCode(CommonCodeVO paramVO) throws Exception;
	
	void deleteCommonCode(CommonCodeVO paramVO) throws Exception;
	
	List<CommonCodeVO> selectCommonCodeListUser(CommonCodeVO paramVO) throws Exception;
	
	void updateCommonCodeSort(CommonCodeVO paramVO) throws Exception;
}
