package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDAO extends DAO {

    // 成績保存
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
                PreparedStatement updateSt = con.prepareStatement(
                    "UPDATE TEST SET POINT=?, CLASS_NUM=? WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?"
                );
                updateSt.setInt(1, point);
                updateSt.setString(2, classNum);
                updateSt.setString(3, student.getNo());
                updateSt.setString(4, subjectCd);
                updateSt.setString(5, school.getCd());
                updateSt.setInt(6, kaisu);
                updateSt.executeUpdate();
                updateSt.close();
            } else {
                PreparedStatement insertSt = con.prepareStatement(
                    "INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)"
                );
                insertSt.setString(1, student.getNo());
                insertSt.setString(2, subjectCd);
                insertSt.setString(3, school.getCd());
                insertSt.setInt(4, kaisu);
                insertSt.setInt(5, point);
                insertSt.setString(6, classNum);
                insertSt.executeUpdate();
                insertSt.close();
            }
            st.close();
        } finally {
            con.close();
        }
    }

    // ① 学生 × 科目 × 回数 の点数を取得
    public Integer getPoint(String studentNo, String subjectCd, School school, int kaisu) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT POINT FROM TEST WHERE STUDENT_NO=? AND SUBJECT_CD=? AND SCHOOL_CD=? AND NO=?"
        );
        st.setString(1, studentNo);
        st.setString(2, subjectCd);
        st.setString(3, school.getCd());
        st.setInt(4, kaisu);

        ResultSet rs = st.executeQuery();

        Integer point = null;
        if (rs.next()) {
            point = rs.getInt("POINT");
        }

        rs.close();
        st.close();
        con.close();

        return point;
    }

    // 科目 × クラス × 回数 の成績一覧
    public List<Test> getListByClass(School school, String subjectCd, String classNum, int kaisu) throws Exception {
        Connection con = getConnection();
        List<Test> list = new ArrayList<>();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM TEST WHERE SCHOOL_CD=? AND SUBJECT_CD=? AND CLASS_NUM=? AND NO=? ORDER BY STUDENT_NO"
        );
        st.setString(1, school.getCd());
        st.setString(2, subjectCd);
        st.setString(3, classNum);
        st.setInt(4, kaisu);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test t = new Test();
            t.setStudentNo(rs.getString("STUDENT_NO"));
            t.setSubjectCd(subjectCd);
            t.setNo(kaisu);
            t.setPoint(rs.getInt("POINT"));
            list.add(t);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
    
    public List<Test> getListByStudent(School school, String studentNo) throws Exception {
        Connection con = getConnection();
        List<Test> list = new ArrayList<>();

        PreparedStatement st = con.prepareStatement(
            "SELECT t.*, s.NAME AS SUBJECT_NAME " +
            "FROM TEST t " +
            "JOIN SUBJECT s ON t.SUBJECT_CD = s.CD AND t.SCHOOL_CD = s.SCHOOL_CD " +
            "WHERE t.SCHOOL_CD=? AND t.STUDENT_NO=? " +
            "ORDER BY t.SUBJECT_CD, t.NO"
        );
        st.setString(1, school.getCd());
        st.setString(2, studentNo);

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Test t = new Test();
            t.setStudentNo(studentNo);
            t.setSubjectCd(rs.getString("SUBJECT_CD"));
            t.setSubjectName(rs.getString("SUBJECT_NAME")); // ★ 科目名セット
            t.setNo(rs.getInt("NO"));
            t.setPoint(rs.getInt("POINT"));
            list.add(t);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

}
