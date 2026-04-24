package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Student;

public class TestDAO extends DAO {

    // 引数の最後に String classNum を追加
    public void save(Student student, String subjectCd, School school, int kaisu, int point, String classNum) throws Exception {
        Connection con = getConnection();
        
        try {
            PreparedStatement st = con.prepareStatement(
                "SELECT POINT FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?"
            );
            st.setString(1, student.getNo());
            st.setString(2, subjectCd);
            st.setString(3, school.getCd());
            st.setInt(4, kaisu);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // UPDATE時にもクラス番号を更新する場合（念のため）
                PreparedStatement updateSt = con.prepareStatement(
                    "UPDATE TEST SET POINT=?, CLASS_NUM=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?"
                );
                updateSt.setInt(1, point);
                updateSt.setString(2, classNum); // 追加
                updateSt.setString(3, student.getNo());
                updateSt.setString(4, subjectCd);
                updateSt.setString(5, school.getCd());
                updateSt.setInt(6, kaisu);
                updateSt.executeUpdate();
                updateSt.close();
            } else {
                // INSERT時に CLASS_NUM を追加
                PreparedStatement insertSt = con.prepareStatement(
                    "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)"
                );
                insertSt.setString(1, student.getNo());
                insertSt.setString(2, subjectCd);
                insertSt.setString(3, school.getCd());
                insertSt.setInt(4, kaisu);
                insertSt.setInt(5, point);
                insertSt.setString(6, classNum); // 追加！
                insertSt.executeUpdate();
                insertSt.close();
            }
            st.close();
        } finally {
            con.close();
        }
    }
}