<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<script type='text/javascript'>

var menuType = "${menuViewVO.menuType}";
var isAttach = "${menuViewVO.isAttach}";

var totalsize = 0.0;
var MAX_TOTAL_SIZE = 30;
Dropzone.autoDiscover = false;

$(document).on('click', 'input.selectedfilename, button.selectfile', function(){
	$(this).closest('div').find('input[type=file]').trigger('click');
});

$(document).on('change', 'input#pdfFile', function(){
	var input = this;
	if($(this)[0].files.length > 0){
		$(this).closest('div').find('input.selectedfilename').val($(this)[0].files[0].name);
		
		var formData = new FormData();
		formData.append("upload", $(this)[0].files[0])
		ajax(null, "/admin/dragDropUploadAjax.do?type=board", formData, function(data, status){
			if(data.uploaded === 1){
				$("#boardForm").append("<input type='hidden' role='attachSaveFileName' name='attachSaveFileName' value='"+data.saveFileName+"' />");
				$("#boardForm").append("<input type='hidden' role='attachOriFileName' name='attachOriFileName' value='"+data.fileName+"' />");
				$("#boardForm").append("<input type='hidden' role='attachFileSize' name='attachFileSize' value='"+data.filesize+"' />");
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
	$("body").append(boardModalList["boardModal"+menuType]);
	$("#trAttchedFile").hide();	
	$("#regDate").datepicker();
	
	if($("#boardContent").length > 0){
		var ckeditor = CKEDITOR.replace('boardContent', { height: 200 });
		
		if(menuType === "3"){
			ckeditor.on( 'fileUploadResponse', function( evt ) {
				console.log(arguments[0].data.fileLoader.xhr.responseText);
			});
		}
	}
	
	$("#companyStartDate").datepicker({changeYear:true, changeMonth:true, yearRange:"1960:+5"});
	
	var table = $('#list').DataTable( {
        ajax: {
            url: '/admin/getBoardListAjax.do',
            type: 'POST',
            data:function(d){
            	d.boardCode = getQuerystring().boardCode;
            }
        }
		, order: [[ 0, 'desc' ]]
        , columns: [
            { data: 'seq', visible:false },
			{ data: 'boardTitle' , createdCell:function (td, cellData, rowData, row, col) {
    			$(td).css('cursor', 'pointer');
	            $(td).click(function(e){
	            	var rowData = table.row( $(this).closest('tr') ).data();
	              	
   	            	postAjax('/admin/selectBoardAjax.do', {seq:rowData.seq, boardCode:getQuerystring().boardCode}, function(data, status){
	   	            	$.each(data.data, function(i, att){
	              			if($('#lbl' + i).length > 0)
	              			{
	              				$('#lbl' + i).text(att);
	              			}
	              		});
	   	            	
	   	            	if($("#lblthumnailImage").length > 0 && data.data.thumbnailAddress){
	              	    	$("#lblthumnailImage").html("<img src='"+data.data.thumbnailAddress+"' style='width:200px;' />")
	              	    }
	   	            	
              			$('#lblboardContent').html(htmlDecode(data.data.boardContent));
              			
              			if(data.data.listBoardAttach && data.data.listBoardAttach.length > 0)
              			{
              				var attachFileHtml = "<ul class='list list-unstyled mb-0'>";
              				$(data.data.listBoardAttach).each(function(i, fileItem){
              					attachFileHtml += "	<li>";
        	   	            	attachFileHtml += "		<i class='icon-download4 text-success mr-2'></i>";
        	   	            	attachFileHtml += "		<a href='/admin/downloadboardfile.do?boardCode="+getQuerystring().boardCode+"&seq="+fileItem.seq+"' target='_blank'>"+fileItem.oriFileName+"</a>";
        	   	            	attachFileHtml += "	</li>";		
              				});
              				
              				attachFileHtml += "</ul>";
              				
              				$("#lblattachedFile").html(attachFileHtml);
              			}
	   	            	
              			if(menuType === "5"){
              				$("#lblboardTitleLink").html("<iframe width='590' height='332' src='"+data.data.boardTitleLink+"' title='"+data.data.boardTitle+"' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>");
              			}
              			
	   	         		$('#modalBoardView').data('seq', rowData.seq);
	   	         		$('#modalBoardView').modal();
                    });
	            	
        		});
            }},
			{ data: 'regDate', className:      'text-center'},
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
                    text: '등록',
                    className: 'mr-1',
                    attr:{
                    	'data-toggle':'modal',
                    	'data-target':'#modalBoardSave'
                    },
                    action: function(e, dt, node, config) {                    	
                    }
                }
            ],
        }
    } );
	
	if($("div#divAttachedFile").length > 0){
		var myDropzone = new Dropzone('div.dropzone',{
			url : "/admin/dragDropUploadAjax.do?type=board",
			maxFiles : 10,
			paramName : "upload",
			maxFilesize : 3,
			addRemoveLinks : true,
			dictDefaultMessage : "파일을 선택해 주십시오.(최대 10개 파일, 각 3MB 최대 30MB 첨부 가능)",
			acceptedFiles : '.xlsx,.xls,image/*,.doc, .docx,.ppt, .pptx,.txt,.pdf,.hwp',
			createImageThumbnails : true,
			success : function(file, response) {
				$("#boardForm").append("<input type='hidden' role='attachSaveFileName' name='attachSaveFileName' value='"+response.saveFileName+"' data-uuid='"+file.upload.uuid+"' />");
				$("#boardForm").append("<input type='hidden' role='attachOriFileName' name='attachOriFileName' value='"+response.fileName+"' data-uuid='"+file.upload.uuid+"' />");
				$("#boardForm").append("<input type='hidden' role='attachFileSize' name='attachFileSize' value='"+file.size+"' data-uuid='"+file.upload.uuid+"' />");
			},
			accept : function(file, done) {
				if (totalsize >= MAX_TOTAL_SIZE) {
					file.status = Dropzone.CANCELED;
					this._errorProcessing([ file ],"최대 30MB 첨부 가능", null);
				} else {
					done();
				}
			},
			init : function() {
				this.on("addedfile",function(file) {
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
	
				this.on("error",function(file) {
					totalsize -= parseFloat((file.size / (1024 * 1024)).toFixed(2));
				});
			}
		});
	}
	
	$("#category").select2();
	
	if(menuType === "2"){
		$('#boardForm').validate({
			rules:{
				boardTitle:{required:true}
			}
		});
	}
	else if(menuType === "3"){
		$('#boardForm').validate({
			rules:{
				boardTitle:{required:true},
			}
		});
	}
	else if(menuType === "4"){
		$('#boardForm').validate({
			rules:{
				boardTitle:{required:true},
			}
		});
	}
	else if(menuType === "5"){
		$('#boardForm').validate({
			rules:{
				boardTitle:{required:true},
				boardTitleLink:{required:true}
			}
		});
	}
	else{
		jQuery.validator.addMethod('attachFileCheck', function(value, element) {
		        return true;
		    }, '필수 입력값 입니다.'
		);
		
		$('#boardForm').validate({
			rules:{
				subject:{required:true},
				attachFile:{attachFileCheck:true}
			}
		});
	}
}

function initEvent(){
	$(document).on('click', 'a[role=dataEdit]', function(){
		
    	$('#modalBoardSave').data('seq', $(this).data('seq'));
		$('#modalBoardSave').modal();
    });  
    
    $(document).on('click', 'a[role=dataRemove]', function(){
    	var seq = $(this).data('seq');
    	
        swalInit.fire({
             title: '게시글을 삭제하시겠습니까?',
             text: '',
             showCancelButton: true,
             confirmButtonText: '예',
             cancelButtonText: '아니요',
             confirmButtonClass: 'btn btn-success',
             cancelButtonClass: 'btn btn-danger',
             buttonsStyling: false
         }).then(function(result) {
             if(result.value) {
           	  postAjax('/admin/deleteBoardAjax.do', {seq:seq, boardCode:getQuerystring().boardCode}, function(data, status){
        			showAjaxMessage(data);
        			if(data.isSuccess === '1')
	        			$('#list').DataTable().ajax.reload();
        		});
             }
             else if(result.dismiss === swal.DismissReason.cancel) {
           	
             }
         });
    });

    $('#btnBoardDataEdit').click(function(){
    	$('#modalBoardSave').data('seq', $('#modalBoardView').data('seq'));
    	$('#modalBoardSave').modal();
    });
    
    $('#modalBoardSave').on('show.bs.modal', function(e) {
        if ($('#modalBoardSave').data('seq'))
    	{
            postAjax('/admin/selectBoardAjax.do', {seq:$(this).data('seq'), boardCode:getQuerystring().boardCode}, function(data, status){
                var formInput = $('#boardForm input[type!=radio]');
        		
        	    $(formInput).each(function(i, input){
                    var inputValue = data.data[$(input).attr('name')];
        		    $(input).val(inputValue);
                });
        	  
        	    if($("#boardContent").length > 0)
                	CKEDITOR.instances.boardContent.setData(data.data.boardContent);
        	    
        	    if(data.data.listBoardAttach && data.data.listBoardAttach.length > 0)
      			{
      				var attachFileHtml = "<ul class='list list-unstyled mb-0'>";
      				$(data.data.listBoardAttach).each(function(i, fileItem){
      					attachFileHtml += "	<li data-seq='"+fileItem.seq+"'>";
	   	            	attachFileHtml += "		<i class='icon-download4 text-success mr-2'></i>";
	   	            	attachFileHtml += "		<a href='/admin/downloadboardfile.do?boardCode="+getQuerystring().boardCode+"&seq="+fileItem.seq+"' target='_blank'>"+fileItem.oriFileName+"</a>";
	   	            	attachFileHtml += "		<button type='button' class='btn btn-sm text-white bg-warning removeaddfile'>삭제</button>";
	   	            	attachFileHtml += "	</li>";		
      				});
      				
      				attachFileHtml += "</ul>";
      				
      				$("#lblattachedFileView").html(attachFileHtml);
      			}
        	    $("#trAttchedFile").show();
            });
        }
        else
        	$("#regDate").val(getToDay());
    });
    
    $(document).on("click", "button.removeaddfile", function(){
    	var li = $(this).closest("li");
    	var seq = $(this).closest("li").data("seq");
    	
    	sweetConfirm("첨부파일을 삭제하시겠습니까?", "", function(){
        	ajax($('#modalBoardSave div.modal-content'), '/admin/deleteBoardAttachFileAjax.do', {seq:seq, boardCode:getQuerystring().boardCode}, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
                	li.remove();
                }
            });
    		
    	});
    });

    $('#modalBoardSave').on('hidden.bs.modal', function(e) {
    	initForm('boardForm');
        $('#modalBoardSave').data('seq', "");
        $('input[role=attachSaveFileName]').remove();
        $('input[role=attachOriFileName]').remove();
        $('input[role=attachFileSize]').remove();
        
        if($("#boardContent").length > 0)
        	CKEDITOR.instances.boardContent.setData("");
        
        $("#lblattachedFileView").html("");
        $("#lblattachedFile").html("");
    });
    
    $('#btnBoardDataSave').click(function(){
        if ($('#boardForm').valid())
		{
        	if($("#boardContent").length > 0)	
            	CKEDITOR.instances.boardContent.updateElement();
            
        	if(menuType === "5"){
        		if(!$("#boardTitleLink").val()){
        			toastr.warning("유튜브 링크주소를 입력해 주세요.");
        			return;
        		}
        		
        		if(!$("#boardTitleLink").val().startsWith("https://")){
	        		var rex = /<iframe[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>/g;
	        		var exec = rex.exec($("#boardTitleLink").val());
	        		if(exec != null && exec.length > 0)
	        			$("#boardTitleLink").val(exec[1]);
        		}
        	}
        	
        	
        	var formData = $('#boardForm').serializeObject();
        	formData.boardCode = getQuerystring().boardCode;
        	//var formData = new FormData($('#boardForm')[0]);
        	//formData.append("boardCode", getQuerystring().boardCode);
        	
            ajax($('#modalBoardSave'), '/admin/mergeBoardAjax.do', formData, function(data, status){
                showAjaxMessage(data);
                if (data.isSuccess === '1')
                {
				    $('#list').DataTable().ajax.reload(null, false);
				    $('#modalBoardSave').modal('hide');
                }
            });
        }
    });
}

</script>

<div class='card'>
	<div class='card-header bg-transparent header-elements-inline'>
		<h5 class='card-title font-weight-bold'><i class="icon-chevron-right mr-1"></i> ${menuViewVO.menuTitle }</h5>
		<div class="header-elements">
			<div class="list-icons ml-3">
          		<!-- <a class="list-icons-item" data-action="collapse"></a> -->
          		<a class="list-icons-item" data-action="reload"></a>
          		<!-- <a class="list-icons-item" data-action="remove"></a> -->
          	</div>
       	</div>
	</div>
	<table id='list' class='table table-hover'>
		<colgroup>
			<col style=''>
			<col style='width: 16%;'>  
			<col style='width: 100px;'>
		</colgroup>
		<thead>
			<tr>
				<th>no</th>
				<th>제목</th>
				<th>작성일</th>
				<th>기능</th>
			</tr>
		</thead>
		<tbody>
			
		</tbody>
	</table>
</div>	
