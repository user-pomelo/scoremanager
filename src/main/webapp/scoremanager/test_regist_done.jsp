<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">登録完了</c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <p class="p-3 bg-success bg-opacity-25">
                登録が完了しました。
            </p>

            <a href="TestRegist.action">戻る</a>　　　　　<a href="TestList.action">成績参照</a>

        </section>

    </c:param>
</c:import>