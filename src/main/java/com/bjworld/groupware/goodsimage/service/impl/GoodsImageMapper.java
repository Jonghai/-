package com.bjworld.groupware.goodsimage.service.impl;

import com.bjworld.groupware.goodsimage.service.impl.GoodsImageVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("goodsimageMapper")
public interface GoodsImageMapper{

	List<GoodsImageVO> selectGoodsImageList(GoodsImageVO paramVO) throws Exception;
	
	Integer selectGoodsImageListTotCnt(GoodsImageVO paramVO) throws Exception;
	
	void mergeGoodsImage(GoodsImageVO paramVO) throws Exception;

    void insertGoodsImage(GoodsImageVO paramVO) throws Exception;

    void updateGoodsImage(GoodsImageVO paramVO) throws Exception;
	
	GoodsImageVO selectGoodsImage(GoodsImageVO paramVO) throws Exception;
	
	void deleteGoodsImage(GoodsImageVO paramVO) throws Exception;
}
