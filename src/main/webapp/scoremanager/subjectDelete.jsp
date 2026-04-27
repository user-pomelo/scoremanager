<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                科目情報削除
            </h2>

            <c:if test="${not empty subject}">
                <p class="mb-4">
                    「${subject.name}（${subject.cd}）」を削除してもよろしいですか？
                </p>

                <form action="SubjectDelete.action" method="post">
                    <input type="hidden" name="cd" value="${subject.cd}">
                    <input type="hidden" name="delete" value="true">

                    <button class="btn btn-danger" type="submit">削除</button>
                </form>
            </c:if>

            <c:if test="${empty subject}">
                <div class="alert alert-warning">
                    科目が存在しません。
                </div>
            </c:if>

            <div class="mt-4">
                <a href="SubjectList.action">戻る</a>
            </div>

        </section>
    </c:param>
</c:import>
