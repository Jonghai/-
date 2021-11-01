package com.bjworld.groupware.relatedinstitution.service.impl;

import com.bjworld.groupware.relatedinstitution.service.impl.RelatedInstitutionVO;
import java.util.List;
import javax.annotation.Resource;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("relatedinstitutionMapper")
public interface RelatedInstitutionMapper{

	List<RelatedInstitutionVO> selectRelatedInstitutionList(RelatedInstitutionVO paramVO) throws Exception;
	
	Integer selectRelatedInstitutionListTotCnt(RelatedInstitutionVO paramVO) throws Exception;
	
	void mergeRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;

    void insertRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;

    void updateRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;
	
	RelatedInstitutionVO selectRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;
	
	void deleteRelatedInstitution(RelatedInstitutionVO paramVO) throws Exception;
}
