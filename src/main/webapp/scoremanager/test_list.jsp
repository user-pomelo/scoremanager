<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">成績参照</c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 bg-secondary bg-opacity-10 py-2 px-4">
                成績参照
            </h2>

            <!-- 科目情報 -->
			<form action="TestListSubjectExecute.action" method="get" class="mb-4">
			    <input type="hidden" name="f" value="sj">
			
			    <div class="row mb-2 align-items-end">
			
			        <!-- 科目情報ラベル -->
			        <div class="col-auto fw-bold">
			            科目情報
			        </div>
			
			        <!-- 入学年度 -->
			        <div class="col-auto">
			            <label class="form-label">入学年度</label>
			            <select name="f1" class="form-select">
			                <option value="">--------</option>
			                <c:forEach var="y" items="${years}">
			                    <option value="${y}" ${y == f1 ? 'selected' : ''}>${y}</option>
			                </c:forEach>
			            </select>
			        </div>
			
			        <!-- クラス -->
			        <div class="col-auto">
			            <label class="form-label">クラス</label>
			            <select name="f2" class="form-select">
			                <option value="">--------</option>
			                <c:forEach var="c" items="${classList}">
			                    <option value="${c}" ${c == f2 ? 'selected' : ''}>${c}</option>
			                </c:forEach>
			            </select>
			        </div>
			
			        <!-- 科目 -->
			        <div class="col-auto">
			            <label class="form-label">科目</label>
			            <select name="f3" class="form-select">
			                <option value="">--------</option>
			                <c:forEach var="s" items="${subjects}">
			                    <option value="${s.cd}" ${s.cd == f3 ? 'selected' : ''}>${s.name}</option>
			                </c:forEach>
			            </select>
			        </div>
			
			        <!-- 検索ボタン -->
			        <div class="col-auto">
			            <button class="btn btn-secondary btn-sm">検索</button>
			        </div>
			    </div>
			
			    <!-- エラー -->
			    <c:if test="${not empty errorSubject}">
			        <div class="text-warning mb-2">${errorSubject}</div>
			    </c:if>
			</form>
			
			
			<!-- 学生情報 -->
			<form action="TestListStudentExecute.action" method="get">
			    <input type="hidden" name="f" value="st">
			
			    <div class="row mb-2 align-items-end">
			
			        <!-- 学生情報ラベル -->
			        <div class="col-auto fw-bold">
			            学生情報
			        </div>
			
			        <!-- 学生番号 -->
			        <div class="col-auto">
			            <label class="form-label">学生番号</label>
			            <input type="text" name="f4" class="form-control"
			                   maxlength="10" placeholder="学生番号を入力してください" value="${f4}" required>
			        </div>
			
			        <!-- 検索ボタン -->
			        <div class="col-auto">
			            <button class="btn btn-secondary btn-sm">検索</button>
			        </div>
			    </div>
			</form>



            <p class="mt-4 text-muted">
                科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
            </p>

        </section>
        <!-- 科目別成績一覧 -->
		<c:if test="${not empty students}">
		    <hr class="my-4">
		
		    <h3 class="h5 mb-3">科目別成績一覧</h3>
		
		    <p class="fw-bold">
		        科目：${subject.name}（${subject.cd}）
		    </p>
		
		    <table class="table table-bordered table-striped">
		        <thead>
		            <tr>
		                <th>入学年度</th>
		                <th>クラス</th>
		                <th>学生番号</th>
		                <th>氏名</th>
		                <th>1回目</th>
		                <th>2回目</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="s" items="${students}">
		                <tr>
		                    <td>${s.entYear}</td>
		                    <td>${s.classNum}</td>
		                    <td>${s.no}</td>
		                    <td>${s.name}</td>
		                    <td>${empty s.point1 ? '-' : s.point1}</td>
		                    <td>${empty s.point2 ? '-' : s.point2}</td>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		</c:if>
		        
		<!-- 学生別成績一覧 -->
		<c:if test="${not empty student}">
		    <hr class="my-4">
		
		    <h3 class="h5 mb-3">学生別成績一覧</h3>
		
		    <p class="fw-bold">
		        氏名：${student.name}（${student.no}）
		    </p>
		
		    <table class="table table-bordered table-striped">
		        <thead>
		            <tr>
		                <th>科目名</th>
		                <th>科目コード</th>
		                <th>回数</th>
		                <th>点数</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:forEach var="t" items="${tests}">
		                <tr>
		                    <td>${t.subjectName}</td>
		                    <td>${t.subjectCd}</td>
		                    <td>${t.no}</td>
		                    <td>${t.point}</td>
		                </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		</c:if>
    </c:param>
</c:import>
