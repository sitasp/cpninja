package org.example.utils;

import org.apache.commons.lang3.StringUtils;

public class NinjaProperties {

    public static String DEFAULT_SELECTED_LANGUAGE                  =           StringUtils.EMPTY;
    public static String WIDTH                                      =           StringUtils.EMPTY;
    public static String HEIGHT                                     =           StringUtils.EMPTY;

    public static String LAST_OPENED_TAB                            =           StringUtils.EMPTY;
    public static String WINDOW_TITLE                               =           StringUtils.EMPTY;
    public static String WINDOW_SUMMARY                             =           StringUtils.EMPTY;


    public static class MenuItem {
        public static int SHORTCUT_FONT_SIZE                        =           Integer.MIN_VALUE;
        public static int ICON_FONT_SIZE                            =           Integer.MIN_VALUE;
        public static int TITLE_FONT_SIZE                           =           Integer.MIN_VALUE;
        public static String SHORTCUT_FONT_COLOR                    =           StringUtils.EMPTY;
        public static String ICON_FONT_COLOR                        =           StringUtils.EMPTY;
        public static String TITLE_FONT_COLOR                       =           StringUtils.EMPTY;
    }
}
