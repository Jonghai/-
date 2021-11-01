<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Footer -->
<div class="navbar navbar-expand-lg navbar-light">
	<div class="text-center d-lg-none w-100">
		<span class="navbar-text">
			&copy; 2020 <spring:message code="site.title"></spring:message> by Bjworld21
		</span>
	</div>

	<div class="navbar-collapse collapse" id="navbar-footer">
		<span class="navbar-text">
			&copy; 2020 <spring:message code="site.title"></spring:message> by Bjworld21
		</span>

		<ul class="navbar-nav ml-lg-auto">
		
			<!-- <li class="nav-item"><a href="/images/egovframework/menual/sitelayout.PNG" class="btn btn-outline bg-white text-white border-white border-2 btn-icon rounded-round" title="사이트 레이아웃" data-popup="lightbox" rel="group"><i class="icon-lifebuoy mr-2"></i> 메뉴얼</a></li> -->
			<!-- <li class="nav-item"><a href="http://demo.interface.club/limitless/docs/" class="navbar-nav-link" target="_blank"><i class="icon-file-text2 mr-2"></i> Docs</a></li>
			<li class="nav-item"><a href="https://themeforest.net/item/limitless-responsive-web-application-kit/13080328?ref=kopyov" class="navbar-nav-link font-weight-semibold"><span class="text-pink-400"><i class="icon-cart2 mr-2"></i> Purchase</span></a></li> -->
		</ul>
	</div>
	
</div>
<!-- /footer -->

<div hidden>
	<!-- <img src="/images/egovframework/menual/grid.PNG" data-popup="lightbox" rel="group" title="목록화면">
	<img src="/images/egovframework/menual/departmentmng.PNG" data-popup="lightbox" rel="group" title="부서관리"> -->
</div>


<script>
$(function(){
	var lastIdx = null;
    var table = $('#list').DataTable();
    $('#list tbody').on('mouseover', 'td', function() {
    	if(table.cell(this).index() === undefined)
    		return;
    	
        var colIdx = table.cell(this).index().column;

        if (colIdx !== lastIdx) {
            $(table.cells().nodes()).removeClass('active');
            $(table.column(colIdx).nodes()).addClass('active');
        }
    }).on('mouseleave', function() {
        $(table.cells().nodes()).removeClass('active');
    });
    
    $('.dataTables_length select').select2({
        minimumResultsForSearch: Infinity,
        dropdownAutoWidth: true,
        width: 'auto'
    });
});
</script>

<div id="divLoginForm" title="로그인" style='display: none;'>
	<form id='frmReLogin' name='frmReLogin'>
		<div class='form-group row'>
			<input id='reUserId' name='reUserId' maxlength='20' value='' class='form-control' type='text' placeholder='로그인 아이디'>
		</div>
		<div class='form-group row'>
			<input id='reUserPwd' name='reUserPwd' maxlength='20' value='' class='form-control' type='password' placeholder='로그인 비밀번호'>
		</div>
	</form>
</div>
