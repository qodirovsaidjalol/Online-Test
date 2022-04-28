package uz.qodirov.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseProperty {
    private Properties properties;
    protected BaseProperty(String path) {
        load(path);
    }

    private void load(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}
