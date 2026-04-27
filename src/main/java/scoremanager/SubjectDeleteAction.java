package scoremanager;

import bean.Subject;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        String delete = request.getParameter("delete");

        SubjectDAO dao = new SubjectDAO();

        // 初回表示（確認画面）
        if (delete == null) {
            Subject subject = dao.get(cd);
            request.setAttribute("subject", subject);
            return "subjectDelete.jsp";
        }

        // ★ 削除前に科目情報を取得しておく（完了画面で表示するため）
        Subject subject = dao.get(cd);

        // 削除処理
        dao.delete(cd);

        // ★ 完了画面へ遷移
        request.setAttribute("subject", subject);
        return "/scoremanager/subjectDeleteDone.jsp";
    }
}
