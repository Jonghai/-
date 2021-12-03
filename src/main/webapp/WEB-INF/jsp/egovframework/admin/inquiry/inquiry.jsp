<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script src="/js/plugins/fancytree/fancytree_all.min.js"></script>
<script src="/js/plugins/datatables/extensions/row_reorder.min.js"></script>

<script type='text/javascript'>


function initControl() {
	
	$("#inquiryDate").datepicker();
	$("#answerDate").datepicker();
	$("#csSeq").select2({minimumResultsForSearch: Infinity});
	
	 var fancytree = $('.tree-ajax').fancytree({
	    	//checkbox: true,
	        //selectMode: 3,
	        source: {
	        	url: '/admin/getInquiryTreeAjax.do'
	        },
	       	 init: function(event, data) {
	        	//$.ui.fancytree.getTree('.tree-ajax').activateKey("0");
	        }, 
	        activate: function(event, data){     
	            $('#list').DataTable().ajax.reload();
	        },
	    });
	
	var table = $('#list').DataTable( {
	serverSide:true
	, processing:true
    , ajax: {
        url: '/admin/getInquiryListAjax.do', //url 정보 수정
        type: 'POST',
        data:function(d){
            var selectedTreeValue = getSelectTreeItem();
            console.log(selectedTreeValue);
         
            if(selectedTreeValue)
            	if(selectedTreeValue.key!="0"){
            	d.csSeq = selectedTreeValue.key;}
            }
    }
	, order: [[ 0, 'desc' ]]
    , columns: [
    	{ 'data': 'seq', visible:false },
		{ 'data': 'csName' ,createdCell:function (td, cellData, rowData, row, col){
			//td cursor 스타일 변경
   			$(td).css('cursor', 'pointer');
	            $(td).click(function(e){
	            	//클릭 이벤트 정의
	            	//클릭한 td 의 데이터 불러오기
	            	var rowData = table.row( $(this).closest('tr') ).data();
	              	
	            	//클릭한 직원의 상세정보 불러 오기
	              	postAjax('/admin/selectInquiryAjax.do', {seq:rowData.seq}, function(data, status){
	              		
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
    	{
            className:      'text-center',
            orderable:      false,
            data:           function(rowObject, f, u, table)
            {
	            var datehtml = "";
            	datehtml+=rowObject.inquiryDate;
	
	            return datehtml;
            },
            'defaultContent': ''
        },
        {'data': 'inquiryTitle'},
    	//무엇을 클릭해야 상세보기 창이 뜨는지 설정하는 코드
    	
        {'data': 'inquiryContent'},
        {
            className:      'text-center',
            orderable:      false,
            data:           function(rowObject, f, u, table)
            {
	            var datehtml = "";
            	datehtml+=rowObject.answerDate;
	
	            return datehtml;
            },
            'defaultContent': ''
        },
        { 'data': 'answerContent' },
    	
 
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
                text: '문의 등록', //메뉴명에 맞는 버튼 이름으로 변경
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
			inquiryDate:{required:true},
			inquiryTitle:{required:true},
			inquiryContent:{required:true},
			csSeq:{required:true}
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
             title: '문의를 삭제하시겠습니까?',
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
           	  	postAjax('/admin/deleteInquiryAjax.do', {seq:seq}, function(data, status){
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
            postAjax('/admin/selectInquiryAjax.do', {seq:$(this).data('seq')}, function(data, status){
            	console.log(data);
                var formInput = $('#form input[type!=radio],#form textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });
        	    $("#csSeq").val(data.data.csSeq).trigger("change.select2");
            });
        }
        else{
        	var selectedNode = getSelectTreeItem();
        	
        	$("#csSeq").val(selectedNode.key);
        	$("#iconName").val("icon-minus2");
        }
    });

    $('#modalSave').on('hidden.bs.modal', function(e) {    	
    	initForm('form');
        $('#modalSave').data('seq', "");
        
    });
    
	//저장 이벤트
	 $('#btnDataSave').click(function(){
	                 
	            var formData = $('#form').serializeObject();
	            
	            ajax(null, '/admin/mergeInquiryAjax.do', formData, function(data, status){
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
	 function getSelectTreeItem(){
			var selectedNode = $(".tree-ajax").fancytree("getTree").getActiveNode();
			return selectedNode;
	 
}
	 

</script>


<!-- 목록 -->
<div class='row m-0 h-100'>
	<div class='col-lg-2 p-0'>
		<div class='card h-100'>
			<div class='card-header header-elements-inline'>
				<h5 class='card-title font-weight-bold'>
					<i class='icon-chevron-right mr-1'></i> 고객사
				</h5>
			</div>
			<div class='card-body'>
				<div class="tree-ajax p-1"></div>
			</div>
		</div>
	</div>
<div class='col-lg-10 p-0'>
<div class='card h-100'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>문의 관리</h5>
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
					<th>고객사</th>
					<th>문의 날짜</th>
					<th>문의 제목</th>
					<th>문의 내용</th>
					<th>답변 일자</th>
					<th>답변 내용</th>
					<th>기능</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>
</div>
</div>	

<div id='modalSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>문의 등록</h5>
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
                                <th>고객사</th>
                                <td><select id="csSeq" name='csSeq' class="from-control">
									<option selected value="" hidden="">고객사 선택</option>
										<c:forEach items="${getCsList}" var="cs">
										<option value="${cs.seq}"><c:out value="${cs.customerName}"/></option>
										</c:forEach>
									</select></td>
                           </tr>
                           <tr>
                                <th>문의 날짜</th>
                                <td><input id='inquiryDate' name='inquiryDate' maxlength='20' class='form-control' type='text' placeholder='문의 날짜'></td>                             
                           </tr>
                            <tr>
                                <th>문의 제목</th>
                                <td><input id='inquiryTitle' name='inquiryTitle' maxlength='20' class='form-control' type='text' placeholder='문의 제목'></td>
                           </tr>
                           <tr>
                                <th>문의 내용</th>
                                <td><textarea style="height: 200px;" id='inquiryContent' name='inquiryContent' maxlength='20' class='form-control' placeholder='문의 내용'></textarea></td>
                           </tr>
                           <tr>
                                <th>답변 일자</th>
                                <td><input id='answerDate' name='answerDate' maxlength='20' class='form-control' type='text' placeholder='답변 일자'></td>                             
                           </tr>
                           <tr>
                                <th>답변 내용</th>
                                <td><textarea style="height: 200px;" id='answerContent' name='answerContent' maxlength='200' class='form-control' placeholder='답변 내용'></textarea></td>
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
				<h5 class='modal-title'>문의 상세</h5>
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
								<th>고객사</th>
								<td><label id='lblcsName'></label></td>
							</tr>
							<tr>
								<th>문의 날짜</th>
								<td><label id='lblinquiryDate'></label></td>
							</tr>
							<tr>
								<th>문의 제목</th>
								<td><label id='lblinquiryTitle'></label></td>
							</tr>
							<tr>
								<th>문의 내용</th>
								<td><label id='lblinquiryContent'></label></td>
							</tr>
							<tr>
								<th>답변 일자</th>
								<td><label id='lblanswerDate'></label></td>
							</tr>
							<tr>
								<th>답변 내용</th>
								<td><label id='lblanswerContent'></label></td>
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
