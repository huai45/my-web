package com.huai.domain;

import lombok.Data;

/**
 * Created by zhonghuai.zhang on 2017/5/5.
 */
@Data
public class Report {
    String id;
    String remark;

    public String toString(){
        return "id="+this.id+" , remark="+this.remark;
    }
}
