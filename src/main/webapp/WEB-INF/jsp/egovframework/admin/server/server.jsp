<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<style>
    .form-setting {
        display: inline-block;
        width: 60px;
        text-align: center;
        padding: 0.375rem 0.75rem;
        font-size: 1rem;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #ddd;
        border-radius: 0.25rem;
        /* transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out; */
    }
</style>

<script type='text/javascript'>

function initControl() {	
	$("#serverStart,#serverEnd").datepicker();
	$("#csSeq").select2({minimumResultsForSearch: Infinity});
	
	var table = $('#list').DataTable( {
	serverSide:true
	, processing:true
    , ajax: {
        url: '/admin/getServerListAjax.do', //url 정보 수정
        type: 'POST'
    }
	, order: [[ 0, 'desc' ]]
    , columns:
    [
    	{ 'data': 'seq', visible:false},
    	{ 'data': 'customerSeq'},
    	//무엇을 클릭해야 상세보기 창이 뜨는지 설정하는 코드
    	{ 'data': 'serverAddress',createdCell:function (td, cellData, rowData, row, col){			
			//td cursor 스타일 변경
   			$(td).css('cursor', 'pointer');			
	            $(td).click(function(e){
	            	//클릭 이벤트 정의	            	
	            	//클릭한 td 의 데이터 불러오기
	            	var rowData = table.row( $(this).closest('tr') ).data();
	              	
	            	//클릭한 직원의 상세정보 불러 오기
	              	postAjax('/admin/selectServerAjax.do', {seq:rowData.seq}, function(data, status){
	              		
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
    	{'data': 'serverIp', className:'text-center' },
    	{'data': 'os', className:'text-center'},
        {'data': 'serverPort', className:'text-center'},
    	{'data': 'serverLocation', className:'text-center'},
    	{
            className:      'text-center',
            orderable:      false,
            data:           function(rowObject, f, u, table)
            {
	            var datehtml = "";
            	datehtml+=rowObject.serverStart;
            	datehtml+='~';
            	datehtml+=rowObject.serverEnd;
	
	            return datehtml;
            },
            'defaultContent': ''
        },     	
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
	            
	            return actionButtonHtml; },
            'defaultContent': '' },    	
    ],     
    buttons: {
    	dom: {
            button: {
                className: 'btn btn-primary'                

            }
        },
        buttons: [
            {
                text: '서버 등록', //메뉴명에 맞는 버튼 이름으로 변경
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
            customerSeq:{required:true},
			serverAddress:{required:true},
			serverIp:{required:true}
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

//번호만 적혀있는지확인
function checkNumber(objName,objSize,nextObjName)
{
    // var objEv = event.srcElement;
    // var numPattern = /([^0-9])/;
    // numPattern = objEv.value.match(numPattern);
    // if(numPattern != null){
    //     alert("숫자만 입력해 주세요!");
    //     objEv.value="";
    //     objEv.focus();
    //     return false;
    // }

    if( objName.value.length == objSize ){
    nextObjName.focus();
    return;
  }
}

function moveFocus(nextObjName)
{   
	var objSize = $(this).attr("size");
	var value = $(this).val();
	
	console.log(size);
   /*  var objEv = event.srcElement;
    var numPattern = /([^0-9])/;
    var numPattern = objEv.value.match(numPattern);
    if (numPattern != null) {
        alert("숫자만 입력해 주세요!");
        objEv.value = "";
        objEv.focus();
        return false;
    } */
    
    var v = $(this).val();
	$(this).val(v.replace(/[^0-9]/gi,''));

    //다음칸으로 이동 http://www.webmadang.net/javascript/javascript.do?action=read&boardid=8001&page=13&seq=19
    if( value.length == objSize ){
    	nextObjName.focus();
    	return;
  	}
}

// function ValidateEmail(inputText) {
//     var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
//     if (mailformat.test(inputText)) {
//         return true;
//     } else {
//         toastr.warning("입력하신 값은 이메일 형식이 아닙니다.");
//         //focus 처리가 필요하면 이곳에! $("#email").focus();
//         return false;
//     }
// }

function ValidateIPaddress(inputText) {
    var ipformat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    if (!ipformat.test(inputText)) {
        toastr.warning('아이피 주소를 다시 입력해 주세요.');            
        //focus 처리가 필요하면 이곳에! $("#ip").focus();
        return;
    }
}

function initEvent() {	
	$("input.ip").keyup(function(){
		var objSize = 3;
	    var nextObjName = "";

	    var controlId = $(this).attr("id");
        // 'serverIp'부분을 없앰("blank") -> 그럼 이제 숫자부분만 남음
	    var controlIndex = controlId.replace("serverIp", "");
	    
	    if(controlIndex === "1"){
	    	nextObjName =  "serverIp2";
	    }
	    else if(controlIndex === "2"){
	    	nextObjName =  "serverIp3";
	    }
	    else if(controlIndex === "3"){
	    	nextObjName =  "serverIp4";
	    }else {
	    	nextObjName =  "os";
	    }

	    //serverIp1
	    var value = $(this).val();
		$(this).val(value.replace(/[^0-9]/gi,''));

	    //다음칸으로 이동 http://www.webmadang.net/javascript/javascript.do?action=read&boardid=8001&page=13&seq=19
	    if( value.length == objSize ){
	    	$("#" + nextObjName).focus();
	    	return;
	  	}
	});

    //서버포트에는 번호만
    $("input.serverPort").keyup(function(){
        var value = $(this).val();
		$(this).val(value.replace(/[^0-9]/gi,''));
    });
	
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
             title: '서버를 삭제하시겠습니까?',
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
           	  	postAjax('/admin/deleteServerAjax.do', {seq:seq}, function(data, status){
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
            postAjax('/admin/selectServerAjax.do', {seq:$(this).data('seq')}, function(data, status){
            	
            	console.log(data.data.serverIp);
            	
            	var ipArr = data.data.serverIp.split('.');
            	
                var formInput = $('#form input[type!=radio],#form textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
        		    
        		    $('#serverIp1').val(ipArr[0]);
        		    $('#serverIp2').val(ipArr[1]);
        		    $('#serverIp3').val(ipArr[2]);
        		    $('#serverIp4').val(ipArr[3]);        		    
                });
        	    $("#csSeq").val(data.data.csSeq).trigger("change.select2");
            });
        }
    });

    $('#modalSave').on('hidden.bs.modal', function(e) {    	
    	initForm('form');
        $('#modalSave').data('seq', "");        
    });
    
    //저장 이벤트
    $('#btnDataSave').click(function () {
        if ($('#form').valid()) {
            var formData = $('#form').serializeArray();
            var serverIp = $('#serverIp1').val() + "." + $('#serverIp2').val() + "." + $('#serverIp3').val() + "." + $('#serverIp4').val();
			
            // var serverIpCheck = $('#serverIp1').val() + $('#serverIp2').val() + $('#serverIp3').val() + $('#serverIp4').val()
            
            ValidateIPaddress(serverIp);            
            formData.push({ "name": "serverIp", "value": serverIp });


            //콘솔에 표시
            console.log(formData);
            ajax(null, '/admin/mergeServerAjax.do', formData, function (data, status) {
                showAjaxMessage(data);
                if (data.isSuccess === '1') {
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
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>서버관리</h5>
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
					<th>고객번호</th>
					<th>서버주소</th>
					<th>아이피  </th>									
					<th>운영체제</th>															
					<th>접속포트</th>
                    <th>설치위치</th>
					<th>계약 시작일~종료일</th>	
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
				<h5 class='modal-title'>서버 등록</h5>
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
                                <th>고객</th>
                                <td>
                                <!-- 
                                Select 태그는 name 속성을 통해 서버에서 접근 가능하다                                 
                                -->
                                	<select id="customerSeq" name='customerSeq' class="from-control">
									<option selected value="" hidden="">고객번호</option>
										<c:forEach items="${getCsList}" var="cs">
											<option value="${cs.seq}">
											<!-- https://lifejusik1004.tistory.com/entry/JSP-JSTL-cout-%ED%83%9C%EA%B7%B8-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0 -->
											<c:out value="${cs.customerName}"/>																				  
											</option>										
										</c:forEach>
									</select>
								</td>
                           </tr>
							<tr>
								<th>서버주소</th>
								<td><textarea id='serverAddress' name='serverAddress' maxlength='20' class='form-control' placeholder='서버주소'></textarea></td>
							</tr>
							<tr>
								<th>아이피</th>
								<td>
                                    <!-- <textarea id='serverPort' name='serverPort' maxlength='50' class='form-control' placeholder='포트번호'></textarea></td> -->
                                    <!-- <input type="text" id="serverIp1" onkeyup="moveFocus(this.form.serverIp2)" class='form-setting' size="3" maxlength="3"> .
                                    <input type="text" id="serverIp2" onkeyup="moveFocus(this.form.serverIp3)" class='form-setting' size="3" maxlength="3"> .
                                    <input type="text" id="serverIp3" onkeyup="moveFocus(this.form.serverIp4)" class='form-setting' size="3" maxlength="3"> .
                                    <input type="text" id="serverIp4" onkeyup="moveFocus(this.form.os)" class='form-setting' size="3" maxlength="3"> -->
                                    
                                    <input type="text" id="serverIp1" class='form-setting ip' maxlength="3"> .
                                    <input type="text" id="serverIp2" class='form-setting ip' maxlength="3"> .
                                    <input type="text" id="serverIp3" class='form-setting ip' maxlength="3"> .
                                    <input type="text" id="serverIp4" class='form-setting ip' maxlength="3">                                    
                                </td>
							</tr>
	                    	<tr>
								<th>운영체제</th>
								<td><input id='os' name='os' maxlength='20' class='form-control' placeholder='운영체제'></textarea></td>
							</tr>
                            <tr>
                                <th>접속포트</th>
                                <td><input id='serverPort' name='serverPort' maxlength='200' class='form-control serverPort' placeholder='서버포트'></textarea></td>                                
                            </tr>
							<tr>
								<th>설치위치</th>
								<td><input id='serverLocation' name='serverLocation' maxlength='200' class='form-control' placeholder='서버위치'></textarea></td>
							</tr>
							<tr>
                                <th>서버 시작일~종료일</th>
                                <td>시작일<input id='serverStart' name='serverStart' maxlength='20' class='form-control' type='text' placeholder='서버 시작일'>종료일 
                                <input id='serverEnd' name='serverEnd' maxlength='20' class='form-control' type='text' placeholder='서버 종료일'></td>                             
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
                <h5 class='modal-title'>서버 상세</h5>
                <button type='button' class='close' data-dismiss='modal'>&times;</button>
            </div>
            <div class='modal-body'>
                <div class='datatable-scroll'>

                    <!-- 메뉴부분 -->
                    <table class='detailtable mb-3'>
                        <colgroup>
                            <col style='width: 20%' />
                            <col style='' />
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>순서</th>
                                <td><label id='lblseq'></label></td>
                            </tr>
                            <tr>
                                <th>고객번호</th>
                                <td><label id='lblcustmerSeq'></label></td>
                            </tr>
                            <tr>
                                <th>서버주소</th>
                                <td><label id='lblserverAddress'></label></td>
                            </tr>
                            <tr>
                                <th>아이피</th>
                                <td><label id='lblserverIp'></label></td>
                            </tr>
                            <tr>
                                <th>운영체제</th>
                                <td><label id='lblos'></label></td>
                            </tr>
                            <tr>
                                <th>접속포트</th>
                                <td><label id='lblserverPort'></label></td>
                            </tr>
                            <tr>
                                <th>설치위치</th>
                                <td><label id='lblserverLocation'></label></td>
                            </tr>
                            <tr>
                                <th>계약 시작일~종료일</th>
                                <td><label id='lblserverStart'></label>
                                    ~ <label id='lblserverEnd'></label></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class='modal-footer border-top'>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'
                    id='btnDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
            </div>
        </div>
    </div>
</div>