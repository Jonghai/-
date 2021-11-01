<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>

function initControl()
{
	$("#standardYear").select2({minimumResultsForSearch: Infinity, width:100});
	$("#historyDate").select2({minimumResultsForSearch: Infinity, width:100});
	
	var standardYear = $("#standardYear");
	var nowYear = new Date().getFullYear();
	standardYear.append("<option value=''>년도</option>");
	for(var i=nowYear;i > 2000 ; i--){
		standardYear.append("<option value='"+i+"'>"+i+"</option>");
	}
	
	$("#historyDate").append("<option value=''>월</option>");
	for(var i=1;i < 13 ; i++){
		var month = i.toString();
		if(month.length === 1)
			month = "0" + month;
		
		$("#historyDate").append("<option value='"+month+"'>"+month+"월</option>");
	}
	
	var table = $('#list').DataTable( {
		serverSide:true
		, processing:true
        , ajax: {
            url: '/admin/getSiteCompanyHistoryListAjax.do',
            type: 'POST'
        }
		, order: [[ 0, 'desc' ]]
        , columns: [
           	{ 'data': 'seq', visible:false },
			{ 'data': 'standardYear'
                , createdCell:function (td, cellData, rowData, row, col) {
        			    $(td).css('cursor', 'pointer');
   	              	    $(td).click(function(e){
   	              		    var rowData = table.row( $(this).closest('tr') ).data();
   	              	
	   	              	    postAjax('/admin/selectSiteCompanyHistoryAjax.do', {seq:rowData.seq}, function(data, status){
		   	              	    $.each(data.data, function(i, att){
		              			    if($('#lbl' + i).length > 0)
		              			    {
		              				    $('#lbl' + i).text(att);
		              			    }
		              		    });

		   	         		    $('#modalSiteCompanyHistoryView').data('seq', rowData.seq);
		   	         		    $('#modalSiteCompanyHistoryView').modal();
                            });
   	            	
            		    });
                } },
			{ 'data': 'historyDate' },
			{ 'data': 'historyContents' },

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
                    text: '연혁 등록',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalSiteCompanyHistorySave'
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
	
	$('#sitecompanyhistoryForm').validate({
		rules:{
			standardYear:{required:true},
			historyDate:{required:true},
			historyContents:{required:true},

		}
	});

	$.each($('#sitecompanyhistoryForm').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });
}

function initEvent() {

	$(document).on('click', 'a[role=dataEdit]', function(){
				
    	$('#modalSiteCompanyHistorySave').data('seq', $(this).data('seq'));
		$('#modalSiteCompanyHistorySave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
         swalInit.fire({
             title: '연혁 삭제하시겠습니까?',
             text: '',
             showCancelButton: true,
             confirmButtonText: '예',
             cancelButtonText: '아니요',
             confirmButtonClass: 'btn btn-success',
             cancelButtonClass: 'btn btn-danger',
             buttonsStyling: false
         }).then(function(result) {
             if(result.value) {
           	  postAjax('/admin/deleteSiteCompanyHistoryAjax.do', {seq:seq}, function(data, status){
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

    $('#btnSiteCompanyHistoryDataEdit').click(function(){
    	$('#modalSiteCompanyHistorySave').data('seq', $('#modalSiteCompanyHistoryView').data('seq'));
    	$('#modalSiteCompanyHistorySave').modal();
    });
    
    $('#modalSiteCompanyHistorySave').on('show.bs.modal', function(e) {
        if ($('#modalSiteCompanyHistorySave').data('seq'))
    	{
            postAjax('/admin/selectSiteCompanyHistoryAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#sitecompanyhistoryForm input[type!=radio],#sitecompanyhistoryForm textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });
				
        	    $("#standardYear").val(data.data.standardYear).trigger("change.select2");
        	    $("#historyDate").val(data.data.historyDate).trigger("change.select2");
                //CKEDITOR.instances.elementId.setData(htmlDecode(data));
            });
        }
    });

    $('#modalSiteCompanyHistorySave').on('hidden.bs.modal', function(e) {
    	initForm('sitecompanyhistoryForm');
        $('#modalSiteCompanyHistorySave').data('seq', "");
        //CKEDITOR.instances.elementId.setData("");
    });
    
    $('#btnSiteCompanyHistoryDataSave').click(function(){
        if ($('#sitecompanyhistoryForm').valid())
		{
            //CKEDITOR.instances.elementId.updateElement();
            var formData = $('#sitecompanyhistoryForm').serializeObject();

            ajax($('#modalSiteCompanyHistorySave div.modal-content'), '/admin/mergeSiteCompanyHistoryAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalSiteCompanyHistorySave').modal('hide');
                }
            });
        }
    });
    
}
</script>

<div class='card'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>연혁</h5>
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
				<col style='width: 10%;'>
				<col style='width: 10%;'>
				<col style=''>
				<col style='width: 100px;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>년도</th>
					<th>월</th>
					<th>내용</th>

					<th>기능</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>	


<div id='modalSiteCompanyHistorySave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>연혁 저장</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='sitecompanyhistoryForm' name='sitecompanyhistoryForm' class='form-horizontal'>
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
                                <th>년도</th>
                                <td><select id='standardYear' name='standardYear'></select> <select id='historyDate' name='historyDate'></select></td>
                        
                            </tr>
                            <tr>
                                <th>내용</th>
                                <td><input id='historyContents' name='historyContents' maxlength='100' class='form-control' type='text' placeholder='내용'></td>
                        
                            </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnSiteCompanyHistoryDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>

<div id='modalSiteCompanyHistoryView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>연혁 상세</h5>
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
                                <th>년도</th>
                                <td><label id='lblstandardYear'></label>년 <label id='lblhistoryDate'></label>월</td>
                        
                            </tr>
                            <tr>
                                <th>내용</th>
                                <td><label id='lblhistoryContents'></label></td>
                        
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnSiteCompanyHistoryDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
