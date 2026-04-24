package scoremanager;

import bean.School;
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

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        if (teacher != null) {
            subject.setSchool(teacher.getSchool());
        } else {
            School school = new School();
            school.setCd("kum"); 
            subject.setSchool(school);
        }

        SubjectDAO dao = new SubjectDAO();
        dao.save(subject);

        return "SubjectList.action"; 
    }
}