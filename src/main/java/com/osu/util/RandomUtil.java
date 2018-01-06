package com.osu.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Ekaterina Pyataeva
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
        throw new UnsupportedOperationException("This is utility class!");
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

}
