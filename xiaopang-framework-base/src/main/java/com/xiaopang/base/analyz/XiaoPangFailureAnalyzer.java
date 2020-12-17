package com.xiaopang.base.analyz;

import com.xiaopang.core.utils.Exceptions;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.springframework.boot.diagnostics.FailureAnalyzer;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.Ordered;


/**
 * @author necho.duan
 * @title: OtterFailureAnalyzer
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 18:57
 */
public class XiaoPangFailureAnalyzer implements FailureAnalyzer, Ordered {

    public XiaoPangFailureAnalyzer() {
    }

    @Override
    public FailureAnalysis analyze(Throwable failure) {
        String failureMessage = failure.getMessage();
        Throwable throwable = NestedExceptionUtils.getMostSpecificCause(failure);
        String description = failureMessage + "\n\n" + Exceptions.getStackTraceAsString(throwable);
        return new FailureAnalysis(description, (String)null, throwable);
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }

}
