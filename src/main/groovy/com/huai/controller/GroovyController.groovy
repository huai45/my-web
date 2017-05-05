package com.huai.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * Created by zhonghuai.zhang on 2017/4/25.
 */

@RestController
@RequestMapping("/Groovy")
class GroovyController {


    @RequestMapping("/hello")
    String  hello(HttpServletRequest request){

        println "id = "+request.getParameter("id")
        println "name = "+request.getParameter("name")

        return "hello world"
    }

}
