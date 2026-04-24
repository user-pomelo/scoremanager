<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">削除完了</c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>

            <p class="p-3 bg-success bg-opacity-25">
                科目情報の削除が完了しました。
            </p>

            <a href="SubjectList.action">科目一覧へ</a>

        </section>

    </c:param>
</c:import>