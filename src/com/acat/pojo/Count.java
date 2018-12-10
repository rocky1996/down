package com.acat.pojo;

import java.io.Serializable;

public class Count implements Serializable{
	private int id;
	private int count;
	private int file_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	@Override
	public String toString() {
		return "Count [id=" + id + ", count=" + count + ", file_id=" + file_id
				+ "]";
	}
	
	
}
