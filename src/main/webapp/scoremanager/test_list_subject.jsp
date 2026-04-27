<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">科目別成績一覧</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                科目別成績一覧
            </h2>

            <div class="mb-3 fw-bold">
                科目：${subject.name}（${subject.cd}）
            </div>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>入学年度</th>
                        <th>クラス</th>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>1回目</th>
                        <th>2回目</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${students}">
                        <tr>
                            <td>${s.entYear}</td>
                            <td>${s.classNum}</td>
                            <td>${s.no}</td>
                            <td>${s.name}</td>

                            <td>
                                <c:choose>
                                    <c:when test="${not empty s.point1}">${s.point1}</c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>

                            <td>
                                <c:choose>
                                    <c:when test="${not empty s.point2}">${s.point2}</c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <a href="TestList.action">戻る</a>

        </section>
    </c:param>
</c:import>
