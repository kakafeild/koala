package com.mengke.koala.client.common.util;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-11
 *
 * Provide common assert helpers on the client.
 *
 * It is recommended that this class be statically imported so
 * that only the method names are in the code itself. For example
 * import static com.mengke.koala.client.common.util.Assert.argHasLength;
 *
 * Each method name gives a clue as to the type of exception that will be thrown
 * if the test is false.
 * Method names with "Arg" will throw IllegalArgumentException;
 * method names with "State" will throw IllegalStateException.
 *
 * Each type of test (e.g. NotNull, HasLength, NotEmpty, etc.) will have
 * two signature flavors,
 * - one with the object and a string to be the message of the thrown exception
 * - one with the object, message, and a Throwable which will be set as the cause
 * of the thrown exception
 *
 * There are two version of each method name - one with "assert", one without.
 * Use the methods withOUT "assert" in their name when using the java assert keyword.
 * These methods return a boolean and work with the java assert keyword.
 * For example, assert argNotNull(param, "class method arg null");
 * The methods without assert in their name intentionally return boolean and
 * the methods with assert in their name intentionally return void.
 * This is so that you don't code something like this:  assert assertArgNotNull(...)
 * It doesn't "read" well and since the method returns a void, it won't work with the java assert.
 *
 */
public class Assert {

	/**
	 *  argument not null
	 */
	public static boolean argNotNull(Object object, String message) {
		return argNotNull(object, message, null);
	}

	/**
	 *  argument not null
	 */
	public static void assertArgNotNull(Object object, String message) {
		assertArgNotNull(object, message, null);
	}

	/**
	 *  argument not null
	 */
	public static boolean argNotNull(Object object, String message, Throwable cause) {
		if (object == null) {
			throw new IllegalArgumentException(message, cause);
		}

		return true;
	}

	/**
	 *  argument not null
	 */
	public static void assertArgNotNull(Object object, String message, Throwable cause) {
		argNotNull(object, message, cause);
	}

	/**
	 *  argument has length (includes not-null checking)
	 */
	public static boolean argHasLength(String string, String message) {
		return argHasLength(string, message, null);
	}

	/**
	 *  argument has length (includes not-null checking)
	 */
	public static void assertArgHasLength(String string, String message) {
		assertArgHasLength(string, message, null);
	}

	/**
	 *  argument has length (includes not-null checking)
	 */
	public static boolean argHasLength(String string, String message, Throwable cause) {
		// first check for null
		assertArgNotNull(string, message, cause);

		if (string.length() == 0) {
			throw new IllegalArgumentException(message, cause);
		}

		return true;
	}

	/**
	 *  argument has length (includes not-null checking)
	 */
	public static void assertArgHasLength(String string, String message, Throwable cause) {
		argHasLength(string, message, cause);
	}

	/**
	 * argument not empty (includes not-null checking)
	 */
	public static boolean argNotEmpty(Collection<?> collection, String message) {
		return argNotEmpty(collection, message, null);
	}

	/**
	 * argument not empty (includes not-null checking)
	 */
	public static void assertArgNotEmpty(Collection<?> collection, String message) {
		assertArgNotEmpty(collection, message, null);
	}

	/**
	 * argument not empty (includes not-null checking)
	 */
	public static boolean argNotEmpty(Collection<?> collection, String message, Throwable cause) {
		// first check for null
		assertArgNotNull(collection, message, cause);

		if (collection.size() == 0) {
			throw new IllegalArgumentException(message, cause);
		}
		return true;
	}

	/**
	 * argument not empty (includes not-null checking)
	 */
	public static void assertArgNotEmpty(Collection<?> collection, String message, Throwable cause) {
		argNotEmpty(collection, message, cause);
	}

	/**
	 *  argument true
	 */
	public static boolean arg(boolean argumentCondition, String message) {
		return arg(argumentCondition, message, null);
	}

	/**
	 *  argument true
	 */
	public static void assertArg(boolean argumentCondition, String message) {
		assertArg(argumentCondition, message, null);
	}

