package com.mengke.koala.client.solrcore.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.xml.client.*;
import com.mengke.koala.client.Constants;
import com.mengke.koala.client.solrcore.SolrCorePresenter;
import com.mengke.koala.client.solrcore.model.DataImportCommand;
import com.mengke.koala.client.solrcore.model.DataImportCommandProperties;
import com.mengke.koala.client.solrcore.model.DataImportStatus;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-12
 */
public class SolrManagerPanel extends VerticalLayoutContainer {

	interface DataImportPanelUiBinder extends UiBinder<Widget, SolrManagerPanel> {
	}

	private static DataImportPanelUiBinder uiBinder = GWT.create(DataImportPanelUiBinder.class);

	public interface DataImportRenderer extends XTemplates {
		@XTemplate("<span>Requests: {requests},</span>" +
						"<span>&nbsp;Fetched: {fetched},</span>" +
						"<span>&nbsp;Skipped: {skipped},</span>" +
						"<span>&nbsp;Processed: {processed}</span>")
		public SafeHtml renderProcess(DataImportStatus status);

		@XTemplate("<strong>Indexing since {elapsedTime}</strong>")
		public SafeHtml renderTime(DataImportStatus status);

		@XTemplate("<strong>{dataImportStatus}</strong>")
		public SafeHtml renderResult(DataImportStatus status);
	}

	DataImportRenderer dataImportRenderer = GWT.create(DataImportRenderer.class);

	@UiField
	PortalLayoutContainer portal;

	private SolrCorePresenter solrCorePresenter;

	public SolrManagerPanel(SolrCorePresenter solrCorePresenter) {
		this.solrCorePresenter = solrCorePresenter;
	}

	@UiField(provided = true)
	ListStore<DataImportCommand> commandStore;
	@UiField(provided = true)
	LabelProvider<DataImportCommand> commandLabelProvider =
					GWT.<DataImportCommandProperties>create(DataImportCommandProperties.class).descriptionLabel();

	@UiField
	ComboBox<DataImportCommand> commandCombo;

	@UiField
	CheckBox verboseCheckBox;

	@UiField
	CheckBox cleanCheckBox;

	@UiField
	CheckBox commitCheckBox;

	@UiField
	CheckBox optimizeCheckBox;

	@UiField
	TextButton executeImportButton;

	@UiField
	SimpleContainer dataImportStatusContainer;

	@Override
	public Widget asWidget() {
		DataImportCommandProperties props = GWT.create(DataImportCommandProperties.class);
		commandStore = new ListStore<DataImportCommand>(props.key());
		commandStore.addAll(Constants.getDataImportCommands());
		Widget widget = uiBinder.createAndBindUi(this);

		portal.setColumnWidth(0, .40);
		portal.setColumnWidth(1, .30);
		portal.setColumnWidth(2, .30);

		return widget;
	}

	@UiHandler({"executeImportButton"})
	public void onButtonClick(SelectEvent event) {
		DataImportCommand command = commandCombo.getValue();
		String dataImportUrl = generateUrlForDataImport(command.getName());
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, dataImportUrl);
		builder.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					checkStatus();
				} else {
					// TODO add error messaging
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				// TODO add error messaging

			}
		});
		try {
			builder.send();
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	private void checkStatus() {
		String checkStatusUrl = generateUrlForStatus();
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, checkStatusUrl);
		builder.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (200 == response.getStatusCode()) {
					DataImportStatus status = parseMessage(response.getText());
					final HTML text = new HTML(dataImportRenderer.renderProcess(status));
					dataImportStatusContainer.setWidget(text);
				} else {
					// TODO add error messaging
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				// TODO add error messaging

			}
		});
		try {
			builder.send();
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	private DataImportStatus parseMessage(String messageXml) {
		DataImportStatus dataImportStatus = new DataImportStatus();
		try {
			// parse the XML document into a DOM
			Document messageDom = XMLParser.parse(messageXml);

			NodeList strNodes = messageDom.getElementsByTagName("str");
			for (int i = 0; i < strNodes.getLength(); i++) {
				Node node = strNodes.item(i);
				Element element = (Element) node;
				String attribute = element.getAttribute("name");
				if ("status".equals(attribute)) {
					Element parentElement = (Element) element.getParentNode();
					if ("response".equals(parentElement.getTagName())) {
						String status = element.getNodeValue();
						dataImportStatus.setStatus(status);
					}
				} else if ("Total Requests made to DataSource".equals(attribute)) {
					Node dataSources = element.getFirstChild();
					dataImportStatus.setRequests(Integer.parseInt(dataSources.getNodeValue()));
				} else if ("Total Rows Fetched".equals(attribute)) {
					Node fetched = element.getFirstChild();
					dataImportStatus.setFetched(Integer.parseInt(fetched.getNodeValue()));
				} else if ("Total Documents Skipped".equals(attribute)) {
					Node skipped = element.getFirstChild();
					dataImportStatus.setSkipped(Integer.parseInt(skipped.getNodeValue()));
				} else if ("Total Documents Processed".equals(attribute)) {
					Node processed = element.getFirstChild();
					dataImportStatus.setProcessed(Integer.parseInt(processed.getNodeValue()));
				} else if ("Time Elapsed".equals(attribute)) {
					Node elapsedTime = element.getFirstChild();
					dataImportStatus.setElapsedTime(elapsedTime.getNodeValue());
				} else if ("Time taken".equals(attribute)) {
					Node timeTaken = element.getFirstChild();
					dataImportStatus.setTimeTaken(timeTaken.getNodeValue());
				} else if ("".equals(attribute)) {
					Node result = element.getFirstChild();
					String strResult = result.getNodeValue();
					dataImportStatus.setDataImportStatus(strResult);
					if (strResult.startsWith("Indexing completed.")) {
						dataImportStatus.setSuccess(true);
					} else {
						dataImportStatus.setSuccess(false);
					}
				}
			}

		} catch (DOMException e) {
			e.printStackTrace();
		}
		return dataImportStatus;
	}

	private String generateUrlForStatus() {
		StringBuffer queryUrl = new StringBuffer();
		queryUrl.append(solrCorePresenter.getDataImportUrl());
		queryUrl.append("?command=status");
		return queryUrl.toString();
	}

	private String generateUrlForDataImport(String command) {
		StringBuffer queryUrl = new StringBuffer();
		queryUrl.append(solrCorePresenter.getDataImportUrl());
		queryUrl.append("?command=")
						.append(command);
		boolean verbose = verboseCheckBox.getValue();
		queryUrl.append("&debug=").append(verbose);
		boolean clean = cleanCheckBox.getValue();
		queryUrl.append("&clean=").append(clean);
		boolean commit = commitCheckBox.getValue();
		queryUrl.append("&commit=").append(commit);
		boolean optimize = optimizeCheckBox.getValue();
		queryUrl.append("&optimize=").append(optimize);
		return queryUrl.toString();
	}
}
