/**
 * <p>
 * 描述：
 * </p>

 * @package ：com.andy.demo.attach.client.factory<br>
 * @author ：wanglongjie<br>
 */
package com.changhongit.attach.ws.client.factory;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.changhongit.attach.ws.client.AttachWS;

/**
 * <p>
 * 描述：获取附件上传工厂类
 * </p>
 * 
 * @author wanglongjie<br>
 * @version v1.0 2017年3月6日下午1:49:59
 */
public class AttachWebServiceClientFactory {
	/**
	 * 与服务器端连接超时时间 15s
	 */
	private static final int CXF_CLIENT_CONNECT_TIMEOUT = 15 * 1000;
	/**
	 * 接收服务端响应超时时间 200s
	 */
	private static final int CXF_CLIENT_RECEIVE_TIMEOUT = 200 * 1000;

	/**
	 * 
	 * <p>
	 * 描述：获取附件上传接口
	 * </p>
	 * 
	 * @Date 2017年3月6日下午1:55:28 <br>
	 * @param wsdl
	 *            webservice 地址
	 * @return 附件上传接口
	 */
	public static AttachWS createClient(String wsdl) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.create(AttachWS.class);

		// 开启MTOM，开启后才能使用二进制流
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("mtom-enabled", Boolean.TRUE);
		factory.setProperties(props);

		// 设置 webservice地址
		factory.setAddress(wsdl);

		// 调用业务
		AttachWS attachWS = (AttachWS) factory.create();

		configTimeout(attachWS);
		return attachWS;
	}

	/**
	 * 
	 * <p>
	 * 描述：配置 客户端与服务器端之间通讯的超时时间
	 * </p>
	 * 
	 * @Date 2017年3月15日下午12:43:52 <br>
	 * @param service
	 */
	private static void configTimeout(Object service) {
		Client client = ClientProxy.getClient(service);
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();

		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(CXF_CLIENT_CONNECT_TIMEOUT);
		policy.setAllowChunking(false);
		policy.setReceiveTimeout(CXF_CLIENT_RECEIVE_TIMEOUT);

		httpConduit.setClient(policy);
	}
}
