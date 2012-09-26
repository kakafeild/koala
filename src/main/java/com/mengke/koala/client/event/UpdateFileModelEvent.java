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
import com.mengke.koala.client.event.UpdateFileModelEvent.UpdateFileModelHandler;
import com.mengke.koala.client.persistence.FileModel;

public class UpdateFileModelEvent extends GwtEvent<UpdateFileModelHandler> {

  public interface UpdateFileModelHandler extends EventHandler {
    void onUpdateFileModel(UpdateFileModelEvent event);
  }

  public static Type<UpdateFileModelHandler> TYPE = new Type<UpdateFileModelHandler>();
  private FileModel fileModel;

  public UpdateFileModelEvent(FileModel fileModel) {
    this.fileModel = fileModel;
  }

  @Override
  public Type<UpdateFileModelHandler> getAssociatedType() {
    return TYPE;
  }

  public FileModel getFileModel() {
    return fileModel;
  }

  @Override
  protected void dispatch(UpdateFileModelHandler handler) {
    handler.onUpdateFileModel(this);
  }

}