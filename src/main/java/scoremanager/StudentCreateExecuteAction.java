package scoremanager;

import bean.Student;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");

        if (classNum == null || classNum.isEmpty()) {
            classNum = "101";
        }

        Integer entYear = null;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        boolean error = false;

        StudentDAO dao = new StudentDAO();

        // エラーメッセージ
        String noError = null;
        String nameError = null;
        String entYearError = null;

        // 必須チェック
        if (no == null || no.isEmpty()) {
            noError = "このフィールドを入力してください";
            error = true;
        }

        if (name == null || name.isEmpty()) {
            nameError = "このフィールドを入力してください";
            error = true;
        }

        if (entYear == null) {
            entYearError = "入学年度を選択してください";
            error = true;
        }

        // 重複チェック
        if (!error && dao.exists(no)) {
            noError = "学生番号が重複しています";
            error = true;
        }

        // エラー時は戻す
        if (error) {
            request.setAttribute("noError", noError);
            request.setAttribute("nameError", nameError);
            request.setAttribute("entYearError", entYearError);

            request.setAttribute("entYears", dao.getEntYears());
            request.setAttribute("classNums", dao.getClassNums());

            return "/scoremanager/studentCreate.jsp";
        }

        // 登録処理
        Student s = new Student();
        s.setNo(no);
        s.setName(name);
        s.setEntYear(entYear);
        s.setClassNum(classNum == null || classNum.isEmpty() ? "101" : classNum);
        s.setIsAttend(true);
        s.setSchoolCd("oom");

        boolean result = dao.save(s);

        if (result) {
            return "/scoremanager/studentCreateDone.jsp";
        }

        request.setAttribute("error", "登録に失敗しました");
        return "/scoremanager/studentCreate.jsp";
    }
}

