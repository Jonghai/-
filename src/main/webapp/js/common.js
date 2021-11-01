jQuery.validator.setDefaults({
	errorClass:"border-warning" , 
	errorPlacement: function (error, element) {
    },
	onfocusout:false,
	onkeyup:false,
	showErrors:function(errorMap, errorList)
	{
		if(errorList.length === 0)
			return;
		
		$(errorList).each(function(i, errorInfo){
			var messsage = errorInfo.message;
			var element = $(errorInfo.element);
			var method = errorInfo.method;
			
			if(element[0].type === "checkbox" || element[0].type === "radio")
			{
				element.parent().parent().addClass("border-1 border-warning");
				element.click(function(){
	            	if($(this).valid())
	            		element.parent().parent().removeClass("border-1 border-warning");
	            });
			}
			else if (element.parent('.input-group').length) { 
	        	element.parent().parent().addClass("border-warning");
	        	
	        } else if (element.hasClass('select2-hidden-accessible')) {     
	            element.next('span').find(".select2-selection").addClass("border-warning");
	            element.change(function(){
	            	if($(this).valid())
	            		element.next('span').find(".select2-selection").removeClass("border-warning");
	            });
	        }
	        else if(element[0].type === "hidden")
			{
				element.closest("div").addClass("border-1 border-warning");
			}
	        else {
	            element.addClass("border-warning");
	        }
			
		});
		
		var errorElement = $(errorList[0].element);
		var method = errorList[0].method;
		var errorMessage = "";
		
		if(method === "required"){
			var placeHolder = errorElement.attr("placeholder");
			if(placeHolder){
				errorMessage = placeHolder;
				if(checkBatchimEnding(placeHolder)){
					errorMessage += "은";
				}
				else
					errorMessage += "는";
				
				//errorMessage = errorMessage +" " + errorList[0].message
				errorMessage = errorMessage +" 필수 입력값 입니다.";
			}
			else{
				if(errorList[0].message)
					errorMessage = errorList[0].message;
				else
					errorMessage = "값을 입력해 주세요.";
			}
		}
		else
			errorMessage = errorList[0].message;
		
		if(typeof toastr !== "undefined")
			toastr.warning(errorMessage);
		else
			alert(errorMessage);
		errorElement.focus();
	}
	
});

$(document).on({

	'show.bs.modal' : function() {
		var zIndex = 1040 + (10 * $('.modal:visible').length);
		$(this).css('z-index', zIndex);
		setTimeout(function() {
			$('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack'); }, 0);
	},

	'hidden.bs.modal' : function() {
		if ($('.modal:visible').length) {
			$('body').addClass('modal-open');
		}
	}
}, '.modal');

/*$(document).on('hidden.bs.modal', function (event) {
	if ($('.modal:visible').length) {
		$('body').addClass('modal-open');
	}
});*/

$(function() {

	$.ajaxSetup({timeout:5000});
	
	$("input").attr("autocomplete", "off");
	
	if(typeof toastr !== 'undefined'){
		toastr.options = {
			positionClass : 'toast-top-center'
		};
	}
	
	$("input[data-inputtype=onlynumber]").keyup(function (e) {
		var v = $(this).val();
		$(this).val(v.replace(/[^0-9]/gi,''));
	});
	
	$("input[data-inputtype=phone]").keyup(function (e) {
		var v = $(this).val();
		$(this).val(v.replace(/[^0-9|\-]/gi,''));
	});

	var queryString = getQuerystring();
});

function appendSystemLog(message)
{
	postAjax("/accesslog/insertAccessLogAjax.do", {logContents:message, link:location.pathname}, function(){});
}

function postAjax(url, data, func, async) {
	
	if(async === false)
		$.ajaxSetup({ async:async });
	
	$.post(url, data, func, "json");
	
	$.ajaxSetup({ async:true });
}

