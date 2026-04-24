package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDAO extends DAO {
	public Teacher search(String id, String password) throws Exception {

	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement(
	        "SELECT * FROM TEACHER WHERE ID = ? AND PASSWORD = ?"
	    );

	    st.setString(1, id);
	    st.setString(2, password);

	    ResultSet rs = st.executeQuery();

	    Teacher teacher = null;

	    if (rs.next()) {
	        teacher = new Teacher();
	        teacher.setId(rs.getString("ID"));
	        teacher.setPassword(rs.getString("PASSWORD"));
	        teacher.setName(rs.getString("NAME"));
	        
	        School school = new School();
            school.setCd(rs.getString("SCHOOL_CD"));
            teacher.setSchool(school);
	    }

	    st.close();
	    con.close();

	    return teacher;
	}
}