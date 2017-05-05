package com.huai.action;

import com.huai.domain.Report;
import com.huai.service.GroovyService;
import com.jd.pop.illegal.api.service.PopBuzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/4/13.
 */

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    public PopBuzService popBuzService;

    @Autowired
    GroovyService groovyService;

    @RequestMapping("/hello")
    public Map hello(ModelMap param, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(" hello ,  param = "+param);
        System.out.println(" hello ,  id = "+request.getParameter("id"));
        System.out.println(" hello ,  remark = "+request.getParameter("remark"));
        System.out.println(" hello ,  param = "+request.getAttribute("remark"));
//        PopBuzService service = (PopBuzService)SpringContextHolder.getBean("popBuzService");
//        String result = service.sayHello("jsf");
//        String result = popBuzService.sayHello("jsf");
//        System.out.println(" result = "+result);

        System.out.println(" result0 = "+groovyService.getString3());
        groovyService.getString();
        System.out.println(" result0 = "+groovyService.getString3());
        groovyService.getString2();
        System.out.println(" result0 = "+groovyService.getString3());
        Map result = new HashMap();
        result.put("name","123");
        result.put("code","456");
        return result;
    }

    @RequestMapping(value = "/helloPost",method = RequestMethod.POST)
    public Map helloPost(ModelMap param, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(" helloPost ,  param = "+param);
        System.out.println(" helloPost ,  id = "+request.getParameter("id"));
        System.out.println(" helloPost ,  remark = "+request.getParameter("remark"));
        System.out.println(" helloPost ,  param = "+request.getAttribute("remark"));

        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader();) {
            char[]buff = new char[1024];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" helloPost ,  sb = "+sb.toString());
//        PopBuzService service = (PopBuzService)SpringContextHolder.getBean("popBuzService");
//        String result = service.sayHello("jsf");
//        String result = popBuzService.sayHello("jsf");
//        System.out.println(" result = "+result);

        System.out.println(" result0 = "+groovyService.getString3());
        groovyService.getString();
        System.out.println(" result0 = "+groovyService.getString3());
        groovyService.getString2();
        System.out.println(" result0 = "+groovyService.getString3());
        Map result = new HashMap();
        result.put("name","123");
        result.put("code","456");
        return result;
    }

    @RequestMapping(value = "/helloPostParam",method = RequestMethod.POST)
    public Map helloPostParam(@ModelAttribute Report report, ModelMap param, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(" helloPostParam ,  report = "+report);
        System.out.println(" helloPostParam ,  param = "+param);

//        PopBuzService service = (PopBuzService)SpringContextHolder.getBean("popBuzService");
//        String result = service.sayHello("jsf");
//        String result = popBuzService.sayHello("jsf");
//        System.out.println(" result = "+result);

        System.out.println(" result0 = "+groovyService.getString3());
        groovyService.getString();
        System.out.println(" result0 = "+groovyService.getString3());
        groovyService.getString2();
        System.out.println(" result0 = "+groovyService.getString3());
        Map result = new HashMap();
        result.put("name","123");
        result.put("code","456");
        return result;
    }

}
