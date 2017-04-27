package com.leo.core.impl.ab;

import android.util.Xml;

import com.leo.core.inter.api.IApi;
import com.leo.core.inter.api.IParseApi;
import com.leo.core.inter.api.IXmlPullApi;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * xml pull解析
 */
public abstract class AXmlPullImplApi<T extends IApi> implements IParseApi<T, InputStream, String, String>,
        IXmlPullApi<T, XmlPullParser, String, String> {

    @Override
    public String parse(InputStream in, String encoding) {
        try {
            //03.使用Pull解析(类似SAX)
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(in, encoding);
            //获取解析标签的类型
            int type = parser.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {//XML结束解析
                switch (type) {
                    case XmlPullParser.START_DOCUMENT://XML开始解析
                        xmlOpen();
                        break;
                    case XmlPullParser.START_TAG://XML开始标签
                        xmlStart(parser);
                        break;
                    case XmlPullParser.TEXT:
                        xmlText(parser.getText());
                        break;
                    case XmlPullParser.END_TAG://XML结束标签
                        xmlEnd(parser.getName());
                        break;
                }
                //细节：重新赋值，不然会造成死循环
                type = parser.next();
            }
            in.close();
            return xmlClose();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
