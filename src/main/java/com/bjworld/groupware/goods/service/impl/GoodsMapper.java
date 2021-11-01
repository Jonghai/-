package com.bjworld.groupware.goods.service.impl;

import com.bjworld.groupware.goods.service.impl.GoodsVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("goodsMapper")
public interface GoodsMapper{

	List<GoodsVO> selectGoodsList(GoodsVO paramVO) throws Exception;
	
	Integer selectGoodsListTotCnt(GoodsVO paramVO) throws Exception;
	
	void mergeGoods(GoodsVO paramVO) throws Exception;

    void insertGoods(GoodsVO paramVO) throws Exception;

    void updateGoods(GoodsVO paramVO) throws Exception;
	
	GoodsVO selectGoods(GoodsVO paramVO) throws Exception;
	
	void deleteGoods(GoodsVO paramVO) throws Exception;
}
