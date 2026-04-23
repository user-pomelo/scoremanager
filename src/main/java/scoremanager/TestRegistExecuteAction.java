package scoremanager;

import java.util.List;

import bean.Student;
import bean.Teacher;
import dao.StudentDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 1. JSPのhidden項目やセレクトボックスから、科目・回数・検索条件を取得
        String subjectCd = request.getParameter("f3"); // 科目コード
        String kaisuStr = request.getParameter("f4");    // 回数
        String entYearStr = request.getParameter("f1"); // 再表示用：入学年度
        String classNum = request.getParameter("f2");   // 再表示用：クラス

        // 数値への変換
        int kaisu = Integer.parseInt(kaisuStr);
        int entYear = Integer.parseInt(entYearStr);

        StudentDAO sDao = new StudentDAO();
        TestDAO tDao = new TestDAO();

        List<Student> students = sDao.filter(entYear, classNum, true);

        for (Student student : students) {

            String pointStr = request.getParameter("point_" + student.getNo());
            
            if (pointStr != null && !pointStr.isEmpty()) {
                int point = Integer.parseInt(pointStr);

                tDao.save(student, subjectCd, teacher.getSchool(), kaisu, point);
            }
        }

        return "/scoremanager/test_regist_done.jsp";
    }
}
