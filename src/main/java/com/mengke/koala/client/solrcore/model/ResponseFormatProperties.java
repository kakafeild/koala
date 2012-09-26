package com.mengke.koala.client.solrcore.model;

import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import static com.google.gwt.editor.client.Editor.Path;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-12
 */
public interface ResponseFormatProperties extends PropertyAccess<ResponseFormat> {

	@Path("id")
	ModelKeyProvider<ResponseFormat> key();

	@Path("name")
	LabelProvider<ResponseFormat> nameLabel();
}
