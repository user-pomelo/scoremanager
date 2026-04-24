<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>科目情報変更</h2>

<form action="SubjectUpdate.action" method="post">

    <div>
        <label>科目コード</label><br>
        <c:out value="${subject.cd}" />
        <input type="hidden" name="cd" value="${subject.cd}">
    </div>

    <c:if test="${not empty error}">
        <div style="color:orange;">
            ${error}
        </div>
    </c:if>

    <div>
        <label>科目名</label><br>
        <input type="text" name="name"
               value="${subject.name}">
    </div>

    <br>

    <button type="submit">変更</button>

</form>

<br>
<a href="SubjectList.action">戻る</a>