package org.example.utils;

import java.awt.*;

public class CommonUtils {

    public static Dimension getScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize;
    }
}
