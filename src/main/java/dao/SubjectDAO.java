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

    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM SUBJECT WHERE SCHOOL_CD = ? ORDER BY CD ASC");
            statement.setString(1, school.getCd());
            ResultSet rSet = statement.executeQuery();

            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("CD"));
                subject.setName(rSet.getString("NAME"));
                subject.setSchool(school);
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
        public Subject get(String cd, School school) throws Exception {
            Subject subject = null;
            Connection connection = getConnection();
            PreparedStatement statement = null;

            try {
                // 特定の学校の、特定の科目コードに一致するデータを取得
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
}