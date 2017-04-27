package com.leo.core.impl.api;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.leo.core.impl.ab.AXmlPullImplApi;
import com.leo.core.utils.LogUtil;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml解析为json
 */
public class Xml2JsonSonApi extends AXmlPullImplApi<Xml2JsonSonApi> {

    private static final String ATTRIBUTE = "attribute";
    private static final String TEXT = "text";

    private Map<String, Object> map;//获取attribute时用
    private List<Map<String, Object>> mapList;//辅助类
    private List<String> line;//名称列表
    private Gson gson;


    @Override
    public Xml2JsonSonApi xmlOpen() {
        line = new ArrayList<>();
        mapList = new ArrayList<>();
        map = new HashMap<>();
        gson = new Gson();
        return this;
    }

    @Override
    public Xml2JsonSonApi xmlStart(XmlPullParser parser) {
        line.add(parser.getName());
        mapList.add(new HashMap<String, Object>());
        getNowMap().put(getNowLine(1), new ArrayList<>());

        map = new HashMap<>();
        for (int i = 0; i < parser.getAttributeCount(); i++) {
            String key = parser.getAttributeName(i);
            String value = parser.getAttributeValue(i);
            if (!TextUtils.isEmpty(key)) {
                map.put(key, value);
            }
        }
        if (map.size() > 0) {
            Map<String, Object> mm = new HashMap<>();
            mm.put(ATTRIBUTE, map);
            ((List) getNowMap().get(getNowLine(1))).add(mm);
            map = null;
        }
        return this;
    }

    @Override
    public Xml2JsonSonApi xmlText(String text) {
        text = text == null ? null : text.trim();
        if (!TextUtils.isEmpty(text)) {
            if (((List) getNowMap().get(getNowLine(1))).size() == 0) {
                getNowMap().put(getNowLine(1), text);
            } else {
                Map<String, Object> mm = new HashMap<>();
                mm.put(TEXT, text);
                ((List) getNowMap().get(getNowLine(1))).add(mm);
            }
        }
        return this;
    }

    @Override
    public Xml2JsonSonApi xmlEnd(String name) {
        if (getNowLine(1).equals(name)) {//除去最后一个列表
            if (mapList.size() > 1) {
                ((List) mapList.get(mapList.size() - 2).get(getNowLine(2))).add(getNowMap());
                mapList.remove(mapList.size() - 1);
                line.remove(line.size() - 1);
            }
        }
        return this;
    }

    @Override
    public String xmlClose() {
        Map<String, Object> mm = getNowMap();
        map = null;
        mapList = null;
        line = null;
        LogUtil.i(false, Xml2JsonSonApi.class, gson.toJson(mm));
        return gson.toJson(mm);
    }

    private Map<String, Object> getNowMap() {
        if (mapList.size() <= 0) {
            map = new HashMap<>();
            mapList.add(map);
            map = null;
        }
        return mapList.get(mapList.size() - 1);
    }

    private String getNowLine(int i) {
        if (line.size() >= i && i > 0)
            return line.get(line.size() - i);
        return null;
    }

}
