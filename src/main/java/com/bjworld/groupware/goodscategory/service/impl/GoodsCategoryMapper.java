package com.bjworld.groupware.goodscategory.service.impl;

import com.bjworld.groupware.goodscategory.service.impl.GoodsCategoryVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("goodscategoryMapper")
public interface GoodsCategoryMapper{

	List<GoodsCategoryVO> selectGoodsCategoryList(GoodsCategoryVO paramVO) throws Exception;
	
	Integer selectGoodsCategoryListTotCnt(GoodsCategoryVO paramVO) throws Exception;
	
	void mergeGoodsCategory(GoodsCategoryVO paramVO) throws Exception;

    void insertGoodsCategory(GoodsCategoryVO paramVO) throws Exception;

    void updateGoodsCategory(GoodsCategoryVO paramVO) throws Exception;
	
	GoodsCategoryVO selectGoodsCategory(GoodsCategoryVO paramVO) throws Exception;
	
	void deleteGoodsCategory(GoodsCategoryVO paramVO) throws Exception;
}
