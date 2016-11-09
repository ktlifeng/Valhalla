package com.valhalla.hall.util;


import net.sf.cglib.beans.BeanGenerator;

/**
 * Created by zishu.lf on 16/11/8.
 */
public class JSONBeanGenerator extends BeanGenerator {

    public JSONBeanGenerator(){
        super();
        this.setSuperclass(JSONBean.class);
    }
}
