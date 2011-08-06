package com.iwildwest.core;

public class Utils {

	public static String getKey(Object object, String string) {
		return new StringBuffer().append(Storable.KEY).append(object.getClass().getSimpleName()).append(string).toString();
	}

}
