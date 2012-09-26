/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.service;

import com.google.gwt.user.client.ui.HasWidgets;
import com.mengke.koala.client.ProfilePresenter;
import com.mengke.koala.client.servicebus.ServiceProvider;

public class ProfileServiceProvider implements ServiceProvider<ProfileServiceRequest> {

  private ProfilePresenter profilePresenter;
  private HasWidgets hasWidgets;

  public ProfileServiceProvider(ProfilePresenter profilePresenter, HasWidgets hasWidgets) {
    this.profilePresenter = profilePresenter;
    this.hasWidgets = hasWidgets;
  }

  @Override
  public void onServiceRequest(ProfileServiceRequest profileServiceRequest) {
    profilePresenter.go(hasWidgets);
  }
}