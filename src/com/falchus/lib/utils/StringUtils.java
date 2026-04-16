package com.falchus.lib.utils;

import java.util.Collection;

import lombok.NonNull;

public class StringUtils {

	/**
	 * Copies all elements from the iterable collection of originals to the collection provided.
	 * 
	 * @param <T>			the collection of strings
	 * @param token			String to search for
	 * @param originals		An iterable collection of strings to filter.
	 * @param collection	The collection to add matches to
	 * @return the collection provided that would have the elements copied into
	 */
	public static <T extends Collection<? super String>> T copyPartialMatches(@NonNull String token, @NonNull Iterable<String> originals, @NonNull T collection) {
		for (String string : originals) {
			if (startsWithIgnoreCase(string, token)) {
				collection.add(string);
			}
		}
		return collection;
	}
	
	/**
	 * This method uses a region to check case-insensitive equality.
	 * Meaning the internal array does not need to be copied like a toLowerCase() call would.
	 * 
	 * @param string	String to check
	 * @param prefix	Prefix of string to compare
	 * @return {@code true} if provided string starts with, ignoring case, the prefix provided
	 */
	public static boolean startsWithIgnoreCase(@NonNull String string, @NonNull String prefix) {
		if (string.length() < prefix.length()) return false;
		return string.regionMatches(true, 0, prefix, 0, prefix.length());
	}
}
