/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.spreadsheet;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mengke.koala.client.persistence.FileModel;

public interface SpreadsheetChartPresenter {

  void configure(FileModel fileModel, Worksheet worksheet);

  void go(HasWidgets hasWidgets);

  void updateTitle();

}
