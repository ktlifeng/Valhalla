package com.valhalla.hall.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.sf.cglib.beans.BeanMap;

import java.util.List;
import java.util.Set;

/**
 * Created by zishu.lf on 16/11/7.
 */
public class JSONBean {
    private BeanMap beanMap;

    public JSONBean() {
        super();
    }

    public <T> T getValue(String key, Class<T> clazz){
        if(beanMap==null){
            beanMap = BeanMap.create(this);
        }
        return (T) beanMap.get(key);
    }

    public void setValue(String key, Object value){
        if(beanMap==null){
            beanMap = BeanMap.create(this);
        }
        beanMap.put(key,value);
    }

    public static JSONBean fromJSON(String json) {
        JSONObject object = JSON.parseObject(json);
        JSONBeanGenerator beanGenerator = new JSONBeanGenerator();
        build(object, beanGenerator);
        return fill(json,beanGenerator);
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }

    private static JSONBean fill(String json, JSONBeanGenerator beanGenerator){
        return JSON.parseObject(json, (Class<JSONBean>) beanGenerator.createClass());
    }

    private static void build(JSONObject object, JSONBeanGenerator beanGenerator) {
        Set<String> sets = object.keySet();
        for (String s : sets) {
            Object object1 = object.get(s);
            if (object1 instanceof JSONObject) {
                JSONBeanGenerator generator = new JSONBeanGenerator();
                build((JSONObject) object1, generator);
                beanGenerator.addProperty(s, generator.create().getClass());
            } else if (object1 instanceof JSONArray) {
//                JSONBeanGenerator generator = new JSONBeanGenerator();
//                build((JSONArray) object1, generator);
//                beanGenerator.addProperty(s, generator.create().getClass());
                beanGenerator.addProperty(s, List.class);
            } else {
                beanGenerator.addProperty(s, object1.getClass());
            }
        }
    }

    private static void build(JSONArray jsonArray, JSONBeanGenerator beanGenerator) {
        for (Object arrObject : jsonArray) {
            if (arrObject instanceof JSONObject) {
                build((JSONObject) arrObject, beanGenerator);
            } else if (arrObject instanceof JSONArray) {
                build((JSONArray) arrObject, beanGenerator);
            } else {
            }
        }
    }
}
