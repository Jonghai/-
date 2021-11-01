package com.bjworld.groupware.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageNavigator {
	private static final Logger logger = LoggerFactory.getLogger(PageNavigator.class);

	private int TotCnt = 0; // 전체 아이템의 갯수
	private int ItemCntPerView = 10; // 한페이지에 보여줄 아이템 갯수
	private int PageCntPerView = 10; // 한페이지에 보여줄 페이지 링크 갯수
	private int CurPageIndex = 0; // 현재 페이지 인덱스
	private static int CurPageGroupIndex = 0; // 현재 페이지 인덱스
	private int MaxPageIndex = 0; // 최대로 보여줘야할 페이지 인덱스갯수를 표시
	private int MaxPageGroupIndex = 0; // 최대로 보여줘야할 페이지 갯수를 표시
	private int FirstItemIndex = 0; // 현재 페이지의 첫번째 아이템 인덱스
	private int LastItemIndex = 0; // 현재 페이지의 마지막 아이템 인덱스
	private int FirstPageIndex = 0; // 현재 페이지의 첫번째 아이템 인덱스
	private int LastPageIndex = 0; // 현재 페이지의 마지막 아이템 인덱스
	private String PageLinkUrl = ""; // 페이지 인덱스를 클릭했을때 호출하는 기본URL
	int[] ArryPageLink;// 기본으로 페이지링크는 10개를 표시

	/**

     * 생성자

     * 

     * @param pTotCnt : 총 아이템의 갯수

     * @param pItemCntPerView : 한페이지에 보여줄 아이템 갯수

     * @param pPageCntPerView : 한페이지에 보여줄 페이지 링크 갯수

     * @param pPageLinkUrl : 페이지이동을 위한 URL

     */

    public PageNavigator(int pTotCnt, int pItemCntPerView, int pPageCntPerView, String pPageLinkUrl) {

        TotCnt = pTotCnt;
        ItemCntPerView = pItemCntPerView;
        PageCntPerView = pPageCntPerView;
        PageLinkUrl = pPageLinkUrl;
        InitPage();
    }

	public int getTotCnt() {
		return TotCnt;
	}

	public void setTotCnt(int totCnt) {
		TotCnt = totCnt;
	}

	public int getItemCntPerView() {
		return ItemCntPerView;
	}

	public void setItemCntPerView(int itemCntPerView) {
		ItemCntPerView = itemCntPerView;
	}

	public int getCurPageGroupIndex() {
		return CurPageIndex;
	}

	/**
	 * 
	 * @param curPageGroupIndex
	 *            - the curPageGroupIndex to set
	 * 
	 */
	public void setCurPageGroupIndex(int curPageGroupIndex) {
		CurPageGroupIndex = curPageGroupIndex;
	}

	public void setCurPage(int curPage) {
		CurPageIndex = curPage;
	}

	/**
	 * 
	 * @return
	 * 
	 */
	public int getFirstItemIndex() {
		return FirstItemIndex;
	}

	/**
	 * 
	 * @param firstItemIndex
	 *            - the firstItemIndex to set
	 * 
	 */
	public void setFirstItemIndex(int firstItemIndex) {
		FirstItemIndex = firstItemIndex;
	}

	/**
	 * 
	 * @return
	 * 
	 */
	public int getLastItemIndex() {
		return LastItemIndex;
	}

	/**
	 * 
	 * @param lastItemIndex
	 *            - the lastItemIndex to set
	 * 
	 */
	public void setLastItemIndex(int lastItemIndex) {
		LastItemIndex = lastItemIndex;
	}

	/**
	 * @return
	 */
	public int getFirstPageIndex() {
		return FirstPageIndex;
	}

	/**
	 * @param firstPageIndex
	 *            - the firstPageIndex to set
	 */
	public void setFirstPageIndex(int firstPageIndex) {
		FirstPageIndex = firstPageIndex;
	}
	/**
	 * @return
	 */
	public int getLastPageIndex() {
		return LastPageIndex;
	}

	/**
	 * @param lastPageIndex
	 *            - the lastPageIndex to set
	 */
	public void setLastPageIndex(int lastPageIndex) {
		LastPageIndex = lastPageIndex;
	}

	/**
	 * @return
	 */
	public String getPageLinkUrl() {
		return PageLinkUrl;
	}

	/**
	 * @param pageLinkUrl
	 *            - 페이지이동을 위한 URL설정
	 */
	public void setPageLinkUrl(String pageLinkUrl) {
		PageLinkUrl = pageLinkUrl;
	}

	// 페이지 초기설정
	public void InitPage() {
		// 최대 페이지인덱스 갯수 계산
		if (TotCnt % ItemCntPerView == 0) { // 전체 아이템갯수와 한화면에 보여줄 아이템겟수설정값이 딱떨어지면 몫이 MaxPageIndex
			MaxPageIndex = TotCnt / ItemCntPerView;
		} else { // 전체 아이템갯수와 한화면에 보여줄 아이템겟수설정값이 딱 떨어지지않으면 나머지 데이터를 보여주기위해 페이지1 추가
			MaxPageIndex = TotCnt / ItemCntPerView + 1;
		}
		// 최대 페이지그룹 갯수 계산
		if (MaxPageIndex % PageCntPerView == 0) { // 전체 페이지인덱스갯수와 한번에 보여줄 인덱스그룹수를 나누었을때 0으로 떨어지면 가장마지막페이지그룹인덱스는 묷으로 설정
			MaxPageGroupIndex = MaxPageIndex / PageCntPerView;
		} else {// 그렇지않고 나머지가 있으면 추가 페이지그룹설정
			MaxPageGroupIndex = MaxPageIndex / PageCntPerView + 1;
		}
		FirstPageIndex = 0;// 첫번째 페이지인덱스
		if (MaxPageGroupIndex > 1) {// 표시할 마지막 페이지 인덱스
			LastPageIndex = PageCntPerView;
		} else {
			LastPageIndex = MaxPageIndex;
		}
	}

	// 해당 인덱스의 페이지설정
	// 리턴값으로 조회된 첫번째 게시글의 순번을 반환(역순)
	public int SetIndex(String pindex) {

		// 최초로딩시 페이지그룹을 초기화한다(CurPageGroupIndex변수는 statics로 선언되어있음)
		if (pindex.equals("0")) {
			CurPageGroupIndex = 0;
		}

		if (pindex.equals("prev")) {// 이전페이지인경우 가장 처음이 아닌경우 페이지그룹인덱스 1감소
			if ((CurPageGroupIndex - 1) >= 0) {
				CurPageGroupIndex--;
			}
			// 현재 페이지인덱스는 이동한 페이지그룹의 첫번째 페이지인덱스로 설정된다.
			CurPageIndex = CurPageGroupIndex * PageCntPerView;
		} else if (pindex.equals("next")) {// 다음페이지인경우 가장 마지막이 아닌경우 페이지그룹인덱스 1증가
			if ((CurPageGroupIndex + 1) < MaxPageGroupIndex) {
				CurPageGroupIndex++;
			}
			// 현재 페이지인덱스는 이동한 페이지그룹의 첫번째 페이지인덱스로 설정된다.
			CurPageIndex = CurPageGroupIndex * PageCntPerView;
		} else {
			CurPageIndex = Integer.valueOf(pindex);
		}
		// 한페이지에 보여지는 첫번째 아이템의 인덱스번호
		FirstItemIndex = (CurPageIndex + 1) * ItemCntPerView - ItemCntPerView;
		// 한페이지에 보여지는 마지막 아이템의 인덱스번호
		LastItemIndex = (CurPageIndex + 1) * ItemCntPerView;
		// 리스트페이지의 URL세팅(향후 파라미터로)
		// PageLinkUrl = "/index.do?curPageIndex=";
		// 첫번째 페이지인덱스(페이지그룹인덱스 * 인덱스갯수 * 보여지는 아이템갯수) - 보여지는 아이템갯수
		FirstPageIndex = (CurPageGroupIndex * PageCntPerView);
		if (MaxPageGroupIndex == (CurPageGroupIndex + 1)) {// 이동한 페이지그룹이 마지막 그룹페이지인경우 남은 페이지인덱스만 보여준다
			LastPageIndex = (CurPageGroupIndex * PageCntPerView) + (MaxPageIndex % PageCntPerView);
		} else if (MaxPageGroupIndex > 1) {// 1보다 크고 마지막 페이지그룹전일경우 페이지를 꽉채워서 보여준다.
			LastPageIndex = (CurPageGroupIndex * PageCntPerView) + PageCntPerView;
		} else {// 그룹페이지가 1개일경우는 그냥 가장 마지막페이지인덱스를 보여준다
			LastPageIndex = MaxPageIndex;
		}

		// 게시글 첫번째 순번을 반환
		int retBbsNum = TotCnt;
		retBbsNum = TotCnt - (CurPageIndex * ItemCntPerView);
		// logger.debug(" retBbsNum : " + retBbsNum, 1);
		return retBbsNum;

	}

	// 해당 인덱스의 페이지설정(페이지 html을 리턴한다.)
	public String getPageHtml() {
		// 현재 페이지가 첫번째 인덱스이면 Previous버튼이 비활성화 , 마지막 인덱스이면 Next버튼 비활성화
		String prevFalg = "";// disabled
		String nextFalg = "";
		if (CurPageIndex == FirstPageIndex) {
			prevFalg = "disabled";
		}
		if (CurPageIndex == LastPageIndex - 1) {
			nextFalg = "disabled";
		}

		// 페이지용 HTML구성

		StringBuffer sbPageHtml = new StringBuffer();
		sbPageHtml.append("<li class='list-inline-item'>");
		sbPageHtml.append(" <a class='u-pagination-v1__item u-pagination-v1-5 rounded g-pa-4-13' href='"+PageLinkUrl+"&pn=prev' aria-label='이전'>");
		sbPageHtml.append(" <span aria-hidden='true'><i class='fa fa-angle-left'></i></span>");
		sbPageHtml.append(" <span class='sr-only'>이전</span>");
		sbPageHtml.append(" </a></li>");

		// 실제 보이는 페이지번호는 1부터 시작하므로 루프돌릴때 +1 처리
		for (int pageIndex = FirstPageIndex; pageIndex < LastPageIndex; pageIndex++) {
			if (pageIndex == (CurPageIndex)) { // 내가 이동한 현재페이지를 표시
				sbPageHtml.append("<li class='list-inline-item g-hidden-sm-down'>");
				sbPageHtml.append("<a class='u-pagination-v1__item u-pagination-v1-5 rounded g-pa-4-11' href='"+PageLinkUrl+"&pn="+String.valueOf(pageIndex+1)+"'>"+String.valueOf(pageIndex+1) +"</a>");
				sbPageHtml.append("</li>");

			} else {
				sbPageHtml.append("<li class='list-inline-item g-hidden-sm-down'>");
				sbPageHtml.append("<a class='u-pagination-v1__item u-pagination-v1-5 rounded g-pa-4-11' href='"+PageLinkUrl+"&pn="+String.valueOf(pageIndex+1)+"'>"+String.valueOf(pageIndex+1) +"</a>");
				sbPageHtml.append("</li>");
			}
		}

		sbPageHtml.append(" <li class='list-inline-item'>");
		sbPageHtml.append(" <a class='u-pagination-v1__item u-pagination-v1-5 rounded g-pa-4-13' href='").append(PageLinkUrl).append("&pn=next' aria-label='다음'>");
		sbPageHtml.append(" <span aria-hidden='true'><i class='fa fa-angle-right'></i></span>");
		sbPageHtml.append(" <span class='sr-only'>다음</span>");
		sbPageHtml.append(" </a>");
		sbPageHtml.append(" </li>");
		return sbPageHtml.toString();
	}
}
