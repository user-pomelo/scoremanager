<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <form action="SubjectUpdate.action" method="post">

                <!-- 科目コード（表示のみ） -->
                <div class="mb-3">
                    <label class="form-label">科目コード</label>
                    <div class="form-control-plaintext">
                        ${subject.cd}
                    </div>
                    <input type="hidden" name="cd" value="${subject.cd}">
                </div>

                <!-- エラーメッセージ -->
                <c:if test="${not empty error}">
                    <div class="alert alert-warning">
                        ${error}
                    </div>
                </c:if>

                <!-- 科目名 -->
                <div class="mb-3">
                    <label class="form-label">科目名</label>
                    <input type="text" name="name" class="form-control"
                           value="${subject.name}" required>
                </div>

                <div class="mt-4">
                    <button class="btn btn-primary" type="submit">変更</button>
                    <br><br>
                    <a href="SubjectList.action">戻る</a>
                </div>

            </form>

        </section>
    </c:param>
</c:import>
