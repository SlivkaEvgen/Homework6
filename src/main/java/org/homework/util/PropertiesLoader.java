package org.homework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final String PROPERTIES_FILE_NAME = "application.properties";
    private static final Properties PROPERTIES;

    static {
        System.out.println("load properties");
        PROPERTIES = new Properties();
        try (InputStream is = getInputStream(PROPERTIES_FILE_NAME)) {
            PROPERTIES.load(is);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getProperties(final String name) {
        System.out.println(" property getProperties");
        return PROPERTIES.getProperty(name);
    }

    public static InputStream getInputStream(final String fileName) {
        System.out.println(" property getInputStream");
        return PropertiesLoader.class.getClassLoader().getResourceAsStream(fileName);
    }
}
