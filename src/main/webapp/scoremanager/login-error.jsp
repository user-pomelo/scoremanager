<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		ログインエラー
	</c:param>

	<c:param name="content">
	
		<section class="me-4">
				
			<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
				ログインエラー
			</h2>
			<p class="mb-4 p-3 bg-danger bg-opacity-75 text-dark">
					ログインIDまたはパスワードが
					間違っています
			</p>
			<div class="text-center">
				<a href="login.jsp">
					戻る
				</a>
			</div>
		</section>

	</c:param>

</c:import>