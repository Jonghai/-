package com.bjworld.groupware.goodsimage.service;

import com.bjworld.groupware.goodsimage.service.impl.GoodsImageVO;
import java.util.List;

public interface GoodsImageService {

    List<GoodsImageVO> selectGoodsImageList(GoodsImageVO paramVO) throws Exception;

    Integer selectGoodsImageListTotCnt(GoodsImageVO paramVO) throws Exception;

    GoodsImageVO selectGoodsImage(GoodsImageVO paramVO) throws Exception;

    void saveGoodsImage(GoodsImageVO paramVO) throws Exception;

    void mergeGoodsImage(GoodsImageVO paramVO) throws Exception;

    void insertGoodsImage(GoodsImageVO paramVO) throws Exception;

    void updateGoodsImage(GoodsImageVO paramVO) throws Exception;

    void deleteGoodsImage(GoodsImageVO paramVO) throws Exception;
}
