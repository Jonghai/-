package com.bjworld.groupware.goodsimage.service.impl;

import com.bjworld.groupware.goodsimage.service.GoodsImageService;
import com.bjworld.groupware.goodsimage.service.impl.GoodsImageMapper;
import com.bjworld.groupware.goodsimage.service.impl.GoodsImageVO;

import com.bjworld.groupware.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;

@Service("goodsimageService")
public class GoodsImageServiceImpl extends EgovAbstractServiceImpl implements GoodsImageService {
    @Resource(name="goodsimageMapper")
    private GoodsImageMapper goodsimageMapper;

    

    @Override
    public List<GoodsImageVO> selectGoodsImageList(GoodsImageVO paramVO) throws Exception {
        return goodsimageMapper.selectGoodsImageList(paramVO);
    }

    @Override
    public Integer selectGoodsImageListTotCnt(GoodsImageVO paramVO) throws Exception {
        return goodsimageMapper.selectGoodsImageListTotCnt(paramVO);
    }

    @Override
    public GoodsImageVO selectGoodsImage(GoodsImageVO paramVO) throws Exception {
        return goodsimageMapper.selectGoodsImage(paramVO);
    }

    @Override
    @Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void saveGoodsImage(GoodsImageVO paramVO) throws Exception {
        
    }

    @Override
    public void mergeGoodsImage(GoodsImageVO paramVO) throws Exception {
        goodsimageMapper.mergeGoodsImage(paramVO);
    }

    @Override
    public void insertGoodsImage(GoodsImageVO paramVO) throws Exception {
        goodsimageMapper.insertGoodsImage(paramVO);
    }

    @Override
    public void updateGoodsImage(GoodsImageVO paramVO) throws Exception {
        goodsimageMapper.updateGoodsImage(paramVO);
    }

    @Override
    public void deleteGoodsImage(GoodsImageVO paramVO) throws Exception {
        goodsimageMapper.deleteGoodsImage(paramVO);
    }
}
