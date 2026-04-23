<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal">科目情報登録</h2>
            
            <form action="SubjectCreateExecute.action" method="post">
                <div class="mb-3">
                    <label class="form-label" for="subject-cd-input">科目コード</label>
                    <input class="form-control" type="text" id="subject-cd-input" name="cd" 
                           placeholder="科目コードを入力してください" maxlength="3" required>
                </div>
                
                <div class="mb-3">
                    <label class="form-label" for="subject-name-input">科目名</label>
                    <input class="form-control" type="text" id="subject-name-input" name="name" 
                           placeholder="科目名を入力してください" required>
                </div>
                
                <div class="mt-4">
                    <button class="btn btn-primary" type="submit">登録</button>
                    <a href="SubjectList.action" class="btn btn-secondary">戻る</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>