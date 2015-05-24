package framework;

import java.sql.Connection;
import java.sql.SQLException;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.ResultQuery;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import com.zaxxer.hikari.HikariDataSource;

public class Database {
    private static Database instance;
    DSLContext create;
    Connection connection;
    private HikariDataSource ds;

    public Database() {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/tripreservation?allowMultiQueries=true");
        ds.setUsername("root");
        ds.setPassword("");
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        create = DSL.using(connection, SQLDialect.MYSQL);
    }
    
    public void executeQuery(String queryString) {
        Query query = this.create.query(queryString);
        query.execute();
    }
    
    public Result<Record> resultQuery(String queryString) {
        ResultQuery<Record> resultQuery = create.resultQuery(queryString);
        return resultQuery.fetch();
    }
    
    public Configuration getConfiguraton() {
        return new DefaultConfiguration().set(connection).set(SQLDialect.MYSQL);
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
