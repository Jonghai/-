package com.bjworld.groupware.company.service.impl;

import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovDateUtil;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.company.service.CompanyService;
import com.bjworld.groupware.company.service.impl.CompanyMapper;
import com.bjworld.groupware.company.service.impl.CompanyVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

@Service("companyService")
public class CompanyServiceImpl extends EgovAbstractServiceImpl implements CompanyService {
	@Value("${Globals.fileStorePath}")
	private String attachFileSavePath;
	
    @Resource(name="companyMapper")
    private CompanyMapper companyMapper;

    @Override
    public List<CompanyVO> selectCompanyList(CompanyVO paramVO) throws Exception {
        return companyMapper.selectCompanyList(paramVO);
    }

    @Override
    public Integer selectCompanyListTotCnt(CompanyVO paramVO) throws Exception {
        return companyMapper.selectCompanyListTotCnt(paramVO);
    }

    @Override
    public CompanyVO selectCompany(CompanyVO paramVO) throws Exception {
        return companyMapper.selectCompany(paramVO);
    }

    @Override
    public void mergeCompany(CompanyVO paramVO) throws Exception {
        companyMapper.mergeCompany(paramVO);
    }

    @Override
    public void insertCompany(CompanyVO paramVO) throws Exception {
        companyMapper.insertCompany(paramVO);
    }

    @Override
    public void updateCompany(CompanyVO paramVO) throws Exception {
        companyMapper.updateCompany(paramVO);
    }

    @Override
    public void deleteCompany(CompanyVO paramVO) throws Exception {
        companyMapper.deleteCompany(paramVO);
    }

