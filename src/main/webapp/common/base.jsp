<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- Bootstrap --%>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
    rel="stylesheet">

<title>${param.title}</title>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
${param.scripts}
</head>

<body>
<div id="wrapper" class="container">

    <%-- ヘッダ --%>
    <header
        class="d-flex flex-wrap justify-content-between align-items-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">

        <h1 class="h4 m-0">得点管理システム</h1>

        <div>
            <c:if test="${not empty sessionScope.teacher}">
            
                <span class="me-3">${sessionScope.teacher.name} 様</span>
                
                <a href="Logout.action">ログアウト</a>
                
            </c:if>
        </div>

    </header>
    
    <%-- ヘッダここまで --%>

    <div class="row justify-content-center">

        <c:choose>

            <%-- ログイン済み --%>
            <c:when test="${teacher != null}">
                <nav class="col-3" style="height:40rem;">
                    <c:import url="/common/navigation.jsp" />
                </nav>

                <main class="col-9 border-start">
                    ${param.content}
                </main>
            </c:when>

            <%-- 未ログイン --%>
            <c:otherwise>
                <main class="col-8">
                    ${param.content}
                </main>
            </c:otherwise>

        </c:choose>

    </div>


    <%-- フッタ --%>
    <footer class="py-0 my-1 bg-dark bg-opacity-10 border-top border-3 text-center text-muted small">
    
        <p>©2023 TIC</p>
        
        <p>大原学園</p>
        
    </footer>
    
    <%--  フッタここまで  --%>

</div>
</body>
</html>
