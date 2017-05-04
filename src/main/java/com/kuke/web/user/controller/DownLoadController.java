package com.kuke.web.user.controller;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kuke.web.core.entity.DocAndType;

@Controller
@RequestMapping(value= "down")
public class DownLoadController {
	private static final String templatePath = "/doc/";
	private List<DocAndType> docAndTypeList;
	private String downloadFileName;
	private DocAndType word;
	private DocAndType docAndType;

	String docid;
	String documentHisId;
	String templateDocName;
	String fileName;
	String fileNameTemp;

	String swfFilename;
	String swfFilePath;
	private InputStream in;

	@SuppressWarnings("null")
	public InputStream getInputStream() {
		HttpServletResponse response = null;
		HttpServletRequest request = null;
		String fileName = "";
		String filePath = "";
		try {
			if (this.templateDocName != null) {
				fileName = this.templateDocName;
				filePath = "/doc/";
				filePath = filePath + fileName;
			}
			this.fileNameTemp = fileName;
			this.setSwfFilename(filePath);
			filePath = request.getServletContext().getRealPath("/") + filePath;
			this.setSwfFilePath(filePath);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.in;

	}
	@RequestMapping(value="download")
	public String downloadfile() {
		try {
			this.in = getInputStream();
			System.out.println("############fan#############"
					+ this.getSwfFilePath());
			System.out.println("############fan#############"
					+ this.getSwfFilename());
			if (new File(this.getSwfFilePath()).exists()
					&& this.templateDocName == null) {
			}
			if ((this.in != null) && (!"".equals(this.in))) {
			}
		} catch (Exception e) {
			System.out.println("=========出异常========");
		}
		return null;
	}

	public List<DocAndType> getDocAndTypeList() {
		return docAndTypeList;
	}

	public void setDocAndTypeList(List<DocAndType> docAndTypeList) {
		this.docAndTypeList = docAndTypeList;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public DocAndType getWord() {
		return word;
	}

	public void setWord(DocAndType word) {
		this.word = word;
	}

	public DocAndType getDocAndType() {
		return docAndType;
	}

	public void setDocAndType(DocAndType docAndType) {
		this.docAndType = docAndType;
	}

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getDocumentHisId() {
		return documentHisId;
	}

	public void setDocumentHisId(String documentHisId) {
		this.documentHisId = documentHisId;
	}

	public String getTemplateDocName() {
		return templateDocName;
	}

	public void setTemplateDocName(String templateDocName) {
		this.templateDocName = templateDocName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNameTemp() {
		return fileNameTemp;
	}

	public void setFileNameTemp(String fileNameTemp) {
		this.fileNameTemp = fileNameTemp;
	}

	public String getSwfFilename() {
		return swfFilename;
	}

	public void setSwfFilename(String swfFilename) {
		int pointAt = swfFilename.lastIndexOf(".");
		String name = swfFilename.substring(0, pointAt);
		this.swfFilename = name + ".swf";
	}

	public String getSwfFilePath() {
		return swfFilePath;
	}

	public void setSwfFilePath(String swfFilePath) {
		int pointAt = swfFilePath.lastIndexOf(".");
		String name = swfFilePath.substring(0, pointAt);
		this.swfFilePath = name + ".swf";
	}

	public static String getTemplatepath() {
		return templatePath;
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

}
