package scoremanager;

import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import dao.StudentDAO;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


        School school = new School();
        school.setCd("tes");

        StudentDAO sDao = new StudentDAO();
        SubjectDAO subDao = new SubjectDAO();

        request.setAttribute("years", sDao.getEntYearList());
        request.setAttribute("classList", sDao.getClassNumList());
        request.setAttribute("subjects", subDao.filter(school)); 
        
        List<Integer> kaisuList = List.of(1, 2);
        request.setAttribute("kaisuList", kaisuList);
        
        String f1 = request.getParameter("f1"); // 入学年度
        String f2 = request.getParameter("f2"); // クラス
        String f3 = request.getParameter("f3"); // 科目コード
        String f4 = request.getParameter("f4"); // 回数


        if (f1 != null && !f1.isEmpty() && f2 != null && !f2.isEmpty() && 
            f3 != null && !f3.isEmpty() && f4 != null && !f4.isEmpty()) {
            
            int entYear = Integer.parseInt(f1);
            int kaisu = Integer.parseInt(f4);
            
            List<Student> students = sDao.filter(entYear, f2, true);
            request.setAttribute("students", students);
            

            Subject subject = subDao.get(f3, school);
            request.setAttribute("subject", subject);
            request.setAttribute("selected_kaisu", kaisu);
            
        } else if (f1 != null || f2 != null || f3 != null || f4 != null) {

            request.setAttribute("errorMessage", "入学年度、クラス、科目、回数を選択してください");
        }

        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);
        request.setAttribute("f4", f4);

        return "/scoremanager/test_regist.jsp";
    }
}
