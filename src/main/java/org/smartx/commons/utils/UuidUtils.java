package org.smartx.commons.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author mahs
 */
public final class UuidUtils {

    private UuidUtils() {
    }

    public static String create() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
