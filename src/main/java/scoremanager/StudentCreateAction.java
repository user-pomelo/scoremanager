package scoremanager;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentCreateAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 現在の年を取得
        int currentYear = java.time.Year.now().getValue();

        // 前10年〜後10年のリストを作成
        List<Integer> entYears = new ArrayList<>();
        for (int y = currentYear - 10; y <= currentYear + 10; y++) {
            entYears.add(y);
        }

        // クラス番号は DAO から取得
        dao.StudentDAO dao = new dao.StudentDAO();
        List<String> classNums = dao.getClassNums();

        request.setAttribute("entYears", entYears);
        request.setAttribute("classNums", classNums);
        request.setAttribute("classNum", "101");

        return "/scoremanager/studentCreate.jsp";
    }
}
