<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>

var totalsize = 0.0;
var MAX_TOTAL_SIZE = 30;


function initControl()
{
    CKEDITOR.replace('eventContent', { height: 200 }); 
	$('#eventStartdate').datepicker({changeYear:true, changeMonth:true, yearRange:'1960:+5'});
	$('#eventEnddate').datepicker({changeYear:true, changeMonth:true, yearRange:'1960:+5'});
	
	var uploadFiledropzone = new Dropzone('div#uploadFiledropzone',{
		url : '/admin/dragDropUploadAjax.do?type=eventnotice',
		maxFiles : 10,
		paramName : 'upload',
		maxFilesize : 3,
		addRemoveLinks : true,
		dictDefaultMessage : '파일을 선택해 주십시오.(최대 10개 파일, 각 3MB 최대 30MB 첨부 가능)',
		acceptedFiles : '.xlsx,.xls,image/*,.doc, .docx,.ppt, .pptx,.txt,.pdf,.hwp',
		createImageThumbnails : true,
		success : function(file, response) {
			$('#eventnoticeForm').append("<input type='hidden' role='attachSaveFileName' name='attachSaveFileName' value='"+response.saveFileName+"' data-uuid='"+file.upload.uuid+"' />");
			$('#eventnoticeForm').append("<input type='hidden' role='attachOriFileName' name='attachOriFileName' value='"+response.fileName+"' data-uuid='"+file.upload.uuid+"' />");
			$('#eventnoticeForm').append("<input type='hidden' role='attachFileSize' name='attachFileSize' value='"+file.size+"' data-uuid='"+file.upload.uuid+"' />");
		},
		accept : function(file, done) {
			if (totalsize >= MAX_TOTAL_SIZE) {
				file.status = Dropzone.CANCELED;
				this._errorProcessing([ file ],'최대 30MB 첨부 가능', null);
			} else {
				done();
			}
		},
		init : function() {
			this.on('addedfile',function(file) {
				totalsize += parseFloat((file.size / (1024 * 1024)).toFixed(2));
			});
	
			this.on('removedfile',function(file) {
				$("input[role=attachSaveFileName][data-uuid='"+ file.upload.uuid+ "']").remove();
				$("input[role=attachOriFileName][data-uuid='"+ file.upload.uuid+ "']").remove();
				$("input[role=attachFileSize][data-uuid='"+ file.upload.uuid+ "']").remove();
	
				if (file.upload.progress != 0) {
					totalsize -= parseFloat((file.size / (1024 * 1024)).toFixed(2));
				}
			});
	
			this.on('error',function(file) {
				totalsize -= parseFloat((file.size / (1024 * 1024)).toFixed(2));
			});
		}
	});



    var table = $('#list').DataTable( {
		serverSide:true
		, processing:true
        , ajax: {
            url: '/admin/getEventNoticeListAjax.do',
            type: 'POST'
        }
		, order: [[ 0, 'desc' ]]
        , columns: [
            { 'data': 'seq', visible:false },
			{ 'data': 'eventTitle' 
                , createdCell:function (td, cellData, rowData, row, col) {
        			    $(td).css('cursor', 'pointer');
   	              	    $(td).click(function(e){
   	              		    var rowData = table.row( $(this).closest('tr') ).data();
   	              	
	   	              	    postAjax('/admin/selectEventNoticeAjax.do', {seq:rowData.seq}, function(data, status){
		   	              	    $.each(data.data, function(i, att){
		              			    if($('#lbl' + i).length > 0)
		              			    {
		              				    $('#lbl' + i).text(att);
		              			    }
		              		    });

								$("#lbleventContent").html(htmlDecode(data.data.eventContent));                                
                                if(data.data.listAttachFile && data.data.listAttachFile.length > 0)
                                {
                                    var attachFileHtml = "<ul class='list list-unstyled mb-0'>";
                                    $(data.data.listAttachFile).each(function(i, fileItem){
                                        attachFileHtml += "<li>";
                                        attachFileHtml += "	<i class='icon-download4 text-success mr-2'></i>";
                                        attachFileHtml += "	<a href='/admin/downloadeventnoticefile.do?seq=" + fileItem.seq + "' target='_blank'>" + fileItem.eventOriFilename + "</a>";
                                        attachFileHtml += "</li>";
                                    });
		              				
                                    attachFileHtml += "</ul>";
		              				
                                    $("#lblattachedFile").html(attachFileHtml);
                                }

		   	         		    $('#modalEventNoticeView').data('seq', rowData.seq);
		   	         		    $('#modalEventNoticeView').modal();
                            });
   	            	
            		    });
                } },
			{ 'data': 'eventStartdate' },
			{ 'data': 'eventEnddate' },
			{ 'data': 'eventItem' },
			{ 'data': 'eventHosting' },
			{ 'data': 'eventCharge' },

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
                    text: '행사관리 등록',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalEventNoticeSave'
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
	
	$('#eventnoticeForm').validate({
		rules:{
			eventTitle:{required:true},

		}
	});

	$.each($('#eventnoticeForm').validate().settings.rules, function(key, value){
	    $('#' + key).parent().prev().html(function(idx, oldHtml){
		    if(oldHtml.indexOf('*') < 0)
			    return '* ' + oldHtml;
	    })
    });
}

function initEvent() {
	

	$(document).on('click', 'a[role=dataEdit]', function(){
				
    	$('#modalEventNoticeSave').data('seq', $(this).data('seq'));
		$('#modalEventNoticeSave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
        sweetConfirm('행사관리 삭제하시겠습니까?', '', function(){
            postAjax('/admin/deleteEventNoticeAjax.do', {seq:seq}, function(data, status){
        		showAjaxMessage(data);
        			
        		if(data.isSuccess === '1')
        		{
	        		$('#list').DataTable().ajax.reload();
        		}
        	});
        });
    });

    $('#btnEventNoticeDataEdit').click(function(){
    	$('#modalEventNoticeSave').data('seq', $('#modalEventNoticeView').data('seq'));
    	$('#modalEventNoticeSave').modal();
    });
    
    $('#modalEventNoticeSave').on('show.bs.modal', function(e) {
        if ($('#modalEventNoticeSave').data('seq'))
    	{
            postAjax('/admin/selectEventNoticeAjax.do', {seq:$(this).data('seq')}, function(data, status){
                var formInput = $('#eventnoticeForm input[type!=radio],#eventnoticeForm textarea');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(htmlDecode(inputValue));
                });

                CKEDITOR.instances.eventContent.setData(htmlDecode(data.data.eventContent));
                
                if(data.data.listAttachFile && data.data.listAttachFile.length > 0)
      			{
      				var attachFileHtml = "<ul class='list list-unstyled mb-0'>";
      				$(data.data.listAttachFile).each(function(i, fileItem){
      					attachFileHtml += "	<li data-seq='"+fileItem.seq+"'>";
	   	            	attachFileHtml += "		<i class='icon-download4 text-success mr-2'></i>";
	   	            	attachFileHtml += "		<a href='/admin/downloadeventnoticefile.do?&seq="+fileItem.seq+"' target='_blank'>"+fileItem.eventOriFilename+"</a>";
	   	            	attachFileHtml += "		<button type='button' class='btn btn-sm text-white bg-warning removeaddfile'>삭제</button>";
	   	            	attachFileHtml += "	</li>";		
      				});
      				
      				attachFileHtml += "</ul>";
      				
      				$("#lblattachedFileView").html(attachFileHtml);
      			}
        	    $("#trAttchedFile").show();
            });
        }
    });

    $('#modalEventNoticeSave').on('hidden.bs.modal', function(e) {
    	initForm('eventnoticeForm');
        $('#modalEventNoticeSave').data('seq', "");
        CKEDITOR.instances.eventContent.setData("");
    });
    
    $('#btnEventNoticeDataSave').click(function(){
        if ($('#eventnoticeForm').valid())
		{
            CKEDITOR.instances.eventContent.updateElement();
            var formData = $('#eventnoticeForm').serializeObject();

            ajax($('#modalEventNoticeSave div.modal-content'), '/admin/mergeEventNoticeAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalEventNoticeSave').modal('hide');
                }
            });
        }
    });
    
    $(document).on("click", "button.removeaddfile", function(){
    	var li = $(this).closest("li");
    	var seq = $(this).closest("li").data("seq");
    	
    	sweetConfirm("첨부파일을 삭제하시겠습니까?", "", function(){
        	ajax($('#modalEventNoticeSave div.modal-content'), '/admin/deleteEventNoticeAttachFileAjax.do', {seq:seq}, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
                	li.remove();
                }
            });
    		
    	});
    });
    
}
</script>

