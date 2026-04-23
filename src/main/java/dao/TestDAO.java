package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Student;

public class TestDAO extends DAO {

     //点数を保存する（存在すれば更新、なければ新規登録）
     
    public void save(Student student, String subjectCd, School school, int kaisu, int point) throws Exception {
        Connection con = getConnection();
        
        try {
            //  指定した条件（学生、科目、学校、回数
            PreparedStatement st = con.prepareStatement(
                "SELECT POINT FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?"
            );
            st.setString(1, student.getNo());
            st.setString(2, subjectCd);
            st.setString(3, school.getCd());
            st.setInt(4, kaisu);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                //  存在する場合：UPDATE（既存のレコードを更新）
                PreparedStatement updateSt = con.prepareStatement(
                    "UPDATE TEST SET POINT=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?"
                );
                updateSt.setInt(1, point);
                updateSt.setString(2, student.getNo());
                updateSt.setString(3, subjectCd);
                updateSt.setString(4, school.getCd());
                updateSt.setInt(5, kaisu);
                updateSt.executeUpdate();
                updateSt.close();
            } else {
                //  存在しない場合：INSERT（新しくデータを作る）
                PreparedStatement insertSt = con.prepareStatement(
                    "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT) VALUES (?, ?, ?, ?, ?)"
                );
                insertSt.setString(1, student.getNo());
                insertSt.setString(2, subjectCd);
                insertSt.setString(3, school.getCd());
                insertSt.setInt(4, kaisu);
                insertSt.setInt(5, point);
                insertSt.executeUpdate();
                insertSt.close();
            }
            st.close();
        } finally {
            con.close();
        }
    }
}
