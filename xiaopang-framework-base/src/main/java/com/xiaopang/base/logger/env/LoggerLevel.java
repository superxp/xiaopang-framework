package com.xiaopang.base.logger.env;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.util.StringUtils;
import java.util.Optional;

/**
 * @author necho.duan
 * @title: LoggerLevel
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 20:53
 */
@ToString
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum LoggerLevel {

    /*** 各环境日志配置
	 */
    dev("INFO","INFO"),
    test("INFO","INFO"),
    demo("INFO","INFO"),
    sit("INFO","INFO"),
    pro("WARN","WARN"),
    unknown("INFO","WARN");

    /**
     * root 日志级别
     */
    private final String root;
    /**
     * 控制台日志级别
     */
    private final String console;

    /**
     * 输出文件格式 "all" or  "elk"
     */
    private Appender appender = Appender.elk;

    public String getAppenderStr(){
        return Optional.ofNullable(appender).orElse(Appender.elk).name();
    }

    /**
     * 环境与日志级别关联
     *
     * @param env 环境
     * @return 日志级别
     */
    public static LoggerLevel of(String env) {
        if (!StringUtils.hasText(env)) {
            return LoggerLevel.dev;
        }
        for (LoggerLevel level : LoggerLevel.values()) {
            if (level.name().equals(env.toLowerCase())) {
                return level;
            }
        }
        return LoggerLevel.unknown;
    }

}

