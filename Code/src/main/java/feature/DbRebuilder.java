package feature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import framework.Database;

public class DbRebuilder {
    private static DbRebuilder instance = new DbRebuilder();
    private DbRebuilder() {
    }
    
    public static DbRebuilder getInstance() {
        return instance;
    }
    
    public void rebuild() {
        this.executeSqlFile(getClass().getClassLoader().getResource("dump.sql").getFile());
    }
    
    public void cleanData() {
        this.executeSqlFile(getClass().getClassLoader().getResource("truncate.sql").getFile());
    }
    
    private void executeSqlFile(String filePath) {
        String fileContent;
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Database.getInstance().executeQuery(fileContent);
    }
    
    
    public static void main(String[] args) throws IOException, SQLException {
        DbRebuilder rebuilder = new DbRebuilder();
        rebuilder.rebuild();
    }
}
