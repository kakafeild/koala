package com.mengke.koala.client.solrcore.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-8-30
 */
public class StatisticsPanel extends VerticalLayoutContainer {

	interface SolrCoreTabPanelUiBinder extends UiBinder<Widget, StatisticsPanel> {
	}

	private static SolrCoreTabPanelUiBinder uiBinder = GWT.create(SolrCoreTabPanelUiBinder.class);



	@Override
	public Widget asWidget() {
		// TODO
		return null;
	}
}
