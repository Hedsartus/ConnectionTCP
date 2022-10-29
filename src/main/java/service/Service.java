package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class Service {
    private static final String path = "settings.properties";
    private int port;
    private String ip;

    public Service() {
        Properties properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream(path);
            Reader reader = new InputStreamReader(fis, "UTF-8");
            properties.load(reader);

            this.ip = properties.getProperty("host");
            this.port = Integer.parseInt(properties.getProperty("port"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }
}
