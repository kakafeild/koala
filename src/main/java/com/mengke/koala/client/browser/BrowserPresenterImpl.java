/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.browser;

import com.mengke.koala.client.DesktopBus;
import com.mengke.koala.client.FileBasedMiniAppPresenterImpl;
import com.mengke.koala.client.FileBasedMiniAppView;
import com.mengke.koala.client.persistence.FileModel;
import com.mengke.koala.client.persistence.FileSystem;

public class BrowserPresenterImpl extends FileBasedMiniAppPresenterImpl implements BrowserPresenter {

  public BrowserPresenterImpl(DesktopBus desktopBus, FileSystem fileSystem, FileModel fileModel) {
    super(desktopBus, fileSystem, fileModel);
  }

  @Override
  protected FileBasedMiniAppView createFileBasedMiniAppView() {
    return new BrowserViewImpl(this);
  }

  @Override
  protected String getDisplayedContent(FileModel fileModel) {
    return fileModel.getName();
  }

  @Override
  protected String getTitle() {
    return "Browser - " + super.getTitle();
  }

  protected boolean isModified() {
    return false;
  }

}
