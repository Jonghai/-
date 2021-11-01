<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Limitless - Responsive Web Application Kit by Eugene Kopyov</title>

	<!-- Global stylesheets -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css">
	<link href="/css/egovframework/admin/css/icomoon/styles.min.css" rel="stylesheet" type="text/css">
	<link href="/css/egovframework/admin/css/all.min.css" rel="stylesheet" type="text/css">
	<!-- /global stylesheets -->

	<!-- Core JS files -->
	<script src="/css/egovframework/admin/js/jquery.min.js"></script>
	<script src="/css/egovframework/admin/js/bootstrap.bundle.min.js"></script>
	<!-- /core JS files -->

	<script src="/js/plugins/datatables/datatables.min.js"></script>
	<script src="/js/plugins/datatables/extensions/buttons.min.js"></script>
	<script src="/js/plugins/notifications/bootbox.min.js"></script>
	
	<script src="/js/jquery.validate.js"></script>
	<script src="/js/common.js"></script>
	
	<script src="/css/egovframework/admin/js/app.js"></script>
	<!-- /theme JS files -->
	
	<script>
	
		//파일 선택 플러그인
		$(document).on('click', 'input.selectedfilename, button.selectfile', function(){
			$(this).closest('div').find('input[type=file]').trigger('click');
		});
		
		$(document).on('change', 'input[type=file]', function(){
			if($(this)[0].files.length > 0){
				$(this).closest('div').find('input.uploadfilename').val($(this)[0].files[0].name);
			}
		});
		
		$(function(){
			$.extend( $.fn.dataTable.defaults, {
				serverSide:true,
				processing:true,
				order: [[ 0, 'desc' ]],
	            autoWidth: false,
	            columnDefs: [{ 
	                orderable: false,
	                width: 100,
	                targets: [ 0 ]
	            }],
	            dom: '<"datatable-header"fBl><"datatable-scroll"t><"datatable-footer"ip>',
	            language: {
	                search: '<span>검색:</span> _INPUT_',
	                searchPlaceholder: '검색중...',
	                lengthMenu: '<span>보기:</span> _MENU_',
	                paginate: { 'first': '처음', 'last': '마지막', 'next': $('html').attr('dir') == 'rtl' ? '&larr;' : '&rarr;', 'previous': $('html').attr('dir') == 'rtl' ? '&rarr;' : '&larr;' }
	            }
	        });
			
			var table = $('#list').DataTable( {
		        ajax: {
		            url: '/admin/getBoardListAjax.do',
		            type: 'POST',
		            data:function(d){
		            	d.code = getQuerystring().code;
		            }
		        }
		        , 'columns': [
		            { 'data': 'no', visible:false },
					{ 'data': 'subject' , createdCell:function(){
						
					}},
					{ 'data': 'wdate'},
		            {
			            'className':      'text-center',
			            'orderable':      false,
			            'data':           function(rowObject, f, u, table)
			            {
				            var actionButtonItem = "";
				
				            actionButtonItem += "<a href='#' class='dropdown-item' data-seq='"+rowObject.seq+"' role='dataEdit'><i class='icon-pencil5'></i>수정</a>";
				            actionButtonItem += "<a href='#' class='dropdown-item' data-seq='"+rowObject.seq+"' role='dataRemove'><i class='icon-x'></i>삭제</a>";	
				
				            var actionButtonHtml = "";
				            actionButtonHtml += "<div class='list-icons'>";
				            actionButtonHtml += "	<div class='dropdown'>";
				            actionButtonHtml += "<a href='#' class='list-icons-item' data-toggle='dropdown'>";
				            actionButtonHtml += "<i class='icon-menu9'></i>";
				            actionButtonHtml += "</a>";
				            actionButtonHtml += "<div class='dropdown-menu dropdown-menu-right'>";
				            actionButtonHtml += actionButtonItem;
				            actionButtonHtml += "</div>";
				            actionButtonHtml += "</div>";
				            actionButtonHtml += "</div>";
				
				            return actionButtonHtml;
			            },
			            'defaultContent': ''
		            },
		        ],    
		        buttons: {
		        	dom: {
		                button: {
		                    className: 'btn bg-teal-400'
		                }
		            },
		            buttons: [
		            	{
		                    text: '등록',
		                    className: 'btn btn-success',
		                    attr:{
		                    },
		                    action: function(e, dt, node, config) {
		                    	bootbox.confirm({
		                            title: 'Confirm dialog',
		                            message: 'Native confirm dialog has been replaced with Bootbox confirm box.',
		                            buttons: {
		                            	cancel: {
		                                    label: 'Cancel',
		                                    className: 'btn-link'
		                                },
		                                confirm: {
		                                    label: 'Yes',
		                                    className: 'btn-primary'
		                                },
		                                
		                            },
		                            callback: function (result) {
		                                bootbox.alert({
		                                    title: 'Confirmation result',
		                                    message: 'Confirm result: ' + result
		                                });
		                            }
		                        });
		                    }
		                }
		            ],
		        }
		    } );
		});
	</script>
	
</head>

