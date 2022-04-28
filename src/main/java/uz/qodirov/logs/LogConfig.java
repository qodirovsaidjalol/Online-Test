package uz.qodirov.logs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.logging.*;

public class LogConfig {

    public static void load() {
        try {
            Logger logger = Logger.getLogger("uz.quizProject");
            InputStream configFile = new FileInputStream("src/main/resources/log.properties");
            LogManager.getLogManager().readConfiguration(configFile);
            logger.setLevel(Level.ALL);


            Handler fileHandler = new FileHandler("src/main/resources/logs.log", 10485760, 1000, true);
            fileHandler.setFormatter(new CustomFormatter());
            logger.addHandler(fileHandler);
            logger.addHandler(new CustomHandler());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
