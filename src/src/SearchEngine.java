package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {
	static int find(String needle, String haystack) {
		int needleLength = needle.length();
		for (int index = 0; index <= haystack.length() - needleLength; index++) {
			String fragment = haystack.substring(index, index + needleLength);
			if (equalsWithWildcard(needle, fragment)) {
				return index;
			}
		}
		return -1;
	}

	private static boolean equalsWithWildcard(String needle, String fragment) {
		for (int index = 0; index < needle.length(); index++) {
			if (needle.charAt(index) == '_' || needle.charAt(index) == fragment.charAt(index)) {
				continue;
			}
			return false;
		}
		return true;
	}


	/////////////////OPTIMIZED

	static int findOptimized(String needle, String haystack) {
		String regNeedle = "\\Q" + needle.replaceAll("_", "\\\\E.\\\\Q") + "\\E";
		Matcher m = Pattern.compile(regNeedle).matcher(haystack);
		if (m.find()) {
			return m.start();
		} else {
			return -1;
		}
	}
}
