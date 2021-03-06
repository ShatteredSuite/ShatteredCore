package com.github.shatteredsuite.core.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides string-related utilities.
 */
public class StringUtil {

    private static final Pattern WORD =
            Pattern.compile("\"((?:[^\\\\\"]|\\\\.)*)\"|([^\\s\"]+)");

    private StringUtil() {
    }

    /**
     * Tests if a string is empty or null.
     *
     * @param string The string to test.
     * @return <code>true</code> if the string is empty or null, <code>false</code> otherwise.
     */
    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isEmpty();
    }

    public static String[] fixArgs(String[] args) {
        ArrayList<String> results = new ArrayList<>();
        Matcher matcher = WORD.matcher(String.join(" ", args));
        while (matcher.find()) {
            String capturedGroup = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            results.add(capturedGroup);
        }
        if (args.length > 0 && args[args.length - 1].equals("")) {
            results.add("");
        }
        return results.toArray(new String[]{});
    }
}
