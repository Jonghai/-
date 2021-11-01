package com.bjworld.groupware.company.service.impl;

import java.util.List;

import com.bjworld.groupware.common.vo.DefaultVO;

public class CompanyVO extends DefaultVO {
	private String seq;
	private String companyBusinessNumber;
	private String companyName;
	private String companyArea;
	private String companyAddressZoneCode;
	private String companyAddress;
	private String companyAddressDetail;
	private String companyCeoName;
	private String companyCeoPhone;
	private String companyCeoPhone1;
	private String companyCeoPhone2;
	private String companyCeoPhone3;
	private String companyCeoEmail;
	private String companyPwd;
	private String companyCorporationNumber;
	private String companyChargeName;
	private String companyChargePosition;
	private String companyChargePhone;
	private String companyChargePhone1;
	private String companyChargePhone2;
	private String companyChargePhone3;
	private String companyChargeEmail;
	private String companyEmployeeCount;
	private String regDate;
	private String regUserSeq;
	private String companyIsExport;
	private String companyIsExportDesc;
	private String companyStatus;
	private String companyStatusDesc;
	private String applicationCount;
	private String companyProduct;
	private String companyExportProduct;
	private String companyStartDate;
	private String companyCeoSex;
	private String companyCeoSexDesc;
	private String companyTel;
	private String companyFax;
	private String etcCommonCodeSeqs;
	private String etcCommonCodeDesc;
	private int latestRegDateDiff;
	private String projectSeq;
	
	private String mainProduct;
	private String businessCode;
	private String businessCodeDesc;
	
	private String isKedex;	
	private String companyGubun;
	private String companyGubunDesc;	
	private String companyBusinessSector;
	private String companyBusinessSectorTitle;
	private String companyBusinessType;
	
	private String isSystemMember;
	private String employeeCountOffice;
	private String employeeCountProduction;
	
	private String homeTaxStatus;
	private String homeTaxSearchDate;
	
	private String companyProductDesc;
	
	public String getCompanyBusinessSectorTitle() {
		return companyBusinessSectorTitle;
	}

	public void setCompanyBusinessSectorTitle(String companyBusinessSectorTitle) {
		this.companyBusinessSectorTitle = companyBusinessSectorTitle;
	}

	
	public String getCompanyCeoPhone1() {
		return companyCeoPhone1;
	}

	public void setCompanyCeoPhone1(String companyCeoPhone1) {
		this.companyCeoPhone1 = companyCeoPhone1;
	}

	public String getCompanyCeoPhone2() {
		return companyCeoPhone2;
	}

	public void setCompanyCeoPhone2(String companyCeoPhone2) {
		this.companyCeoPhone2 = companyCeoPhone2;
	}

	public String getCompanyCeoPhone3() {
		return companyCeoPhone3;
	}

	public void setCompanyCeoPhone3(String companyCeoPhone3) {
		this.companyCeoPhone3 = companyCeoPhone3;
	}

	public String getCompanyChargePhone1() {
		return companyChargePhone1;
	}

	public void setCompanyChargePhone1(String companyChargePhone1) {
		this.companyChargePhone1 = companyChargePhone1;
	}

	public String getCompanyChargePhone2() {
		return companyChargePhone2;
	}

	public void setCompanyChargePhone2(String companyChargePhone2) {
		this.companyChargePhone2 = companyChargePhone2;
	}

	public String getCompanyChargePhone3() {
		return companyChargePhone3;
	}

	public void setCompanyChargePhone3(String companyChargePhone3) {
		this.companyChargePhone3 = companyChargePhone3;
	}

	public String getHomeTaxSearchDate() {
		return homeTaxSearchDate;
	}

	public void setHomeTaxSearchDate(String homeTaxSearchDate) {
		this.homeTaxSearchDate = homeTaxSearchDate;
	}

	public String getHomeTaxStatus() {
		return homeTaxStatus;
	}

	public void setHomeTaxStatus(String homeTaxStatus) {
		this.homeTaxStatus = homeTaxStatus;
	}

	private String saleAmountJSON;
	private String productJSON;
	private String exportAmountJSON;
	private String exportProductJSON;
	private String factoryJSON;
	private String financialJSON;
	private String etcInfoJSON;
	private String certifiedJSON;
	private String skillJSON;
	private String kedexXML;
	
	private String id;
	private String text;
	
	private String companyHomepage;
	private String companyRentType;
	private String companyRentTypeDesc;
	
	public String getCertifiedJSON() {
		return certifiedJSON;
	}

	public void setCertifiedJSON(String certifiedJSON) {
		this.certifiedJSON = certifiedJSON;
	}

	public String getSkillJSON() {
		return skillJSON;
	}

	public void setSkillJSON(String skillJSON) {
		this.skillJSON = skillJSON;
	}

