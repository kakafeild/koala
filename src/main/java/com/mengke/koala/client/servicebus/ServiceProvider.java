/**
 * Sencha GXT 3.0.1 - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.mengke.koala.client.servicebus;

public interface ServiceProvider<T extends ServiceRequest>
{
  public void onServiceRequest(T serviceRequest);
}