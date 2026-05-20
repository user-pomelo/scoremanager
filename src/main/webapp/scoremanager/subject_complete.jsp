<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">登録完了</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                科目情報登録
            </h2>
            
            <div class="alert alert-success bg-success bg-opacity-25 border-0 text-dark py-3 px-4 mb-4 rounded">
                登録が完了しました。
            </div>
            
            <div class="mt-4">
                <a href="SubjectList.action" class="me-3">科目一覧へ</a>	
            </div>
        </section>
    </c:param>
</c:import>