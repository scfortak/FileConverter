package com.spark.fc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SparkConfigurationUtil {

    private final static String CONFIG_FILE_NAME = "sparkConfig.properties";
    private final static String PRM_BUSINESS_SERVER_URL = "business.server.url";
    private final static String PRM_REMOTE_SERVICE_URL = "remote.service.url";
    private final static String PRM_REST_API_BASE_PATH = "rest.api.base.path";

    private static Properties getPropertiesFile() {
    Properties prop = new Properties();
    InputStream input = null;

    try {
	input = SparkConfigurationUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME);

	if (input == null) {
	        return null;
	}

	prop.load(input);

	return prop;
    }
    catch (IOException ex) {
	ex.printStackTrace();
    }
    finally {
	if (input != null) {
	        try {
		    input.close();
	        }
	        catch (IOException e) {
		    e.printStackTrace();
	        }
	}
    }

    return null;
    }

    public static String getBusinessServerUrl() {
    return getPropertiesFile().getProperty(PRM_BUSINESS_SERVER_URL);
    }

    public static String getRemoteServiceUrl() {
    return getPropertiesFile().getProperty(PRM_REMOTE_SERVICE_URL);
    }

    public static String getRestApiBasePath() {
    return getPropertiesFile().getProperty(PRM_REST_API_BASE_PATH);
    }

	
}
