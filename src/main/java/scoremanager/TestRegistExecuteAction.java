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

        String subjectCd = request.getParameter("f3"); // 科目コード
        String kaisuStr = request.getParameter("f4");    // 回数
        String entYearStr = request.getParameter("f1"); // 入学年度
        String classNum = request.getParameter("f2");   // クラス

        int kaisu = Integer.parseInt(kaisuStr);
        int entYear = Integer.parseInt(entYearStr);

        StudentDAO sDao = new StudentDAO();
        TestDAO tDao = new TestDAO();

        List<Student> students = sDao.filter(entYear, classNum, true);

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

