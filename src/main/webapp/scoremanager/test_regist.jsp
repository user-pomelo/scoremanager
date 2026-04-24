<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績管理</c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>
            
            <!-- 検索フォーム -->
            <form action="TestRegist.action" method="get" class="mb-4">
                <div class="row mb-2">
                    <div class="col-auto">
                        <label>入学年度</label>
                        <select name="f1" class="form-select">
                            <option value="">--------</option>
                            <c:forEach var="y" items="${years}">
                                <option value="${y}" ${y == f1 ? 'selected' : ''}>${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-auto">
                        <label>クラス</label>
                        <select name="f2" class="form-select">
                            <option value="">--------</option>
                            <c:forEach var="c" items="${classList}">
                                <option value="${c}" ${c == f2 ? 'selected' : ''}>${c}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-auto">
                         <label>科目</label>
                         <select name="f3" class="form-select">
                             <option value="">--------</option>
                             <c:forEach var="s" items="${subjects}">
                                 <option value="${s.cd}" ${s.cd == f3 ? 'selected' : ''}>${s.name}</option>
                             </c:forEach>
                         </select>
                    </div>
                    
                    <div class="col-auto">
                         <label>回数</label>
                         <select name="f4" class="form-select">
                             <option value="">--------</option>
                             <c:forEach var="k" items="${kaisuList}">
                                 <option value="${k}" ${k == f4 ? 'selected' : ''}>${k}</option>
                             </c:forEach>
                         </select>
                    </div>

                    <div class="col-auto d-flex align-items-end">
                        <button type="submit" class="btn btn-sm btn-secondary">検索</button>
                    </div>
                </div>
            </form>

            <c:if test="${not empty errorMessage}">
                <div class="text-danger mb-3">${errorMessage}</div>
            </c:if>

            <c:if test="${not empty students}">
                <div class="mb-2">
                    科目：${subject.name} （${f4}回目）
                </div>

                <!-- 登録用フォーム -->
                <form action="TestRegistExecute.action" method="post">
                    
                    <input type="hidden" name="f1" value="${f1}">
                    <input type="hidden" name="f2" value="${f2}">
                    <input type="hidden" name="f3" value="${f3}">
                    <input type="hidden" name="f4" value="${f4}">

                    <table class="table table-hover">
                        <thead>
                            <tr class="border-bottom">
                                <th>入学年度</th> 
                                <th>クラス</th>  
                                <th>学生番号</th> 
                                <th>氏名</th>     
                                <th>点数</th>     
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
                                        <div style="width: 200px;">
                                            
                                            <input type="number" name="point_${s.no}" value="${s.point}" 
                                                   class="form-control" min="0" max="100">
                                            <div class="text-warning" style="font-size: 0.75rem; margin-top: 2px;">
                                                0～100の範囲で入力してください
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="mt-4">
                        <button type="submit" class="btn btn-secondary py-2 px-3">
                            登録して終了
                        </button>
                    </div>
                </form>
            </c:if>
        </section>
    </c:param>
</c:import>

