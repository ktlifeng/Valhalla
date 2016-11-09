package com.valhalla.hall.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by ktlifeng@163.com on 16/11/7.
 */
public class JSONBeanTest {

    public static void main(String[] args) {
        String s = "{\"name1\":{\"name3\":{\"name3\":\"value3\",\"name4\":1111}},\"list1\":[1,2,3]}";
        JSONBean myBean = JSONBean.fromJSON(s);
        System.out.println(JSON.toJSONString(myBean));
        JSONBean quota = myBean.getValue("name1",JSONBean.class);
        JSONBean doRec = quota.getValue("name2",JSONBean.class);
        doRec.setValue("name4",-100);
        System.out.println(myBean.toJSON());
        List testList = myBean.getValue("list1",List.class);
        System.out.println(testList);

    }
}
