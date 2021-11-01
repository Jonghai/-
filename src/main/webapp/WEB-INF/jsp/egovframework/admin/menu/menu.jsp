<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script src="/js/plugins/fancytree/fancytree_all.min.js"></script>
<script src="/js/plugins/datatables/extensions/row_reorder.min.js"></script>

<script type='text/javascript'>

function initControl()
{
    $('#menuType').select2({minimumResultsForSearch: Infinity});

    var fancytree = $('.tree-ajax').fancytree({
    	//checkbox: true,
        //selectMode: 3,
        source: {
        	url: '/admin/getMenuTreeAjax.do'
        },
        init: function(event, data) {
        	$.ui.fancytree.getTree('.tree-ajax').activateKey("0");
        },
        activate: function(event, data){
            $('#list').DataTable().ajax.reload();
        },
    });
    
    var table = $('#list').DataTable( {
		serverSide:true
		, processing:true		
        , ajax: {
            url: '/admin/getMenuListAjax.do',
            type: 'POST',
            data:function(d){
            	var selectedTreeValue = getSelectTreeItem();
            	
            	if(selectedTreeValue)
            		d.parentSeq = selectedTreeValue.key;
            }
        }
		, order: [[ 0, 'desc' ]]
        , columns: [
           	{ 'data': 'seq', visible:false },
			{ 'data': 'menuTitle' 
                , createdCell:function (td, cellData, rowData, row, col) {
        			    $(td).css('cursor', 'pointer');
   	              	    $(td).click(function(e){
   	              		    var rowData = table.row( $(this).closest('tr') ).data();
   	              	
	   	              	    postAjax('/admin/selectMenuAjax.do', {seq:rowData.seq}, function(data, status){
		   	              	    $.each(data.data, function(i, att){
		              			    if($('#lbl' + i).length > 0)
		              			    {
		              				    $('#lbl' + i).text(att);
		              			    }
		              		    });

		   	              	    if(data.data.menuContent)
		   	              			$("#menuContent").html(data.data.menuContent.replaceEnter());

		   	         		    $('#modalMenuView').data('seq', rowData.seq);
		   	         		    $('#modalMenuView').modal();
                            });
   	            	
            		    });
                } },
			{ 'data': 'sort' },
			{ 'data': 'menuLink' },
			{ 'data': 'menuTypeDesc' },
			{ 'data': 'isUsedDesc' },
			{ 'data': 'menuContent' },
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
                    text: '메뉴 등록',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalMenuSave'
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
	
	$('#menuForm').validate({
		rules:{
			menuTitle:{required:true},
			parentSeq:{required:true},
			menuLink:{required:true},
			menuType:{required:true},

		}
	});

	$.each($('#menuForm').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });
}

function initEvent() {
	

	$(document).on('click', 'a[role=dataEdit]', function(){
				
    	$('#modalMenuSave').data('seq', $(this).data('seq'));
		$('#modalMenuSave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
        sweetConfirm('메뉴를 삭제하시겠습니까?', '', function(){
            postAjax('/admin/deleteMenuAjax.do', {seq:seq}, function(data, status){
        		showAjaxMessage(data);
        			
        		if(data.isSuccess === '1')
        		{
	        		$('#list').DataTable().ajax.reload();
        		}
        	});
        });
    });

    $('#btnMenuDataEdit').click(function(){
    	$('#modalMenuSave').data('seq', $('#modalMenuView').data('seq'));
    	$('#modalMenuSave').modal();
    });
    
    $('#modalMenuSave').on('show.bs.modal', function(e) {
        if ($('#modalMenuSave').data('seq'))
    	{
            postAjax('/admin/selectMenuAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#menuForm input[type!=radio],#menuForm textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });

        	    $("input[name=isUsed][value="+data.data.isUsed+"]").prop("checked", true);
                $("#menuType").val(data.data.menuType).trigger("change.select2");
            });
        }
        else{
        	var selectedNode = getSelectTreeItem();
        	
        	$("#parentSeq").val(selectedNode.key);
        	$("#iconName").val("icon-minus2");
        }
    });

    $('#modalMenuSave').on('hidden.bs.modal', function(e) {
    	initForm('menuForm');
        $('#modalMenuSave').data('seq', "");
        $("#menuLink").attr("readonly", false);
    });
    
    $('#btnMenuDataSave').click(function(){
        if ($('#menuForm').valid())
		{
            
            var formData = $('#menuForm').serializeObject();

            ajax($('#modalMenuSave div.modal-content'), '/admin/mergeMenuAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalMenuSave').modal('hide');
                }
            });
        }
    });
    
    $("#menuType").change(function(){
    	var menuType = $("#menuType").val();
    	if(menuType === "2"
    		|| menuType === "3"
    		|| menuType === "4"
    		|| menuType === "5"){
    		$("#menuLink").val("/admin/board.do");
    		$("#menuLink").attr("readonly", true);
    	}
    	else if(menuType === "0"){
    		$("#menuLink").val("#");
    		$("#menuLink").attr("readonly", true);
    	}
    	else{
    		$("#menuLink").val("");
    		$("#menuLink").attr("readonly", false);
    	}
    });
}

