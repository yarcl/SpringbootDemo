package com.yarcl.springquart.constant;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xiaozhi on 2019/6/19.
 */
public class SystemConstantImpl {
    private static SystemConstantImpl systemConstantImpl;
    private static Map<String, Map<String, Object>> systemConstMap = new HashMap<>();

    private SystemConstantImpl() {
    }

    /**
     * 初始化数据
     * @return
     */
    public static SystemConstantImpl initObject() {
        // 创建对象
        if(ObjectUtils.isEmpty(systemConstantImpl)) {
            systemConstantImpl = new SystemConstantImpl();
        }
        return systemConstantImpl;
    }

    /**
     * 初始化数据
     * @param initValue Map类型数据<quartzId, json对象>
     * @return
     */
    public static SystemConstantImpl initObject(Map<String, String> initValue) {
        // 创建对象
        if(ObjectUtils.isEmpty(systemConstantImpl)) {
            systemConstantImpl = new SystemConstantImpl();
        }
        // 初始化数据
        initValue.entrySet().stream().forEach(item -> {
            String objId = item.getKey();
            String strValue = item.getValue();
            try {
                // 插入数据
                systemConstantImpl.initializedObjValue(objId, strValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return systemConstantImpl;
    }

    /**
     * 初始化json对象
     * @param objId
     * @param strValue json对象
     */
    private void initializedObjValue(String objId, String strValue) {
        if (ObjectUtils.isEmpty(systemConstMap.get(objId))) {
            Map<String, Object> quartzObj = new HashMap<>();
            JSONObject job = JSONObject.parseObject(strValue);
            Set<Map.Entry<String, Object>> entrySet = job.entrySet();
            entrySet.stream().forEach(entry -> {
                String key = entry.getKey();
                Object obj = entry.getValue();
                quartzObj.put(key, obj);
            });
            systemConstMap.put(objId, quartzObj);
        }
    }

    /**
     * 获取常量对象
     * @param quartzId
     * @return
     */
    public Map<String, Object> fetchObjValue(String quartzId) {
        Map<String, Object> fetchResult = systemConstMap.get(quartzId);
        return fetchResult;
    }

    /**
     * 获取常量对象当中的常量数据
     * @param objId 对象Id
     * @param attrId 属性Id
     */
    public Object fetchAttrValue(String objId, String attrId) {
        Map<String, Object> mapObj = systemConstMap.get(objId);
        if(ObjectUtils.isEmpty(mapObj)) {
            return null;
        } else {
            return mapObj.get(attrId);
        }
    }

    /**
     * 修改指定key的常量数据
     * @param objId
     * @param strValue
     */
    public void updateObjValue(String objId, String strValue) {
        if(systemConstMap.containsKey(objId)) {
            Map<String, Object> objMap = new HashMap<>();
            systemConstMap.remove(objId);
            JSONObject job = JSONObject.parseObject(strValue);
            Set<Map.Entry<String, Object>> entrySet = job.entrySet();
            entrySet.stream().forEach(entry -> {
                String key = entry.getKey();
                Object obj = entry.getValue();
                objMap.put(key, obj);
            });
            systemConstMap.put(objId, objMap);
        }
    }

    /**
     * 修改指定key的常量数据
     * @param objId
     * @param attrId
     * @param strValue
     */
    public void updateAttrValue(String objId, String attrId, String strValue) {
        Map<String, Object> objMap = systemConstMap.get(objId);
        if(systemConstMap.containsKey(objId)) {
            Object obj = objMap.get(attrId);
            if(!ObjectUtils.isEmpty(obj)) {
                objMap.put(attrId, strValue);
            }
        }
    }

    /**
     * 删除指定key的常量数据
     * @param objId
     */
    public void deleteObjValue(String objId) {
        if(systemConstMap.containsKey(objId)) {
            systemConstMap.remove(objId);
        }
    }

    /**
     * 删除指定obj的属性常量数据
     * @param objId
     * @param attrId
     */
    public void deleteAttrValue(String objId, String attrId) {
        if(systemConstMap.containsKey(objId)) {
            systemConstMap.get(objId).remove(attrId);
        }
    }
}
