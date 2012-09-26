/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.mengke.koala.client.event.AddFileModelEvent.AddFileModelHandler;
import com.mengke.koala.client.persistence.FileModel;

public class AddFileModelEvent extends GwtEvent<AddFileModelHandler> {

  public interface AddFileModelHandler extends EventHandler {
    void onAddFileModel(AddFileModelEvent event);
  }

  public static Type<AddFileModelHandler> TYPE = new Type<AddFileModelHandler>();
  private FileModel fileModel;

  public AddFileModelEvent(FileModel fileModel) {
    this.fileModel = fileModel;
  }

  @Override
  public Type<AddFileModelHandler> getAssociatedType() {
    return TYPE;
  }

  public FileModel getFileModel() {
    return fileModel;
  }

  @Override
  protected void dispatch(AddFileModelHandler handler) {
    handler.onAddFileModel(this);
  }
}