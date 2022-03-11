## 内部类实例化
外部类写一个方法，方法返回内部类
``` java
public Animal getAnimal(String arg) {
    Animal animal = null;
    if ("cat".equals(arg)) {
        animal = new Cat();
    } else if ("dog".equals(arg)) {
        animal = new Dog();
    } else {
        throw new RuntimeException("暂时没有该小动物！");
    }
    return animal;
}
```
外部类实例化创建内部类
``` java
public static void main(String[] args) {
    SimpleFactory simpleFactory = new SimpleFactory();
    Factory factory = simpleFactory.new Factory();
}
```
静态内部类直接通过外部类实例化
``` java
class Solution {
    static class Test {}
    public static void main(String[] args) {
        Solution.Test test = new Solution.Test();    
    }
}
```

## 读取配置文件
``` java
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;
 
public class XMLUtil {
    //该方法用于从XML配置文件中提取图表类型，并返回类型名
	public static String getChartType() {
		try {
			//创建文档对象
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;							
			doc = builder.parse(new File("config.xml")); 
		
			//获取包含图表类型的文本节点
			NodeList nl = doc.getElementsByTagName("chartType");
            Node classNode = nl.item(0).getFirstChild();
            String chartType = classNode.getNodeValue().trim();
            
            //通过类名生成实例对象并将其返回
            Class c = Class.forName(charType);
            Object obj = c.newInstance();
            return obj;
        }   
       	catch(Exception e) {
           	e.printStackTrace();
        	return null;
        }
    }
}
```