function getSelectTreeItem(){
	var selectedNode = $(".tree-ajax").fancytree("getTree").getActiveNode();
	return selectedNode;
}

</script>
<div class='row m-0 h-100'>
	<div class='col-lg-2 p-0'>
		<div class='card h-100'>
			<div class='card-header header-elements-inline'>
				<h5 class='card-title font-weight-bold'>
					<i class='icon-chevron-right mr-1'></i> 시스템메뉴관리
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
				<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>메뉴목록</h5>
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
						<col style='width: 15%;'>
						<col style='width: 10%;'>
						<col style='width: 10%;'>
						<col style='width: 10%;'>
						<col style='width: 10%;'>
						<col style=''>
						<col style='width: 100px;'>
					</colgroup>
					<thead>
						<tr>
							<th>seq</th>
							<th>메뉴명</th>
							<th>정렬</th>
							<th>주소</th>
							<th>메뉴타입</th>
							<th>사용여부</th>
							<th>메뉴설명</th>
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

<div id='modalMenuSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>메뉴 저장</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='menuForm' name='menuForm' class='form-horizontal'>
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
                                <th>메뉴명</th>
                                <td><input id='menuTitle' name='menuTitle' maxlength='50' class='form-control' type='text' placeholder='메뉴명'></td>
                            </tr>
                            <tr>
                                <th>상위 메뉴</th>
                                <td><input id='parentSeq' name='parentSeq'  class='form-control' type='text' placeholder='상위 메뉴' readonly></td>
                            </tr>
                            <tr>
                                <th>정렬</th>
                                <td><input id='sort' name='sort'  class='form-control' type='text' placeholder='정렬'></td>
                            </tr>
                            <tr>
                                <th>메뉴타입</th>
                                <td>
                                	<select id='menuType' name='menuType'>
                                		<option value=''>메뉴타입</option>
                                		<option value='0'>폴더</option>
                                		<option value='1'>기본</option>
                                		<option value='2'>게시판-기본</option>
                                		<option value='3'>게시판-갤러리</option>
                                		<option value='4'>게시판-PDF</option>
                                		<option value='5'>게시판-동영상(유튜브)</option>
                                	</select>
                                </td>
                            </tr>
                            <tr>
                                <th>메뉴 주소</th>
                                <td><input id='menuLink' name='menuLink' maxlength='100' class='form-control' type='text' placeholder='메뉴 주소'></td>
                            </tr>
                            <tr>
                                <th>메뉴 아이콘</th>
                                <td><input id='iconName' name='iconName' maxlength='30' class='form-control' type='text' placeholder='메뉴 아이콘'></td>
                            </tr>
                            <tr>
                                <th>사용여부</th>                                
                                <td>
                                    <label class='custom-control custom-radio custom-control-inline'>
                                        <input type='radio' class='custom-control-input' name='isUsed' value='1' checked>
							            <span class='custom-control-label'>예</span>
						            </label>
                                    <label class='custom-control custom-radio custom-control-inline'>
                                        <input type='radio' class='custom-control-input' name='isUsed' value='0'>
							            <span class='custom-control-label'>아니요</span>
						            </label>
                                </td>
                            </tr>
                            <tr>
                                <th>메뉴설명</th>
                                <td><textarea id='menuContent' name='menuContent'  class='form-control' maxlength='500' placeholder='정렬'></textarea></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnMenuDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>

<div id='modalMenuView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>메뉴 상세</h5>
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
                                <th>메뉴명</th>
                                <td><label id='lblmenuTitle'></label></td>
                        
                            </tr>
                            <tr>
                                <th>정렬</th>
                                <td><label id='lblsort'></label></td>
                        
                            </tr>
                            <tr>
                                <th>메뉴타입</th>
                                <td><label id='lblmenuTypeDesc'></label></td>
                        
                            </tr>
                            <tr>
                                <th>메뉴 링크</th>
                                <td><label id='lblmenuLink'></label></td>
                        
                            </tr>
                            <tr>
                                <th>메뉴 아이콘</th>
                                <td><label id='lbliconName'></label></td>
                        
                            </tr>
                            <tr>
                                <th>사용여부</th>
                                <td><label id='lblisUsedDesc'></label></td>
                            </tr>
                            <tr>
                                <th>메뉴설명</th>
                                <td><label id='lblmenuContent'></label></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnMenuDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
