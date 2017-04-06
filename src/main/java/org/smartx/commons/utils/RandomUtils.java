package org.smartx.commons.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 随机工具类
 *
 * @author kext
 */
public final class RandomUtils {

    // exclude 0,1,o,l
    private static final char[] LOWCASE_LETTERS_AND_NUMBERS =
            new char[]{'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                    'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                    'z'};

    public static String randomAlphanumeric(int count) {
        return RandomStringUtils.randomAlphanumeric(count);
    }

    public static String randomNumeric(int count) {
        return RandomStringUtils.randomNumeric(count);
    }

    public static String randomLowcaseAlphanumeric(int count) {
        return RandomStringUtils.random(count, LOWCASE_LETTERS_AND_NUMBERS);
    }

}
