package com.bjworld.groupware.goods.service.impl;

import com.bjworld.groupware.goods.service.GoodsService;
import com.bjworld.groupware.goods.service.impl.GoodsMapper;
import com.bjworld.groupware.goods.service.impl.GoodsVO;

import com.bjworld.groupware.goodsimage.service.GoodsImageService;
import com.bjworld.groupware.goodsimage.service.impl.GoodsImageVO;
import com.bjworld.groupware.common.util.EgovStringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import javax.annotation.Resource;

@Service("goodsService")
public class GoodsServiceImpl extends EgovAbstractServiceImpl implements GoodsService {
	@Resource(name = "goodsMapper")
	private GoodsMapper goodsMapper;

	@Resource(name = "goodsimageService")
	private GoodsImageService goodsimageService;

	@Override
	public List<GoodsVO> selectGoodsList(GoodsVO paramVO) throws Exception {
		return goodsMapper.selectGoodsList(paramVO);
	}

	@Override
	public Integer selectGoodsListTotCnt(GoodsVO paramVO) throws Exception {
		return goodsMapper.selectGoodsListTotCnt(paramVO);
	}

	@Override
	public GoodsVO selectGoods(GoodsVO paramVO) throws Exception {
		return goodsMapper.selectGoods(paramVO);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public void saveGoods(GoodsVO paramVO) throws Exception {

		String seq = paramVO.getSeq();

		if (EgovStringUtil.isEmpty(seq))
			paramVO.setSeq(null);

		mergeGoods(paramVO);

		seq = paramVO.getSeq();

		if (paramVO.getAttachSaveFileName() != null) {
			for (int i = 0; i < paramVO.getAttachSaveFileName().length; i++) {
				GoodsImageVO fileVO = new GoodsImageVO();
				fileVO.setGoodsSeq(seq);
				fileVO.setSaveFilename(paramVO.getAttachSaveFileName()[i]);
				fileVO.setOriFilename(paramVO.getAttachOriFileName()[i]);
				fileVO.setFilesize(paramVO.getAttachFileSize()[i]);
				goodsimageService.insertGoodsImage(fileVO);
			}
		}

	}

	@Override
	public void mergeGoods(GoodsVO paramVO) throws Exception {
		goodsMapper.mergeGoods(paramVO);
	}

	@Override
	public void insertGoods(GoodsVO paramVO) throws Exception {
		goodsMapper.insertGoods(paramVO);
	}

	@Override
	public void updateGoods(GoodsVO paramVO) throws Exception {
		goodsMapper.updateGoods(paramVO);
	}

	@Override
	public void deleteGoods(GoodsVO paramVO) throws Exception {
		goodsMapper.deleteGoods(paramVO);
	}
}
