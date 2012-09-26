package com.mengke.koala.client.nls;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.mengke.koala.shared.nls.KoalaResources;

import java.util.HashMap;
import java.util.Map;

import static com.mengke.koala.client.common.util.Assert.assertArgNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-11
 */
public class KoalaClientResources extends KoalaResources {

	/* _map will always have a value (see setMap) */
	private static Map<String,String> _map = new HashMap<String,String>();

	static {
		// give the static methods in the parent class a way to reach this instance.
		instance = getInstance();
	}

	private KoalaClientResources() {
		super();
	}

	public static KoalaResources getInstance() {
		if (instance == null) {
			instance = new KoalaClientResources();
		}
		return instance;
	}

	@Override
	protected String getResourceString(String key) {
		String value = _map.get(key);
		return value == null ? key : value;
	}

	public static void setResourceMap(final Map<String,String> map) {
		assertArgNotNull(map, "KoalaClientResources setMap map is null");

		_map = map;

		// now's a good time to export the methods
		exportMethodsToJavascript();
	}

	public static void exportMethodsToJavascript() {
		if (GWT.isClient()) {
			defineJStoJavaProxy();
		}
	}

	/**
	 * JavaScript code can access the methods of KoalaResources by using the following JavaScript syntax:
	 * KoalaResources.<method-name>() (e.g. KoalaResources.getString(String key))
	 */
	private static native JavaScriptObject defineJStoJavaProxy() /*-{
		var proxy = {};

		// access to NumberConstants methods
		proxy.getString = $entry(@com.mengke.koala.client.nls.KoalaClientResources::getString(Ljava/lang/String;[Ljava/lang/Object;));
		proxy.getInteger = $entry(@com.mengke.koala.client.nls.KoalaClientResources::getInteger(Ljava/lang/String;));

		// this line of code gets this collection of methods into the namespace of
		// top.TRMResources. There's one critical line of code in I18NUtil.js that
		// gets this collection of methods all the way out to the global namespace
		// so that the classic javascript can use TRMResources.getString() instead of top.TRMResources.getString().

		$wnd.TRMResources = proxy;

		return proxy;
	}-*/;

}
