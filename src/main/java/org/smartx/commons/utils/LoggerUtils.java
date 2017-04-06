package org.smartx.commons.utils;

import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * 日志工具类
 *
 * @author kext
 */
public final class LoggerUtils {

    public static String getErrorMsg(String format, Object[] argArray) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        return ft.getMessage();
    }

    public static String getErrorMsg(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        return ft.getMessage();
    }

}
