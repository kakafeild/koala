package com.mengke.koala.client.solrcore.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mengke.koala.client.Constants;
import com.mengke.koala.client.common.util.StringUtils;
import com.mengke.koala.client.solrcore.SolrCorePresenter;
import com.mengke.koala.client.solrcore.model.ResponseFormat;
import com.mengke.koala.client.solrcore.model.ResponseFormatProperties;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.box.MultiLinePromptMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

import static com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-12
 */
public class QueryPanel implements IsWidget {

	interface QueryPanelUiBinder extends UiBinder<Widget, QueryPanel> {
	}

	private static QueryPanelUiBinder uiBinder = GWT.create(QueryPanelUiBinder.class);

	private BorderLayoutContainer blc;

	private SolrCorePresenter solrCorePresenter;

	public QueryPanel(SolrCorePresenter solrCorePresenter) {
		this.solrCorePresenter = solrCorePresenter;
	}

	@UiField(provided = true)
	BorderLayoutData westData = new BorderLayoutData(350);
	@UiField(provided = true)
	MarginData centerData = new MarginData();

	ResponseFormatProperties formatProperties = GWT.create(ResponseFormatProperties.class);
	ListStore<ResponseFormat> store = new ListStore<ResponseFormat>(formatProperties.key());

	@UiField
	TextField requestHandlerField;

	@UiField
	TextArea queryField;

	@UiField
	TextArea filterQueryField;

	@UiField
	TextField sortField;

	@UiField
	TextField startField;

	@UiField
	TextField rowsField;

	@UiField
	TextField fieldLimitField;

	@UiField
	CheckBox debugCheckBox;

	@UiField
	Frame resultContainer;

	@UiField
	TextButton executeQueryButton;

	@UiField
	TextButton generateQueryButton;

	@Override
	public Widget asWidget() {
		if (blc == null) {
			store.addAll(Constants.getResponseFormats());
			blc = (BorderLayoutContainer) uiBinder.createAndBindUi(this);
		}
		resultContainer.getElement().setPropertyInt("frameBorder", 0);
		return blc;
	}

	@UiHandler({"executeQueryButton", "generateQueryButton"})
	public void onButtonClick(SelectEvent event) {
		if (event.getSource() == executeQueryButton) {
			String queryUrl = generateUrlForQuery() + "&wt=json&indent=on";
			resultContainer.setUrl(queryUrl);
		} else if (event.getSource() == generateQueryButton) {
			final MultiLinePromptMessageBox box = new MultiLinePromptMessageBox("Query Builder",
							"Please edit the query url as your wish");
			String queryUrl = generateUrlForQuery();
			box.getTextArea().setText(queryUrl);
			box.show();
		}
	}

	private String generateUrlForQuery() {
		StringBuffer queryUrl = new StringBuffer();
		queryUrl.append(solrCorePresenter.getQueryUrl());
		String queryText = queryField.getText();
		if (StringUtils.isNotBlank(queryText)) {
			queryUrl.append("?q=")
							.append(queryText);
		}
		String filterQueryText = filterQueryField.getText();
		if (StringUtils.isNotBlank(filterQueryText)) {
			queryUrl.append("&fq=")
							.append(filterQueryText);
		}
		String sortText = sortField.getText();
		if (StringUtils.isNotBlank(sortText)) {
			queryUrl.append("&sort=")
							.append(sortText);
		}
		String startText = startField.getText();
		if (StringUtils.isNotBlank(startText)) {
			queryUrl.append("&start=")
							.append(startText);
		}
		String rowsText = rowsField.getText();
		if (StringUtils.isNotBlank(rowsText)) {
			queryUrl.append("&rows=")
							.append(rowsText);
		}
		String fieldLimitText = fieldLimitField.getText();
		if (StringUtils.isNotBlank(fieldLimitText)) {
			queryUrl.append("&fl=")
							.append(fieldLimitText);
		}

		queryUrl.append("&debugQuery=")
		        .append(debugCheckBox.getValue());
		return queryUrl.toString();
	}
}
