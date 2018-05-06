package com.qming.yunprint.model;

import java.util.List;

/**
 * @yunprint
 * @Description:
 * @Author: qming_c
 * @Date: 2018-05-06-15:38
 */
public class OrderVo {
	private String uuid;
	private String phone;
	private String address;
	private double price;
	private String time;
	private int count;
	private List<FileVo> files;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<FileVo> getFiles() {
		return files;
	}

	public void setFiles(List<FileVo> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "OrderVo{" + "uuid='" + uuid + '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", price='" + price + '\'' + ", files=" + files + '}';
	}
}
