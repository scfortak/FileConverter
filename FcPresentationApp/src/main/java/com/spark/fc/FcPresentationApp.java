package com.spark.fc;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.spark.fc.util.SparkConfigurationUtil;
import com.spark.iron.remote.RemotingConfigManager;
import com.spark.iron.security.annotation.IronAuthentication;
import com.spark.iron.security.annotation.IronAuthorization;
import com.spark.iron.security.annotation.IronCorsMapping;
import com.spark.iron.security.annotation.IronCorsRegistration;

@IronCorsRegistration(corsMapping = { @IronCorsMapping(allowedMethods = { "GET", "POST", "PUT", "PATCH", "DELETE" }, allowedHeaders = {"*" }, allowedOrigins = { "*" }, maxAge = 1800, pattern = "/**") })
@IronAuthentication(filterProcessesUrl = "/rest/authenticate", nonFilterProcessesUrl = "")
@IronAuthorization(filterProcessesUrl = "/rest/**", nonFilterProcessesUrl = { "/rest/authenticate", "/index.html" })
@SpringBootApplication(scanBasePackages = "com.spark")
public class FcPresentationApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
    SpringApplication.run(FcPresentationApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(FcPresentationApp.class);
    }

    @PostConstruct
    public void init() {
    String serverUrl = SparkConfigurationUtil.getBusinessServerUrl();
    String remoteServerServiceUrl = SparkConfigurationUtil.getRemoteServiceUrl();

    RemotingConfigManager.getInstance().addConfig("com.spark.auth", serverUrl + remoteServerServiceUrl);
    RemotingConfigManager.getInstance().addConfig("com.spark.credit", serverUrl + remoteServerServiceUrl);
    RemotingConfigManager.getInstance().addConfig("com.spark.file", serverUrl + remoteServerServiceUrl);
    }

}