<body class='custom-scrollbars'>
	<div class="navbar navbar-expand-lg navbar-dark navbar-static">
		<div class="d-flex flex-1 d-lg-none">
			<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-mobile">
				<i class="icon-paragraph-justify3"></i>
			</button>
			<button type="button" class="navbar-toggler sidebar-mobile-main-toggle">
				<i class="icon-transmission"></i>
			</button>
		</div>
		<div class="navbar-brand text-center text-lg-left">
			<a href="index.html" class="d-inline-block">
				<img src="/css/egovframework/img/userpage/header_logo.png" class="d-none d-sm-block" alt="">
				<img src="../../../../global_assets/images/logo_icon_light.png" class="d-sm-none" alt="">
			</a>
		</div>
		<div class="collapse navbar-collapse order-2 order-lg-1" id="navbar-mobile">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a href="#" class="navbar-nav-link sidebar-control sidebar-main-toggle d-none d-lg-block">
						<i class="icon-transmission"></i>
					</a>
				</li>
			</ul>
		</div>
		<ul class="navbar-nav flex-row order-1 order-lg-2 flex-1 flex-lg-0 justify-content-end align-items-center">
			<li class="nav-item nav-item-dropdown-lg dropdown dropdown-user h-100">
				<a href="#" class="navbar-nav-link navbar-nav-link-toggler dropdown-toggle d-inline-flex align-items-center h-100" data-toggle="dropdown">
					<img src="../../../../global_assets/images/placeholders/placeholder.jpg" class="rounded-pill mr-lg-2" height="34" alt="">
					<span class="d-none d-lg-inline-block">관리자</span>
				</a>
				<div class="dropdown-menu dropdown-menu-right">
					<a href="#" class="dropdown-item"><i class="icon-switch2"></i> 로그아웃</a>
				</div>
			</li>
		</ul>
	</div>
	<div class="page-content">
		<div class="sidebar sidebar-dark sidebar-main sidebar-expand-lg">
			<div class="sidebar-content">
				<div class="sidebar-section">
					<ul class="nav nav-sidebar">
						${leftMenuHtml}
					</ul>
				</div>
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content-inner">
				<div class="card">
					<div class="card-header">
						<h5 class="card-title font-weight-bold">경영공시 - 경영목표</h5>
					</div>
					<table id='list' class='table table-hover'>
						<colgroup>
							<col style=''>
							<col style='width: 10%;'>  
							<col style='width: 100px;'>
						</colgroup>
						<thead>
							<tr>
								<th>seq</th>
								<th>제목</th>
								<th>등록일</th>
								<th>기능</th>
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>
				</div>
			</div>
			<div class="navbar navbar-expand-lg navbar-light border-bottom-0 border-top">
				<div class="text-center d-lg-none w-100">
					<button type="button" class="navbar-toggler dropdown-toggle" data-toggle="collapse" data-target="#navbar-footer">
						<i class="icon-unfold mr-2"></i>
						Footer
					</button>
				</div>

				<div class="navbar-collapse collapse" id="navbar-footer">
					<span class="navbar-text">
						&copy; 2021. GIVET by BJWorld21
					</span>
				</div>
			</div>
		</div>
	</div>
	
	<div id='modalBoardSave' class='<!-- modal fade -->'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title font-weight-bold'>게시글 저장</h5>
				<button type='button' class='close font-weight-bold' data-dismiss='modal'>&times;</button>
			</div>
            <div class='modal-body'>
	           	<form id='boardForm' name='boardForm' class='form-horizontal'>
               		<input id='seq' name='seq' type='hidden' />
                	<div class="form-group">
						<label>제목:</label>
						<input type="text" class="form-control" placeholder="제목" id='subject' name='subject'>
					</div>
					<div class="form-group">
						<label>카테고리:</label>
						<select id='category'>
							<option value='1'>카테고리1</option>
							<option value='2'>카테고리1</option>
							<option value='3'>카테고리1</option>
						</select>
					</div>
					<div class="form-group">
						<label class="d-block">라디오버튼:</label>

						<label class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" name="gender" checked>
							<span class="custom-control-label">예</span>
						</label>

						<label class="custom-control custom-radio custom-control-inline">
							<input type="radio" class="custom-control-input" name="gender">
							<span class="custom-control-label">아니요</span>
						</label>
					</div>
					<div class="form-group">
						<label class="d-block">체크박스:</label>

						<label class="custom-control custom-checkbox custom-control-inline">
							<input type="checkbox" class="custom-control-input" name="gender" checked>
							<span class="custom-control-label">예</span>
						</label>

						<label class="custom-control custom-checkbox custom-control-inline">
							<input type="checkbox" class="custom-control-input" name="gender">
							<span class="custom-control-label">아니요</span>
						</label>
					</div>
											
					<div class="form-group">
						<label>내용:</label>
						<textarea id='content' name='content' class="form-control"></textarea>
					</div>
					<div class="form-group">
						<label>첨부파일:</label>
						<div class='dropzone' id='divAttachedFile'></div>
					</div>
				</form>
            </div>
            <div class='modal-footer'>
				<button type='button' class='btn bg-primary text-white' id='btnBoardDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>
