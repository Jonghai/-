<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><spring:message code="site.title"></spring:message> 관리자</title>

<!-- Global stylesheets -->
<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
<link href="/css/egovframework/admin/css/icomoon/styles.min.css" rel="stylesheet" type="text/css">
<link href="/css/egovframework/admin/css/all.min.css" rel="stylesheet" type="text/css">
<link href="/css/egovframework/admin/css/custom.css" rel="stylesheet" type="text/css">
<!-- /global stylesheets -->

<!-- Core JS files -->
<script src="/css/egovframework/admin/js/jquery.min.js"></script>
<script src="/css/egovframework/admin/js/bootstrap.bundle.min.js"></script>
<!-- /core JS files -->

<script src="/js/plugins/datatables/datatables.min.js"></script>
<script src="/js/plugins/datatables/extensions/buttons.min.js"></script>
<script src="/js/plugins/notifications/bootbox.min.js"></script>

<script src="/js/plugins/ckeditor/ckeditor.js"></script>
<script src="/js/plugins/ckeditor/editor_ckeditor.js"></script>
<script src="/js/plugins/dropzone/dropzone.min.js"></script>

<!-- select2 -->
<script src="/js/plugins/select2/select2.min.js"></script>
<link href="/js/plugins/select2/select2.css" rel="stylesheet" type="text/css">

<!-- sweet_alert -->
<script src="/js/plugins/sweetalert/sweet_alert.min.js"></script>
<link href="/js/plugins/sweetalert/sweet_alert.css" rel="stylesheet" type="text/css">

<!-- toastr -->
<script src="/js/plugins/toastr/toastr.min.js"></script>
<link href="/js/plugins/toastr/toastr.css" rel="stylesheet" type="text/css">

<script src="/js/plugins/blockui/blockui.min.js"></script>

<!-- jqueryui -->
<script src="/js/plugins/jqueryui/widgets.min.js"></script>
<script src="/js/plugins/jqueryui/effects.min.js"></script>
<script src="/js/plugins/jqueryui/datepicker-ko.js"></script>

<script src="/js/jquery.validate.js"></script>
<script src="/js/messages_ko.js"></script>
<script src="/js/common.js"></script>
<script src="/js/view/board.js"></script>

<script src="/css/egovframework/admin/js/app.js"></script>
<!-- /theme JS files -->

<script>
	Dropzone.autoDiscover = false;
	
	var swalInit = swal.mixin({
	    buttonsStyling: false,
	    confirmButtonClass: 'btn btn-primary',
	    cancelButtonClass: 'btn btn-light'
	});
	
	$(function(){
		$.extend( $.fn.dataTable.defaults, {
			serverSide:true,
			processing:true,
	        autoWidth: false,
	        //dom: '<"datatable-header"iBl><"datatable-scroll"t><"datatable-footer"p>',
	        dom: '<"datatable-header"fBl><"datatable-scroll"t><"datatable-footer"ip>',
	       /*  language: {
	            search: '<span>검색:</span> _INPUT_',
	            searchPlaceholder: '검색',
	            loadingRecords : "로딩중...",
	            lengthMenu: '<span>보기:</span> _MENU_',
	            zeroRecords: "검색된 데이터가 없습니다.",
	            emptyTable: "데이터가 없습니다.",
	            info:"- 총 _TOTAL_ 중 _START_에서 _END_까지 표시",
	            infoEmpty:"데이터가 없습니다.",
	            paginate: {
	                "first":      "처음",
	                "last":       "마지막",
	                "next":       "다음",
	                "previous":   "이전"
	            },
	            aria: {
	                "sortAscending":  ": 오름차순으로 정렬",
	                "sortDescending": ": 내림차순으로 정렬"
	            }
	        }, */
	        lengthMenu: [[20, 50, 100, 1000], [20, 50, 100, 1000]],
	        pageLength:20
	    });	 
		
		if(typeof initControl !== 'undefined')
			initControl();
		if(typeof initEvent !== 'undefined')
			initEvent();
		
		var cardcollapseElement = $('[data-action=collapse]');
		var cardContainer = '.card';
		var cardCollapsedClass = 'card-collapsed';
		
		var cardcollapseElement = $('[data-action=collapse]');
		
		cardcollapseElement.on('click', function(e) {
            e.preventDefault();

            const parentContainer = $(this).parents('.card'),
                  collapsibleContainer = parentContainer.find('> .collapse');

            if (parentContainer.hasClass(cardCollapsedClass)) {
                parentContainer.removeClass(cardCollapsedClass);
                collapsibleContainer.collapse('show');
            }
            else {
                parentContainer.addClass(cardCollapsedClass);
                collapsibleContainer.collapse('hide');
            }
        });
		
		const reloadButtonElement = $('[data-action=reload]'),
        overlayContainer = '.card',
        overlayClass = 'card-overlay',
        spinnerClass = 'icon-spinner9 spinner text-body',
        overlayAnimationClass = 'card-overlay-fadeout';


  		// Configure
		reloadButtonElement.on('click', function(e) {
			e.preventDefault();
		    $('#list').DataTable().ajax.reload(null, false);
		     /*  // Create overlay with spinner
		      $(this).parents(overlayContainer).append($('<div class="' + overlayClass + '"><i class="' + spinnerClass + '"></i></div>'));
		
		      // Remove overlay after 2.5s, for demo only
		      setTimeout(function() {
		          $('.' + overlayClass).addClass(overlayAnimationClass).on('animationend animationcancel', function() {
		              $(this).remove();
		          });
		      }, 2500); */
		});
		  
		
		
		
	});
</script>