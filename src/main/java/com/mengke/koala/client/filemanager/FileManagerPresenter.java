/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.filemanager;

import com.mengke.koala.client.Presenter;
import com.mengke.koala.client.persistence.FileModel;
import com.mengke.koala.client.persistence.FileModel.FileType;

public interface FileManagerPresenter extends Presenter {

  boolean isEnableCreate();

  boolean isEnableDelete();

  boolean isEnableEditName();

  boolean isEnableOpen();

  void onCollapse();

  void onCreate(FileType fileType);

  void onDelete();

  void onEditFileNameComplete(boolean isSaved);

  void onEditName();

  void onExpand();

  void onOpen();

  void onSelect(FileModel fileModel);

}