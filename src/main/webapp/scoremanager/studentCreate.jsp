<%-- 学生登録画面 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

    <c:param name="title">
        学生情報登録
    </c:param>

    <c:param name="content">

        <section class="me-4">

            <!-- タイトル -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                学生情報登録
            </h2>

            <!-- フォーム -->
            <form action="StudentCreateExecute.action" method="post">

                <!-- 入学年度 -->
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <select name="entYear" class="form-select">
                        <option value="">--------</option>
                        <c:forEach var="y" items="${entYears}">
                            <option value="${y}" ${y == entYear ? 'selected' : ''}>
                                ${y}
                            </option>
                        </c:forEach>
                    </select>

                    <c:if test="${not empty entYearError}">
                        <div class="text-warning">
                            ${entYearError}
                        </div>
                    </c:if>
                </div>

                <!-- 学生番号 -->
                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text"
                           name="no"
                           class="form-control"
                           value="${no}"
                           placeholder="学生番号を入力してください">

                    <c:if test="${not empty noError}">
                        <div class="text-warning">
                            ${noError}
                        </div>
                    </c:if>
                </div>

                <!-- 氏名 -->
                <div class="mb-3">
                    <label class="form-label">氏名</label>
                    <input type="text"
                           name="name"
                           class="form-control"
                           value="${name}"
                           placeholder="氏名を入力してください">

                    <c:if test="${not empty nameError}">
                        <div class="text-warning">
                            ${nameError}
                        </div>
                    </c:if>
                </div>

                <!-- クラス -->
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select name="classNum" class="form-select">
                        <option value="">--------</option>
                        <c:forEach var="c" items="${classNums}">
                            <option value="${c}" ${c == classNum ? 'selected' : ''}>
    							${c}
							</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- ボタン -->
                <button type="submit" class="btn btn-primary">
                    登録して終了
                </button>
                <br>

                <a href="StudentList.action">
                    戻る
                </a>

            </form>

        </section>

    </c:param>
</c:import>