function ajax(roadingElement, url, data, func, isFile, async)
{
	if(isFile === undefined)
		isFile = false;
	if(async === undefined)
		async = true;
		
	var ajaxLoading = roadingElement;
	
	if(ajaxLoading === undefined
			|| ajaxLoading === null )
		ajaxLoading = $("body");
	
	if(ajaxLoading)
	{
	    $(ajaxLoading).block({
	    		baseZ: 10000, 
	            message: '<i class="icon-sync spinner"></i> 요청을 처리중입니다......',
	            overlayCSS: {
	                backgroundColor: '#1B2024',
	                opacity: 0.85,
	                cursor: 'wait'
	            },
	            css: {
	                border: 0,
	                padding: 0,
	                backgroundColor: 'none',
	                color: '#fff'
	            }
	    });	        
	    
	    $('.blockUI.blockMsg').css("top", "200px");
	}
	
	var ajaxOption = {
	       	url: url,	        
	        data: data,
	        type: 'POST',
	        success: func,
	        async:async,
	        timeout: 60000,
	        complete:function(response, status)
	        {
	        	if(status === "timeout"){
	        		if(typeof toastr !== 'undefined')
	        			toastr.warning("처리 지연 발생 잠시 후 다시 시도해 주십시오.");
	        	}
	        	else{
	        		//if(response.responseJSON)
	        		//	showAjaxMessage(response.responseJSON);
	        	}
	        	
	        	if(ajaxLoading)
	        		$(ajaxLoading).unblock();
	        }
	        
	    };
	if(isFile)
	{
		ajaxOption.processData = false;
		ajaxOption.contentType = false;
	}
	else{
		//\ajaxOption.processData = true;
		//ajaxOption.contentType = true;
	}

    $.ajax(ajaxOption);
}

function htmlEncode(value) {
	return $('<div/>').text(value).html();
}

function htmlDecode(value) {
	if(!value)
		return "";
	
	return value.replace(/&amp;/g, '&')
				.replace(/&lt;/g, '<')
				.replace(/&gt;/g, '>')
				.replace(/&quot;/g, '\"')
				.replace(/&#39;/g, '\'')
				.replace(/&#x2F;/g, '/')
				.replace(/&#x60;/g, '\`')
				.replace(/&#x3D;/g, '\=');
}

function showAjaxMessage(ajaxData) {
	if(ajaxData.msg){
		if (ajaxData.isSuccess === "1"){
			if(typeof toastr !== 'undefined')
				toastr.success(ajaxData.msg);
		}
		else if (ajaxData.isSuccess === "0"){
			if(typeof toastr !== 'undefined')
				toastr.warning(ajaxData.msg);
		}
	}
}

function redirect(url) {
	location.href = url;
}

function getQuerystring() {
	var params = {};
	window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str,
			key, value) {
		params[key] = decodeURIComponent(value);
	});
	return params;
}

function onlyNumber(evt) {
	var theEvent = evt || window.event;

	// Handle paste
	if (theEvent.type === 'paste') {
		key = event.clipboardData.getData('text/plain');
	} else {
		// Handle key press
		var key = theEvent.keyCode || theEvent.which;
		key = String.fromCharCode(key);
	}
	var regex = /[0-9]|\./;
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
}

function setCookie(name, value, expiredays) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	document.cookie = name + "=" + escape(value) + "; path=/; expires="
			+ todayDate.toGMTString() + ";"
}

function getCookie(name) {
    var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return value? value[2] : null;
}

function getToDay() 
{ 
	var d = new Date();
	var month = '' + (d.getMonth() + 1);
	var day = '' + d.getDate();
	var year = d.getFullYear(); 
	if (month.length < 2) 
		month = '0' + month; 
	
	if (day.length < 2) 
		day = '0' + day; 
	
	return [year, month, day].join('-'); 
}

function formatDate(date) 
{ 
	var d = new Date(date);
	var month = '' + (d.getMonth() + 1);
	var day = '' + d.getDate();
	var year = d.getFullYear(); 
	if (month.length < 2) 
		month = '0' + month; 
	
	if (day.length < 2) 
		day = '0' + day; 
	
	return [year, month, day].join('-'); 
}

function strToDate(dateStr)
{
	dateStr = dateStr.replace(/-/g, '');
	
	var y = dateStr.substr(0, 4);
    var m = dateStr.substr(4, 2);
    var d = dateStr.substr(6, 2);
    return new Date(y,m-1,d);

}

function initForm(parentId)
{
	$("#" + parentId + " input[type!=radio][type!=checkbox]").val("");
	if($("#" + parentId + " select[multiple!='multiple']").length > 0)
	{
		$("#" + parentId + " select[multiple!='multiple']").val("");
		$("#" + parentId + " select").trigger("change");
	}
	$("#" + parentId + " textarea").val("");
	
	$("input").removeClass("border-warning");
	$(".dz-preview").remove();
	$(".dropzone").removeClass("dz-started dz-max-files-reached");
}

function validatePassword(character) {
    return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/.test(character)
}

function validateIP(ip) {
    var expIP = /^(1|2)?\d?\d([.](1|2)?\d?\d){3}$/;
    return expIP.test(ip);
}

function fnExcelReport(id)
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById(id); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
    	$("body").append("<iframe id='txtArea1' style='display:none'></iframe>");
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"통계.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  

    return (sa);
}

