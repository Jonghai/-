<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script src="/js/plugins/fancytree/fancytree_all.min.js"></script>
<script src="/js/plugins/datatables/extensions/row_reorder.min.js"></script>

<script type='text/javascript'>

var listOrganizationJSON = null;

function initControl()
{
	$("#sideBarTitle").html("<i class='icon-chevron-right mr-1'></i>부서관리");
	
    $('#empOrgSeq').select2({minimumResultsForSearch: Infinity});
    
    getOrganizationList();
    
    $("#empOrgSeq").append("<option value=''>부서선택</option>");
    $(listOrganizationJSON).each(function(i, organizationItem){
    	$("#empOrgSeq").append("<option value='"+organizationItem.seq+"'>"+organizationItem.fullPaths+"</option>");
    });
}

function initEvent() {
	

	var fancytree = $('.tree-ajax').fancytree({
    	//checkbox: true,
        //selectMode: 3,
        source: {
        	url: '/admin/getOrganizationTreeAjax.do'
        },
        init: function(event, data) {
        },
        activate: function(event, data){
            var node = data.node;
            $("#empOrgSeq").val(node.key).trigger("change.select2");
            
			ajax($("#tblOrganzation"), "/admin/selectOrganizationAjax.do", {"seq":node.key}, function(data, status){
    			
    			if(data.isSuccess === "1"){
    				$("#orgSeq").val(data.data.seq);
    				$("#orgName").val(htmlDecode(data.data.orgName));
    				$("#orgField").val(htmlDecode(data.data.orgField));
    				$("#orgDirection").val(htmlDecode(data.data.orgDirection));
    			}else{
    				showAjaxMessage(data);
    			}
    		});
            
			$('#list').DataTable().ajax.reload();
        },
    });
	
	var table = $('#list').DataTable( {
		serverSide:true
		, processing:true
		, rowReorder: {selector: 'span.reorder'}
        , ajax: {
            url: '/admin/getEmployeeListAjax.do',
            type: 'POST',
            data:function(d){
            	var selectedOrgNode = getSelectTreeItem();
            	
            	if(selectedOrgNode)
            		d.empOrgSeq = selectedOrgNode.key;
            	else
            		d.empOrgSeq = "0";
            }
        }
		, order: [[ 7, 'asc' ]]
        , columns: [
            { 'data': 'seq', visible:false },
			{ 'data': 'empName'
                , createdCell:function (td, cellData, rowData, row, col) {
	 			    $(td).css('cursor', 'pointer');
	           	    $(td).click(function(e){
	           		    var rowData = table.row( $(this).closest('tr') ).data();
	           	
	            	    postAjax('/admin/selectEmployeeAjax.do', {seq:rowData.seq}, function(data, status){
	            	    	
		             	    $.each(data.data, function(i, att){
		         			    if($('#lbl' + i).length > 0)
		         			    {
		         				    $('#lbl' + i).html(att);
		         			    }
		         		    });
							$("#lblempJob").html(data.data.empJob.replaceEnter());
		        		    $('#modalEmployeeView').data('seq', rowData.seq);
		        		    $('#modalEmployeeView').modal();
	                	});
	         	
	     		    });
            }},
			{ 'data': 'empPosition' },
			{ 'data': 'empPhone' },
			{ 'data': 'empEmail' },
			{ 'data': 'empJob' },
			{ 'data': 'empOrgName' },
			{ 'data': function(rowObject){
				return "<span class='reorder'>"+rowObject.empSort+"</span>";
			}, 'name':"empSort"},
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
                    text: '직원 등록',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalEmployeeSave'
                    },
                    action: function(e, dt, node, config) {
                    }
                }
            ],
        }
    } );
	
	table.on('row-reorder', function (e, diff, edit) {
		var employeeSortArray = [];
       	$("#list > tbody > tr").each(function(i, tr){
       		var seq = table.row(tr).data().seq
       		var sort = i + 1;
       		
       		employeeSortArray.push({seq:seq, empSort:sort});
       	});
       	
       	postAjax("/admin/reorderEmployeeAjax.do", {employeeSortInfoJSON :JSON.stringify(employeeSortArray)}, function(){
       		
       	}, false);
       	
    });
	
	//validate add method
	//jQuery.validator.addMethod('addCheck', function(value, element) {
	//        return (value.length > 0);
	//    }, 'This field is required.'
	//);
	
	$('#employeeForm').validate({
		rules:{
			empOrgSeq:{required:true},
			empName:{required:true},
			empPosition:{required:true},
			empPhone:{required:true},
			empJob:{required:true},

		}
	});

	$.each($('#employeeForm').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });

	$(document).on('click', 'a[role=dataEdit]', function(){
				
    	$('#modalEmployeeSave').data('seq', $(this).data('seq'));
		$('#modalEmployeeSave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
         swalInit.fire({
             title: '직원 삭제하시겠습니까?',
             text: '',
             showCancelButton: true,
             confirmButtonText: '예',
             cancelButtonText: '아니요',
             confirmButtonClass: 'btn btn-success',
             cancelButtonClass: 'btn btn-danger',
             buttonsStyling: false
         }).then(function(result) {
             if(result.value) {
           	  postAjax('/admin/deleteEmployeeAjax.do', {seq:seq}, function(data, status){
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

    $('#btnEmployeeDataEdit').click(function(){
    	$('#modalEmployeeSave').data('seq', $('#modalEmployeeView').data('seq'));
    	$('#modalEmployeeSave').modal();
    });
    
    $('#modalEmployeeSave').on('show.bs.modal', function(e) {
        if ($('#modalEmployeeSave').data('seq'))
    	{
            postAjax('/admin/selectEmployeeAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#employeeForm input[type!=radio],#employeeForm textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(inputValue);
                });
				
                //CKEDITOR.instances.elementId.setData(htmlDecode(data));
            });
        }
    });

    $('#modalEmployeeSave').on('hidden.bs.modal', function(e) {
    	initForm('employeeForm');
        $('#modalEmployeeSave').data('seq', "");
        //CKEDITOR.instances.elementId.setData("");
    });
    
    $('#btnEmployeeDataSave').click(function(){
        if ($('#employeeForm').valid())
		{
            //CKEDITOR.instances.elementId.updateElement();
            /* var formData = new FormData($('#employeeForm')[0]);

            ajax($('#modalEmployeeSave div.modal-content'), '/admin/mergeEmployeeAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalEmployeeSave').modal('hide');
                }
            }); */
            
            ajax($('#modalEmployeeSave div.modal-content'), '/admin/mergeEmployeeAjax.do', $('#employeeForm').serializeObject(), function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalEmployeeSave').modal('hide');
                }
            });
            
        	/* var formData = $('#employeeForm').serialize();

            postAjax('/admin/mergeEmployeeAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalEmployeeSave').modal('hide');
                }
            }); */
        }
    });    
    
    $('#btnOrganizationDataSave').click(function(){
    	var selectedOrg = getSelectTreeItem();
    	
    	if(selectedOrg){
    		var formData = $("#organizationForm").serializeObject();
    		formData.seq = $("#orgSeq").val();
    		
			ajax($("#tblOrganzation"), "/admin/mergeOrganizationAjax.do", formData, function(data, status){
				showAjaxMessage(data);
    			if(data.isSuccess === "1"){
    				$('.tree-ajax').fancytree();
    			}else{
    				
    			}
    		});
    		
    	}
    	else{
    		toastr.warning("정보를 저장할 부서를 선택해 주세요.");
    		return;
    	}
    });
}

function getSelectTreeItem(){
	var selectedNode = $(".tree-ajax").fancytree("getTree").getActiveNode();
	return selectedNode;
}

function getOrganizationList(){
	postAjax("/admin/selectOrganizationTreeListAjax.do", {}, function(data, status){
		if(data.isSuccess === "1")
			listOrganizationJSON = data.data;
	}, false);
}
</script>

<div class='card ml-2 mr-2 mb-2 card-collapsed'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>부서정보</h5>
		<div class='header-elements'>
			<div class='list-icons'>
				<a class='list-icons-item' data-action='collapse'></a>
	  		</div>
  		</div>
	</div>
	<div class="collapse" style="">
    	<div class='card-body'>
    		<form id='organizationForm' name='organizationForm' class='form-horizontal'>
    			<input type='hidden' id='orgSeq' name='orgSeq'>
				<table class='detailtable mb-0' id='tblOrganzation'>
					<colgroup>
						<col style='width: 20%' />
						<col style='' />
					</colgroup>
					<tbody>
						<tr>
		                    <th>부서명</th>
		                    <td><input id='orgName' name='orgName' maxlength='20' class='form-control' type='text' placeholder='부서명'></td>
		                </tr>
		                <tr>
		                    <th>연구 분야</th>
		                    <td><textarea id='orgField' name='orgField' style='height:150px;' maxlength='65535' class='form-control' placeholder='연구 분야'></textarea></td>
		                </tr>
		                <tr>
		                    <th>연구개발 방향</th>
		                    <td><textarea id='orgDirection' name='orgDirection' style='height:150px;' maxlength='65535' class='form-control' placeholder='연구개발 방향'></textarea></td>
		                </tr>
					</tbody>
					
				</table>
				<label class='text-primary font-weight-semibold'>* 부서 추가 필요시 유지보수 업체에 문의 부탁드립니다(사용자 화면 조직도 페이지와 관련이 있음)</label>
			</form>
		</div>
		<div class='card-footer text-right'>
			<button type='button' id='btnOrganizationDataSave' class='btn btn-primary'>부서저장</button>
		</div>
	</div>
	
</div>
<div class='card ml-2 mr-2'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>소속 직원</h5>
        <div class='header-elements'>
			<div class='list-icons ml-3'>
          		<!-- <a class='list-icons-item' data-action='collapse'></a> -->
          		<a class='list-icons-item' data-action='help'>
          			<i class='icon-question3'></i>
          		</a>
          		<a class='list-icons-item' data-action='reload'></a>
          		
          		

          		<!-- <a class='list-icons-item' data-action='remove></a> -->
          	</div>
       	</div>
	</div>
    <div class='card-body'>
		<table id='list' class='table table-hover'>
			<colgroup>
				<col style=''>
				<col style='width: 14%;'>
				<col style='width: 12%;'>
				<col style='width: 14%;'>
				<col style='width: 20%;'>
				<col style='width: 14%;'>
				<col style='width: 8%;'>
				<col style='width: 80px;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>이름</th>
					<th>직위(보직)</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>업무내용</th>
					<th>부서</th>
					<th>순서</th>
					<th>기능</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>	


<div id='modalEmployeeSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>직원 저장</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='employeeForm' name='employeeForm' class='form-horizontal'>
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
                                <th>부서</th>
                                <td><select id='empOrgSeq' name='empOrgSeq'></select></td>
                       
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td><input id='empName' name='empName' maxlength='20' class='form-control' type='text' placeholder='이름'></td>
                        
                            </tr>
                            <tr>
                                <th>직위(보직)</th>
                                <td><input id='empPosition' name='empPosition' maxlength='10' class='form-control' type='text' placeholder='직위(보직)'></td>
                        
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td><input id='empPhone' name='empPhone' maxlength='20' class='form-control' type='text' placeholder='전화번호'></td>
                        
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><input id='empEmail' name='empEmail' maxlength='100' class='form-control' type='text' placeholder='이메일'></td>
                        
                            </tr>
                            <tr>
                                <th>업무내용</th>
                                <td><textarea id='empJob' name='empJob' maxlength='200' class='form-control' placeholder='업무내용' rows='5' cols='100'></textarea></td>
                        
                            </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnEmployeeDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>

<div id='modalEmployeeView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>직원 상세</h5>
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
                                <th>부서</th>
                                <td><label id='lblempOrgName'></label></td>
                        
                            </tr>
                            <tr>
                                <th>이름</th>
                                <td><label id='lblempName'></label></td>
                        
                            </tr>
                            <tr>
                                <th>직위(보직)</th>
                                <td><label id='lblempPosition'></label></td>
                        
                            </tr>
                            <tr>
                                <th>전화번호</th>
                                <td><label id='lblempPhone'></label></td>
                        
                            </tr>
                            <tr>
                                <th>이메일</th>
                                <td><label id='lblempEmail'></label></td>
                        
                            </tr>
                            <tr>
                                <th>업무내용</th>
                                <td><label id='lblempJob'></label></td>
                        
                            </tr>                            
                        </tbody>
                    </table>
                </div>
            </div>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnEmployeeDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
