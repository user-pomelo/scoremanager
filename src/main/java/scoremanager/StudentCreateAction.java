package scoremanager;

import java.util.List;

import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        StudentDAO dao = new StudentDAO();

        List<Integer> entYears = dao.getEntYears();
        List<String> classNums = dao.getClassNums();

        request.setAttribute("entYears", entYears);
        request.setAttribute("classNums", classNums);
        request.setAttribute("classNum", "101");


        return "/scoremanager/studentCreate.jsp";
    }
}