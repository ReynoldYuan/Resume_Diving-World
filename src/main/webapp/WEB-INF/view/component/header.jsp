<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	

<header class="p-3 mb-3 border-bottom">
	<div class="container">
		<div
			class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
			<a href="${root}/" class="nav-link px-2 link-secondary"><img src='assets/diving.jpg'  style="width: 50px;"></a>
			<ul
				class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
				<li><a href="${root}/lessons" class="nav-link px-2 link-dark">課程資訊</a></li>
				
				<c:if test="${loggedInMember!=null }">
					<!-- <li><a href="${root}/profile" class="nav-link px-2 link-dark">會員資訊</a></li> -->
					<li><a href="${root}/add_lesson" class="nav-link px-2 link-dark">新增課程</a></li>
				</c:if>
			</ul>
			<div class="dropdown text-end">

				<c:if test="${loggedInMember==null }">
					<a href="${root}/login">
						<button class="btn btn-primary">會員登入</button>
					</a>
				</c:if>
				
				<c:if test="${loggedInMember!=null }">
					<img style="width: 50px" src="${loggedInMember.mem_photo }"> ${loggedInMember.mem_name }
				</c:if>

			</div>
		</div>
	</div>
</header>