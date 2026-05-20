package scoremanager;

import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

	    @Override
	    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

	        request.setCharacterEncoding("UTF-8");

	        String cd = request.getParameter("cd");
	        String name = request.getParameter("name");

	        SubjectDAO dao = new SubjectDAO();
	        Subject subject = dao.get(cd);

	        if (name == null) {
	            request.setAttribute("subject", subject);
	            return "subjectUpdate.jsp";
	        }

	        if (subject == null) {
	            request.setAttribute("error", "科目が存在していません");
	            return "subjectUpdate.jsp";
	        }

	        if (name.isEmpty()) {
	            request.setAttribute("subject", subject);
	            request.setAttribute("error", "科目名を入力してください");
	            return "subjectUpdate.jsp";
	        }

	        subject.setName(name);
	        dao.update(subject);

	        request.setAttribute("subject", subject);  
	        return "/scoremanager/subjectUpdateDone.jsp";
	    }

}