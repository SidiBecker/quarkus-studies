package com.sidneibecker.quarkus.util;

import org.eclipse.microprofile.config.ConfigProvider;

public class Util {

	public static String getApplicationProperty(String key) {
		return ConfigProvider.getConfig().getValue(key, String.class);
	}
	
}
