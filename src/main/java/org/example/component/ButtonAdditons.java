package org.example.component;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public interface ButtonAdditons {

    void addButtons(List<JButton> buttons);

    default List<JButton> createButtons() {
        return Collections.emptyList();
    }
}
