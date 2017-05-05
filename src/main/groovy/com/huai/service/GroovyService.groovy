package com.huai.service

import org.springframework.stereotype.Service

/**
 * Created by zhonghuai.zhang on 2017/4/25.
 */
@Service
class GroovyService {

    def str =  "i am a person"

    String  getString(){
        str = 1 + 23
        return str
    }

    String  getString2(){

        str =  "i am a person too "
        return str

    }

    String  getString3(){

        return str

    }
}
