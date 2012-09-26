package com.mengke.koala.client.solrcore.model;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-17
 */
public class Source {

	private static int nextId = 1;

	public enum FileType {
		XML, JSON
	}

	private Integer id;

	private FileType type;

	private String url;

	public Source(String url, FileType type) {
		this.id = nextId++;
		this.url = url;
		this.type = type;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Source getData() {
		return this;
	}
}
