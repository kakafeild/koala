package com.mengke.koala.client.solrcore.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-20
 */
public class DataImportCommand implements Serializable {

	private String name;

	private String description;

	public DataImportCommand(String name) {
		this.name = name;
	}

	public DataImportCommand(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public DataImportCommand() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