	@Override
	public String selectCompanyByBusinessNumber(String companyBusinessNumber) throws Exception {
		return companyMapper.selectCompanyByBusinessNumber(companyBusinessNumber);
	}

	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED , rollbackFor=Exception.class)
	public void saveCompany(CompanyVO paramVO) throws Exception {
		if(EgovStringUtil.isEmpty(paramVO.getSeq()))
			paramVO.setSeq(null);
			
		String companySeq = paramVO.getSeq();
		mergeCompany(paramVO);
		if(EgovStringUtil.isEmpty(companySeq))
			companySeq = paramVO.getSeq();

	}

	@Override
	public CompanyVO selectCompanyUserLogin(CompanyVO paramVO) {
		return companyMapper.selectCompanyUserLogin(paramVO);
	}

	@Override
	public CompanyVO selectCompanyForPassword(CompanyVO paramVO) {
		return companyMapper.selectCompanyForPassword(paramVO);
	}

	@Override
	public void updateCompanyPassword(CompanyVO paramVO) throws Exception {
		companyMapper.updateCompanyPassword(paramVO);
	}

	@Override
	public CompanyVO selectCompanyBySystemMember(String companyBusinessNumber) throws Exception {
		return companyMapper.selectCompanyBySystemMember(companyBusinessNumber);
	}
	
	@Override
	public void selectExcelCompany(CompanyVO paramVO, HttpServletRequest request, HttpServletResponse response, String[] paramKey) throws Exception {
		List<HashMap<String, String>> company = companyMapper.selectExcelCompany(paramVO);
		
		List<HashMap<String, String>> result = new ArrayList<HashMap<String,String>>();
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		
		map.put("companyName","기업명");
		map.put("businessNumber","사업자등록번호");
		map.put("corporationNumber","법인등록번호");
		map.put("startDate","설립일");
		map.put("ceoName","대표자");
		map.put("ceoPhone","대표자연락처");
		map.put("ceoEmail","대표자이메일");
		map.put("chargeName","담당자");
		map.put("chargePhone","담당자연락처");
		map.put("chargeEmail","담당자이메일");
		map.put("tel","대표번호");
		map.put("gubun","사업자구분");
		map.put("ceoSex","대표자성별");
		map.put("businessSector","업종");
		map.put("businessType","업태");
		map.put("saleAmount","매출액(최근3년)");
		map.put("employee","종업원");
		map.put("product","주요품목");
		map.put("etc","기업기타");
		map.put("address","본사주소");
		
		result.add(map);
		
		String [] headers;
		
		if(paramKey[0].equals("all")) {
			headers = new String[map.size()];
			paramKey = new String[map.size()];
			
			int i=0; 
			
			for(String key : map.keySet()){
				headers[i] = map.get(key);
				paramKey[i] = key;
				i++;
            }
		}else {
			headers = new String[paramKey.length];
					
			for(int i=0; i<paramKey.length; i++) {
				map.get(paramKey[i]);
				
				headers[i] = map.get(paramKey[i]);
			}
		}
		String saveFilePath = makeExcel(company, headers, paramKey);
		EgovFileMngUtil.downFile(request, response, "자금시스템 기업현황.xlsx", saveFilePath);
	}

	public String makeExcel(List<HashMap<String, String>> company, String[] headers, String[] paramKey) throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook.createSheet("자금시스템 기업현황");
		XSSFRow row = null;		
		XSSFCell cell = null;
		
		for(int i=0; i<headers.length; i++) {
			sheet1.setColumnWidth(i, 5000);
		}
		
		XSSFFont boldFont = workbook.createFont();
		boldFont.setBold(true);
		boldFont.setFontName("맑은 고딕");
		
		XSSFFont titleFont = workbook.createFont();
		titleFont.setBold(true);
		titleFont.setFontHeight((short)500);
		titleFont.setFontName("맑은 고딕");
		
		XSSFCellStyle boldStyle = workbook.createCellStyle();
		boldStyle.setFont(boldFont);
		
		XSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setBorderBottom(BorderStyle.NONE);
		titleStyle.setAlignment(HorizontalAlignment.LEFT);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		titleStyle.setFont(titleFont);
		

		XSSFCellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setBorderTop(BorderStyle.NONE);
		dateStyle.setAlignment(HorizontalAlignment.LEFT);
		dateStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(234, 234, 234)));
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		XSSFCellStyle rowCellStyle = workbook.createCellStyle();
		rowCellStyle.setBorderTop(BorderStyle.THIN);
		rowCellStyle.setBorderBottom(BorderStyle.THIN);
		rowCellStyle.setBorderLeft(BorderStyle.THIN);
		rowCellStyle.setBorderRight(BorderStyle.THIN);
		// rowCellStyle.setWrapText(true);
		rowCellStyle.setVerticalAlignment(VerticalAlignment.TOP);		
		
		int MergeCount = headers.length;

		sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, MergeCount-1));
		
		row = sheet1.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(titleStyle);
		cell.setCellValue("기업현황");
		row.setHeight((short)1000);
		
		row = sheet1.createRow(1);
		for(int i=0; i<headers.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			cell.setCellValue(headers[i]);
		}
		
		if (company != null && company.size() > 0) {
			int goodsStartIndex = 2;
			for (HashMap<String, String> mapItem : company) {
				// 시트에 하나의 행을 생성한다(i 값이 0이면 첫번째 줄에 해당)
				row = sheet1.createRow((short) goodsStartIndex);
				goodsStartIndex++;
				
				for (int key = 0; key < paramKey.length; key++) {
					mapItem.getClass();
					Object value = mapItem.get(paramKey[key]);
					
					cell = row.createCell(key);
					cell.setCellStyle(rowCellStyle);
					if(value == null) {
						cell.setCellValue("");
					}
					else
						cell.setCellValue(value.toString());
				}
			}
		}
		
		String tempFolderPath = attachFileSavePath +File.separator + "temp";
		
		String saveFileName = UUID.randomUUID().toString().replaceAll("-", "") + ".xlsx";
		String saveFullPath = tempFolderPath + File.separator + saveFileName;

		File tempPath = new File(tempFolderPath);
		if (!tempPath.exists()) {
			tempPath.mkdirs();
		}

		try (FileOutputStream fileoutputstream = new FileOutputStream(saveFullPath)) {
			workbook.write(fileoutputstream);
		} catch (Exception ex) {
			workbook.close();
			throw ex;
		}

		workbook.close();
		
		return saveFullPath;
	}

	@Override
	public List<String> selectCompanyListByHomeTaxStatusCheck(CompanyVO paramVO) throws Exception {
		return companyMapper.selectCompanyListByHomeTaxStatusCheck(paramVO);
	}

	@Override
	public void updateCompanyAtHomeTaxStatus(CompanyVO paramVO) throws Exception {
		companyMapper.updateCompanyAtHomeTaxStatus(paramVO);
	}

}
