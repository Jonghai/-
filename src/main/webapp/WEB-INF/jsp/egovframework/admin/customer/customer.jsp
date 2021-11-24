<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<!-- prefix가 파일을 불러올 수 있도록 처리해주는 부분 -->

<script type='text/javascript'>

//컨트롤 초기화
function initControl()
{
    // DataTables https://ponyozzang.tistory.com/220
    // 데이터 테이블 설명1  https://ponyozzang.tistory.com/220
    // 가장비슷한 형식 : https://zamezzz.tistory.com/310

    var table = $('#list').DataTable( {
    	/*
		Server-side설명	https://zamezzz.tistory.com/310

		*/
		serverSide:true
		, processing:true
        , ajax: {
            url: '/admin/getCustomerListAjax.do', //url 정보 수정
            type: 'POST'
        }
        // order는 [ [ 열 번호, 정렬 순서 ], ... ] 형식으로 설정합니다.
        // 열 번호는 0부터 시작합니다. 0은 1번째 항목, 1은 2번째 항목입니다.
        // 정렬 순서는 오름 차순 = "asc", 
        // 내림 차순 = "desc"로 설정합니다.
        // 복수의 항목으로 정렬하고 싶은 경우에는 다음과 같이 설정합니다.
		, order: [[ 0, 'desc' ]]
        , columns: [
           	{ 'data': 'seq' },//보여지지는 않음
			{ 'data': 'customerName', createdCell:function (td, cellData, rowData, row, col) {                
                                                    //td cursor 스타일 변경 https://www.codingfactory.net/10265
                                                    //pointer설명 http://www.homejjang.com/09/cursor.php
                    $(td).css('cursor', 'pointer');
                    $(td).click(function(e){
                                                    //클릭 이벤트 정의

                                                    //클릭한 td 의 데이터 불러오기
                                                    //closest : https://elena90.tistory.com/entry/jQuery-closet-%EA%B3%BC-parents
                                                    // selector 에서 매칭되는 첫번째 element를 자기 자신을 포함하며 DOM 트리에서 상위 element 로 거슬러 올라가면서 찾아내는 메소드이다.
                                                    // 즉, 현재 위치에서 가장 가까운 element 하나만 찾아내는 것으로 selector 에 매칭되는 모든 element를 찾아내는 .parent() 와 가장 큰 차이점이다.

                                                    // row.data()에 대한 설명 https://datatables.net/reference/api/row().data()
                                                    // 반환값은 배열형태

  	            	var rowData = table.row( $(this).closest('tr') ).data();

                                                    //클릭한 직원의 상세정보 불러 오기
                                                    // function postAjax(url, data, func, async) {
	
                                                    //     if (async === false)
                                                    //         $.ajaxSetup({ async: async });

                                                    //     $.post(url, data, func, "json");

                                                    //     $.ajaxSetup({ async: true });
                                                    // }
   	              	postAjax('/admin/selectCustomerAjax.do', {seq:rowData.seq}, function(data, status){
   	              		console.log(data);


	   	            	$.each(data.data, function(key, value){
	              			if($('#lbl' + key).length > 0)
	              			{
	              				$('#lbl' + key).text(value);
	              			}
	              		});
                                                    //상세화면 seq 지정
                                                    // $(selector).data(key,value)
                                                    // 데이터 저장할 때 사용
	   	         		$('#modalView').data('seq', rowData.seq);

                                                    // View.naver?isHttpsRedirect=true&blogId=ksh81850&logNo=220421356848
	   	         		$('#modalView').modal();
                    });
           		});
            }, className:'text-center'},
			/* { 'data': 'customerName', className:'text-center' }, */
			{ 'data': 'customerManager', className:'text-center' },
			{ 'data': 'customerRank', className:'text-center' },
			{ 'data': 'customerPhone', className:'text-center' },
			{ 'data': 'customerEmail', className:'text-center' },            
            {
	            className:      'text-center',
	            orderable:      false,
	            data:           function(rowObject, f, u, table)
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
                    className: 'btn btn-primary'
                }
            },
            buttons: [
                {
                    text: '고객 등록', //메뉴명에 맞는 버튼 이름으로 변경
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalSave' //저장 모달창 아이디로 변경
                    },
                    action: function(e, dt, node, config) {
                    }
                }
            ],
        }
    } );

	//validate add method
	//jQuery.validator.addMethod('addCheck', function(value, element) {
	//        return (value.length > 0);
	//    }, 'This field is required.'
	//);

	//필수 입력값 체크
	//폼아이디 변경
	//필수 입력값 name 지정
	$('#form').validate({
		rules:{
			customerId:{required:true},
			customerName:{required:true},			
		}
	});

	//필수 입력 항목에 별 표시
	//폼 아이디 변경
	$.each($('#form').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });
}

