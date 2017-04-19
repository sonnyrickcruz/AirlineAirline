package com.airline.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	private StringUtils() {
	}

	public static boolean isInString(String text, String term) {
		boolean isInString = false;
		String textReplace = text.replaceAll("[-+.^:,\\s{1,}]","");
		String termReplace = term.replaceAll("[-+.^:,\\s{1,}]","");
		String regexTerm = "(" + termReplace + ")";
		Pattern p = Pattern.compile(regexTerm, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
		Matcher m = p.matcher(textReplace);
		if (m.find()) {
			isInString = true;
		}

		return isInString;
	}
}
