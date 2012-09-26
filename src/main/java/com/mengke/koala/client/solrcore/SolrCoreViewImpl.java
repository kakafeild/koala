package com.mengke.koala.client.solrcore;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.mengke.koala.client.filemanager.images.Images;
import com.mengke.koala.client.solrcore.ui.SolrManagerPanel;
import com.mengke.koala.client.solrcore.ui.QueryPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.HideEvent;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-8-29
 */
public class SolrCoreViewImpl implements SolrCoreView {

	private Window window;
	private TabPanel tabPanel;
	private SolrCorePresenter solrCorePresenter;

	public SolrCoreViewImpl(SolrCorePresenter solrCorePresenter) {
		this.solrCorePresenter = solrCorePresenter;
	}

	@Override
	public void onHide(HideEvent hideEvent) {
		// TODO add close logic soon
	}

	@Override
	public Widget asWidget() {
		return getWindow();
	}

	private Window getWindow() {
		if (window == null) {
			window = new Window();
			window.setHeadingText(getTitle());
			window.getHeader().setIcon(Images.getImageResources().folder());
			window.setMinimizable(true);
			window.setMaximizable(true);
			window.setPixelSize(1200, 800);
			window.setOnEsc(false);
			window.addHideHandler(this);
			window.setWidget(getTablePanel());
		}
		return window;
	}

	private String getTitle() {
		return solrCorePresenter.getSolrCoreName();
	}

	public TabPanel getTablePanel() {
		if (tabPanel == null) {
			tabPanel = new TabPanel();
			tabPanel.setBorders(false);

			tabPanel.add(getStatisticsPanel(), new TabItemConfig("Statistics"));
			tabPanel.add(getQueryPanel(), new TabItemConfig("Query"));
			tabPanel.add(getSchemaPanel(), new TabItemConfig("Schema"));
			tabPanel.add(getConfigPanel(), new TabItemConfig("Config"));
			tabPanel.add(getDataImportPanel(), new TabItemConfig("Data Import"));
			tabPanel.add(getReplicationPanel(), new TabItemConfig("Replication"));
		}
		return tabPanel;
	}

	private IsWidget getReplicationPanel() {
		return new VerticalLayoutContainer();  //To change body of created methods use File | Settings | File Templates.
	}

	private IsWidget getDataImportPanel() {
		return new SolrManagerPanel(solrCorePresenter).asWidget();
	}

	private IsWidget getConfigPanel() {
		return new VerticalLayoutContainer();  //To change body of created methods use File | Settings | File Templates.
	}

	private IsWidget getSchemaPanel() {
		return new VerticalLayoutContainer();  //To change body of created methods use File | Settings | File Templates.
	}

	private IsWidget getStatisticsPanel() {
		return new VerticalLayoutContainer();
	}

	public IsWidget getQueryPanel() {
		return new QueryPanel(solrCorePresenter).asWidget();
	}
}
