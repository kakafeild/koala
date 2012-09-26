/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.spreadsheet;

import com.mengke.koala.client.FileBasedMiniAppView;

public interface SpreadsheetView extends FileBasedMiniAppView {

  int getSelectedColumn();

  int getSelectedRow();

  void refresh();

  void setValue(Worksheet worksheet);

  void updateDetails(String cellName, String cellValue);

  void updateGrid();

  void updateSelectedCells(String value);

}