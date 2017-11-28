package gz.itcast.a_dom4j_write;

import java.io.File;
import java.io.FileOutputStream;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 讨论写出内容到xml文档的细节
 * 
 * @author APPle
 *
 */
public class Demo2 {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Document doc = new SAXReader().read(new File("./src/contact.xml"));
		// 指定文件输出的位置
		FileOutputStream out = new FileOutputStream("e:/contact.xml");
		
		/**
		 * 1.指定写出的格式
		 */
		OutputFormat format = OutputFormat.createCompactFormat(); //紧凑的格式.去除空格换行.项目上线的时候
		//OutputFormat format = OutputFormat.createPrettyPrint(); //漂亮的格式.有空格和换行.开发调试的时候
		/**
		 * 2.指定生成的xml文档的编码
		 *    同时影响了xml文档保存时的编码  和  xml文档声明的encoding的编码（xml解析时的编码）
		 *    结论： 使用该方法生成的xml文档避免中文乱码问题。
		 */
		format.setEncoding("utf-8");
		
		
		//1.创建写出对象
		XMLWriter writer = new XMLWriter(out,format);
		
		//2.写出对象
		writer.write(doc);
		//3.关闭流
		writer.close();
	}
}
