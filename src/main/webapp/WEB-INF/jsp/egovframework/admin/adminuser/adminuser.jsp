<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>

function initControl()
{
    

    var table = $('#list').DataTable( {
		serverSide:true
		, processing:true
        , ajax: {
            url: '/admin/getAdminUserListAjax.do',
            type: 'POST'
        }
		, order: [[ 0, 'desc' ]]
        , columns: [
           	{ 'data': 'seq', visible:false },
			{ 'data': 'userId', createdCell:function (td, cellData, rowData, row, col) {
       			$(td).css('cursor', 'pointer');
  	            $(td).click(function(e){
  	            	var rowData = table.row( $(this).closest('tr') ).data();
  	              	
   	              	postAjax('/admin/selectAdminUserAjax.do', {seq:rowData.seq}, function(data, status){
	   	            	$.each(data.data, function(i, att){
	              			if($('#lbl' + i).length > 0)
	              			{
	              				$('#lbl' + i).text(att);
	              			}
	              		});
						
	   	         		$('#modalAdminUserView').data('seq', rowData.seq);
	   	         		$('#modalAdminUserView').modal();
                    });
           		});
            }, className:'text-center'},
			{ 'data': 'userName', className:'text-center' },
			{ 'data': 'userPosition', className:'text-center' },
			{ 'data': 'userDeptName', className:'text-center' },
			{ 'data': 'regDate', className:'text-center' },
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
                    text: '시스템관리자 등록',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalAdminUserSave'
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
	
	$('#adminuserForm').validate({
		rules:{
			userId:{required:true},
			userName:{required:true},
			userDeptName:{required:true},
		}
	});

	$.each($('#adminuserForm').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });
}

function initEvent() {
	

	$(document).on('click', 'a[role=dataEdit]', function(){
				
    	$('#modalAdminUserSave').data('seq', $(this).data('seq'));
		$('#modalAdminUserSave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
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
           	  postAjax('/admin/deleteAdminUserAjax.do', {seq:seq}, function(data, status){
        			showAjaxMessage(data);
        			
        			if(data.isSuccess === '1')
        			{
	        			$('#list').DataTable().ajax.reload();
        			}
        		});
             }
             else if(result.dismiss === swal.DismissReason.cancel) {
           	
             }
         });
    });

    $('#btnAdminUserDataEdit').click(function(){
    	$('#modalAdminUserSave').data('seq', $('#modalAdminUserView').data('seq'));
    	$('#modalAdminUserSave').modal();
    });
    
    $('#modalAdminUserSave').on('show.bs.modal', function(e) {
        if ($('#modalAdminUserSave').data('seq'))
    	{
            postAjax('/admin/selectAdminUserAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#adminuserForm input[type!=radio],#adminuserForm textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });
				
        	    $("#userPwd").val("");
                
            });
        }
    });

    $('#modalAdminUserSave').on('hidden.bs.modal', function(e) {
    	initForm('adminuserForm');
        $('#modalAdminUserSave').data('seq', "");
        
    });
    
    $('#btnAdminUserDataSave').click(function(){
        if ($('#adminuserForm').valid())
		{
            
            var formData = $('#adminuserForm').serializeObject();

            ajax($('#modalAdminUserSave div.modal-content'), '/admin/mergeAdminUserAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalAdminUserSave').modal('hide');
                }
            });
        }
    });
    
}
</script>

<div class='card'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>시스템관리자</h5>
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
				<col style='width: 10%;'>
				<col style='width: 100px;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>사용자 아이디</th>
					<th>사용자 이름</th>
					<th>직급</th>
					<th>부서</th>
					<th>등록일</th>
					<th>기능</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>	


<div id='modalAdminUserSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>시스템관리자 저장</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='adminuserForm' name='adminuserForm' class='form-horizontal'>
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
                                <th>사용자 아이디</th>
                                <td><input id='userId' name='userId' maxlength='20' class='form-control' type='text' placeholder='사용자 아이디'></td>
                            </tr>
                            <tr>
                                <th>사용자 비밀번호</th>
                                <td><input id='userPwd' name='userPwd' maxlength='50' class='form-control' type='password' placeholder='사용자 비밀번호'></td>
                            </tr>
                            <tr>
                                <th>사용자 이름</th>
                                <td><input id='userName' name='userName' maxlength='20' class='form-control' type='text' placeholder='사용자 이름'></td>
                            </tr>
                            <tr>
                                <th>직급</th>
                                <td><input id='userPosition' name='userPosition' maxlength='8' class='form-control' type='text' placeholder='직급'></td>
                            </tr>
                            <tr>
                                <th>부서</th>
                                <td><input id='userDeptName' name='userDeptName' maxlength='20' class='form-control' type='text' placeholder='부서'></td>
                            </tr>
                            <tr>
                                <th>자리번호</th>
                                <td><input id='userPhone' name='userPhone' maxlength='20' class='form-control' type='text' placeholder='자리번호'></td>
                            </tr>
                            <tr>
                                <th>핸드폰</th>
                                <td><input id='userPhone2' name='userPhone2' maxlength='20' class='form-control' type='text' placeholder='핸드폰'></td>
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input id='userEmail' name='userEmail' maxlength='100' class='form-control' type='text' placeholder='이메일'></td>
                            </tr>
                           
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnAdminUserDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>

<div id='modalAdminUserView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>시스템관리자 상세</h5>
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
								<th>사용자 아이디</th>
								<td><label id='lbluserId'></label></td>
							</tr>
							<tr>
								<th>사용자 이름</th>
								<td><label id='lbluserName'></label></td>
							</tr>
							<tr>
								<th>직급</th>
								<td><label id='lbluserPosition'></label></td>
							</tr>
							<tr>
								<th>부서</th>
								<td><label id='lbluserDeptName'></label></td>
							</tr>
							<tr>
								<th>자리번호</th>
								<td><label id='lbluserPhone'></label></td>
							</tr>
							<tr>
								<th>핸드폰</th>
								<td><label id='lbluserPhone2'></label></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><label id='lbluserEmail'></label></td>
							</tr>
							<tr>
								<th>등록일</th>
								<td><label id='lblregDate'></label></td>
							</tr>

						</tbody>
					</table>
				</div>
			</div>
			<div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnAdminUserDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
