package com.splicemachine.training.function;

import org.apache.commons.lang3.text.WordUtils;

public class CustomFunctions {

	/**
	 * Function that extracts a given number of characters from the left side of a supplied text string.
	 *
	 * @param source
	 * @param i
	 * @return
	 */
	public static String getleft(String source, int i) {
		String sVal = source;

		if (source != null && source.length() > i) {
			if (i > 0) {
				sVal = source.substring(0,i);
			}
		}

		return sVal;
	}

	/**
	 * Function that extracts a given number of characters from the right side of a supplied text string.
	 *
	 * @param source
	 * @param i
	 * @return
	 */
	public static String getright(String source, int i) {
		String sVal = source;

		if (source != null && source.length() > i) {
			if (i > 0) {
				sVal = source.substring(source.length() - i);
			}
		}

		return sVal;
	}

	/**
	 * Function that capitalizes a given string.
	 *
	 * @param source
	 * @return
	 */
	public static String capitalize(String source) {
		return WordUtils.capitalizeFully(source);
	}

	/**
	 * Function that replaces a char in a string with another char.
	 *
	 * @param source
	 * @param find
	 * @param replace
	 * @return
	 */
	public static String replacestr(String source, String find, String replace) {
		return source.replace(find, replace);
	}

	/**
	 * Function that returns the position of the first occurrence
	 * of a string within a string.
	 *
	 * @param source
	 * @param str
	 * @return
	 */
	public static Integer indexofstr(String source, String str) {
		int index = source.indexOf(str);
		if (index > 0) {
			index++;
		}
		return index;
	}

}