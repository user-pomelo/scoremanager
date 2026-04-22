<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		ログアウト
	</c:param>

	<c:param name="content">
	
		<section class="me-4">
				
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				ログアウト
			</h2>
			<p class="text-center mb-4 p-2 bg-success bg-opacity-50 text-dark">
					ログアウトしました
			</p>
			<div class="me-4">
				<a href="login.jsp">
					ログイン
				</a>
			</div>
		</section>

	</c:param>

</c:import>