package gz.itcast.a_dom4j_write;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

/**
 *  课堂练习： 
 * 1.使用dom4j的api来生成以下的xml文件
<Students>
<Student id="1">
	<name>张三</name>
	<gender>男</gender>
	<grade>计算机1班</grade>
	<address>广州天河</address>
</Student>
<Student id="2">
	<name>李四</name>
	<gender>女</gender>
	<grade>计算机2班</grade>
	<address>广州越秀</address>
</Student>
</Students>

2.修改id为2的学生的姓名，改为“王丽”

3.删除id为2的学生
 * @author APPle
 *
 */
public class Demo4 {
	/**
	 * 1.生成指定xml文档
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception{
		Document doc = DocumentHelper.createDocument();
		
		Element rootElem = doc.addElement("Students");
		Element elem1 = rootElem.addElement("Student");
		elem1.addAttribute("id", "1");
		Element name1 = elem1.addElement("name");
		name1.addText("张三");
		Element gender1 = elem1.addElement("gender");
		gender1.addText("男");
		Element grade1 = elem1.addElement("grade");
		grade1.addText("计算机1班");
		Element address1 = elem1.addElement("address");
		address1.addText("广州天河");
		
		Element elem2 = rootElem.addElement("Student");
		elem2.addAttribute("id", "2");
		Element name2 = elem2.addElement("name");
		name2.addText("李四");
		Element gender2 = elem2.addElement("gender");
		gender2.addText("女");
		Element grade2 = elem2.addElement("grade");
		grade2.addText("计算机2班");
		Element address2 = elem2.addElement("address");
		address2.addText("广州越秀");
		
		
		FileOutputStream out = new FileOutputStream("e:/contact.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 2.修改id为2的学生姓名
	 * @throws Exception
	 */
	@Test
	public void test2() throws Exception{
		Document doc = new SAXReader().read(new File("e:/contact.xml"));
		Iterator<Element> it = doc.getRootElement().elementIterator("Student");
		while(it.hasNext()){
			Element stuElem = it.next();
			//1.2 查询id为id的学生标签
			if(stuElem.attributeValue("id").equals("2")){
				stuElem.element("name").setText("王丽");
				break;
			}
		}
		FileOutputStream out = new FileOutputStream("e:/contact.xml");
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		writer.write(doc);
		writer.close();
	}
	
	/**
	 * 3.删除id为2的学生
	 * @throws Exception
	 */
	@Test
	public void test3() throws Exception{
		//1.查询到id为2的学生
		Document doc = new SAXReader().read(new File("e:/contact.xml"));
		//1.1 找到所有的Student标签
		Iterator<Element> it = doc.getRootElement().elementIterator("Student");
		while(it.hasNext()){
			Element stuElem = it.next();
			//1.2 查询id为id的学生标签
			if(stuElem.attributeValue("id").equals("2")){
				//1.3 删除该学生标签
				stuElem.detach();
				break;
			}
		}
	
		
		//3.1 输出位置
		FileOutputStream out = new FileOutputStream("e:/contact.xml");
		//3.2 指定格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 设置编码
		format.setEncoding("utf-8");
		XMLWriter writer = new XMLWriter(out,format);
		//3.3 写出内容
		writer.write(doc);
		//3.4关闭资源
		writer.close();
	}
}