<div class='card'>
	<div class='card-header header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class='icon-chevron-right mr-1'></i>행사관리</h5>
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
			<col style='width: 10%;'>
			<col style='width: 10%;'>
			<col style='width: 10%;'>
			<col style='width: 10%;'>
			<col style='width: 10%;'>

			    <col style='width: 100px;'>
		    </colgroup>
		    <thead>
			    <tr>
				    <th>seq</th>
			<th>행사 제목</th>
			<th>행사 시작일</th>
			<th>행사 종료일</th>
			<th>행사 품목</th>
			<th>행사 주최</th>
			<th>행사 담당자</th>

				    <th>기능</th>
			    </tr>
		    </thead>
		    <tbody>
			
		    </tbody>
	    </table>
    </div>
</div>	


<div id='modalEventNoticeSave' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>행사관리 저장</h5>
				<button type='button' class='close' data-dismiss='modal'>&times;</button>
			</div>

			<form id='eventnoticeForm' name='eventnoticeForm' class='form-horizontal'>
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
                                <th>행사 제목</th>
                                <td><input id='eventTitle' name='eventTitle' maxlength='100' class='form-control' type='text' placeholder='행사 제목'></td>
                            </tr>
                            <tr>
                                <th>행사 내용</th>
                                <td><textarea id='eventContent' name='eventContent' maxlength='65535' class='form-control' placeholder='행사 내용' rows='5' cols='100'></textarea></td>
                            </tr>
                            <tr>
                                <th>행사 시작일</th>
                                <td><input id='eventStartdate' name='eventStartdate' maxlength='10' class='form-control' type='text' placeholder='행사 시작일'></td>
                            </tr>
                            <tr>
                                <th>행사 종료일</th>
                                <td><input id='eventEnddate' name='eventEnddate' maxlength='10' class='form-control' type='text' placeholder='행사 종료일'></td>
                            </tr>
                            <tr>
                                <th>행사 위치</th>
                                <td><input id='eventLocation' name='eventLocation' maxlength='100' class='form-control' type='text' placeholder='행사 위치'></td>
                            </tr>
                            <tr>
                                <th>행사 규모</th>
                                <td><input id='eventScale' name='eventScale' maxlength='10' class='form-control' type='text' placeholder='행사 규모'></td>
                            </tr>
                            <tr>
                                <th>행사 분야</th>
                                <td><input id='eventCategory' name='eventCategory' maxlength='20' class='form-control' type='text' placeholder='행사 분야'></td>
                            </tr>
                            <tr>
                                <th>행사 품목</th>
                                <td><input id='eventItem' name='eventItem' maxlength='20' class='form-control' type='text' placeholder='행사 품목'></td>
                            </tr>
                            <tr>
                                <th>행사 주최</th>
                                <td><input id='eventHosting' name='eventHosting' maxlength='100' class='form-control' type='text' placeholder='행사 주최'></td>
                            </tr>
                            <tr>
                                <th>행사 담당자</th>
                                <td><input id='eventCharge' name='eventCharge' maxlength='20' class='form-control' type='text' placeholder='행사 담당자'></td>
                            </tr>
                            <tr>
                                <th>행사 연락처</th>
                                <td><input id='eventPhone' name='eventPhone' maxlength='30' class='form-control' type='text' placeholder='행사 연락처'></td>
                            </tr>
                            <tr>
                                <th>행사 이메일</th>
                                <td><input id='eventEmail' name='eventEmail' maxlength='100' class='form-control' type='text' placeholder='행사 이메일'></td>
                            </tr>
                            <tr>
                                <th>행사 홈페이지</th>
                                <td><input id='eventHomepage' name='eventHomepage' maxlength='100' class='form-control' type='text' placeholder='행사 홈페이지'></td>
                            </tr>
                            <tr>
                                <th>파일첨부</th>
                                <td>
                                    <div class='dropzone' id='uploadFiledropzone'></div>
                                </td>
                            </tr>
                            <tr id='trAttchedFile'>
                                <th>첨부파일</th>
                                <td id='lblattachedFileView'></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                </div>
			</form>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' id='btnEventNoticeDataSave'>저장</button>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>

