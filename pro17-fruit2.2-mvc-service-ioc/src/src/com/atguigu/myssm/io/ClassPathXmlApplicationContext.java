package src.com.atguigu.myssm.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory{
    private Map<String,Object> beanMap=new HashMap<>();

    public ClassPathXmlApplicationContext() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder() ;
            //3.创建Document对象
            Document document = documentBuilder.parse(inputStream);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for(int i = 0 ; i<beanNodeList.getLength() ; i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element)beanNode ;
                    String beanId =  beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class<?> beanClass = Class.forName(className);
                    //创建bean实例
                    Object beanObj = beanClass.newInstance() ;
                    //将bean实例对象保存到map容器中
                    beanMap.put(beanId , beanObj) ;
                    //到此位置，bean和bean直接的依赖关系没有设置
                }
            }
            //5.组装bean之间的依赖关系
            for(int i = 0 ; i<beanNodeList.getLength() ; i++){
                Node beanNode = beanNodeList.item(i);
                if(beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element beanElement = (Element) beanNode;
                    String beanId = beanElement.getAttribute("id");
                    NodeList beanChildNodeList = beanElement.getChildNodes();
                    for (int j = 0; j < beanChildNodeList.getLength() ; j++) {
                        Node beanChildNode = beanChildNodeList.item(j);
                        if(beanChildNode.getNodeType()==Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())){
                            Element propertyElement = (Element) beanChildNode;
                            String propertyName = propertyElement.getAttribute("name");
                            String propertyRef = propertyElement.getAttribute("ref");
                            //1) 找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            //2) 将refObj设置到当前bean对应的实例的property属性上去
                            Object beanObj = beanMap.get(beanId);
                            Class beanClazz = beanObj.getClass();
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj,refObj);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
