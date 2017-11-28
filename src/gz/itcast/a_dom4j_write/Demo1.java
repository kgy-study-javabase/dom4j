package gz.itcast.a_dom4j_write;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 第一个写出内容到xml文档
 * 
 * @author APPle
 *
 */
public class Demo1 {

	public static void main(String[] args) throws DocumentException, IOException {
		// 一、读取或创建一个Document对象
		// 读取day07项目的xm文件
		Document doc = new SAXReader().read(new File("./src/contact.xml"));

		// 二、修改Document对象内容
		Element rootElem = doc.getRootElement();
		rootElem.setName("contactlisttest");
		
		// 三、把修改后的Document对象写出到xml文档中
		// 指定文件输出的位置
		FileOutputStream out = new FileOutputStream("e:/contact.xml");
		// 1.创建写出对象
		XMLWriter writer = new XMLWriter(out);

		// 2.写出对象
		writer.write(doc);
		// 3.关闭流
		writer.close();
	}

}
