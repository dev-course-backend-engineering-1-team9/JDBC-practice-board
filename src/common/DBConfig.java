package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private static final String PROPERTIES_FILE = "src/resources/dbconfig.properties";
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(input);
            URL = properties.getProperty("url");
            USERNAME = properties.getProperty("username");
            PASSWORD = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return URL;
    }

    public static String getUsername() {
        return USERNAME;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}