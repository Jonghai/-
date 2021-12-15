<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/tiles/admin_header.jsp"%>

<style>
@media (min-width: 576px){
	.login-form {
	    width: 24rem;
	}
}
</style>
<script type="text/javascript">
		$(function(){
			
			$("#frmLogin").validate({
				rules:{
					userId:"required",
					userPwd:"required",
				},
				/* messages:{
					userId:"아이디를 입력해 주십시오.",
					userPwd:"비밀번호를 입력해 주십시오."
				} */
			});
			$("#btnLogin").click(function(){
				if($("#frmLogin").valid())
				{
					var userId = $("#userId").val();
					var userPwd = $("#userPwd").val();
					
					postAjax("/admin/loginProcessAjax.do", {userId:userId, userPwd:userPwd}, function(data, status){
						if(data.isSuccess === "1"){
							location.href = data.data;
						}
						else
							showAjaxMessage(data);
					});
				}
			});
			
			$("#userID,#userPwd").keyup(function(e){
				if(e.keyCode === 13)
					$("#btnLogin").trigger("click");
			});	
		});
	</script>
</head>

<body>

	<!-- Main navbar -->
	<!-- <div class="navbar navbar-expand-md navbar-light navbar-static">
		<div class="navbar-brand wmin-200 pb-1 pt-1">
			<a href="#" class="d-inline-block">
				<img src="/css/egovframework/limitless/global/images/logo_light2.png" style='width:200px;height:38px;' alt="">
			</a>
		</div>
	</div> -->
	<!-- /main navbar -->


	<!-- Page content -->
	<div class="page-content login-cover">
	<!-- <div class="page-content login-cover" style="background:url('/css/egovframework/img/adminloginback.png')"> -->

		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Content area -->
			<div class="content d-flex justify-content-center align-items-center pt-0">

				<!-- Login form -->
				<form class="login-form" action="/login/loginProcess.do" id="frmLogin" name="frmLogin" method="post">
					<div class="card mb-0">
						<div class="card-body">
							<div class="text-center mb-3">
								<!-- <i class="icon-reading icon-2x text-slate-300 border-slate-300 border-3 rounded-round p-3 mb-3 mt-1"></i> -->
								<img class='mt-2 mb-2' src='/css/egovframework/img/bj_logo.png' alt='LOGO'>
								<span class="d-block text-muted">인가된 사용자만 접근할 수 있습니다.</span>
							</div>

							<div class="form-group form-group-feedback form-group-feedback-left">
								<input type="text" class="form-control" placeholder="로그인 ID" id="userId" name="userId" maxlength="20">
								<div class="form-control-feedback">
									<i class="icon-user text-muted"></i>
								</div>
							</div>

							<div class="form-group form-group-feedback form-group-feedback-left">
								<input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="로그인 비밀번호"  maxlength="20">
								<div class="form-control-feedback">
									<i class="icon-lock2 text-muted"></i>
								</div>
							</div>														
							<div class="form-group">
								<button type="button" class="btn btn-primary btn-block" id="btnLogin">로그인 <i class="icon-circle-right2 ml-2"></i></button>
							</div>

							<div class="text-center">
								<!-- <a href="login_password_recover.html">Forgot password?</a> -->
							</div>
						</div>
					</div>
				</form>
				<!-- /login form -->
			</div>
			<!-- /content area -->
		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->
	
</body>
</html>
