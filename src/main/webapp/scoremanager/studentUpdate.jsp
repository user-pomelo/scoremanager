<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生情報変更</c:param>

    <c:param name="content">

        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>

            <form action="StudentEditExecute.action" method="post">

                <!-- 入学年度 -->
                <div class="mb-3">
                    <label class="form-label">入学年度</label>
                    <input type="text" class="form-control"
                           name="entYear"
                           value="${student.entYear}"
                           readonly>
                </div>

                <!-- 学生番号 -->
                <div class="mb-3">
                    <label class="form-label">学生番号</label>
                    <input type="text" class="form-control"
                           name="no"
                           value="${student.no}"
                           readonly>
                </div>

                <div class="mb-3">
				    <label class="form-label">氏名</label>
				    <input type="text"
				           name="name"
				           class="form-control"
				           value="${student.name}"
				           required>
				    <c:if test="${not empty nameError}">
				        <div class="text-danger">${nameError}</div>
				    </c:if>
				</div>

				
                <!-- クラス -->
                <div class="mb-3">
                    <label class="form-label">クラス</label>
                    <select name="classNum" class="form-select">
                        <c:forEach var="c" items="${classNums}">
                            <option value="${c}"
                                ${c == student.classNum ? 'selected' : ''}>
                                ${c}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- 在学中 -->
                <div class="form-check mb-3">
                    <input type="checkbox" name="isAttend"
                           class="form-check-input"
                           ${student.isAttend ? 'checked' : ''}>
                    <label class="form-check-label">在学中</label>
                </div>

                <button type="submit" class="btn btn-primary">
                    変更
                </button>
                <br>

                <a href="StudentList.action">戻る</a>

            </form>

        </section>

    </c:param>
</c:import>