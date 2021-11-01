package com.bjworld.groupware.commoncode.service.impl;

import com.bjworld.groupware.commoncode.service.CommonCodeService;
import com.bjworld.groupware.commoncode.service.impl.CommonCodeMapper;
import com.bjworld.groupware.commoncode.service.impl.CommonCodeVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("commoncodeService")
public class CommonCodeServiceImpl extends EgovAbstractServiceImpl implements CommonCodeService {
    @Resource(name="commoncodeMapper")
    private CommonCodeMapper commoncodeMapper;

    @Override
    public List<CommonCodeVO> selectCommonCodeList(CommonCodeVO paramVO) throws Exception {
        return commoncodeMapper.selectCommonCodeList(paramVO);
    }

    @Override
    public Integer selectCommonCodeListTotCnt(CommonCodeVO paramVO) throws Exception {
        return commoncodeMapper.selectCommonCodeListTotCnt(paramVO);
    }

    @Override
    public CommonCodeVO selectCommonCode(CommonCodeVO paramVO) throws Exception {
        return commoncodeMapper.selectCommonCode(paramVO);
    }

    @Override
    public void mergeCommonCode(CommonCodeVO paramVO) throws Exception {
        commoncodeMapper.mergeCommonCode(paramVO);
    }

    @Override
    public void insertCommonCode(CommonCodeVO paramVO) throws Exception {
        commoncodeMapper.insertCommonCode(paramVO);
    }

    @Override
    public void updateCommonCode(CommonCodeVO paramVO) throws Exception {
        commoncodeMapper.updateCommonCode(paramVO);
    }

    @Override
    public void deleteCommonCode(CommonCodeVO paramVO) throws Exception {
        commoncodeMapper.deleteCommonCode(paramVO);
    }

	@Override
	public List<CommonCodeVO> selectCommonCodeListUser(CommonCodeVO paramVO) throws Exception {
		return commoncodeMapper.selectCommonCodeListUser(paramVO);
	}

	@Override
	public void updateCommonCodeSort(CommonCodeVO paramVO) throws Exception {
		commoncodeMapper.updateCommonCodeSort(paramVO);
	}
}
