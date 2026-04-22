package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {

    public List<Student> getAll() throws Exception {

        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM STUDENT ORDER BY NO"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("NO"));
            s.setName(rs.getString("NAME"));
            s.setEntYear(rs.getInt("ENT_YEAR"));
            s.setClassNum(rs.getString("CLASS_NUM"));
            s.setIsAttend(rs.getBoolean("IS_ATTEND"));
            s.setSchoolCd(rs.getString("SCHOOL_CD"));
            list.add(s);
        }

        st.close();
        con.close();

        return list;
    }

    public List<Student> filter(Integer entYear, String classNum, Boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM STUDENT WHERE 1=1 ");

        if (entYear != null) {
            sql.append("AND ENT_YEAR = ? ");
        }

        if (classNum != null && !classNum.isEmpty()) {
            sql.append("AND CLASS_NUM = ? ");
        }

        if (isAttend != null) {
            sql.append("AND IS_ATTEND = ? ");
        }

        sql.append("ORDER BY NO");

        PreparedStatement st = con.prepareStatement(sql.toString());

        int index = 1;

        if (entYear != null) {
            st.setInt(index++, entYear);
        }

        if (classNum != null && !classNum.isEmpty()) {
            st.setString(index++, classNum);
        }

        if (isAttend != null) {
            st.setBoolean(index++, isAttend);
        }

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student s = new Student();
            s.setNo(rs.getString("NO"));
            s.setName(rs.getString("NAME"));
            s.setEntYear(rs.getInt("ENT_YEAR"));
            s.setClassNum(rs.getString("CLASS_NUM"));
            s.setIsAttend(rs.getBoolean("IS_ATTEND"));
            s.setSchoolCd(rs.getString("SCHOOL_CD"));
            list.add(s);
        }

        st.close();
        con.close();

        return list;
    }

    public List<Integer> getEntYearList() throws Exception {
        List<Integer> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT ENT_YEAR FROM STUDENT ORDER BY ENT_YEAR"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getInt("ENT_YEAR"));
        }

        st.close();
        con.close();

        return list;
    }

    public List<String> getClassNumList() throws Exception {
        List<String> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT CLASS_NUM FROM STUDENT ORDER BY CLASS_NUM"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("CLASS_NUM"));
        }

        st.close();
        con.close();

        return list;
    }
    
    public boolean exists(String no) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT COUNT(*) FROM STUDENT WHERE NO = ?"
        );

        st.setString(1, no);

        ResultSet rs = st.executeQuery();
        rs.next();

        boolean result = rs.getInt(1) > 0;

        st.close();
        con.close();

        return result;
    }
    
    public boolean save(Student s) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) " +
            "VALUES (?, ?, ?, ?, ?, ?)"
        );

        st.setString(1, s.getNo());
        st.setString(2, s.getName());
        st.setInt(3, s.getEntYear());
        st.setString(4, s.getClassNum());
        st.setBoolean(5, s.getIsAttend());
        st.setString(6, s.getSchoolCd());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }
    
    public List<Integer> getEntYears() throws Exception {

        List<Integer> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT ENT_YEAR FROM STUDENT ORDER BY ENT_YEAR"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getInt("ENT_YEAR"));
        }

        st.close();
        con.close();

        return list;
    }
    
    public List<String> getClassNums() throws Exception {

        List<String> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT CLASS_NUM FROM STUDENT ORDER BY CLASS_NUM"
        );

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("CLASS_NUM"));
        }

        st.close();
        con.close();

        return list;
    }
    
    public boolean update(Student s) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "UPDATE STUDENT " +
            "SET NAME = ?, CLASS_NUM = ?, IS_ATTEND = ? " +
            "WHERE NO = ?"
        );

        st.setString(1, s.getName());
        st.setString(2, s.getClassNum());
        st.setBoolean(3, s.getIsAttend());
        st.setString(4, s.getNo());

        int line = st.executeUpdate();

        st.close();
        con.close();

        return line > 0;
    }
    
    public Student get(String no) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM STUDENT WHERE NO = ?"
        );

        st.setString(1, no);

        ResultSet rs = st.executeQuery();

        Student s = null;

        if (rs.next()) {
            s = new Student();
            s.setNo(rs.getString("NO"));
            s.setName(rs.getString("NAME"));
            s.setEntYear(rs.getInt("ENT_YEAR"));
            s.setClassNum(rs.getString("CLASS_NUM"));
            s.setIsAttend(rs.getBoolean("IS_ATTEND"));
            s.setSchoolCd(rs.getString("SCHOOL_CD"));
        }

        st.close();
        con.close();

        return s;
    }
    
    private String baseSql;

    private List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
        return null;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend)
            throws Exception {
        return null;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend)
            throws Exception {
        return null;
    }
}