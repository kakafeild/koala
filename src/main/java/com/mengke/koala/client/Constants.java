package com.mengke.koala.client;

import com.mengke.koala.client.solrcore.model.DataImportCommand;
import com.mengke.koala.client.solrcore.model.ResponseFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-12
 */
public class Constants {

	public static List<ResponseFormat> getResponseFormats() {
		List<ResponseFormat> formats = new ArrayList<ResponseFormat>();
		formats.add(new ResponseFormat("xml", "xml"));
		formats.add(new ResponseFormat("json", "json"));
		formats.add(new ResponseFormat("python", "python"));
		formats.add(new ResponseFormat("ruby", "ruby"));
		formats.add(new ResponseFormat("php", "php"));
		formats.add(new ResponseFormat("csv", "csv"));
		return formats;
	}

	public static List<DataImportCommand> getDataImportCommands() {
		List<DataImportCommand> commands = new ArrayList<DataImportCommand>();
		commands.add(new DataImportCommand("full-import", "Full Import"));
		commands.add(new DataImportCommand("delta-import", "Delta Import"));
		return commands;
	}
}
