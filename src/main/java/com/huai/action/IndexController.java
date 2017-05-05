package com.huai.action;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by zhonghuai.zhang on 2017/4/13.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String home(){
        System.out.println("  index  page ");
        return "common/index";
    }

    @GetMapping("/demo")
    public String demo(Model model){
        System.out.println("  demo  page ");
//        model.addAttribute("screen_content","/demo/demo.vm");
        return "demo/iview";
    }

    @GetMapping("/template")
    public String template(Model model){
        System.out.println("  template  page ");
//        model.addAttribute("screen_content","/demo/demo.vm");
        return "demo/template";
    }

    @GetMapping("/router")
    public String router(Model model){
        System.out.println("  router  page ");
//        model.addAttribute("screen_content","/demo/demo.vm");
        return "demo/router";
    }

}
