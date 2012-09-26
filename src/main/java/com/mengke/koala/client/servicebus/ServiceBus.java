/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.servicebus;

import java.util.HashMap;

public class ServiceBus {
  private HashMap<Object, Object> serviceProviders = new HashMap<Object, Object>();

  public ServiceBus() {
  }

  @SuppressWarnings("unchecked")
  public <T extends ServiceRequest> T invoke(T serviceRequest) {
    ServiceProvider<T> serviceProvider = (ServiceProvider<T>) serviceProviders.get(serviceRequest.getClass());
    if (serviceProvider == null) {
      throw new IllegalStateException("No service provider, serviceRequest=" + serviceRequest.getClass().getName());
    }
    serviceProvider.onServiceRequest(serviceRequest);
    return serviceRequest; // for one step access to getters
  }

  public <T extends ServiceRequest> void registerServiceProvider(Class<T> serviceRequestClass,
      ServiceProvider<T> serviceProvider) {
    Object previousServiceProvider = serviceProviders.put(serviceRequestClass, serviceProvider);
    if (previousServiceProvider != null) {
      throw new IllegalStateException("Provider already defined, serviceRequest=" + serviceRequestClass.getName()
          + ", serviceProvider=" + serviceProvider.getClass().getName());
    }
  }

  public <T extends ServiceRequest> void removeServiceProvider(Class<T> serviceRequestClass) {
    serviceProviders.remove(serviceRequestClass);
  }

}
