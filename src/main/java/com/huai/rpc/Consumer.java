package com.huai.rpc;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/5/10.
 */
public class Consumer {

    public static void main(String[] args)  throws Exception {
        MyService service = (MyService) Proxy.newProxyInstance(MyService.class.getClassLoader(), new Class[]{MyService.class},
                JdkProxyInvocationHandler.getInstance());

        Map param = new HashMap();
        param.put("id","1");
        param.put("name","zhangsan");
        Map result = service.hello2(param,"huai23",100);
        System.out.println("result:"+result);

    }

}
