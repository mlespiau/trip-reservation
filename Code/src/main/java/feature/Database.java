package feature;

import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.zaxxer.hikari.HikariDataSource;

public class Database {
    private static Database instance;
    HikariDataSource ds;
    DSLContext create;

    public Database() {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/tripreservation?allowMultiQueries=true");
        ds.setUsername("root");
        ds.setPassword("");
        try {
            create = DSL.using(ds.getConnection(), SQLDialect.MYSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void executeQuery(String queryString) {
        Query query = this.create.query(queryString);
        query.execute();
    }
    
    public Result<Record> resultQuery(String queryString) {
        ResultQuery<Record> resultQuery = create.resultQuery(queryString);
        return resultQuery.fetch();
    }
    
    public DSLContext getDslContext() {
        return this.create;
    }
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
