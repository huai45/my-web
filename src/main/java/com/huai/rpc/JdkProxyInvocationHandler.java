package com.huai.rpc;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/5/10.
 */
public class JdkProxyInvocationHandler implements InvocationHandler {

    private static JdkProxyInvocationHandler instance= null;

    /** 私有构造器 */
    private JdkProxyInvocationHandler() {
    }

    @Override
    public Object invoke(Object target, Method targetMethod, Object[] params)
            throws Throwable {
        // Proxy示例实现了提供的所有接口，并继承自Proxy
        System.out.println(target instanceof Proxy); // true
        System.out.println(target instanceof MyService); // true
        System.out.println(target.getClass().getInterfaces());
        Class[] intfs = target.getClass().getInterfaces();
        String clazz = "";
        String method = "";
        for(int i=0;i<intfs.length;i++){
            clazz = intfs[i].getName();
            System.out.println("intf = "+clazz);
        }
        method = targetMethod.getName();
        Class[] paramTypes  = targetMethod.getParameterTypes();
        Map vo = new HashMap();
        vo.put("intf",clazz);
        vo.put("method",method);
        vo.put("paramTypes",paramTypes);
        vo.put("params",params);
        HelloClient client = new HelloClient(vo);
        client.connect("127.0.0.1", 8000);
        return vo.get("data");
    }

    /**
     * 获取该类实例
     * @return
     */
    public static JdkProxyInvocationHandler getInstance(){
        if (instance == null){
            initInstance();
        }
        return instance;
    }

    /**
     * 初始化示例。
     */
    private static synchronized void initInstance(){
        if (instance == null){
            instance = new JdkProxyInvocationHandler();
        }
    }
}
