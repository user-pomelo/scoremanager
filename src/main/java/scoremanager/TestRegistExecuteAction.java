package scoremanager;

import java.util.List;

import bean.School;
import bean.Student;
import dao.StudentDAO;
import dao.TestDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        School school = new School();
        school.setCd("tes");

        String subjectCd = request.getParameter("f3");
        String kaisuStr = request.getParameter("f4");
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");

        int kaisu = Integer.parseInt(kaisuStr);
        int entYear = Integer.parseInt(entYearStr);

        StudentDAO sDao = new StudentDAO();
        TestDAO tDao = new TestDAO();

        List<Student> students = sDao.filter(entYear, classNum, true);

        // ★ エラーフラグ
        boolean hasError = false;

        // 入力チェック
        for (Student student : students) {

            String pointStr = request.getParameter("point_" + student.getNo());

            if (pointStr != null && !pointStr.isEmpty()) {

                try {
                    int point = Integer.parseInt(pointStr);

                    // ★ 0～100 の範囲チェック
                    if (point < 0 || point > 100) {
                        hasError = true;
                    }

                } catch (NumberFormatException e) {
                    hasError = true;
                }
            }
        }

        // エラーがあれば元の画面へ戻す
        if (hasError) {

            request.setAttribute("errorMessage", "0～100の範囲で入力してください。");

            // 再表示用データ
            request.setAttribute("students", students);
            request.setAttribute("f1", entYearStr);
            request.setAttribute("f2", classNum);
            request.setAttribute("f3", subjectCd);
            request.setAttribute("f4", kaisuStr);

            return "/scoremanager/test_regist.jsp";
        }

        //  エラーなし → 保存処理
        for (Student student : students) {

            String pointStr = request.getParameter("point_" + student.getNo());

            if (pointStr != null && !pointStr.isEmpty()) {
                int point = Integer.parseInt(pointStr);
                tDao.save(student, subjectCd, school, kaisu, point, classNum);
            }
        }

        return "/scoremanager/test_regist_done.jsp";
    }
}
