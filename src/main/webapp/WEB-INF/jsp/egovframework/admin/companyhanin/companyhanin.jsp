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
            url: '/admin/getCompanyHaninListAjax.do',
            type: 'POST'
        }
		, order: [[ 0, 'desc' ]]
        , columns: [
            { 'data': 'seq', visible:false },
            { 'data': 'companyCategory' },
			{ 'data': 'companyName'
                , createdCell:function (td, cellData, rowData, row, col) {
        			    $(td).css('cursor', 'pointer');
   	              	    $(td).click(function(e){
   	              		    var rowData = table.row( $(this).closest('tr') ).data();
   	              	
	   	              	    postAjax('/admin/selectCompanyHaninAjax.do', {seq:rowData.seq}, function(data, status){
		   	              	    $.each(data.data, function(i, att){
		              			    if($('#lbl' + i).length > 0)
		              			    {
		              				    $('#lbl' + i).text(att);
		              			    }
		              		    });

		   	         		    $('#modalCompanyHaninView').data('seq', rowData.seq);
		   	         		    $('#modalCompanyHaninView').modal();
                            });
   	            	
            		    });
            } },
			{ 'data': 'companyProduct' },
            {
	            className:      'text-center',
	            orderable:      false,
	            data:           function(rowObject, f, u, table)
	            {
		            var actionButtonItem = "";
		
		            actionButtonItem += "<a href='#' class='dropdown-item' data-seq='"+rowObject.seq+"' role='dataEdit'><i class='icon-pencil5'></i>Edit</a>";
		            actionButtonItem += "<a href='#' class='dropdown-item' data-seq='"+rowObject.seq+"' role='dataRemove'><i class='icon-x'></i>Delete</a>";	
		
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
                    text: 'Korean Company Save',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalCompanyHaninSave'
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
	
	$('#companyhaninForm').validate({
		rules:{
			companyName:{required:true},

		}
	});

	$.each($('#companyhaninForm').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });
}

function initEvent() {
	

	$(document).on('click', 'a[role=dataEdit]', function(){
				
    	$('#modalCompanyHaninSave').data('seq', $(this).data('seq'));
		$('#modalCompanyHaninSave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
         swalInit.fire({
             title: 'Delete Korean Company?',
             text: '',
             showCancelButton: true,
             confirmButtonText: '예',
             cancelButtonText: '아니요',
             confirmButtonClass: 'btn btn-success',
             cancelButtonClass: 'btn btn-danger',
             buttonsStyling: false
         }).then(function(result) {
             if(result.value) {
           	  postAjax('/admin/deleteCompanyHaninAjax.do', {seq:seq}, function(data, status){
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

    $('#btnCompanyHaninDataEdit').click(function(){
    	$('#modalCompanyHaninSave').data('seq', $('#modalCompanyHaninView').data('seq'));
    	$('#modalCompanyHaninSave').modal();
    });
    
    $('#modalCompanyHaninSave').on('show.bs.modal', function(e) {
        if ($('#modalCompanyHaninSave').data('seq'))
    	{
            postAjax('/admin/selectCompanyHaninAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#companyhaninForm input[type!=radio],#companyhaninForm textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });

                
            });
        }
    });

    $('#modalCompanyHaninSave').on('hidden.bs.modal', function(e) {
    	initForm('companyhaninForm');
        $('#modalCompanyHaninSave').data('seq', "");
        
    });
    
    $('#btnCompanyHaninDataSave').click(function(){
        if ($('#companyhaninForm').valid())
		{
            
            var formData = $('#companyhaninForm').serializeObject();

            ajax($('#modalCompanyHaninSave div.modal-content'), '/admin/mergeCompanyHaninAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalCompanyHaninSave').modal('hide');
                }
            });
        }
    });
    
}
</script>

<div class='card'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>Korean Company</h5>
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
				<col style='width: 20%;'>
				<col style=''>
				<col style='width: 30%;'>
				<col style='width: 100px;'>
			</colgroup>
			<thead>
				<tr>
					<th>seq</th>
					<th>Category</th>
					<th>CompanyName</th>
					<th>Product/Service</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	</div>
</div>	


<div id='modalCompanyHaninSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>Korean Company Save</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='companyhaninForm' name='companyhaninForm' class='form-horizontal'>
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
                                <th>CompanyName</th>
                                <td><input id='companyName' name='companyName' maxlength='100' class='form-control' type='text' placeholder='CompanyName'></td>
                            </tr>
                            <tr>
                                <th>Homepage</th>
                                <td><input id='companyWebsite' name='companyWebsite' maxlength='100' class='form-control' type='text' placeholder='Homepage'></td>
                            </tr>
                            <tr>
                                <th>Address</th>
                                <td><input id='companyAddress' name='companyAddress' maxlength='200' class='form-control' type='text' placeholder='Address'></td>
                            </tr>
                            <tr>
                                <th>Phone</th>
                                <td><input id='companyPhone' name='companyPhone' maxlength='50' class='form-control' type='text' placeholder='Phone'></td>
                            </tr>
                            <tr>
                                <th>Category</th>
                                <td><input id='companyCategory' name='companyCategory' maxlength='20' class='form-control' type='text' placeholder='Category'></td>
                            </tr>
                            <tr>
                                <th>Product/Service</th>
                                <td><textarea id='companyProduct' name='companyProduct' maxlength='200' class='form-control' placeholder='Product/Service'></textarea></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnCompanyHaninDataSave'>Save</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>Close</button>
			</div>
		</div>
	</div>
</div>

<div id='modalCompanyHaninView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>Korean Company Detail</h5>
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
                                <th>CompanyName</th>
                                <td><label id='lblcompanyName'></label></td>
                        
                            </tr>
                            <tr>
                                <th>Homepage</th>
                                <td><label id='lblcompanyWebsite'></label></td>
                        
                            </tr>
                            <tr>
                                <th>Address</th>
                                <td><label id='lblcompanyAddress'></label></td>
                        
                            </tr>
                            <tr>
                                <th>Phone</th>
                                <td><label id='lblcompanyPhone'></label></td>
                        
                            </tr>
                            <tr>
                                <th>Category</th>
                                <td><label id='lblcompanyCategory'></label></td>
                        
                            </tr>
                            <tr>
                                <th>Product/Service</th>
                                <td><label id='lblcompanyProduct'></label></td>
                        
                            </tr>
                            <tr>
                                <th>Registed Time</th>
                                <td><label id='lblregDate'></label></td>
                        
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnCompanyHaninDataEdit'>Edit</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>Close</button>
			</div>
		</div>
	</div>
</div>
