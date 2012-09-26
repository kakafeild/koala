package com.mengke.koala.client.common.util;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public class StringUtils {
	public static final String EMPTY = "";
	//represents the query string (HTTP) characters to be escaped (position i) and the escape char (position i+1)
	static final String[] QUERY_STRING_ESCAPE_CHARS = {"\'", "%27"};

	public static void writeList(Iterable<?> source, CharSequence sep, StringBuilder target) {
		Iterator<?> it = source.iterator();
		if (it.hasNext()) {
			target.append(String.valueOf(it.next()));
			while (it.hasNext()) {
				target.append(sep);
				target.append(String.valueOf(it.next()));
			}
		}
	}

	public static String writeList(Iterable<?> source, CharSequence sep) {
		StringBuilder result = new StringBuilder();
		writeList(source, sep, result);
		return result.toString();
	}

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.length() > 0;
	}

	public static boolean isBlank(String s) {
		return s == null || s.trim().length() == 0;
	}

	public static boolean isNotBlank(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static String substringAfterLast(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (isEmpty(separator)) {
			return EMPTY;
		}
		int pos = str.lastIndexOf(separator);
		if (pos == -1 || pos == (str.length() - separator.length())) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	/**
	 * Escapes the given string using the rules for HTTP query string
	 * @param str the string to be escaped
	 * @return the escaped string
	 */
	public static String escapeQueryString(String str) {
		if (isEmpty(str)) {
			return str;
		}
		boolean isEscaped = false;
		for (int i = 0; i < QUERY_STRING_ESCAPE_CHARS.length; i+=2) {
			if (str.contains(QUERY_STRING_ESCAPE_CHARS[i])) {
				isEscaped = true;
				break;
			}
		}
		String escaped = str;
		if (isEscaped) {
			for (int i = 0; i < QUERY_STRING_ESCAPE_CHARS.length; i+=2) {
				escaped = escaped.replace(QUERY_STRING_ESCAPE_CHARS[i], QUERY_STRING_ESCAPE_CHARS[i+1]);
			}
		}
		return escaped;
	}
}
