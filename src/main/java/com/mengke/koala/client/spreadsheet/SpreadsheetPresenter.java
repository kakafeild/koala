/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.spreadsheet;

import com.mengke.koala.client.FileBasedMiniAppPresenter;

public interface SpreadsheetPresenter extends FileBasedMiniAppPresenter {

  void onCellValueChange(int rowIndex, int columnIndex, String value);

  void onColumnCountChange(int value);

  void onColumnMove();

  void onDeleteColumn();

  void onDeleteRow();

  void onDetailValueChange(String value);

  void onDragDrop();

  void onInsertAbove();

  void onInsertBelow();

  void onInsertLeft();

  void onInsertRight();

  void onOpenChart();

  void onRowCountChange(int value);

}