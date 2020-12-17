package com.xiaopang.base.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 * @author necho.duan
 * @title: XiaoPangBaseConfiguration
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 19:47
 */


@Order(Integer.MIN_VALUE)
@Configuration(
        proxyBeanMethods = false
)
@PropertySource({"classpath:/xiaopang-frame-base/base.properties"})
public class XiaoPangBaseConfiguration {

    public XiaoPangBaseConfiguration() {
    }

}
