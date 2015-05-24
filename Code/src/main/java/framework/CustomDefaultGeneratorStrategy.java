package framework;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class CustomDefaultGeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String str = super.getJavaClassName(definition, mode);
        if (mode == Mode.POJO) {
            str += "Pojo";
        }
        return str;
    }
}