	/**
	 *  argument true
	 */
	public static boolean arg(boolean argumentCondition, String message, Throwable cause) {
		if (argumentCondition != true) {
			throw new IllegalArgumentException(message, cause);
		}
		return true;
	}

	/**
	 *  argument true
	 */
	public static void assertArg(boolean argumentCondition, String message, Throwable cause) {
		arg(argumentCondition, message, cause);
	}

	/**
	 * state true
	 */
	public static boolean state(boolean state, String message) {
		return state(state, message, null);
	}

	/**
	 * state true
	 */
	public static void assertState(boolean state, String message) {
		assertState(state, message, null);
	}

	/**
	 * state true
	 */
	public static boolean state(boolean state, String message, Throwable cause) {
		if (state != true) {
			throw new IllegalStateException(message, cause);
		}
		return true;
	}

	/**
	 * state true
	 */
	public static void assertState(boolean state, String message, Throwable cause) {
		state(state, message, cause);
	}

	/**
	 * state not null
	 */
	public static boolean stateNotNull(Object object, String message) {
		return stateNotNull(object, message, null);
	}

	/**
	 * state not null
	 */
	public static void assertStateNotNull(Object object, String message) {
		assertStateNotNull(object, message, null);
	}

	/**
	 * state not null
	 */
	public static boolean stateNotNull(Object object, String message, Throwable cause) {
		if (object == null) {
			throw new IllegalStateException(message, cause);
		}

		return true;
	}

	/**
	 * state not null
	 */
	public static void assertStateNotNull(Object object, String message, Throwable cause) {
		stateNotNull(object, message, cause);
	}

	/**
	 * state has length (includes not-null checking)
	 */
	public static boolean stateHasLength(String string, String message) {
		return stateHasLength(string, message, null);
	}

	/**
	 * state has length (includes not-null checking)
	 */
	public static void assertStateHasLength(String string, String message) {
		assertStateHasLength(string, message, null);
	}

	/**
	 * state has length (includes not-null checking)
	 */
	public static boolean stateHasLength(String string, String message, Throwable cause) {
		// first check for null
		assertStateNotNull(string, message, cause);

		if (string.length() == 0) {
			throw new IllegalStateException(message, cause);
		}

		return true;
	}

	/**
	 * state has length (includes not-null checking)
	 */
	public static void assertStateHasLength(String string, String message, Throwable cause) {
		stateHasLength(string, message, cause);
	}

	/**
	 * state not empty (includes not-null checking)
	 */
	public static boolean stateNotEmpty(Collection<?> collection, String message) {
		return stateNotEmpty(collection, message, null);
	}

	/**
	 * state not empty (includes not-null checking)
	 */
	public static void assertStateNotEmpty(Collection<?> collection, String message) {
		assertStateNotEmpty(collection, message, null);
	}

	/**
	 * state not empty (includes not-null checking)
	 */
	public static boolean stateNotEmpty(Collection<?> collection, String message, Throwable cause) {
		// first check for null
		assertStateNotNull(collection, message, cause);

		if (collection.size() == 0) {
			throw new IllegalStateException(message, cause);
		}

		return true;
	}

	/**
	 * state not empty (includes not-null checking)
	 */
	public static void assertStateNotEmpty(Collection<?> collection, String message, Throwable cause) {
		stateNotEmpty(collection, message, cause);
	}

	/**
	 * state null
	 */
	public static boolean stateNull(Object object, String message) {
		return stateNull(object, message, null);
	}

	/**
	 * state null
	 */
	public static void assertStateNull(Object object, String message) {
		assertStateNull(object, message, null);
	}

	/**
	 * state null
	 */
	public static boolean stateNull(Object object, String message, Throwable cause) {
		if (object != null) {
			throw new IllegalStateException(message, cause);
		}

		return true;
	}

	/**
	 * state null
	 */
	public static void assertStateNull(Object object, String message, Throwable cause) {
		stateNull(object, message, cause);
	}

}
