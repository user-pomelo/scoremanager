package scoremanager;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        School school = new School();
        school.setCd("tes");

        StudentDAO sDao = new StudentDAO();
        ClassNumDAO cDao = new ClassNumDAO();
        SubjectDAO subDao = new SubjectDAO();
        TestDAO tDao = new TestDAO();

        // 入力値取得
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        // 入力値保持
        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);

        // プルダウン再セット
        request.setAttribute("years", sDao.getEntYearList());
        request.setAttribute("classList", cDao.filter(school));
        request.setAttribute("subjects", subDao.filter(school));

        // 未選択チェック
        if (f1 == null || f1.isEmpty() ||
            f2 == null || f2.isEmpty() ||
            f3 == null || f3.isEmpty()) {

            request.setAttribute("errorSubject", "入学年度とクラスと科目を選択してください");
            return "/scoremanager/test_list.jsp";
        }

        // 検索処理
        int entYear = Integer.parseInt(f1);
        List<Student> students = sDao.filter(entYear, f2, true);

        //点数をセットする（1回目・2回目）
        for (Student s : students) {
            Integer p1 = tDao.getPoint(s.getNo(), f3, school, 1);
            s.setPoint1(p1);

            Integer p2 = tDao.getPoint(s.getNo(), f3, school, 2);
            s.setPoint2(p2);
        }

        Subject subject = subDao.get(f3, school);

        request.setAttribute("students", students);
        request.setAttribute("subject", subject);

        return "/scoremanager/test_list.jsp";
    }
}
