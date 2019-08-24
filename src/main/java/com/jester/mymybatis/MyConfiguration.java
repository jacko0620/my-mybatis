package com.jester.mymybatis;

import com.google.common.collect.Lists;
import com.jester.mymybatis.config.Function;
import com.jester.mymybatis.config.MapperBean;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * @author yuxinzh
 * @create 2019/8/16
 */
public class MyConfiguration extends BaseFile {

    private ClassLoader loader = ClassLoader.getSystemClassLoader();

    /**
     * 读xml
     * @return
     */
    public Connection build(){
        try {
            BufferedInputStream is = new BufferedInputStream(loader.getResourceAsStream("config.xml"));
            SAXReader reader = new SAXReader();
            Document document = reader.read(is);
            Element element = document.getRootElement();
            return this.handleDataSource(element);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理获取到的xml文件内容，链接数据库
     * @param element
     * @return
     */
    private Connection handleDataSource(Element element) {
        if (!"database".equals(element.getName())) {
            logger.error("file error:{}",element.getName());
            throw new RuntimeException("file error");
        }

        Element property;
        String name;
        String value;
        String driverClassName = "";
        String url = "";
        String username = "";
        String password = "";


        for (Object item : element.elements("property")) {
            property = (Element) item;
            name = property.attributeValue("name");
            if (StringUtils.isBlank(name)) {
                throw new RuntimeException("属性名称为空");
            }
            value = property.hasContent()?property.getText():property.attributeValue("value");;
            if (StringUtils.isBlank(value)) {
                throw new RuntimeException("属性值为空");
            }

            switch (name) {
                case "driverClassName":
                    driverClassName = value;
                    break;
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                default:
                    logger.error("属性名称错误：{}",name);
                    throw new RuntimeException("属性名称错误");
            }
        }

        try {
            Class.forName(driverClassName);
            Connection connection = DriverManager.getConnection(url,username,password);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * todo 读sqlmapper
     * @param path
     * @return
     */
    public MapperBean readMapper(String path) {
        try {
            MapperBean mapperBean = new MapperBean();

            InputStream stream = loader.getResourceAsStream(path);
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            Element root = document.getRootElement();

            // <mapper namespace="com.jester.ssm.dao.IUserDao">
            mapperBean.setInterfaceName(root.attributeValue("namespace").trim());
            List<Function> list = Lists.newArrayList();
            Iterator<Element> it = root.elementIterator();
            Function function;
            Element element;
            String sqlType,functionName,sql,resultType;
            while (it.hasNext()) {
                function = new Function();
                element = it.next();
                sqlType = element.getName().trim();
                functionName = element.attributeValue("id").trim();
                sql = element.getTextTrim();
            }

            return mapperBean;
        } catch (DocumentException e) {
            logger.error("readMapper fail ",e);
            throw new RuntimeException(e);
        }

    }

}
