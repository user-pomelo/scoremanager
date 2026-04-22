<%-- 学生登録完了画面 --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        学生情報登録完了
    </c:param>

    <c:param name="content">

        <section class="me-4">

            <!-- タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報登録
            </h2>
            
            <!-- 完了メッセージ -->
			<p class="mb-4 p-3 bg-success bg-opacity-10 text-dark">
    			学生の登録が完了しました。
			</p>

            <!-- リンク -->
            <div class="d-grid gap-2 col-6">

                <a href="StudentCreate.action">
                    戻る
                </a>

                <a href="StudentList.action">
                    学生一覧
                </a>

            </div>

        </section>

    </c:param>

</c:import>