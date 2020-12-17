package com.xiaopang.base.logger;

import com.xiaopang.base.logger.env.LoggerLevel;
import com.xiaopang.base.logger.util.*;
import com.xiaopang.core.common.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Properties;
import java.util.stream.Stream;

/**
 * @author necho.duan
 * @title: LoggerInitializer
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 20:50
 */
public class LoggerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static final OnceDone ONCE_DONE = new OnceDone();

    public LoggerInitializer() {
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (ONCE_DONE.once()) {
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            String appName = environment.getProperty("spring.application.name");
            String[] profiles = environment.getActiveProfiles();
            String activeProfile = (String) Stream.of(profiles).findFirst().orElse("");
            String startJarPath = LoggerInitializer.class.getResource("/").getPath().split("!")[0];
            System.err.println(String.format("---[%s]---启动中，读取到的环境变量:[%s]，jar地址:[%s]---", appName, activeProfile, startJarPath));
            LoggerLevel envLogLevel = LoggerLevel.of(activeProfile);
            System.out.println("LoggerInitializer.envLogLevel----->" + envLogLevel);
            String logBase = "/data/logs";
            Properties properties = System.getProperties();
            properties.setProperty("spring.application.name", appName);
            properties.setProperty("spring.application.profile", activeProfile);
            properties.setProperty("OTTER_LOGGING_PATH", logBase);
            String rootLevel = environment.getProperty("logging.level.root", envLogLevel.getRoot());
            properties.setProperty("LOG_ROOT_LEVEL", rootLevel);
            Level amityLevel = ConsolePredicate.getAmityLevel(environment, envLogLevel);
            System.out.println("LoggerInitializer.ConsoleLevelFilter.setAmityLevel----->" + amityLevel);
            String appendRef = environment.getProperty("LOG_APPEND_KEY", envLogLevel.getAppenderStr());
            if (StringUtils.equalsIgnoreCase(appendRef, "elk")) {
                properties.setProperty("LOG_APPEND_VALUE", "elkFile");
                properties.setProperty("logging.file.name", String.format("%s/%s/elk.log", logBase, appName));
            } else {
                properties.setProperty("LOG_APPEND_VALUE", "rewriteAllFile");
                properties.setProperty("logging.file.name", String.format("%s/%s/all.log", logBase, appName));
            }

            properties.setProperty("logging.config", "classpath:xiaopang-frame-base/log4j2.xml");
            properties.setProperty("log4j2.isThreadContextMapInheritable", "true");
        }
    }
}
