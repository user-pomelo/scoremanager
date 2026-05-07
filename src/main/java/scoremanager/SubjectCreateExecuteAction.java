package scoremanager;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        SubjectDAO dao = new SubjectDAO();

        boolean error = false;
        String cdError = null;

        // 重複チェック
        if (dao.exists(cd)) {
            cdError = "科目コードが重複しています";
            error = true;
        }

        if (error) {
            // 入力値を戻す
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.setAttribute("cdError", cdError);

            // エラーメッセージを渡す
            request.setAttribute("cdError", cdError);

            return "/scoremanager/subject_create.jsp";
        }

        // 正常登録処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        School school = new School();
        school.setCd("tes");
        subject.setSchool(school);

        dao.save(subject);

        return "SubjectList.action";
    }
}
