<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">変更完了</c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>

			<p class="text-center mb-4 p-2 bg-success bg-opacity-50 text-dark">
					変更が完了しました
			</p>

            <a href="StudentList.action">学生一覧へ</a>

        </section>

    </c:param>
</c:import>