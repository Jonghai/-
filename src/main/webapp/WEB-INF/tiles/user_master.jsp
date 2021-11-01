<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<tiles:insertAttribute name="user_top" />
</head>

<body>
	<div id="wrap"> 
	<!-- Content area -->
	<tiles:insertAttribute name="user_header" />
	
	<div class="content" id="content">
	<tiles:insertAttribute name="user_body" />
	</div>
	
	<!-- /content area -->
	<tiles:insertAttribute name="user_footer" />
	</div>			
</body>
</html>