function numberWithCommas(x) {
	if(x)
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	return "0";
}

function bizNoFormatter(num) {
	var formatNum = '';
	try {
		if (num.length === 10) {
			/*if (type == 0) {
				formatNum = num.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-*****');
			} else {
				formatNum = num.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-$3');
			}*/
			formatNum = num.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-$3');
		}
	} catch (e) {
		formatNum = num;
	}
	return formatNum;
}

function getAdminPageListPagination(element, totalCount, listSize, pageNumber)
{
	var groupLength = 5;
	var currentUrl = location.pathname + "?";
	var currentPage = "1";
	var queryString = getQuerystring();
	
	if(queryString.pn)
		currentPage = getQuerystring().pn;
	
	if(pageNumber !== undefined && pageNumber !== null)
		currentPage = pageNumber;
	
	
	$.each(queryString, function(key, value){
		if(key !== "seq" && key !== "pn")
		{
			currentUrl += key + "=" + value + "&";
		}
	});
	
	var nCurrentPage = 1;
	try
	{
		if(!isNaN(currentPage))
			nCurrentPage = Number(currentPage);
	}
	catch(e){console.log(e);}
	
	var nTotalPage = parseInt(totalCount / listSize);
	var nRemainder = totalCount % listSize;
	if(nTotalPage !== 0)
	{
		if(nRemainder !== 0)
			nTotalPage = nTotalPage+1;
	}
	else
		nTotalPage = 1;
	
	var prevPageNumber = nCurrentPage - 1;
	var pprevPageNumber = nCurrentPage - groupLength;
	var prevPageUrl = currentUrl+"pn="+prevPageNumber;
	var pprevPageUrl = currentUrl+"pn="+pprevPageNumber;
	
	if(prevPageNumber < 1)
	{
		prevPageNumber = 1;
		prevPageUrl = currentUrl+"pn=1";
	}
	if(pprevPageNumber < 1){
		pprevPageNumber = 1;
		pprevPageUrl = currentUrl+"pn=1";
	}
	
	/*
	<a class="paginate_button previous disabled" data-dt-idx="0" tabindex="0" id="list_previous">이전</a>
	<span>
		<a class="paginate_button current" aria-controls="list" data-dt-idx="1" tabindex="0">1</a>
		<a class="paginate_button " aria-controls="list" data-dt-idx="2" tabindex="0">2</a>
		<a class="paginate_button " aria-controls="list" data-dt-idx="3" tabindex="0">3</a>
		<a class="paginate_button " aria-controls="list" data-dt-idx="4" tabindex="0">4</a>
		<a class="paginate_button " aria-controls="list" data-dt-idx="5" tabindex="0">5</a>
		<span class="ellipsis">…</span>
		<a class="paginate_button " aria-controls="list" data-dt-idx="6" tabindex="0">1334</a>
	</span>
	<a class="paginate_button next" aria-controls="list" data-dt-idx="7" tabindex="0" id="list_next">다음</a>
	*/
	
	var prevDisabled = "";
	if(pageNumber === 1)
		prevDisabled = "disabled";
	
	var itemHtml = "<a class='paginate_button previous "+prevDisabled+"' data-pagenumber='"+prevPageNumber+"'>이전</a>";
	itemHtml += "<span>";
	var pageStartNumber = nCurrentPage - 2;
	
	//if(nTotalPage === groupLength)
	//	pageStartNumber = 1;
	
	if(pageStartNumber <= 0)
		pageStartNumber = 1;
	
	for(var i = pageStartNumber;i < pageStartNumber+5; i++)
	{
		if(i <= nTotalPage)
		{
			var current = "";
			if(pageNumber === i.toString() || pageNumber === i)
				current = "current";
			
			itemHtml += "<a class='paginate_button "+current+"' data-pagenumber='"+i+"' tabindex='"+i+"'>"+i+"</a>";
			
		}
	}
	
	if(nTotalPage > 10 && nCurrentPage + 5 < nTotalPage){
		itemHtml += "<span class='ellipsis'>…</span>";
		itemHtml += "<a class='paginate_button' data-pagenumber='"+nTotalPage+"'>"+nTotalPage+"</a>";
	}
	itemHtml += "</span>";
	var nextPageNumber = nCurrentPage + 1;
	var nnextPageNumber = nCurrentPage + groupLength;
	var nextPageUrl = currentUrl+"pn="+nextPageNumber;
	var nnextPageUrl = currentUrl+"pn="+nnextPageNumber;
	
	/*if(nextPageNumber > nTotalPage){
		nextPageNumber = nTotalPage.toString();
		nextPageUrl = currentUrl+"pn="+nTotalPage.toString();
	}
	
	if(nnextPageNumber > nTotalPage){
		nnextPageNumber = nTotalPage.toString();
		nnextPageUrl = currentUrl+"pn="+nTotalPage.toString();
	}*/
	var nextDisabled = "";
	if(nTotalPage < nextPageNumber)
		nextDisabled = "disabled";
	
	itemHtml += "<a class='paginate_button next "+nextDisabled+"' data-pagenumber='"+nextPageNumber+"'>다음</a>";
	
	$(element).html(itemHtml);
}

