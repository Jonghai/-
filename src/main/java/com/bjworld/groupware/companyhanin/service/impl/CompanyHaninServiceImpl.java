package com.bjworld.groupware.companyhanin.service.impl;

import com.bjworld.groupware.companyhanin.service.CompanyHaninService;
import com.bjworld.groupware.companyhanin.service.impl.CompanyHaninMapper;
import com.bjworld.groupware.companyhanin.service.impl.CompanyHaninVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("companyhaninService")
public class CompanyHaninServiceImpl extends EgovAbstractServiceImpl implements CompanyHaninService {
    @Resource(name="companyhaninMapper")
    private CompanyHaninMapper companyhaninMapper;

    @Override
    public List<CompanyHaninVO> selectCompanyHaninList(CompanyHaninVO paramVO) throws Exception {
        return companyhaninMapper.selectCompanyHaninList(paramVO);
    }

    @Override
    public Integer selectCompanyHaninListTotCnt(CompanyHaninVO paramVO) throws Exception {
        return companyhaninMapper.selectCompanyHaninListTotCnt(paramVO);
    }

    @Override
    public CompanyHaninVO selectCompanyHanin(CompanyHaninVO paramVO) throws Exception {
        return companyhaninMapper.selectCompanyHanin(paramVO);
    }

    @Override
    public void mergeCompanyHanin(CompanyHaninVO paramVO) throws Exception {
        companyhaninMapper.mergeCompanyHanin(paramVO);
    }

    @Override
    public void insertCompanyHanin(CompanyHaninVO paramVO) throws Exception {
        companyhaninMapper.insertCompanyHanin(paramVO);
    }

    @Override
    public void updateCompanyHanin(CompanyHaninVO paramVO) throws Exception {
        companyhaninMapper.updateCompanyHanin(paramVO);
    }

    @Override
    public void deleteCompanyHanin(CompanyHaninVO paramVO) throws Exception {
        companyhaninMapper.deleteCompanyHanin(paramVO);
    }
}
