package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {

    public List<Subject> search() throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM SUBJECT ORDER BY CD ASC");
            ResultSet rSet = statement.executeQuery();
            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("CD"));
                subject.setName(rSet.getString("NAME"));
                list.add(subject);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return list;
    }

    public void save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)");
            statement.setString(1, subject.getSchool().getCd());
            statement.setString(2, subject.getCd());
            statement.setString(3, subject.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
    }
    // 修正後（saveメソッドの次から最後まで）
    public Subject get(String cd, School school) throws Exception {
        Subject subject = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE CD = ? AND SCHOOL_CD = ?");
            statement.setString(1, cd);
            statement.setString(2, school.getCd());
            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                subject = new Subject();
                subject.setCd(rSet.getString("CD"));
                subject.setName(rSet.getString("NAME"));
                subject.setSchool(school);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) { statement.close(); }
            if (connection != null) { connection.close(); }
        }
        return subject;
    }

    public void update(Subject subject) throws Exception {
        String sql = "UPDATE subject SET name = ? WHERE cd = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getCd());
            ps.executeUpdate();
        }
    }

    public void delete(String cd) throws Exception {
        String sql = "DELETE FROM subject WHERE cd = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cd);
            ps.executeUpdate();
        }
    }
}
