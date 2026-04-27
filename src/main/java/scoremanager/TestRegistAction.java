package scoremanager;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        School school = new School();
        school.setCd("tes");

        StudentDAO sDao = new StudentDAO();
        SubjectDAO subDao = new SubjectDAO();
        TestDAO tDao = new TestDAO();

        // プルダウン用データ
        request.setAttribute("years", sDao.getEntYearList());
        request.setAttribute("classList", sDao.getClassNumList());
        request.setAttribute("subjects", subDao.filter(school));
        request.setAttribute("kaisuList", List.of(1, 2));

        // パラメータ取得
        String f1 = request.getParameter("f1"); // 入学年度
        String f2 = request.getParameter("f2"); // クラス
        String f3 = request.getParameter("f3"); // 科目コード
        String f4 = request.getParameter("f4"); // 回数

        // 入力値保持
        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);
        request.setAttribute("f4", f4);

        // 全部選択されている場合のみ検索
        if (f1 != null && !f1.isEmpty() &&
            f2 != null && !f2.isEmpty() &&
            f3 != null && !f3.isEmpty() &&
            f4 != null && !f4.isEmpty()) {

            int entYear = Integer.parseInt(f1);
            int kaisu = Integer.parseInt(f4);

            // 学生一覧取得
            List<Student> students = sDao.filter(entYear, f2, true);

            //点数をセットする
            for (Student s : students) {
                // 1回目の点数
                Integer p1 = tDao.getPoint(s.getNo(), f3, school, 1);
                s.setPoint1(p1);

                // 2回目の点数
                Integer p2 = tDao.getPoint(s.getNo(), f3, school, 2);
                s.setPoint2(p2);
            }

            request.setAttribute("students", students);

            // 科目情報
            Subject subject = subDao.get(f3, school);
            request.setAttribute("subject", subject);
            request.setAttribute("selected_kaisu", kaisu);

        } else if (f1 != null || f2 != null || f3 != null || f4 != null) {
            // どれかだけ選ばれている → エラー
            request.setAttribute("errorMessage", "入学年度、クラス、科目、回数を選択してください");
        }

        return "/scoremanager/test_regist.jsp";
    }
}
