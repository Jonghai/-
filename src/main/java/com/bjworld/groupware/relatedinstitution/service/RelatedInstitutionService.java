package com.bjworld.groupware.relatedinstitution.service;

import com.bjworld.groupware.relatedinstitution.service.impl.RelatedInstitutionVO;
import java.util.List;

public interface RelatedInstitutionService {

    List<RelatedInstitutionVO> selectRelatedInstitutionList(RelatedInstitutionVO paramVO) throws Exception;

    Integer selectRelatedInstitutionListTotCnt(RelatedInstitutionVO paramVO) throws Exception;

    RelatedInstitutionVO selectRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;

    void mergeRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;

    void insertRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;

    void updateRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;

    void deleteRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;
}
