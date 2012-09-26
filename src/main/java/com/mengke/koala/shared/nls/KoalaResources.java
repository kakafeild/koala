package com.mengke.koala.shared.nls;

import com.google.gwt.core.client.GWT;

import static com.mengke.koala.client.common.util.Assert.argHasLength;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-8-30
 *
 * This abstract class in the shared.nls package provides common methods
 * used by the KoalaClientResources classes in the client.nls and server.nls packages
 * where the differences in handling messages are located.
 *
 */
public abstract class KoalaResources {

	private static String[] SUBSTITUTION_STRINGS = {
					"{0}", "{1}", "{2}", "{3}", "{4}", "{5}", "{6}", "{7}", "{8}", "{9}"
	};

	// KoalaClientResource subclasses in client.nls and server.nls packages will set this.
	protected static KoalaResources instance;

	// Subclasses will provide the appropriate instance whether on client or server.
	protected abstract String getResourceString(String key);

	/**
	 * Returns the message for the specified key.
	 *
	 * @param key message key
	 * @return message associated with key, or the key itself if no message found
	 */
	public static String getString(final String key) {
		assert argHasLength(key, "KoalaResources getString key is null/empty");
		if (instance != null) {
			String value = instance.getResourceString(key);
			return value == null ? key : value; // never null
		}
		return key;
	}

	/**
	 * Returns the message for the specified key.
	 *
	 * @param key  message key
	 * @param args optional arguments in message
	 * @return message associated with key, or the key itself if no message found
	 */
	public static String getString(final String key, final Object... args) {
		String value = getString(key);

		return args == null ? value : resolveParams(value, args);
	}

	public static Integer getInteger(final String key) {
		assert argHasLength(key, "KoalaResources getInteger key is null/empty");
		Integer integer = 0;

		String value = getString(key);

		try {
			integer = new Integer(value);
		} catch (NumberFormatException e) {
			if (GWT.isClient() || (value != null && !value.equals(key))) {
				String message = "KoalaResources getInteger NumberFormatException for key: " + key + ", value: " + value;
				throw new NumberFormatException(message);
			}
		}

		return integer;
	}

	public static String resolveParams(String template, Object... args) {
		argHasLength(template, "KoalaResources resolveParams message empty/null");

		String message = template;

		if (args != null) {
			for (int i = 0, len = args.length; i < len; i++) {  // resolve any params
				// use a pre-built string instead of building a string again and again
				String target;
				if (i < SUBSTITUTION_STRINGS.length) {
					target = SUBSTITUTION_STRINGS[i];
				} else {
					target = "{" + i + "}";
				}
				message = message.replace(target, args[i] == null ? "(null)" : args[i].toString());
			}
		}

		return message;
	}

}
