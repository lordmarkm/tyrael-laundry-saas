package com.tyrael.laundry.commons.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Mark Martinez, created Dec 8, 2015
 *
 */
public class SlugUtil {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final String NO_NAME = "no-name";

    public static String toSlug(final String input) {
      if (StringUtils.isEmpty(input)) {
          return NO_NAME;
      }
      String trimmed = input.trim().replaceAll("\\s+", " ");
      String nowhitespace = WHITESPACE.matcher(trimmed).replaceAll("-");
      String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
      String slug = NONLATIN.matcher(normalized).replaceAll("");
      return slug.toLowerCase(Locale.ENGLISH);
    }

    public static String toSlug(final String... inputs) {
        if (inputs.length < 1) {
            return NO_NAME;
        }
        StringBuilder compundSlug = new StringBuilder();
        for (int i = 0; i < inputs.length; i++) {
            if (i > 0) {
                compundSlug.append("-");
            }
            compundSlug.append(toSlug(inputs[i]));
        }
        return compundSlug.toString();
    }
}
