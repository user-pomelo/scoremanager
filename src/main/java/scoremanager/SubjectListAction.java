package scoremanager;

import java.util.List;

import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubjectDAO dao = new SubjectDAO();
        List<Subject> list = dao.search();

        request.setAttribute("subjects", list);

        return "subject_list.jsp";
    }
}