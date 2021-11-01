<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Main sidebar -->
<div class="sidebar sidebar-light sidebar-main sidebar-expand-md align-self-start m-0">

	<!-- Sidebar mobile toggler -->
	<div class="sidebar-mobile-toggler text-center">
		<a href="#" class="sidebar-mobile-main-toggle">
			<i class="icon-arrow-left8"></i>
		</a>
		<span class="font-weight-semibold">Main sidebar</span>
		<a href="#" class="sidebar-mobile-expand">
			<i class="icon-screen-full"></i>
			<i class="icon-screen-normal"></i>
		</a>
	</div>
	<!-- /sidebar mobile toggler -->


	<!-- Sidebar content -->
	<div class="sidebar-content">
		<div class="card card-sidebar-mobile">

			<!-- Header -->
			<div class="card-header header-elements-inline">
				<h6 class="card-title">충남 자금관리시스템</h6>
				<div class="header-elements">
					<div class="list-icons">
						<a class="list-icons-item" data-action="collapse"></a>
					</div>
				</div>
			</div>
			
			<!-- User menu -->
			<div class="sidebar-user">
				<div class="card-body">
					<div class="media">
						<div class="mr-3">
							<a href="#"><img src="/css/egovframework/limitless/global/images/placeholders/placeholder.jpg" width="38" height="38" class="rounded-circle" alt=""></a>
						</div>

						<div class="media-body">
							<div class="media-title font-weight-semibold">${sessionVO.userName}</div>
							<div class="font-size-xs opacity-50">
								<i class="icon-pin font-size-sm"></i> &nbsp;${sessionVO.deptName}
							</div>
						</div>

						<!-- <div class="ml-3 align-self-center">
							<a href="#" class="text-white"><i class="icon-cog3"></i></a>
						</div> -->
					</div>
				</div>
			</div>
			<!-- /user menu -->

			
			<!-- Main navigation -->
			<div class="card-body p-0">
				<ul class="nav nav-sidebar" data-nav-type="accordion">

					<!-- Main -->

					${limitlessLeftMenuHtml}
					<!-- /main -->

				
						
				</ul>
			</div>
			<!-- /main navigation -->

		</div>
	</div>
	<!-- /sidebar content -->
	
</div>
<!-- /main sidebar -->