package org.example.module.execution.java;

import org.example.module.execution.common.Language;

public class Java {
    public static String addComments(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append("//   ");
        sb.append(s);
        sb.append("\n");
        return sb.toString();
    }
}
