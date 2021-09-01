package com.sima.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.sima.springframework.beans.BeanException;
import com.sima.springframework.beans.PropertyValue;
import com.sima.springframework.beans.factory.config.BeanDefinition;
import com.sima.springframework.beans.factory.config.BeanReference;
import com.sima.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.sima.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.sima.springframework.core.io.Resource;
import com.sima.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try {
            doLoadBeanDefinitions(resource.getInputStream());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeanException {
        for (Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeanException {
        for (String loc : locations){
            loadBeanDefinitions(loc);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        loadBeanDefinitions(getResourceLoader().getResource(location));
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNotes = root.getChildNodes();
        for (int i = 0; i < childNotes.getLength(); i++) {
            if (!(childNotes.item(i) instanceof Element)) continue;
            if (! "bean".equals(childNotes.item(i).getNodeName())) continue;

            Element bean = (Element) childNotes.item(i);
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            String scope = bean.getAttribute("scope");
            String initMethodName = bean.getAttribute("init-method");
            String destroyMethodName = bean.getAttribute("destroy-method");

            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethodName);
            beanDefinition.setDestroyMethodName(destroyMethodName);

            if (StrUtil.isNotEmpty(scope)){
                beanDefinition.setScope(scope);
            }

            // 读取属性并填充
            for (int j = 0; j <bean.getChildNodes().getLength(); j++){
                if (!(bean.getChildNodes().item(j) instanceof Element)) continue;
                if (! "property".equals(bean.getChildNodes().item(j).getNodeName())) continue;

                Element property = (Element) bean.getChildNodes().item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;
                PropertyValue pv = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addProperty(pv);
            }

            if (getRegistry().containsBeanDefinition(beanName)){
                throw new BeanException("Duplicate beanName[" + beanName + "] is not allown");
            }

            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

}
