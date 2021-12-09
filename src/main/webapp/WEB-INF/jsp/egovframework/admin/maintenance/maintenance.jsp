<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>


function initControl() {
	
	$("#requestDate,#proDate").datepicker();
	$("#projectSeq").select2({minimumResultsForSearch: Infinity});
	
	var table = $('#list').DataTable( {
	serverSide:true
	, processing:true
    , ajax: {
        url: '/admin/getMaintenanceListAjax.do', //url 정보 수정
        type: 'POST'
    }
	, order: [[ 0, 'desc' ]]
    , columns: [
    	{ 'data': 'seq', visible:false},
    	//무엇을 클릭해야 상세보기 창이 뜨는지 설정하는 코드
    	{ 'data': 'projectName' ,createdCell:function (td, cellData, rowData, row, col){
			
			//td cursor 스타일 변경
   			$(td).css('cursor', 'pointer');
			
	            $(td).click(function(e){
	            	//클릭 이벤트 정의
	            	
	            	//클릭한 td 의 데이터 불러오기
	            	var rowData = table.row( $(this).closest('tr') ).data();
	              	
	            	//클릭한 직원의 상세정보 불러 오기
	              	postAjax('/admin/selectMaintenanceAjax.do', {seq:rowData.seq}, function(data, status){
	              		$('#btnFinish').data('seq',data.data.seq);
	              		
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
    	{'data': 'requestName'},
    	{'data': 'requestContent'},
    	{
            className:      'text-center',
            orderable:      false,
            data:           function(rowObject, f, u, table)
            {
	            var datehtml = "";
            	datehtml+=rowObject.requestDate;
	
	            return datehtml;
            },
            'defaultContent': ''
        },
    	{'data': 'proResult' },
    	{
            className:      'text-center',
            orderable:      false,
            data:           function(rowObject, f, u, table)
            {
	            var datehtml = "";
            	datehtml+=rowObject.proDate;
	
	            return datehtml;
            },
            'defaultContent': ''
        },
    	{'data': 'proState'},
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
                text: '유지보수 등록', //메뉴명에 맞는 버튼 이름으로 변경
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
			projectSeq:{required:true},
			requestName:{required:true},
			requestContent:{required:true},
			requestDate:{required:true}
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
             title: '데이터를 삭제하시겠습니까?',
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
           	  	postAjax('/admin/deleteMaintenanceAjax.do', {seq:seq}, function(data, status){
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
    $('#btnFinish').click(function(){
    	 postAjax('/admin/updateProStateMaintenanceAjax.do', {seq:$(this).data('seq')}, function(data, status){
      //만약 1(성공)시 list에 새로고침 없이 리로드
         	if(data.isSuccess === '1')
			{
    			$('#list').DataTable().ajax.reload();
			}
         });
    });
    
    //수정화면 상세데이터 바인딩
    $('#modalSave').on('show.bs.modal', function(e) {
        if ($('#modalSave').data('seq'))
    	{
            postAjax('/admin/selectMaintenanceAjax.do', {seq:$(this).data('seq')}, function(data, status){
            	console.log(data);
                var formInput = $('#form input[type!=radio],#form textarea');
                
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });
        	    //수정창에서 선텍된 상태로 나오게 하는 코드
        	    $("#projectSeq").val(data.data.projectSeq).trigger("change.select2");
            });
        }
    });

    $('#modalSave').on('hidden.bs.modal', function(e) {    	
    	initForm('form');
        $('#modalSave').data('seq', "");
        
    });
    
	//저장 이벤트
	 $('#btnDataSave').click(function(){
		 		//if ($('#form').valid()) = 저장버튼을 눌렀을때 폼아이디 값이 유효하다면 저장을 실행한다 유효하지 않다면 실행하지 않고 입력하라고 안내창이 뜸
				 if ($('#form').valid())
				 {
					 
				 
	                 
	            var formData = $('#form').serializeObject();
	            
	            ajax(null, '/admin/mergeMaintenanceAjax.do', formData, function(data, status){
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
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>유지보수관리</h5>
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
					<th>프로젝트명</th>
					<th>요청자명</th>
					<th>요청내용</th>
					<th>요청날짜</th>
					<th>처리결과</th>
					<th>처리일자</th>
					<th>진행 상태</th>
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
				<h5 class='modal-title'>유지보수 등록</h5>
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
                                <th>프로젝트명</th>
                                <td><select id="projectSeq" name='projectSeq' class="from-control">
									<option selected value="" hidden="">프로젝트 선택</option>
										<c:forEach items="${getPjList}" var="pj">
										<option value="${pj.seq}"><c:out value="${pj.projectName}"/></option>
										</c:forEach>
									</select></td>
                           </tr>
                            <tr>
                                <th>요청자명</th>
                                <td><input id='requestName' name='requestName' maxlength='20' class='form-control' type='text' placeholder='요청자명'></td>
                           </tr>
                           <tr>
                                <th>요청내용</th>
                                <td><textarea style="height: 200px;" id='requestContent' name='requestContent' maxlength='200' class='form-control' placeholder='요청내용'></textarea></td>
                           </tr>
                           <tr>
                                <th>요청날짜</th>
                                <td><input id='requestDate' name='requestDate' maxlength='20' class='form-control' type='text' placeholder='요청날짜'></td>                             
                           </tr>
                           <tr>
                                <th>처리결과</th>
                                <td><textarea style="height: 200px;" id='proResult' name='proResult' maxlength='200' class='form-control' placeholder='처리결과 설명'></textarea></td>
                           </tr>
                           <tr>
                                <th>처리일자</th>
                                <td><input id='proDate' name='proDate' maxlength='20' class='form-control' type='text' placeholder='처리일자'></td>                             
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
				<h5 class='modal-title'>상세페이지</h5>
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
								<th>프로젝트명</th>
								<td><label id='lblprojectName'></label></td>
							</tr>
							<tr>
								<th>요청자명</th>
								<td><label id='lblrequestName'></label></td>
							</tr>
							<tr>
								<th>요청내용</th>
								<td><label id='lblrequestContent'></label>
							</tr>
							<tr>
								<th>요청일자</th>
								<td><label id='lblrequestDate'></label></td>
							</tr>
							<tr>
								<th>처리결과</th>
								<td><label id='lblproResult'></label></td>
							</tr>
							<tr>
								<th>처리일자</th>
								<td><label id='lblproDate'></label></td>
							</tr>
							<tr>
								<th>처리상태</th>
								<td><label id='lblproState'></label></td>
							</tr>
			
						</tbody>
					</table>
				</div>
			</div>
			<div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnFinish'>처리완료</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
