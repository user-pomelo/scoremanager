package scoremanager;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String name = request.getParameter("name");
        String cd = request.getParameter("cd");

        Map<String, String> errors = new HashMap<>();

        if (teacher == null) {
            return "login.jsp";
        }

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool()); 

        SubjectDAO dao = new SubjectDAO();
        dao.save(subject);

        return "subject_create_done.jsp"; 
    }
}