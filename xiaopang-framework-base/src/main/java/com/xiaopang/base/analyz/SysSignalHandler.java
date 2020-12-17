package com.xiaopang.base.analyz;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author necho.duan
 * @title: SysSignalHandler
 * @projectName xiaopang-framework
 * @description:
 * @date 2020/12/15 11:56
 */
public class SysSignalHandler  implements SignalHandler {

    @Override
    public void handle(final Signal sig) {

       System.out.println(String.valueOf(sig.getName())+" : "+sig.getNumber()+"" + " received, shutdown jvm");
       if(sig.getName().equals("TERM")){
           System.exit(0);
       }
    }

}
