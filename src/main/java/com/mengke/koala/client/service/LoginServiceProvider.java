/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.service;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mengke.koala.client.LoginPresenter;
import com.mengke.koala.client.servicebus.ServiceProvider;

public class LoginServiceProvider implements ServiceProvider<LoginServiceRequest> {

  private LoginPresenter loginPresenter;
  private HasWidgets hasWidgets;

  public LoginServiceProvider(LoginPresenter loginPresenter, HasWidgets hasWidgets) {
    this.loginPresenter = loginPresenter;
    this.hasWidgets = hasWidgets;
  }

  @Override
  public void onServiceRequest(LoginServiceRequest loginServiceRequest) {
    loginPresenter.go(hasWidgets);
  }

}
