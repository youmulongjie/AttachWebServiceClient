/**
 * <p>
 * 描述：
 * </p>

 * @package ：com.andy.demo.attach.test<br>
 * @author ：wanglongjie<br>
 */
package com.andy.demo.attach.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import com.changhongit.attach.ws.bean.AttachBean;
import com.changhongit.attach.ws.bean.CxfFileWrapper;
import com.changhongit.attach.ws.client.AttachWS;
import com.changhongit.attach.ws.client.factory.AttachWebServiceClientFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * <p>
 * 描述：测试类
 * </p>
 * 
 * @author wanglongjie<br>
 * @version v1.0 2017年3月6日下午1:57:36
 */
public class AttachTest {
	// 本地 webservice
	String wsdl = "http://localhost:8080/AttachWebService/services/attachWS?wsdl";
	// ftp webservice
	String ftpwsdl = "http://localhost:8080/AttachWebService/services/attachFtpWS?wsdl";

	/**
	 * 
	 * <p>
	 * 描述：测试 上传到服务器本地WebService
	 * </p>
	 * 
	 * @throws Exception
	 * 
	 */
	// @Test
	public void testAttachWS() throws Exception {
		System.out.println("本地上传下载测试...........");
		AttachWS attachWS = AttachWebServiceClientFactory.createClient(wsdl);
		testQuery(attachWS);
		System.out.println("**************");
		
		long attachId = upload(attachWS);
		down(attachWS, attachId);
		delete(attachWS, attachId);
		System.out.println("********************************");
	}

	/**
	 * 
	 * <p>
	 * 描述：测试 上传到FTP WebService
	 * </p>
	 * 
	 * @throws Exception
	 * 
	 */
	// @Test
	public void testAttachFtpWS() throws Exception {
		System.out.println("ftp上传下载测试...........");

		AttachWS attachWS = AttachWebServiceClientFactory.createClient(ftpwsdl);
		testQuery(attachWS);
		System.out.println("**************");
		
		long attachId = upload(attachWS);
		down(attachWS, attachId);
		delete(attachWS, attachId);
		System.out.println("********************************");
	}

	/**
	 * 
	 * <p>
	 * 描述：测试查询所有上传文件
	 * </p>
	 * 
	 */
	public void testQuery(AttachWS attachWS) {
		query(attachWS);
	}

	// 测试上传
	@SuppressWarnings("resource")
	private static long upload(AttachWS attachWS) throws Exception {
		System.out.println("上传附件测试......");
		long a = System.currentTimeMillis();
		// d盘根目录下文件
		String name = "1.zip";
		System.out.println("上传文件名：" + name);
		CxfFileWrapper fileWrapper = new CxfFileWrapper();
		fileWrapper.setFileName(name);
		DataSource ds = new FileDataSource(new File("d:\\" + name));

		long size = new FileInputStream(new File("d:\\" + name)).available();
		System.out.println("共： " + size + "字节，" + size / 1024 / 1024 + "M");
		DataHandler dataHandler = new DataHandler(ds);
		fileWrapper.setFile(dataHandler);
		long attachId = attachWS.upload(fileWrapper);

		long b = System.currentTimeMillis();
		System.out.println("上传成功。返回附件ID号：" + attachId + "，上传 共耗时：" + (b - a)
				/ 1000 + "秒！");

		return attachId;
	}

	// 测试下载
	private static void down(AttachWS attachWS, long attachId) throws Exception {
		System.out.println("下载附件测试......");
		long a = System.currentTimeMillis();
		CxfFileWrapper cxfFileWrapper = attachWS.download(attachId);

		String name = cxfFileWrapper.getFileName();
		System.out.println("附件ID：" + attachId + "; 文件名：" + name);

		DataHandler dataHandler = cxfFileWrapper.getFile();
		DataSource ds = dataHandler.getDataSource();
		InputStream is = ds.getInputStream();

		// 下载文件保存到e盘
		OutputStream os = new FileOutputStream(new File("d:\\downloadTest-"
				+ name));
		int BUFFER_SIZE = 1024 * 100;
		byte[] by = new byte[BUFFER_SIZE];
		int len = 0;
		while ((len = is.read(by, 0, BUFFER_SIZE)) != -1) {
			os.write(by, 0, len);
		}

		os.flush();
		os.close();
		is.close();
		ds = null;

		long b = System.currentTimeMillis();
		System.out.println("下载 共耗时：" + (b - a) / 1000 + "秒！");
	}

	// 测试删除
	private static void delete(AttachWS attachWS, long attachId)
			throws Exception {
		System.out.println("删除附件测试......");
		long a = System.currentTimeMillis();
		System.out.println("附件ID：" + attachId);
		attachWS.delete(attachId);
		long b = System.currentTimeMillis();
		System.out.println("删除 共耗时：" + (b - a) / 1000 + "秒！");
	}

	private static void query(AttachWS attachWS) {
		String json = attachWS.queryAll();
		Gson gson = new Gson();
		List<AttachBean> list = gson.fromJson(json,
				new TypeToken<List<AttachBean>>() {
				}.getType());
		System.out.println("附件个数：" + list.size());
		for (AttachBean attachBean : list) {
			System.out.println(attachBean);
		}
	}
}
