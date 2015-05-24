package feature;

import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.zaxxer.hikari.HikariDataSource;

public class Database {
    private static Database instance;
    HikariDataSource ds;

    public Database() {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/tripreservation?allowMultiQueries=true");
        ds.setUsername("root");
        ds.setPassword("");
    }
    
    public void executeQuery(String queryString) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Query query = create.query(queryString);
            query.execute();
        }
    }
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;

    }
}
