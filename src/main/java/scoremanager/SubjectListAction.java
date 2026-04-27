package scoremanager;

import java.util.List;

import bean.School;
import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        School school = new School();
        school.setCd("tes");

        SubjectDAO dao = new SubjectDAO();
        List<Subject> list = dao.filter(school);

        request.setAttribute("subjects", list);
        return "subject_list.jsp";
    }
}
