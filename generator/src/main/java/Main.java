import org.apache.commons.cli.*;
import java.io.IOException;

import static ca.mcmaster.cas.se2aa4.a2.generator.Configurations.configurations.runConfig;

public class Main {

    // generate irregular and regular mesh at the same time
    // standard command line call - java -jar generator.jar -mf sample.mesh -mv regular -s 500 -ss 25 -t 1
    // standard CLI call for irregular - java -jar generator.jar -mf sample.mesh -mv irregular -w 500 -h 500 -num 200
    public static void main(String[] args) {
        runConfig(args);
    }
}