<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生別成績一覧</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                学生別成績一覧
            </h2>

            <div class="fw-bold mb-3">
                氏名：${student.name}（${student.no}）
            </div>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>科目名</th>
                        <th>科目コード</th>
                        <th>回数</th>
                        <th>点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="t" items="${tests}">
                        <tr>
                            <td>${t.subjectName}</td>
                            <td>${t.subjectCd}</td>
                            <td>${t.no}</td>
                            <td>${t.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="TestList.action">戻る</a>

        </section>
    </c:param>
</c:import>
