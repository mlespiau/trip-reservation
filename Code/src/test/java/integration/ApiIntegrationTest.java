package integration;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import spark.Spark;
import feature.Application;
import feature.DbRebuilder;

public class ApiIntegrationTest {

    @BeforeClass
    public static void beforeClass() throws InterruptedException {
        DbRebuilder.getInstance().rebuild();
        Application.main(null);
        Spark.awaitInitialization();
    }
    
    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }
    
    @After
    public void tearDown() throws IOException {
        DbRebuilder.getInstance().cleanData();
    }
}
