<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">学生管理</c:param>

    <c:param name="content">

        <section class="me-4">

            <!-- タイトル -->
            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                学生管理
            </h2>
            
			<!-- 新規登録 -->
			<div class="mb-2 d-flex">
    			<a href="StudentCreate.action" class="ms-auto">
        			新規登録
    			</a>
			</div>
            
            <!-- 絞り込み -->
            <form action="StudentList.action" method="get" class="mb-4">

                <div class="row mb-2">

                    <!-- 入学年度 -->
                    <div class="col-auto">
                        <label>入学年度</label>
                        <select name="f1" class="form-select">
                            <option value="">--------</option>
                            <c:forEach var="y" items="${years}">
                                <option value="${y}" ${y == f1 ? 'selected' : ''}>${y}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- クラス -->
                    <div class="col-auto">
                        <label>クラス</label>
                        <select name="f2" class="form-select">
                            <option value="">--------</option>
                            <c:forEach var="c" items="${classList}">
                                <option value="${c}" ${c == f2 ? 'selected' : ''}>${c}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 在学中 -->
                    <div class="col-auto d-flex align-items-end">
                        <input type="checkbox" name="f3" value="t" class="form-check-input me-1"<c:if test="${not empty f3}">checked</c:if>>
                        <label>在学中</label>
                    </div>

                    <!-- ボタン -->
                    <div class="col-auto d-flex align-items-end">
                        <button type="submit"  class="btn btn-sm btn-secondary">
                            絞り込み
                        </button>
                    </div>
                </div>
            </form>
            
            <c:if test="${not empty errorMessage}">
			    <div class="alert alert-danger">
			        ${errorMessage}
			    </div>
			</c:if>

            <!-- 件数表示 -->
            <div class="mb-2">
                検索結果：${students.size()} 件
            </div>

            <!-- 一覧 -->
            <c:choose>
                <c:when test="${not empty students}">
                    <table class="table table-bordered table-striped">

                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>クラス</th>
                                <th>在学中</th>
                                <th></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="s" items="${students}">
                                <tr>
                                    <td>${s.entYear}</td>
                                    <td>${s.no}</td>
                                    <td>${s.name}</td>
                                    <td>${s.classNum}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${s.isAttend}">○</c:when>
                                            <c:otherwise>×</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="StudentEdit.action?no=${s.no}">
                                            変更
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </c:when>

                <c:otherwise>
                    <div class="alert alert-warning">
                        該当する学生情報がありません。
                    </div>
                </c:otherwise>

            </c:choose>

        </section>

    </c:param>
</c:import>