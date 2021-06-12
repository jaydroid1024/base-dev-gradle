package com.jay.base_dev_gradle.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 注解处理器页面收集需要生成的类
 * @author jaydroid
 * @version 1.0
 * @date 5/25/21
 */
class RouterMapping_123 {
    public static Map<String, String> get() {
        HashMap<String, String> map = new HashMap<>();
        map.put("router://aaa", "com.a.aActivity");
        map.put("router://bbb", "com.b.bActivity");
        map.put("router://ccc", "com.c.cActivity");
        return map;
    }
}
