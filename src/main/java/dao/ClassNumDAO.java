package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDAO extends DAO {

    // 1件取得
    public ClassNum get(String classNum, School school) throws Exception {
        Connection con = getConnection();
        ClassNum cn = null;

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM CLASS_NUM WHERE SCHOOL_CD = ? AND CLASS_NUM = ?"
        );
        st.setString(1, school.getCd());
        st.setString(2, classNum);

        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            cn = new ClassNum();
            cn.setSchool(school);
            cn.setClassNum(rs.getString("CLASS_NUM"));
        }

        rs.close();
        st.close();
        con.close();

        return cn;
    }

    // クラス一覧取得
    public List<String> filter(School school) throws Exception {
        Connection con = getConnection();
        List<String> list = new ArrayList<>();

        PreparedStatement st = con.prepareStatement(
            "SELECT CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD = ? ORDER BY CLASS_NUM"
        );
        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("CLASS_NUM"));
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // 新規登録
    public boolean save(ClassNum classNum) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO CLASS_NUM (SCHOOL_CD, CLASS_NUM) VALUES (?, ?)"
        );
        st.setString(1, classNum.getSchool().getCd());
        st.setString(2, classNum.getClassNum());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }

    // クラス名変更
    public boolean save(ClassNum classNum, String newClassNum) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "UPDATE CLASS_NUM SET CLASS_NUM = ? WHERE SCHOOL_CD = ? AND CLASS_NUM = ?"
        );
        st.setString(1, newClassNum);
        st.setString(2, classNum.getSchool().getCd());
        st.setString(3, classNum.getClassNum());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }
}
