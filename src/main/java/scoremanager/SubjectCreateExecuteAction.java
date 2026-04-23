package scoremanager;

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

        if (teacher == null) {
            return "login.jsp";
        }

        String name = request.getParameter("name");
        
        Subject subject = new Subject();
        subject.setCd("S" + (System.currentTimeMillis() % 10000)); 
        subject.setName(name);
        subject.setSchool(teacher.getSchool()); 

        SubjectDAO dao = new SubjectDAO();
        dao.save(subject);

        return "SubjectList.action"; 
    }
}