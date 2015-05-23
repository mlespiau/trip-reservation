package feature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Query;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.zaxxer.hikari.HikariDataSource;

public class DbRebuilder {
    HikariDataSource ds;
    
    public DbRebuilder() throws SQLException {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/tripreservation?allowMultiQueries=true");
        ds.setUsername("root");
        ds.setPassword("");
    }
    
    public void rebuild() throws IOException, SQLException {
        String dumpFilePath = getClass().getClassLoader().getResource("dump.sql").getFile();
        String dump = new String(Files.readAllBytes(Paths.get(dumpFilePath)));
        this.executeQuery(dump);
    }
    
    private void executeQuery(String queryString) throws SQLException {
        try (Connection conn = ds.getConnection()) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Query query = create.query(queryString);
            query.execute();
        }
    }
    
    public static void main(String[] args) throws IOException, SQLException {
        DbRebuilder rebuilder = new DbRebuilder();
        rebuilder.rebuild();
    }
}
