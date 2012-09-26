package com.mengke.koala.client.solrcore.model;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import static com.google.gwt.editor.client.Editor.Path;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-20
 */
public interface DataImportCommandProperties extends PropertyAccess<DataImportCommand> {

	@Path("name")
	ModelKeyProvider<DataImportCommand> key();

	@Path("description")
	LabelProvider<DataImportCommand> descriptionLabel();

	ValueProvider<DataImportCommand, String> name();

	ValueProvider<DataImportCommand, String> description();
}