<div id='modalEventNoticeView' class='modal fade'>
	<div class='modal-dialog modal-xl'>
		<div class='modal-content'>
			<div class='modal-header bg-primary text-white'>
				<h5 class='modal-title'>행사관리 상세</h5>
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
                                <th>행사 제목</th>
                                <td><label id='lbleventTitle'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 내용</th>
                                <td><label id='lbleventContent'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 시작일</th>
                                <td><label id='lbleventStartdate'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 종료일</th>
                                <td><label id='lbleventEnddate'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 위치</th>
                                <td><label id='lbleventLocation'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 규모</th>
                                <td><label id='lbleventScale'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 분야</th>
                                <td><label id='lbleventCategory'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 품목</th>
                                <td><label id='lbleventItem'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 주최</th>
                                <td><label id='lbleventHosting'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 담당자</th>
                                <td><label id='lbleventCharge'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 연락처</th>
                                <td><label id='lbleventPhone'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 이메일</th>
                                <td><label id='lbleventEmail'></label></td>
                        
                            </tr>
                            <tr>
                                <th>행사 홈페이지</th>
                                <td><label id='lbleventHomepage'></label></td>
                            </tr>
                            <tr>
                                <th>등록시간</th>
                                <td><label id='lblregDate'></label></td>
                        
                            </tr>
                            <tr>
                                <th>첨부파일</th>
                                <td><label id='lblattachedFile'></label></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class='modal-footer border-top'>
				<button type='button' class='btn bg-primary text-white' data-dismiss='modal' id='btnEventNoticeDataEdit'>수정</button>
                <button type='button' class='btn bg-primary text-white' data-dismiss='modal'>닫기</button>
			</div>
		</div>
	</div>
</div>
