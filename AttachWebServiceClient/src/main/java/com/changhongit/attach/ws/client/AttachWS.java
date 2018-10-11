package com.changhongit.attach.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.changhongit.attach.ws.bean.CxfFileWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "AttachWS", targetNamespace = "http://service.ws.attach.changhongit.com/")
public interface AttachWS {
	@WebMethod
	String queryAll();
	
	/**
	 * 
	 * @param attachId
	 * @return returns boolean
	 * @throws Exception
	 */
	@WebMethod
	public boolean delete(
			@WebParam(name = "attachId", targetNamespace = "http://service.ws.attach.changhongit.com/") long attachId)
			throws Exception;

	/**
	 * 
	 * @param attachId
	 * @return returns com.andy.demo.attach.service.CxfFileWrapper
	 * @throws Exception
	 */
	@WebMethod
	public CxfFileWrapper download(
			@WebParam(name = "attachId", targetNamespace = "http://service.ws.attach.changhongit.com/") long attachId)
			throws Exception;

	/**
	 * 
	 * @param file
	 * @return returns long
	 * @throws Exception
	 */
	@WebMethod
	public long upload(
			@WebParam(name = "file", targetNamespace = "http://service.ws.attach.changhongit.com/") CxfFileWrapper file)
			throws Exception;

}
