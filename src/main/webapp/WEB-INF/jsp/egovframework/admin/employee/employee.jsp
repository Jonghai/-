<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>


function initControl() {
	
	$("#departmentSeq").select2({minimumResultsForSearch: Infinity});
	
	var table = $('#list').DataTable( {
	serverSide:true
	, processing:true
    , ajax: {
        url: '/admin/getEmployeeListAjax.do', //url 정보 수정
        type: 'POST'
    }
	, order: [[ 0, 'desc' ]]
    , columns: [
    	{ 'data': 'seq' },
    	//무엇을 클릭해야 상세보기 창이 뜨는지 설정하는 코드
    	{ 'data': 'empName' ,createdCell:function (td, cellData, rowData, row, col){
			
			//td cursor 스타일 변경
   			$(td).css('cursor', 'pointer');
			
	            $(td).click(function(e){
	            	//클릭 이벤트 정의
	            	
	            	//클릭한 td 의 데이터 불러오기
	            	var rowData = table.row( $(this).closest('tr') ).data();
	              	
	            	//클릭한 직원의 상세정보 불러 오기
	              	postAjax('/admin/selectEmployeeAjax.do', {seq:rowData.seq}, function(data, status){
	              		
	              		//상세화면 항목에 데이터 삽입
   	            	$.each(data.data, function(key, value){	   	            		
              			if($('#lbl' + key).length > 0)
              			{
              				$('#lbl' + key).text(value);
              			}
              		});	   	            	
					
	              		//상세화면 seq 지정
   	         		$('#modalView').data('seq', rowData.seq);
   	         		$('#modalView').modal();
                });
       		});
        }, className:'text-center'},
    	{'data': 'empPhone' },
    	{'data': 'deptName' },
    	{'data': 'empRank' },
    	{'data': 'addressZonecode' },
    	{'data': 'addRess' },
    	{'data': 'addressDetail'},
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
                text: '직원 등록', //메뉴명에 맞는 버튼 이름으로 변경
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
	//필수 입력값 체크
	//폼아이디 변경
	//필수 입력값 name 지정
	$('#form').validate({
		rules:{
			empName:{required:true},
			empPhone:{required:true},
			departmentSeq:{required:true},
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
             title: '시스템관리자 삭제하시겠습니까?',
             text: '',
             showCancelButton: true,
             confirmButtonText: '예',
             cancelButtonText: '아니요',
             confirmButtonClass: 'btn btn-success',
             cancelButtonClass: 'btn btn-danger',
             buttonsStyling: false
         }).then(function(result) {
        	 
             if(result.value) {
            	//예
            	//삭제 Url 변경
           	  	postAjax('/admin/deleteEmployeeAjax.do', {seq:seq}, function(data, status){
        			showAjaxMessage(data);
        			
        			if(data.isSuccess === '1')
        			{
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
            postAjax('/admin/selectEmployeeAjax.do', {seq:$(this).data('seq')}, function(data, status){
            	console.log(data);
                var formInput = $('#form input[type!=radio],#form textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });
				 
            });
        }
    });

    $('#modalSave').on('hidden.bs.modal', function(e) {    	
    	initForm('form');
        $('#modalSave').data('seq', "");
        
    });
    
	//저장 이벤트
	 $('#btnDataSave').click(function(){
	                 
	            var formData = $('#form').serializeObject();
	            
	            ajax(null, '/admin/mergeEmployeeAjax.do', formData, function(data, status){
	                showAjaxMessage(data);
	                if (data.isSuccess === '1')
	                {
	                	//목록 새로고침
					    $('#list').DataTable().ajax.reload(null, false);
	                	
					    //모달창 닫기
					    $('#modalSave').modal('hide');
					    
	                }
	            });
	 		
	    });
	
}

</script>


<!-- 목록 -->
<div class='card'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>직원관리</h5>
        <div class='header-elements'>
			<div class='list-icons ml-3'>
          		<!-- <a class='list-icons-item' data-action='collapse'></a> -->
          		<a class='list-icons-item' data-action='reload'></a>
          		<!-- <a class='list-icons-item' data-action='remove></a> -->
          	</div>
       	</div>
	</div>
	<div class='card-body'>
		<table id='list' class='table table-hover' style='text-align: center;'>
			<colgroup>
				<col style=''>
				<col style='width: 20%;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>직원이름</th>
					<th>전화번호</th>
					<th>부서</th>
					<th>직급</th>
					<th>우편번호</th>
					<th>주소</th>
					<th>상세주소</th>
					<th>기능</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>	

<div id='modalSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>직원등록</h5>
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
                                <th>직원이름</th>
                                <td><input id='empName' name='empName' maxlength='20' class='form-control' type='text' placeholder='직원이름'></td>
                           </tr>
                           <tr>
                                <th>전화번호</th>
                                <td><input id='empPhone' name='empPhone' maxlength='20' class='form-control' type='text' placeholder='전화번호'></td>
                           </tr>
                           <tr>
                                <th>부서</th>
                                <td><select id="departmentSeq" name='departmentSeq' class="from-control">
									<option selected value="" hidden="">부서선택</option>
										<c:forEach items="${getDeptList}" var="dept">
										<option value="${dept.seq}"><c:out value="${dept.deptName}"/></option>
										</c:forEach>
									</select></td>
                           </tr>
                           <tr>
                                <th>직급</th>
                                <td><input id='empRank' name='empRank' maxlength='20' class='form-control' type='text' placeholder='직급'></td>
                           </tr>
                           <tr>
                                 <th>우편번호</th>
                                <td><input id='addressZonecode' name='addressZonecode' maxlength='20' class='form-control' type='text' placeholder='우편번호'></td>
                           </tr>
                           <tr>
                                 <th>주소</th>
                                <td><input id='addRess' name='addRess' maxlength='20' class='form-control' type='text' placeholder='주소'></td>
                           </tr>
                           <tr>
                                 <th>상세주소</th>
                                <td><input id='addressDetail' name='addressDetail' maxlength='20' class='form-control' type='text' placeholder='상세주소'></td>
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
				<h5 class='modal-title'>직원목록 상세</h5>
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
								<th>직원이름</th>
								<td><label id='lblempName'></label></td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><label id='lblempPhone'></label></td>
							</tr>
							<tr>
								<th>부서</th>
								<td><label id='lbldeptName'></label></td>
							</tr>
							<tr>
								<th>직급</th>
								<td><label id='lblempRank'></label></td>
							</tr>
							<tr>
								<th>우편번호</th>
								<td><label id='lbladdressZonecode'></label></td>
							</tr>
							<tr>
								<th>주소</th>
								<td><label id='lbladdRess'></label></td>
							</tr>
							<tr>
								<th>상세주소</th>
								<td><label id='lbladdressDetail'></label></td>
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
