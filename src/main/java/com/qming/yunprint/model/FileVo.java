package com.qming.yunprint.model;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-05-06-15:41
 */
public class FileVo {
	private String name;
	private String duplex;
	private String color;
	private int page;
	private int copy;
	private double price;
	private String documentId;
	private String cover;

	@Override
	public String toString() {
		return "FileVo{" + "name='" + name + '\'' + ", duplex='" + duplex + '\'' + ", color='" + color + '\'' + ", page=" + page + ", copy=" + copy + ", price=" + price + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuplex() {
		return duplex;
	}

	public void setDuplex(String duplex) {
		this.duplex = duplex;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCopy() {
		return copy;
	}

	public void setCopy(int copy) {
		this.copy = copy;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}
}
