package com.xiaopang.base.boot;

import com.xiaopang.core.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

/**
 * @author necho.duan
 * @title: OtterStartEventListener
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 19:50
 */
@Configuration(
        proxyBeanMethods = false
)
public class XiaoPangStartEventListener {

   private static final Logger log = LoggerFactory.getLogger(XiaoPangStartEventListener.class);

    public XiaoPangStartEventListener() {
    }

    @Async
    @Order
    @EventListener({WebServerInitializedEvent.class})
    public void afterStart(WebServerInitializedEvent event) {
        WebServerApplicationContext applicationContext = event.getApplicationContext();
        Environment environment = applicationContext.getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        int localPort = event.getWebServer().getPort();
        String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
        log.warn("\n---[{}]---启动完成，当前使用的端口:[{}]，环境变量:[{}]---", new Object[]{appName, localPort, profile});
        if (UtilClass.isPresent("springfox.documentation.spring.web.plugins.Docket", (ClassLoader)null)) {
            System.out.println(String.format("\nhttp://localhost:%s/doc.html", localPort));
        } else {
            System.out.println(String.format("\nhttp://localhost:%s", localPort));
        }
    }

}
