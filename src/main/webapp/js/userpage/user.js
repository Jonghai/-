$(document).ready(function() {
	$('a[href="#"]').click(function(ignore) {
        ignore.preventDefault();  
    });
	
	$(".headmu > ul > li").mouseover(function() {
		$("#header").addClass("over");
		$(this).addClass("on");
	})
	
	$(".headmu > ul > li").mouseout(function() {
		$("#header").removeClass("over");
		$(this).removeClass("on");
	})
	
	$(".headmu > ul > li > a").focus(function() {
		$("#header").addClass("over");
		$(this).parent().addClass("on");
	})
	
	$(".headmu > ul > li:last-child .submu ul li:last-child() div > a").blur(function() {
		$("#header").removeClass("over");
		$(".headmu > ul > li").removeClass("on");
	})
	
	$(".mheadmp a.menu").click(function() {
		$(".mobile-menu").addClass("on");
		$("#header").addClass("over");
	})
	
	$(".mobile-menu ul.one-depth > li > a").click(function() {
		$(this).parent().toggleClass("active");
		$(this).parent().children("ul").toggleClass("show");
	})
	
	$(".mobile-menu a.close").click(function() {
		$(".mobile-menu").removeClass("on");
		$("#header").removeClass("over");
	})
	
	$(".headmp a.menu").click(function() {
		$("#header").toggleClass("ston");
		$(".sitemap").toggleClass("active");
	})
	
	$(".sitemap a.last").blur(function() {
		$("#header").removeClass("ston");
		$(".sitemap").removeClass("active");
	})
})

function getQuerystring() {
	var params = {};
	window.location.search.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(str,
			key, value) {
		params[key] = decodeURIComponent(value);
	});
	return params;
}
  
function putMenuClassOn(code) {
	$(".left-menu li."+code).addClass("on")		
	$(".left-menu li."+code).parent().parent("li").addClass("on");
	$(".current").html($(".left-menu li."+code +" a").text());
}

function putTopHtml(code, oDepth) {
	var topHtml = "<li>HOME</li>";
	
	var parentName = $(".left-menu li."+code).parent().parent("li").children("a").text();
	var childrenName = $(".left-menu li."+code+" a").text();
	
	if(parentName) {
		topHtml += "<li>"+oDepth+"</li>";
		topHtml += "<li>"+parentName+"</li>";
		topHtml += "<li>"+childrenName+"</li>";
	}else {
		topHtml += "<li>"+oDepth+"</li>";
		topHtml += "<li>"+childrenName+"</li>";
	}
	
	$(".top ul").html(topHtml);
}

function dateInput(n,m) {//n이 이전, m이 현재
	var date = new Date();
	
	var month = date.getMonth();
	var day = date.getDate();
	
	var start;
	var today = new Date(Date.parse(date));
	
	if(n === "7") {
		start = new Date(new Date().setDate(day - 7));
	}else if(n === "30") {
		start = new Date(new Date().setMonth(month - 1));
	}else if(n === "90") {
		start = new Date(new Date().setMonth(month - 3));
	}
	
	var yyyy = start.getFullYear();
	var mm = start.getMonth()+1;
	var dd = start.getDate();
	
	if(mm<10) {
		mm = "0" + mm;
	}
	
	if(dd<10) {
		dd = "0" + dd;
	}
	
	var t_yyyy = today.getFullYear();
	var t_mm = today.getMonth()+1;
	var t_dd = today.getDate();
	
	if(t_mm<10) {
		t_mm = "0" + t_mm;
	}
	
	if(t_dd<10) {
		t_dd = "0" + t_dd;
	}
	
	$(".startdate").val(yyyy+"-"+mm+"-"+dd);
	$(".enddate").val(t_yyyy+"-"+t_mm+"-"+t_dd);
}