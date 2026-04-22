package scoremanager;

import bean.Student;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentEditAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String no = request.getParameter("no");

        StudentDAO dao = new StudentDAO();
        Student student = dao.get(no);

        request.setAttribute("student", student);
        request.setAttribute("classNums", dao.getClassNums());

        return "/scoremanager/studentUpdate.jsp";
    }
}