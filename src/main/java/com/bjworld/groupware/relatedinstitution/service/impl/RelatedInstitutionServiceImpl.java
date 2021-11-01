package com.bjworld.groupware.relatedinstitution.service.impl;

import com.bjworld.groupware.relatedinstitution.service.RelatedInstitutionService;
import com.bjworld.groupware.relatedinstitution.service.impl.RelatedInstitutionMapper;
import com.bjworld.groupware.relatedinstitution.service.impl.RelatedInstitutionVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.annotation.Resource;

@Service("relatedinstitutionService")
public class RelatedInstitutionServiceImpl extends EgovAbstractServiceImpl implements RelatedInstitutionService {
    @Resource(name="relatedinstitutionMapper")
    private RelatedInstitutionMapper relatedinstitutionMapper;

    @Override
    public List<RelatedInstitutionVO> selectRelatedInstitutionList(RelatedInstitutionVO paramVO) throws Exception {
        return relatedinstitutionMapper.selectRelatedInstitutionList(paramVO);
    }

    @Override
    public Integer selectRelatedInstitutionListTotCnt(RelatedInstitutionVO paramVO) throws Exception {
        return relatedinstitutionMapper.selectRelatedInstitutionListTotCnt(paramVO);
    }

    @Override
    public RelatedInstitutionVO selectRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception {
        return relatedinstitutionMapper.selectRelatedInstitution(paramVO);
    }

    @Override
    public void mergeRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception {
        relatedinstitutionMapper.mergeRelatedInstitution(paramVO);
    }

    @Override
    public void insertRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception {
        relatedinstitutionMapper.insertRelatedInstitution(paramVO);
    }

    @Override
    public void updateRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception {
        relatedinstitutionMapper.updateRelatedInstitution(paramVO);
    }

    @Override
    public void deleteRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception {
        relatedinstitutionMapper.deleteRelatedInstitution(paramVO);
    }
}
