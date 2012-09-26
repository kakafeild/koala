package com.mengke.koala.client.solrcore.model;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-12
 */
public class ResponseFormat {

	private String id;

	private String name;

	public ResponseFormat() {
	}

	public ResponseFormat(String id) {
		this.id = id;
	}

	public ResponseFormat(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
