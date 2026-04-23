package dao;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
    static DataSource ds;
    public Connection getConnection() throws Exception {
        if (ds == null) {
            InitialContext ic = new InitialContext();
            // 「java:」の後の「/」を消して、kaihatsu（sあり）に修正
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/kaihatsu");
        }
        return ds.getConnection();
    }
}