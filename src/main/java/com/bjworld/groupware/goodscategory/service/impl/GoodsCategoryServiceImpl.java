package com.bjworld.groupware.goodscategory.service.impl;

import com.bjworld.groupware.goodscategory.service.GoodsCategoryService;
import com.bjworld.groupware.goodscategory.service.impl.GoodsCategoryMapper;
import com.bjworld.groupware.goodscategory.service.impl.GoodsCategoryVO;

import com.bjworld.groupware.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;

@Service("goodscategoryService")
public class GoodsCategoryServiceImpl extends EgovAbstractServiceImpl implements GoodsCategoryService {
    @Resource(name="goodscategoryMapper")
    private GoodsCategoryMapper goodscategoryMapper;

    

    @Override
    public List<GoodsCategoryVO> selectGoodsCategoryList(GoodsCategoryVO paramVO) throws Exception {
        return goodscategoryMapper.selectGoodsCategoryList(paramVO);
    }

    @Override
    public Integer selectGoodsCategoryListTotCnt(GoodsCategoryVO paramVO) throws Exception {
        return goodscategoryMapper.selectGoodsCategoryListTotCnt(paramVO);
    }

    @Override
    public GoodsCategoryVO selectGoodsCategory(GoodsCategoryVO paramVO) throws Exception {
        return goodscategoryMapper.selectGoodsCategory(paramVO);
    }

    @Override
    @Transactional(isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void saveGoodsCategory(GoodsCategoryVO paramVO) throws Exception {
        
    }

    @Override
    public void mergeGoodsCategory(GoodsCategoryVO paramVO) throws Exception {
        goodscategoryMapper.mergeGoodsCategory(paramVO);
    }

    @Override
    public void insertGoodsCategory(GoodsCategoryVO paramVO) throws Exception {
        goodscategoryMapper.insertGoodsCategory(paramVO);
    }

    @Override
    public void updateGoodsCategory(GoodsCategoryVO paramVO) throws Exception {
        goodscategoryMapper.updateGoodsCategory(paramVO);
    }

    @Override
    public void deleteGoodsCategory(GoodsCategoryVO paramVO) throws Exception {
        goodscategoryMapper.deleteGoodsCategory(paramVO);
    }
}
