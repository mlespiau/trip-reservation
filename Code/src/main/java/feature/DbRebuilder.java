package feature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DbRebuilder {
    
    public void rebuild() throws IOException, SQLException {
        String dumpFilePath = getClass().getClassLoader().getResource("dump.sql").getFile();
        String dump = new String(Files.readAllBytes(Paths.get(dumpFilePath)));
        Database.getInstance().executeQuery(dump);
    }
    
    public static void main(String[] args) throws IOException, SQLException {
        DbRebuilder rebuilder = new DbRebuilder();
        rebuilder.rebuild();
    }
}
