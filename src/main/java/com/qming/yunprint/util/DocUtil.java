package com.qming.yunprint.util;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.doc.DocClient;
import com.baidubce.services.doc.model.CreateDocumentResponse;
import com.baidubce.services.doc.model.GetDocumentResponse;
import com.baidubce.services.doc.model.ReadDocumentResponse;
import com.qming.yunprint.model.FileEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-04-25-20:22
 */
@Component
@Scope("prototype")
public class DocUtil {
	Logger logger = LoggerFactory.getLogger(PricingUtil.class);

	private static final String ACCESS_KEY_ID = "dbbb0f3e86e64d7687798fb8ecba8343";
	private static final String SECRET_ACCESS_KEY = "";
	private static DocClient client;

	public DocUtil() {
		BceClientConfiguration config = new BceClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
		client = new DocClient(config);
	}


	/**
	 *
	 * @param file
	 * @param title 文档标题
	 * @param format 文档格式。有效值：doc, docx, ppt, pptx, xls, xlsx, vsd, pot, pps, rtf, wps, et, dps, pdf, txt, epub
	 */
	public  String createDocument(File file, String title, String format) {
		logger.debug(title);
		logger.debug(format);
		CreateDocumentResponse resp = client.createDocument(file, title, format);
		return resp.getDocumentId();
	}

	public FileEntity getDocument(String documentId) {
		GetDocumentResponse resp = client.getDocument(documentId);
		if (resp.getStatus().equals("PUBLISHED")) {
			FileEntity fileEntity = new FileEntity();
			fileEntity.setDocumentId(documentId);
			fileEntity.setCoverUrl(resp.getPublishInfo().getCoverUrl());
			fileEntity.setPages(resp.getPublishInfo().getPageCount());
			return fileEntity;
		}else {
			return null;
		}

	}
	public String getReaderToken(String documentId) {
		ReadDocumentResponse response = client.readDocument(documentId);
		return response.getToken();
	}
	public  void deleteDocument(DocClient client, String documentId) {
		client.deleteDocument(documentId);
	}
	public  boolean ispublished(String documentId){
		GetDocumentResponse resp = client.getDocument(documentId);
		return "PUBLISHED".equals(resp.getStatus());
	}
	public  boolean waitToPublished(String documentId) {
		while (!ispublished(documentId)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}
