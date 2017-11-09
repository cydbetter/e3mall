package cn.e3mall.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.utils.FastDFSClient;
import cn.e3mall.utils.PictureResult;

@Controller
public class PicUploadController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	/**
	 * 请求路径:/pic/upload
	 * 请求参数：uploadFile
	 * 返回参数:pictureResult
	 */
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult uploadPicture(MultipartFile uploadFile) {
		
		PictureResult result = null;
		String url = "";
		
		try {
		//获取文件名，获取文件扩展名
		String filename = uploadFile.getOriginalFilename();
		String extName = filename.substring(filename.lastIndexOf(".")+1);
		//创建图片服务器，上传图片
		FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
			
		//上传图片
		String fileUrl = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
		
		//拼接全部URL
		url = IMAGE_SERVER_URL + fileUrl;
		System.out.println(url);
		
		//返回上传结果 0 成功 1失败 
		result = new PictureResult(0, url,"图片上传成功");
		return result;
		} catch (Exception e) {

			result = new PictureResult(1, url,"图片上传失败");
			return result;
		}
		
	}
	
}
