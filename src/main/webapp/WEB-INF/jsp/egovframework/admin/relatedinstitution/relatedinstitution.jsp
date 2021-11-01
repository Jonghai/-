<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>

$(document).on('click', 'input.selectedfilename, button.selectfile', function(){
	$(this).closest('div').find('input[type=file]').trigger('click');
});

$(document).on('change', 'input#uploadFile', function(){
	var input = this;
	if($(this)[0].files.length > 0){
		$(this).closest('div').find('input.selectedfilename').val($(this)[0].files[0].name);
		
		var formData = new FormData();
		formData.append("upload", $(this)[0].files[0])
		ajax(null, "/admin/dragDropUploadAjax.do?type=relatedinstitution", formData, function(data, status){
			if(data.uploaded === 1){
				$("#relatedinstitutionForm").append("<input type='hidden' role='logoSaveFilename' name='logoSaveFilename' value='"+data.saveFileName+"' />");
				$("#relatedinstitutionForm").append("<input type='hidden' role='logoOriFilename' name='logoOriFilename' value='"+data.fileName+"' />");
			}
			else{
				toastr.warning("업로드 실패");
				input.val("");
			}
		}, true);
	}
});


function initControl()
{
    $('#institutionCategory').select2({minimumResultsForSearch: Infinity});


    var table = $('#list').DataTable( {
		serverSide:true
		, processing:true
        , ajax: {
            url: '/admin/getRelatedInstitutionListAjax.do',
            type: 'POST'
        }
		, order: [[ 0, 'desc' ]]
        , columns: [
            { 'data': 'seq', visible:false },
			{ 'data': 'institutionName'
                , createdCell:function (td, cellData, rowData, row, col) {
        			    $(td).css('cursor', 'pointer');
   	              	    $(td).click(function(e){
   	              		    var rowData = table.row( $(this).closest('tr') ).data();
   	              	
	   	              	    postAjax('/admin/selectRelatedInstitutionAjax.do', {seq:rowData.seq}, function(data, status){
		   	              	    $.each(data.data, function(i, att){
		              			    if($('#lbl' + i).length > 0)
		              			    {
		              				    $('#lbl' + i).text(att);
		              			    }
		              		    });
		   	              	    
		   	              	    if(data.data.logoSaveFilename)
		   	              	    	$("#lbllogoOriFilename").html("<img src='/upload/relatedinstitution/"+data.data.logoSaveFilename+"' alt='관련기관 logo 이미지'/>");
		   	              	    
		   	         		    $('#modalRelatedInstitutionView').data('seq', rowData.seq);
		   	         		    $('#modalRelatedInstitutionView').modal();
                            });
   	            	
            		    });
                } },
			{ 'data': 'institutionUrl' },
			{ 'data': 'institutionCategoryDesc' },
			{ 'data': function(rowObject){
				if(rowObject.logoSaveFilename){
					return "<img src='/upload/relatedinstitution/"+rowObject.logoSaveFilename+"' style='width:260px' alt='관련기관 logo 이미지'/>";
				}
				return "";
			} },

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
                    text: '관련기관 등록',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalRelatedInstitutionSave'
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
	
	$('#relatedinstitutionForm').validate({
		rules:{
			institutionName:{required:true},
			institutionCategory:{required:true},
			logoOriFilename:{required:true},

		}
	});

	$.each($('#relatedinstitutionForm').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });
}

function initEvent() {
	

	$(document).on('click', 'a[role=dataEdit]', function(){
				
    	$('#modalRelatedInstitutionSave').data('seq', $(this).data('seq'));
		$('#modalRelatedInstitutionSave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
         swalInit.fire({
             title: '관련기관을 삭제하시겠습니까?',
             text: '',
             showCancelButton: true,
             confirmButtonText: '예',
             cancelButtonText: '아니요',
             confirmButtonClass: 'btn btn-success',
             cancelButtonClass: 'btn btn-danger',
             buttonsStyling: false
         }).then(function(result) {
             if(result.value) {
           	  postAjax('/admin/deleteRelatedInstitutionAjax.do', {seq:seq}, function(data, status){
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

    $('#btnRelatedInstitutionDataEdit').click(function(){
    	$('#modalRelatedInstitutionSave').data('seq', $('#modalRelatedInstitutionView').data('seq'));
    	$('#modalRelatedInstitutionSave').modal();
    });
    
    $('#modalRelatedInstitutionSave').on('show.bs.modal', function(e) {
        if ($('#modalRelatedInstitutionSave').data('seq'))
    	{
            postAjax('/admin/selectRelatedInstitutionAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#relatedinstitutionForm input[type!=radio],#relatedinstitutionForm textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });

                $("#institutionCategory").val(data.data.institutionCategory).trigger("change.select2");
            });
        }
    });

    $('#modalRelatedInstitutionSave').on('hidden.bs.modal', function(e) {
    	initForm('relatedinstitutionForm');
        $('#modalRelatedInstitutionSave').data('seq', "");
        
        $('input[name=logoSaveFilename]').remove();
        $('input[name=logoOriFilename]').remove();
    });
    
    $('#btnRelatedInstitutionDataSave').click(function(){
        if ($('#relatedinstitutionForm').valid())
		{
            
            var formData = new FormData($('#relatedinstitutionForm')[0]);

            ajax($('#modalRelatedInstitutionSave div.modal-content'), '/admin/mergeRelatedInstitutionAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalRelatedInstitutionSave').modal('hide');
                }
            }, true);
        }
    });
    
}
</script>

<div class='card'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>관련기관</h5>
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
				<col style='width: 30%;'>
				<col style='width: 10%;'>
				<col style='width: 30%;'>

				<col style='width: 100px;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>기관/업체명</th>
					<th>링크</th>
					<th>구분</th>
					<th>로고 이미지</th>

					<th>기능</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>	


<div id='modalRelatedInstitutionSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>관련기관 저장</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='relatedinstitutionForm' name='relatedinstitutionForm' class='form-horizontal'>
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
                                <th>기관/업체명</th>
                                <td><input id='institutionName' name='institutionName' maxlength='50' class='form-control' type='text' placeholder='기관/업체명'></td>
                            </tr>
                            <tr>
                                <th>링크</th>
                                <td><input id='institutionUrl' name='institutionUrl' maxlength='100' class='form-control' type='text' placeholder='링크'></td>
                            </tr>
                            <tr>
								<th>구분</th>
								<td>
									<select id='institutionCategory' name='institutionCategory'>
											<option value=''>기관구분</option>
											<option value='1'>공공기관</option>
											<option value='2'>연구기관</option>
											<option value='3'>대학</option>
									</select></td>
								</tr>
                            <tr>
                                <th>로고 이미지</th>
                                <td>
                                    <div class='input-group'>
	                                    <input type='text' class='form-control selectedfilename' placeholder='파일명' readonly>
	                                    <input type='file' style='display:none;' name='uploadFile' id='uploadFile' accept="image/*">
                                        <span class='input-group-append'>
		                                    <button class='btn btn-light selectfile' type='button'>파일 선택</button>
	                                    </span>
                                    </div>
                                    <span class='text-primary font-weight-semibold'>* 로고 이미지를 등록하면 기존 로그 이미지와 교체 됩니다. </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnRelatedInstitutionDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>

<div id='modalRelatedInstitutionView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>관련기관 상세</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>
            <div class='modal-body'>
                
                    <div class='datatable-scroll'>
	                    <table class='detailtable mb-3'>
	                    	<colgroup>
	                    		<col style='width:20%'/>
	                    		<col style=''/>
	                    	</colgroup>
	                    	<tbody>

                            <tr>
                                <th>기관/업체명</th>
                                <td><label id='lblinstitutionName'></label></td>
                        
                            </tr>
                            <tr>
                                <th>링크</th>
                                <td><label id='lblinstitutionUrl'></label></td>
                        
                            </tr>
                            <tr>
                                <th>구분</th>
                                <td><label id='lblinstitutionCategoryDesc'></label></td>
                        
                            </tr>
                            <tr>
                                <th>로고 이미지</th>
                                <td><label id='lbllogoOriFilename'></label></td>
                        
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnRelatedInstitutionDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
