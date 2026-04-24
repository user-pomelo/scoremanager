package scoremanager;

import java.io.IOException;

import bean.Subject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SubjectUpdateAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String cd = req.getParameter("cd");

            SubjectDao dao = new SubjectDao();
            Subject subject = dao.get(cd);

            if (subject == null) {
                req.setAttribute("error", "科目が存在していません");
            } else {
                req.setAttribute("subject", subject);
            }

            req.getRequestDispatcher("/main/subjectUpdate.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");

            String cd = req.getParameter("cd");
            String name = req.getParameter("name");

            SubjectDao dao = new SubjectDao();
            Subject subject = dao.get(cd);

            // 存在チェック
            if (subject == null) {
                req.setAttribute("error", "科目が存在していません");
                req.getRequestDispatcher("/main/subjectUpdate.jsp").forward(req, resp);
                return;
            }

            // 未入力チェック
            if (name == null || name.isEmpty()) {
                req.setAttribute("subject", subject);
                req.getRequestDispatcher("/main/subjectUpdate.jsp").forward(req, resp);
                return;
            }

            subject.setName(name);
            dao.update(subject);

            resp.sendRedirect("SubjectList.action");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}