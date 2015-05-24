package feature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DbRebuilder {
    private static DbRebuilder instance = new DbRebuilder();
    private DbRebuilder() {
    }
    
    public static DbRebuilder getInstance() {
        return instance;
    }
    
    public void rebuild() {
        String dumpFilePath = getClass().getClassLoader().getResource("dump.sql").getFile();
        String dump;
        try {
            dump = new String(Files.readAllBytes(Paths.get(dumpFilePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Database.getInstance().executeQuery(dump);
    }
    
    public void cleanData() {
        String truncateFilePath = getClass().getClassLoader().getResource("truncate.sql").getFile();
        String truncate;
        try {
            truncate = new String(Files.readAllBytes(Paths.get(truncateFilePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Database.getInstance().executeQuery(truncate);
    }
    
    
    public static void main(String[] args) throws IOException, SQLException {
        DbRebuilder rebuilder = new DbRebuilder();
        rebuilder.rebuild();
    }
}
