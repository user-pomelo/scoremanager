package scoremanager;

import java.util.ArrayList;
import java.util.List;

import bean.Student;
import dao.StudentDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        StudentDAO dao = new StudentDAO();

        // 現在の年を取得
        int currentYear = java.time.Year.now().getValue();

        // 前10年〜後10年のリストを作成
        List<Integer> years = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            years.add(y);
        }

        // クラス一覧は DAO から取得
        request.setAttribute("years", years);
        request.setAttribute("classList", dao.getClassNumList());

        // パラメータ取得
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        // バリデーション
        if ((f2 != null && !f2.isEmpty()) && (f1 == null || f1.isEmpty())) {
            request.setAttribute("errorMessage", "クラスを指定する場合は入学年度も指定してください");

            request.setAttribute("f1", f1);
            request.setAttribute("f2", f2);
            request.setAttribute("f3", f3);

            return "/scoremanager/list.jsp";
        }

        // 絞り込み条件
        Integer entYear = (f1 == null || f1.isEmpty()) ? null : Integer.parseInt(f1);
        String classNum = (f2 == null || f2.isEmpty()) ? null : f2;
        Boolean isAttend = (f3 == null) ? null : true;

        List<Student> list;

        if (entYear == null && classNum == null && isAttend == null) {
            list = dao.getAll();
        } else {
            list = dao.filter(entYear, classNum, isAttend);
        }

        request.setAttribute("students", list);

        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);

        return "/scoremanager/list.jsp";
    }
}
