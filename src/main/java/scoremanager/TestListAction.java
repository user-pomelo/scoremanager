package scoremanager;

import java.util.List;

import bean.School;
import dao.ClassNumDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 学校コード（仮）
        School school = new School();
        school.setCd("tes");

        // DAO
        StudentDAO sDao = new StudentDAO();
        ClassNumDAO cDao = new ClassNumDAO();
        SubjectDAO subDao = new SubjectDAO();

        // 入学年度一覧
        List<Integer> years = sDao.getEntYearList();
        request.setAttribute("years", years);

        // クラス一覧
        List<String> classList = cDao.filter(school);
        request.setAttribute("classList", classList);

        // 科目一覧
        request.setAttribute("subjects", subDao.filter(school));

        // 科目検索用の識別コード
        request.setAttribute("f", "sj");

        // 初期値（空）
        request.setAttribute("f1", "");
        request.setAttribute("f2", "");
        request.setAttribute("f3", "");
        request.setAttribute("f4", "");

        return "/scoremanager/test_list.jsp";
    }
}
