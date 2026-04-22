<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="container-fluid d-flex justify-content-between align-items-center">
	
	<!-- タイトル -->
	<h1 class="h4 m-0">
		得点管理システム
	</h1>

	<!-- ログイン情報 -->
	<div>
		<c:if test="${not empty sessionScope.teacher}">
			<span class="me-3">
				${sessionScope.teacher.name} 様
			</span>

			<a href="Logout.action">
				ログアウト
			</a>
		</c:if>
	</div>

</div>