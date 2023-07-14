package com.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilityClass {
	public static String getPropertyFileValue(String key) throws IOException {
		FileInputStream file = new FileInputStream("src\\test\\resources\\PropertyFiles\\Config.properties");
		Properties p = new Properties();
		p.load(file);
		return p.getProperty(key);
	}
}
