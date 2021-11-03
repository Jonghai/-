<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>


function initControl() {  
	
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
    	{ 'data': 'empName' },
    	{'data': 'empPhone' },
    	{'data': 'departmentSeq' },
    	{'data': 'empRank' },
    	{'data': 'addressZonecode' },
    	{'data': 'addRess' },
    	{'data': 'addressDetail' },
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
}

function initEvent() {
	
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
					<th>부서명</th>
					<th>직급</th>
					<th>우편번호</th>
					<th>주소</th>
					<th>상세주소</th>
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
                                <th>부서명</th>
                                <td><input id='departmentSeq' name='departmentSeq' maxlength='20' class='form-control' type='text' placeholder='부서명'></td>
                           </tr>
                           <tr>
                                <th>직급</th>
                                <td><input id='empRank' name='empRank' maxlength='20' class='form-control' type='text' placeholder='직급'></td>
                           </tr>
                           <tr>
                                 <th>우편번호</th>
                                <td><input id='empRank' name='addressZonecode' maxlength='20' class='form-control' type='text' placeholder='우편번호'></td>
                           </tr>
                           <tr>
                                 <th>주소</th>
                                <td><input id='empRank' name='addRess' maxlength='20' class='form-control' type='text' placeholder='주소'></td>
                           </tr>
                           <tr>
                                 <th>상세주소</th>
                                <td><input id='empRank' name='addressDetail' maxlength='20' class='form-control' type='text' placeholder='상세주소'></td>
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