	public String getCompanyRentTypeDesc() {
		return companyRentTypeDesc;
	}

	public void setCompanyRentTypeDesc(String companyRentTypeDesc) {
		this.companyRentTypeDesc = companyRentTypeDesc;
	}

	
	public String getEtcInfoJSON() {
		return etcInfoJSON;
	}

	public void setEtcInfoJSON(String etcInfoJSON) {
		this.etcInfoJSON = etcInfoJSON;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}
	
	public String getEmployeeCountOffice() {
		return employeeCountOffice;
	}

	public void setEmployeeCountOffice(String employeeCountOffice) {
		this.employeeCountOffice = employeeCountOffice;
	}

	public String getEmployeeCountProduction() {
		return employeeCountProduction;
	}

	public void setEmployeeCountProduction(String employeeCountProduction) {
		this.employeeCountProduction = employeeCountProduction;
	}

	public String getCompanyRentType() {
		return companyRentType;
	}

	public void setCompanyRentType(String companyRentType) {
		this.companyRentType = companyRentType;
	}

	public String getCompanyHomepage() {
		return companyHomepage;
	}

	public void setCompanyHomepage(String companyHomepage) {
		this.companyHomepage = companyHomepage;
	}

	public String getId() {
		return this.getSeq();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.getCompanyName();
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	public String getKedexXML() {
		return kedexXML;
	}

	public void setKedexXML(String kedexXML) {
		this.kedexXML = kedexXML;
	}

	public String getIsSystemMember() {
		return isSystemMember;
	}

	public void setIsSystemMember(String isSystemMember) {
		this.isSystemMember = isSystemMember;
	}

	public String getCompanyBusinessSector() {
		return companyBusinessSector;
	}

	public void setCompanyBusinessSector(String companyBusinessSector) {
		this.companyBusinessSector = companyBusinessSector;
	}

	public String getCompanyBusinessType() {
		return companyBusinessType;
	}

	public void setCompanyBusinessType(String companyBusinessType) {
		this.companyBusinessType = companyBusinessType;
	}

	public String getCompanyTel() {
		return companyTel;
	}

	public void setCompanyTel(String companyTel) {
		this.companyTel = companyTel;
	}

	public String getCompanyCeoSexDesc() {
		return companyCeoSexDesc;
	}

	public void setCompanyCeoSexDesc(String companyCeoSexDesc) {
		this.companyCeoSexDesc = companyCeoSexDesc;
	}

	public String getCompanyStartDate() {
		return companyStartDate;
	}

	public void setCompanyStartDate(String companyStartDate) {
		this.companyStartDate = companyStartDate;
	}

	public String getCompanyCeoSex() {
		return companyCeoSex;
	}

	public void setCompanyCeoSex(String companyCeoSex) {
		this.companyCeoSex = companyCeoSex;
	}

	public String getCompanyGubun() {
		return companyGubun;
	}

	public void setCompanyGubun(String companyGubun) {
		this.companyGubun = companyGubun;
	}

	public String getCompanyGubunDesc() {
		return companyGubunDesc;
	}

	public void setCompanyGubunDesc(String companyGubunDesc) {
		this.companyGubunDesc = companyGubunDesc;
	}

	public String getCompanyEmployeeCount() {
		return companyEmployeeCount;
	}

	public void setCompanyEmployeeCount(String companyEmployeeCount) {
		this.companyEmployeeCount = companyEmployeeCount;
	}

	public String getProjectSeq() {
		return projectSeq;
	}

	public void setProjectSeq(String projectSeq) {
		this.projectSeq = projectSeq;
	}

	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessCodeDesc() {
		return businessCodeDesc;
	}

	public void setBusinessCodeDesc(String businessCodeDesc) {
		this.businessCodeDesc = businessCodeDesc;
	}

	public String getIsKedex() {
		return isKedex;
	}

	public void setIsKedex(String isKedex) {
		this.isKedex = isKedex;
	}

	public String getEtcCommonCodeSeqs() {
		return etcCommonCodeSeqs;
	}

	public void setEtcCommonCodeSeqs(String etcCommonCodeSeqs) {
		this.etcCommonCodeSeqs = etcCommonCodeSeqs;
	}

	public String getEtcCommonCodeDesc() {
		return etcCommonCodeDesc;
	}

	public void setEtcCommonCodeDesc(String etcCommonCodeDesc) {
		this.etcCommonCodeDesc = etcCommonCodeDesc;
	}

	public int getLatestRegDateDiff() {
		return latestRegDateDiff;
	}

	public void setLatestRegDateDiff(int latestRegDateDiff) {
		this.latestRegDateDiff = latestRegDateDiff;
	}

	public String getCompanyExportProduct() {
		return companyExportProduct;
	}

	public void setCompanyExportProduct(String companyExportProduct) {
		this.companyExportProduct = companyExportProduct;
	}

	public String getCompanyProduct() {
		return companyProduct;
	}

	public void setCompanyProduct(String companyProduct) {
		this.companyProduct = companyProduct;
	}

	public String getApplicationCount() {
		return applicationCount;
	}

	public void setApplicationCount(String applicationCount) {
		this.applicationCount = applicationCount;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public String getCompanyStatusDesc() {
		return companyStatusDesc;
	}

	public void setCompanyStatusDesc(String companyStatusDesc) {
		this.companyStatusDesc = companyStatusDesc;
	}

	public String getSaleAmountJSON() {
		return saleAmountJSON;
	}

	public void setSaleAmountJSON(String saleAmountJSON) {
		this.saleAmountJSON = saleAmountJSON;
	}

	public String getProductJSON() {
		return productJSON;
	}

	public void setProductJSON(String productJSON) {
		this.productJSON = productJSON;
	}

	public String getExportAmountJSON() {
		return exportAmountJSON;
	}

	public void setExportAmountJSON(String exportAmountJSON) {
		this.exportAmountJSON = exportAmountJSON;
	}

	public String getExportProductJSON() {
		return exportProductJSON;
	}

	public void setExportProductJSON(String exportProductJSON) {
		this.exportProductJSON = exportProductJSON;
	}

	public String getFactoryJSON() {
		return factoryJSON;
	}

	public void setFactoryJSON(String factoryJSON) {
		this.factoryJSON = factoryJSON;
	}

	public String getCompanyIsExportDesc() {
		return companyIsExportDesc;
	}

	public void setCompanyIsExportDesc(String companyIsExportDesc) {
		this.companyIsExportDesc = companyIsExportDesc;
	}

	public String getRegUserSeq() {
		return regUserSeq;
	}

	public void setRegUserSeq(String regUserSeq) {
		this.regUserSeq = regUserSeq;
	}
	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCompanyBusinessNumber() {
		return companyBusinessNumber;
	}

	public void setCompanyBusinessNumber(String companyBusinessNumber) {
		this.companyBusinessNumber = companyBusinessNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyArea() {
		return companyArea;
	}

	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}

	public String getCompanyAddressZoneCode() {
		return companyAddressZoneCode;
	}

	public void setCompanyAddressZoneCode(String companyAddressZoneCode) {
		this.companyAddressZoneCode = companyAddressZoneCode;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddressDetail() {
		return companyAddressDetail;
	}

	public void setCompanyAddressDetail(String companyAddressDetail) {
		this.companyAddressDetail = companyAddressDetail;
	}

	public String getCompanyCeoName() {
		return companyCeoName;
	}

	public void setCompanyCeoName(String companyCeoName) {
		this.companyCeoName = companyCeoName;
	}

	public String getCompanyCeoPhone() {
		return companyCeoPhone;
	}

	public void setCompanyCeoPhone(String companyCeoPhone) {
		this.companyCeoPhone = companyCeoPhone;
	}

	public String getCompanyCeoEmail() {
		return companyCeoEmail;
	}

	public void setCompanyCeoEmail(String companyCeoEmail) {
		this.companyCeoEmail = companyCeoEmail;
	}

	public String getCompanyPwd() {
		return companyPwd;
	}

	public void setCompanyPwd(String companyPwd) {
		this.companyPwd = companyPwd;
	}

	public String getCompanyCorporationNumber() {
		return companyCorporationNumber;
	}

	public void setCompanyCorporationNumber(String companyCorporationNumber) {
		this.companyCorporationNumber = companyCorporationNumber;
	}

	public String getCompanyChargeName() {
		return companyChargeName;
	}

	public void setCompanyChargeName(String companyChargeName) {
		this.companyChargeName = companyChargeName;
	}

	public String getCompanyChargePosition() {
		return companyChargePosition;
	}

	public void setCompanyChargePosition(String companyChargePosition) {
		this.companyChargePosition = companyChargePosition;
	}

	public String getCompanyChargePhone() {
		return companyChargePhone;
	}

	public void setCompanyChargePhone(String companyChargePhone) {
		this.companyChargePhone = companyChargePhone;
	}

	public String getCompanyChargeEmail() {
		return companyChargeEmail;
	}

	public void setCompanyChargeEmail(String companyChargeEmail) {
		this.companyChargeEmail = companyChargeEmail;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getCompanyIsExport() {
		return companyIsExport;
	}

	public void setCompanyIsExport(String companyIsExport) {
		this.companyIsExport = companyIsExport;
	}

	public String getFinancialJSON() {
		return financialJSON;
	}

	public void setFinancialJSON(String financialJSON) {
		this.financialJSON = financialJSON;
	}

	public String getCompanyProductDesc() {
		return companyProductDesc;
	}

	public void setCompanyProductDesc(String companyProductDesc) {
		this.companyProductDesc = companyProductDesc;
	}
	
}
