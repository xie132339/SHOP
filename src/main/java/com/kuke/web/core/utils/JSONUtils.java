package com.kuke.web.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONUtils {
	/**
	 * 
	 * @Title: json2List
	 * @Description: 对象转集合
	 * @param json
	 * @return List<Map<String,Object>> 返回类型
	 */
	public static List<Map<String, Object>> toList(Object json) {  
        JSONArray jsonArr = new JSONArray();
        jsonArr.add(json);  
        List<Map<String, Object>> arrList = new ArrayList<Map<String, Object>>();  
        for (int i = 0; i < jsonArr.size(); ++i) {  
            arrList.add(toMap(toJson(jsonArr.get(i)), false));  
        }  
        return arrList;  
    } 
	
	/**
	 * 
	 * @Title: toColumnReturnList
	 * @Description: 把对象属性转List
	 * @param obj
	 * @return List<Object> 返回类型
	 */
	public static List<Object> toColumnReturnList(Object obj) {
		List<Object> list = new ArrayList<Object>();
		if(!Utils.isEmpty(obj)) {
			if (obj instanceof Object) {
				list.add(toMap(toJson(obj), false));  
			}
		}
		return list;
	}  
	
	/**
	 * 
	 * @Title: toJson
	 * @Description: 对象转JSON
	 * @param obj
	 * @return String 返回类型
	 */
	public static String toJson(Object obj) {
		return JSONObject.toJSONString(obj);
	}
	
	/**
	 * 
	 * @Title: strJson2Map
	 * @Description: json转map
	 * @param json
	 * @param isSerialize 是否拼接key=value&key=value格式
	 * @return Map<String,Object> 返回类型
	 */
	public static Map<String, Object> toMap(String json, boolean isSerialize) {
		Map<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.parseObject(json);  
        Iterator<Entry<String, Object>> it = jsonObject.entrySet().iterator();  
        String value = "";
		while (it.hasNext()) {
			 Map.Entry<String, Object> param = (Map.Entry<String, Object>) it.next();  
            if (param.getValue() instanceof JSONObject) {  
            	data.put(param.getKey(), toMap(param.getValue().toString(), false));  
            } else if (param.getValue() instanceof JSONArray) {  
            	data.put(param.getKey(), toList(param.getValue()));  
            } else if (param.getValue() instanceof String) {  
            	if(isSerialize) {
            		if(value.length() <= 0)
            			value = param.getKey() + "=" + param.getValue();
            		else
            			value += "&" + param.getKey() + "=" + param.getValue();
            		data.put("serialize", param.getValue());
            	} else {
        			data.put(param.getKey(), param.getValue());  
            	}
            } else {  
            	data.put(param.getKey(), JSONObject.toJSONString(param.getValue(), SerializerFeature.WriteClassName));  
            }  
		}		 
	  return data;	
	}

	/**
	 * 
	 * @Title: toJSONArray
	 * @Description: 转jsonArray
	 * @param json
	 * @return JSONArray 返回类型
	 */
	public static JSONArray toJSONArray(Object obj) {
		JSONArray jr = new JSONArray();
		jr.add(obj);
		return jr;
	}
	
	/**
	 * 
	 * @Title: JSONObject
	 * @Description: 转JSONObject
	 * @param json
	 * @return JSONArray 返回类型
	 */
	public static JSONObject toJSONObject(String json) {
		return JSONObject.parseObject(json);
	}
}
