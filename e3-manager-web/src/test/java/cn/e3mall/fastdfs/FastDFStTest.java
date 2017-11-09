package cn.e3mall.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastDFStTest {

	/**
	 * 使用客户端，测试fastDFS的文件上传
	 * 
	 * 1.通过读取配置文件，把本地图片上传到Linux上的fastDFS服务器中
	 * 并返回访问路径
	 * @throws Exception 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * 
	 */
	
	@Test
	public void run1() throws Exception {
		
		//指定上传图片的绝对路径
		String picPath = "C:\\Users\\Administrator\\Desktop\\周口人才市场.jpg";
		
		//图片存储服务器的地址，使用client.conf
		String conf = "D:\\Work\\java\\Git\\GitRepository\\e3\\e3mall\\e3-manager-web\\src\\main\\resources\\client.conf";
		
		//加载并连接图片服务器
		ClientGlobal.init(conf);
		//创建图片服务器的triker
		TrackerClient tc = new TrackerClient();
		
		//从客户端获取trieker连接
		TrackerServer ts = tc.getConnection();
		
		//创建storage的客户端，存储图片
		StorageServer ss = null;
		
		StorageClient sc = new StorageClient(ts, ss);
		String[] files = sc.upload_file(picPath, "jpg", null);
		
		//打印路径
		for (String str : files) {
			System.out.println(str);
		}
		
	}
	
}
