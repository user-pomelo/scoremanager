<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>科目情報削除</h2>

<c:if test="${not empty subject}">
    <p>
        「${subject.name}（${subject.cd}）」を削除してもよろしいですか？
    </p>

    <form action="SubjectDelete.action" method="post">
        <input type="hidden" name="cd" value="${subject.cd}">
        <input type="hidden" name="delete" value="true">
        <button type="submit" style="background:red;color:white;">削除</button>
    </form>
</c:if>

<c:if test="${empty subject}">
    <p>科目が存在しません</p>
</c:if>

<br>
<a href="SubjectList.action">戻る</a>