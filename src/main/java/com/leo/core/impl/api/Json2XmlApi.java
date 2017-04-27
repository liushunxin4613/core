package com.leo.core.impl.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leo.core.inter.api.IParseApi;

import java.util.Map;

/**
 * json转换成xml
 */
public class Json2XmlApi implements IParseApi<Json2XmlApi, String, String, String> {

    private static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
    private static final String XML_ROOT = "root";
    private static String ATTRIBUTE = "attribute";
    private static String TEXT = "text";

    public void setXml(String attribute, String text){
        ATTRIBUTE = attribute;
        TEXT = text;
    }

    /**
     * json转换成xml
     *
     * @param in
     *            传入数据
     * @param root
     *            根节点名称
     * @return xml数据
     */
    public String parse(@NonNull String in, String root) {
        StringBuilder xml = new StringBuilder();
        if (!TextUtils.isEmpty(in) && !TextUtils.isEmpty(root)) {
            Object obj = JSON.parse(in);
            xml.append(XML_HEAD);
            if (obj instanceof JSONObject && ((JSONObject) obj).size() != 1) {
                xml.append("\n").append("<").append(root).append(">");
                xml.append(parse(obj, null, 1, false));
                xml.append("</").append(root).append(">");
            } else if (obj instanceof JSONArray
                    && ((JSONArray) obj).size() != 1) {
                xml.append("\n").append("<").append(root).append(">");
                xml.append(parse(obj, null, 1, false));
                xml.append("</").append(root).append(">");
            } else if (obj instanceof JSON) {
                xml.append(parse(obj, null, 0, false));
            }
        }
        return xml.toString();
    }

    /**
     * json转换成xml
     *
     * @param in
     *            传入数据
     * @return xml数据
     */
    public String parse(String in) {
        return parse(in, XML_ROOT);
    }

    private String text;

    private String parse(Object obj, StringBuilder xml, int t, boolean au) {
        if (xml == null) {
            xml = new StringBuilder();
        }
        if (obj instanceof JSONObject) {
            JSONObject jo = (JSONObject) obj;
            for (Map.Entry<String, Object> entry : jo.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (!TextUtils.isEmpty(key)) {
                    if (value == null) {
                        xml.append("\n").append(tString("\t", t));
                        xml.append("<").append(key).append("/>");
                    } else if (value instanceof String) {
                        if (au) {
                            xml.append(" ").append(key).append("=\"").append(value).append("\"");
                        } else {
                            if(key.equals(TEXT)){
                                text = (String) value;
                            }else{
                                xml.append("\n").append(tString("\t", t));
                                xml.append("<").append(key).append(">")
                                        .append(value)
                                        .append("</").append(key).append(">");
                            }
                        }
                    } else {// JSONObject,JSONArray及其他
                        if (key.equals(ATTRIBUTE)) {
                            xml.replace(xml.length() - 1, xml.length(), "");
                            parse(value, xml, t, true);
                            xml.append(">");
                        } else {
                            xml.append("\n").append(tString("\t", t++));
                            xml.append("<").append(key).append(">");
                            parse(value, xml, t, au);
                            if(!TextUtils.isEmpty(text)){
                                int size = 0;
                                if(value instanceof JSONArray){
                                    JSONArray array = (JSONArray) value;
                                    size = array.size();
                                    for(int i = 0; i < array.size(); i++){
                                        if(array.get(i) instanceof JSONObject){
                                            JSONObject j = (JSONObject) array.get(i);
                                            if(j.get(ATTRIBUTE) != null || j.get(TEXT) != null){
                                                size--;
                                            }
                                        }
                                    }
                                }
                                if(size > 0){
                                    xml.append("\n").append(tString("\t", t));
                                    xml.append(text).append("\n").append(tString("\t", --t));
                                }else{
                                    xml.append(text);
                                }
                                text = null;
                            }else{
                                xml.append("\n").append(tString("\t", --t));
                            }
                            String str = xml.toString().trim();
                            if(str.endsWith("\">")){
                                xml.replace(xml.lastIndexOf(">"), xml.length(), "/>");
                            }else {
                                xml.append("</").append(key).append(">");
                            }
                        }
                    }
                }
            }
        } else if (obj instanceof JSONArray) {
            JSONArray ja = (JSONArray) obj;
            for (int i = 0; i < ja.size(); i++) {
                parse(ja.get(i), xml, t, au);
            }
        }
        return xml.toString();
    }

    private String tString(String str, int t) {
        String s = "";
        if (!TextUtils.isEmpty(str) && t != 0) {
            for (int i = 0; i < t; i++) {
                s += str;
            }
            return s;
        }
        return s;
    }

}