function getTextLength(str) {
    var len = 0;
    for (var i = 0; i < str.length; i++) {
        if (escape(str.charAt(i)).length == 6) {
            len++;
        }
        len++;
    }
    return len;
}

String.prototype.startsWith = function(str) {
	if (this.length < str.length) { return false; }
	return this.indexOf(str) == 0;
}

String.prototype.endsWith = function(str) {
	if (this.length < str.length) { return false; }
	return this.lastIndexOf(str) + str.length == this.length;
}

String.prototype.replaceEnter = function(){
	if(this){
		return this.replace(/\n/g, '<br />');
	}
	else
		return "";
}


function checkBatchimEnding(word) {
	  if (typeof word !== 'string') return null;
	 
	  var lastLetter = word[word.length - 1];
	  var uni = lastLetter.charCodeAt(0);
	 
	  if (uni < 44032 || uni > 55203) return null;
	 
	  return (uni - 44032) % 28 != 0;
}

function sweetConfirm(title, text, okFunction, cancleFunction){
	swalInit.fire({
        title: title,
        text: text,
        showCancelButton: true,
        confirmButtonText: '예',
        cancelButtonText: '아니요',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then(function(result) {
        if(result.value) {
        	okFunction.call();
        }
        else if(result.dismiss === swal.DismissReason.cancel) {
        	cancleFunction.call();
        }
    });
}

jQuery.fn.serializeObject = function() {
    var obj = null;
    try {
        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
            var arr = this.serializeArray();
            if (arr) {
                obj = {};
                jQuery.each(arr, function() {
                	if(obj[this.name])
                		obj[this.name] += ","  +this.value; 
                	else
                		obj[this.name] = this.value;
                });
            }
        }
    } catch (e) {
    } finally {
    }
 
    return obj;
};