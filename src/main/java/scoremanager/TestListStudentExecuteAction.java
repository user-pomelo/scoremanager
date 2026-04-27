package scoremanager;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        School school = new School();
        school.setCd("tes");

        StudentDAO sDao = new StudentDAO();
        ClassNumDAO cDao = new ClassNumDAO();
        SubjectDAO subDao = new SubjectDAO();
        TestDAO tDao = new TestDAO();

        // 入力値取得
        String f4 = request.getParameter("f4");

        // 入力値保持
        request.setAttribute("f4", f4);

        // プルダウン再セット
        request.setAttribute("years", sDao.getEntYearList());
        request.setAttribute("classList", cDao.filter(school));
        request.setAttribute("subjects", subDao.filter(school));

        // 未入力チェック
        if (f4 == null || f4.isEmpty()) {
            request.setAttribute("errorStudent", "このフィールドを入力してください");
            return "/scoremanager/test_list.jsp";
        }

        // 学生情報取得
        Student student = sDao.get(f4);

        // 学生の全成績を取得
        List<Test> tests = tDao.getListByStudent(school, f4);

        request.setAttribute("student", student);
        request.setAttribute("tests", tests);

        return "/scoremanager/test_list.jsp";
    }
}
