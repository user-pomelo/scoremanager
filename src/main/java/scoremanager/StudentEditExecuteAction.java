package scoremanager;

import bean.Student;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentEditExecuteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setCharacterEncoding("UTF-8");

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String nameError = null;
        boolean error = false;
        if (name == null || name.isEmpty()) {
            nameError = "このフィールドを入力してください";
            error = true;
        }
        if (error) {
            request.setAttribute("nameError", nameError);
            StudentDAO dao = new StudentDAO();
            Student student = dao.get(no);
            request.setAttribute("student", student);
            request.setAttribute("classNums", dao.getClassNums());

            return "/scoremanager/studentUpdate.jsp";
        }
        String classNum = request.getParameter("classNum");
        String isAttendStr = request.getParameter("isAttend");

        boolean isAttend = (isAttendStr != null);

        if (name == null || name.isEmpty()) {
            request.setAttribute("error", "氏名を入力してください");
            return "/scoremanager/studentUpdate.jsp";
        }

        Student s = new Student();
        s.setNo(no);
        s.setName(name);
        s.setClassNum(classNum);
        s.setIsAttend(isAttend);

        StudentDAO dao = new StudentDAO();
        boolean result = dao.update(s);

        if (result) {
            return "/scoremanager/studentUpdateDone.jsp";
        }

        request.setAttribute("error", "更新に失敗しました");
        return "/scoremanager/studentUpdate.jsp";
    }
}