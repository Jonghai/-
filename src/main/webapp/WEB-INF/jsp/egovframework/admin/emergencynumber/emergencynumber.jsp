<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>


function initControl() {  
	
	var table = $('#list').DataTable( {
	serverSide:true
	, processing:true
    , ajax: {
        url: '/admin/getEmergencynumberListAjax.do', //url 정보 수정
        type: 'POST'
    }
	, order: [[ 0, 'desc' ]]
    , columns: [
    	{'data': 'seq' },
    	{'data': 'employeeSeq' },
    	{'data': 'emerNum' },
    	{'data': 'empName' },
    	{'data': 'empRel' },
    	
    ],     
    buttons: {
    	dom: {
            button: {
                className: 'btn btn-primary'
            }
        },
        buttons: [
            {
                text: '비상연락망 등록', //메뉴명에 맞는 버튼 이름으로 변경
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
	            
	            ajax(null, '/admin/mergeEmergencynumberAjax.do', formData, function(data, status){
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
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>비상연락망 관리</h5>
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
				<col style='width: 20%;'>
				<col style='width: 20%;'>
				<col style='width: 20%;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>직원 외래키 연결</th>
					<th>비상연락망</th>
					<th>비상연락망 주인 성함</th>
					<th>관계</th>
					
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
				<h5 class='modal-title'>비상연락망</h5>
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
                                <th>직원</th>
                                <td><input id='employeeSeq' name='employeeSeq' maxlength='20' class='form-control' type='text' placeholder='직원 외래키'></td>
                           </tr>
                           <tr>
                                <th>비상연락망 전화번호</th>
                                <td><input id='emerNum' name='emerNum' maxlength='20' class='form-control' type='text' placeholder='비상연락망 전화번호'></td>
                           </tr>
                           <tr>
                                <th>비상연락망 주인 성함</th>
                                <td><input id='empName' name='empName' maxlength='20' class='form-control' type='text' placeholder='비상연락망 주인 성함'></td>
                           </tr>
                           <tr>
                                <th>관계</th>
                                <td><input id='empRel' name='empRel' maxlength='20' class='form-control' type='text' placeholder='관계'></td>
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
