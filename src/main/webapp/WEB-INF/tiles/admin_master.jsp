<%@ page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	
	<tiles:insertAttribute name="admin_header" />
</head>

<body class='custom-scrollbars'>
	<tiles:insertAttribute name="admin_top" />
	<div class="page-content">
		<div class="sidebar sidebar-dark sidebar-main sidebar-expand-lg">
			<div class="sidebar-content">
				<div class="sidebar-section">
					<ul class="nav nav-sidebar">
						${leftMenuHtml}
					</ul>
				</div>
			</div>
		</div>
		<div class="content-wrapper">
			<div class="content-inner">
				<tiles:insertAttribute name="admin_body" />
			</div>
			<div class="navbar navbar-expand-lg navbar-light border-bottom-0 border-top">
				<div class="text-center d-lg-none w-100">
					<span class="navbar-text">
						<spring:message code="site.copyright"></spring:message>
					</span>
				</div>

				<div class="navbar-collapse collapse" id="navbar-footer">
					<span class="navbar-text">
						<spring:message code="site.copyright"></spring:message>
					</span>
				</div>
			</div>

		</div>
	</div>
</body>
</html>
