package com.xiaopang.core.common;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author necho.duan
 * @title: OnceDone
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/2 20:49
 */
public class OnceDone {
    private AtomicBoolean atomic = new AtomicBoolean(true);

    public OnceDone() {
    }

    public boolean once() {
        return !this.atomic.get() ? false : this.atomic.getAndSet(false);
    }

    public void onceDo(Runnable run) {
        if (this.once()) {
            run.run();
        }

    }
}
