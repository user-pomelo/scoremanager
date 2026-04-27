<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		ログイン
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="container" style="max-width: 500px;">
			<h2 class="h3 mb-4 fw-normal text-center bg-secondary bg-opacity-10 py-2">
				ログイン
			</h2>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger text-center">
					${errorMessage}
				</div>
			</c:if>
			
			<form action="Login.action" method="post">
				
				<div class="mb-3">
				    <label class="form-label">ID</label>
				    <input type="text"
				           name="login"
				           class="form-control"
				           required
				           maxlength="10"
				           placeholder="ID"
				           inputmode="latin"
				           style="ime-mode: disabled;">
				</div>
				
				<div class="mb-3">
				    <label class="form-label">パスワード</label>
				    <input type="password"
				           name="password"
				           id="password"
				           class="form-control"
				           required
				           maxlength="30"
				           placeholder="パスワード"
				           inputmode="latin"
				           style="ime-mode: disabled;">
				
				    <div class="text-center mt-2">
				        <input class="form-check-input" type="checkbox"
				               id="showPassword" onclick="togglePassword()">
				        <label class="form-check-label" for="showPassword">
				            パスワードを表示
				        </label>
				    </div>
				</div>



				<script>
					function togglePassword() {
						const pw = document.getElementById("password");
						const check = document.getElementById("showPassword");
						if (check.checked) {
							pw.type = "text";
						} else {
							pw.type = "password";
						}
					}
				</script>

				<div class="text-center">
					<button type="submit" class="btn btn-primary px-4">
						ログイン
					</button>
				</div>

			</form>
		</section>
	</c:param>

</c:import>