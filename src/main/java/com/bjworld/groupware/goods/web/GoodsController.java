package com.bjworld.groupware.goods.web;

import com.bjworld.groupware.common.util.AjaxResult;
import com.bjworld.groupware.common.util.ProjectUtility;
import com.bjworld.groupware.common.SystemConstant;
import com.bjworld.groupware.common.util.EgovSessionCookieUtil;
import com.bjworld.groupware.common.util.EgovStringUtil;
import com.bjworld.groupware.common.util.UploadFileVO;
import com.bjworld.groupware.common.util.EgovBasicLogger;
import com.bjworld.groupware.common.util.EgovFileMngUtil;
import com.bjworld.groupware.common.vo.SessionVO;
import com.bjworld.groupware.goods.service.GoodsService;
import com.bjworld.groupware.goods.service.impl.GoodsVO;
import com.bjworld.groupware.goodsimage.service.GoodsImageService;
import com.bjworld.groupware.goodsimage.service.impl.GoodsImageVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.File;
import java.util.List;
import java.util.HashMap;

@Controller
@RequestMapping("/admin")
public class GoodsController {

	Logger logger = LoggerFactory.getLogger(GoodsController.class);

	@Value("${Globals.fileStorePath}")
	private String attachFileSavePath;

	@Resource(name = "goodsService")
	private GoodsService goodsService;

	@Resource(name = "goodsimageService")
	private GoodsImageService goodsimageService;

	@RequestMapping("/goods.do")
	public String goods(HttpServletRequest request, Model model) throws Exception {
		return "goods/goods.at";
	}

	@RequestMapping(value = "/getGoodsListAjax.do")
	@ResponseBody
	public HashMap<String, Object> getGoodsListAjax(HttpServletRequest request, GoodsVO paramVO) throws Exception {

		List<?> dataList = goodsService.selectGoodsList(paramVO);
		// Total Count
		Integer total = goodsService.selectGoodsListTotCnt(paramVO);

		HashMap<String, Object> listMap = new HashMap<String, Object>();
		listMap.put("draw", paramVO.getDraw());
		listMap.put("recordsTotal", total);
		listMap.put("recordsFiltered", total);
		listMap.put("data", dataList);
		return listMap;
	}

	@RequestMapping(value = "/mergeGoodsAjax.do")
	@ResponseBody
	public AjaxResult<String> mergeBuyerCompanyAjax(HttpServletRequest request, GoodsVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();
		try {

			// SessionVO sessionVO = SessionUtils.getCurrentUserSession();
			// paramVO.setRegLoginId(sessionVO.getLoginId());

			if (StringUtils.isEmpty(paramVO.getSeq()))
				paramVO.setSeq(null);

			goodsService.saveGoods(paramVO);

			result.setData("");
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setMsg("데이터를 저장하였습니다.");

		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "데이터를 저장 중"));
		}

		return result;
	}

	@RequestMapping(value = "/selectGoodsAjax.do")
	@ResponseBody
	public AjaxResult<GoodsVO> selectGoodsAjax(HttpServletRequest request, GoodsVO paramVO) throws Exception {

		AjaxResult<GoodsVO> result = new AjaxResult<>();

		try {
			GoodsVO viewVO = goodsService.selectGoods(paramVO);

			if (viewVO != null) {
				GoodsImageVO imageParamVO = new GoodsImageVO();
				imageParamVO.setGoodsSeq(viewVO.getSeq());

				List<GoodsImageVO> listGoodsImage = goodsimageService.selectGoodsImageList(imageParamVO);
				viewVO.setListGoodsImage(listGoodsImage);
			}

			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData(viewVO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 데이터를 불러 오는 중"));
		}

		return result;
	}

	@RequestMapping(value = "/deleteGoodsAjax.do")
	@ResponseBody
	public AjaxResult<String> deleteGoodsAjax(HttpServletRequest request, GoodsVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();

		try {
			goodsService.deleteGoods(paramVO);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData("");
			result.setMsg("데이터를 삭제하였습니다.");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, " 데이터를 삭제 하는 중"));
		}

		return result;
	}

	@RequestMapping(value = "/downloadgoodsimage.do")
	@ResponseBody
	public void downloadgoodsimage(HttpServletRequest request, HttpServletResponse response, GoodsImageVO paramVO)
			throws Exception {

		try {
			GoodsImageVO viewVO = goodsimageService.selectGoodsImage(paramVO);
			if (viewVO != null) {
				String saveFileName = viewVO.getSaveFilename();
				String uploadFolderPath = attachFileSavePath + File.separator + "goods";
				EgovFileMngUtil.downFile(request, response, viewVO.getOriFilename(),
						uploadFolderPath + File.separator + saveFileName);
			} else
				throw new Exception("요청한 파일데이터가 존재하지 않습니다.");
		} catch (Exception ex) {
			try {
				ProjectUtility.writeResponseMessage(response,
						"<script>alert('다운로드 하려는 파일에 문제가 발생하였습니다.'); history.back(); </script>");
			} catch (Exception e) {
				EgovBasicLogger.info(e.getMessage());
			}
		}
	}

	@RequestMapping(value = "/deleteGoodsImageAjax.do")
	@ResponseBody
	public AjaxResult<String> deleteGoodsImageAjax(HttpServletRequest request, GoodsImageVO paramVO) throws Exception {

		AjaxResult<String> result = new AjaxResult<>();

		try {
			GoodsImageVO viewFile = goodsimageService.selectGoodsImage(paramVO);

			if (viewFile != null) {
				String saveFileName = viewFile.getSaveFilename();
				if (!EgovStringUtil.isEmpty(saveFileName)) {
					String saveFullPath = attachFileSavePath + File.separator + "goods" + File.separator + saveFileName;

					try {
						File f = new File(saveFullPath);
						f.delete();
					} catch (Exception ex) {
						EgovBasicLogger.info(ex.getMessage());
					}
				}
			}

			goodsimageService.deleteGoodsImage(paramVO);
			result.setIsSuccess(SystemConstant.AJAX_SUCCESS);
			result.setData("");
			result.setMsg("첨부파일을 삭제하였습니다.");
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setIsSuccess(SystemConstant.AJAX_FAIL);
			result.setMsg(String.format(SystemConstant.AJAX_ERROR_MESSAGE, "첨부파일을 삭제 하는 중"));
		}

		return result;
	}

}
