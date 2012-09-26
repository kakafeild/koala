/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client;

public interface FileBasedMiniAppPresenter extends Presenter {

  void bind();

  void onClose();

  void onSave();

  void onContentUpdate();

  void unbind();
}