//이벤트 초기화
function initEvent() {

	//목록 수정버튼 클릭시 이벤트
	$(document).on('click', 'a[role=dataEdit]', function(){

		//모달창 아이디 변경
    	$('#modalSave').data('seq', $(this).data('seq'));
		$('#modalSave').modal();
    });

    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');

    	//안내 문구 변경
         swalInit.fire({
             title: '고객명단을 삭제하시겠습니까?',
             text: '',
             showCancelButton: true,
             confirmButtonText: '예',
             cancelButtonText: '아니요',
             confirmButtonClass: 'btn btn-success',
             cancelButtonClass: 'btn btn-danger',
             buttonsStyling: false
         }).then(function(result) {

             if(result.value) {
            	/*
             	함수 구성
             	function postAjax(url, data, func, async)
             	*/
            	//예
            	//삭제 Url 변경
           	  	postAjax('/admin/deleteCustomerAjax.do', {seq:seq}, function(data, status){
        			showAjaxMessage(data);

        			if(data.isSuccess === '1')
        			{
        				/*
        				https://ponyozzang.tistory.com/220

        				jQuery작성 방법은

        				jQuery(function($){})
        				$(function((){})
        				$(document).ready(function(){})
        				어느것을 사용해도 상관없습니다.

        				주의할 점은 .dataTable()과 .DataTable()을 주의해야 합니다.

        				대문자 또는 소문자로 작성해도 동작은 하지만
        				소문자 .dataTable() 로 작성한 경우에는 반환값이 jQuery 오브젝트이고,
        				대문자 .DataTable()로 작성한 경우에는 반환값이 DataTable의 API 오브젝트가 됩니다.
        				.dataTable()의 경우 .dataTable().api()를 사용하여 API 오브젝트를 취득할 수 있습니다.
        				*/

        				/*
        				https://datatables.net/reference/api/ajax.reload()
        				말그대로 다시 불러오는 함수
        				*/
        				
	        			$('#list').DataTable().ajax.reload();
        			}
        		});
             }
             else if(result.dismiss === swal.DismissReason.cancel) {
           		//아니요
             }
         });
    });

    $('#btnDataEdit').click(function(){
    	$('#modalSave').data('seq', $('#modalView').data('seq'));
    	$('#modalSave').modal();
    });

    //수정화면 상세데이터 바인딩
    $('#modalSave').on('show.bs.modal', function(e) {
        if ($('#modalSave').data('seq'))
    	{
            postAjax('/admin/selectCustomerAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#form input[type!=radio],#form textarea');

        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });
        	    /* $("#userPwd").val(""); */
            });
        }
    });

    $('#modalSave').on('hidden.bs.modal', function(e) {
    	initForm('form');
        $('#modalSave').data('seq', "");
    });

    //저장클릭 이벤트
    $('#btnDataSave').click(function(){
        if ($('#form').valid())
		{
            var formData = $('#form').serializeObject();

            ajax(null, '/admin/mergeCustomerAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
                	//목록 새로고침
				    $('#list').DataTable().ajax.reload(null, false);

				    //모달창 닫기
				    $('#modalSave').modal('hide');
                }
            });
        }
    });
}
</script>

<!-- 목록 -->
<div class='card'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>고객관리자</h5>
        <div class='header-elements'>
			<div class='list-icons ml-3'>
          		<!-- <a class='list-icons-item' data-action='collapse'></a> -->
          		<a class='list-icons-item' data-action='reload'></a>
          		<!-- <a class='list-icons-item' data-action='remove></a> -->
          	</div>
       	</div>
	</div>
	<div class='card-body'>
		<table id='list' class='table table-hover'>
			<colgroup>
				<col style=''>
				<col style='width: 20%;'>
				<col style='width: 10%;'>
				<col style='width: 20%;'>
				<col style='width: 25%;'>
				<col style='width: 25%;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>고객명</th>
					<th>담당자</th>
					<th>직급</th>
					<th>휴대폰</th>
					<th>이메일</th>
					<th>기능</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>

<!-- 등록/수정 창 -->
<div id='modalSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>고객 저장</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='form' name='form' class='form-horizontal'>
                <input id='seq' name='seq' type='hidden' />
                <div class='modal-body'>

                    <div class='datatable-scroll'>
	                    <table class='detailtable mb-3'>
	                    	<colgroup>
	                    		<col style='width:20%'/>
	                    		<col style=''/>
	                    	</colgroup>
	                    	<tbody>
                            <tr>
                                <th>고객명</th>
                                <td><input id='customerName' name='customerName' maxlength='20' class='form-control' type='text' placeholder='고객 이름'></td>
                            </tr>
                            <tr>
                                <th>담당자</th>
                                <td><input id='customerManager' name='customerManager' maxlength='50' class='form-control' type='text' placeholder='담당자'></td>
                            </tr>
                            <tr>
                                <th>직위</th>
                                <td><input id='customerRank' name='customerRank' maxlength='20' class='form-control' type='text' placeholder='직위'></td>
                            </tr>
                            <tr>
                                <th>핸드폰</th>
                                <td><input id='customerPhone' name='customerPhone' maxlength='20' class='form-control' type='text' placeholder='핸드폰'></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input id='customerEmail' name='customerEmail' maxlength='100' class='form-control' type='text' placeholder='이메일'></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 상세보기 창 -->
<div id='modalView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>고객목록 상세</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>
			<div class='modal-body'>
				<div class='datatable-scroll'>
					<table class='detailtable mb-3'>
						<colgroup>
							<col style='width: 20%' />
							<col style='' />
						</colgroup>
						<tbody>
							<tr>
								<th>고객명</th>
								<td><label id='lblcustomerName'></label></td>
							</tr>
							<tr>
								<th>담당자</th>
								<td><label id='lblcustomerManager'></label></td>
							</tr>
							<tr>
								<th>직위</th>
								<td><label id='lblcustomerRank'></label></td>
							</tr>
							<tr>
								<th>핸드폰</th>
								<td><label id='lblcustomerPhone'></label></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><label id='lblcustomerEmail'></label></td>
							</tr>							
						</tbody>
					</table>
				</div>
			</div>
			<div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
