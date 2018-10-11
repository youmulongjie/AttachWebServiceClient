package com.changhongit.attach.ws.bean;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.ws.soap.MTOM;

/**
 * <p>
 * Java class for cxfFileWrapper complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="cxfFileWrapper">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cxfFileWrapper", propOrder = { "file", "fileName" })
@MTOM()
public class CxfFileWrapper {

	@XmlMimeType("application/octet-stream")
	protected DataHandler file;
	protected String fileName;

	/**
	 * Gets the value of the file property.
	 * 
	 * @return possible object is {@link DataHandler }
	 * 
	 */
	public DataHandler getFile() {
		return file;
	}

	/**
	 * Sets the value of the file property.
	 * 
	 * @param value
	 *            allowed object is {@link DataHandler }
	 * 
	 */
	public void setFile(DataHandler value) {
		this.file = value;
	}

	/**
	 * Gets the value of the fileName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the value of the fileName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileName(String value) {
		this.fileName = value;
	}

}
