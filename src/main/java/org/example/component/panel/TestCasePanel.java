package org.example.component.panel;

import org.example.component.ButtonActions;
import org.example.component.CaseComponent;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class TestCasePanel extends JPanel {
    private CaseComponent           input;
    private CaseComponent           output;
    private CaseComponent           expected;
    private ButtonActions           btnActions;
    private Integer                 count;
    private JScrollPane             scrollPane;
    private Double                  width  = NinjaConstants.TestCase.PANEL_WIDTH;
    private Double                  height = NinjaConstants.TestCase.PANEL_HEIGHT;
    private Problem.Test            test;
    public TestCasePanel(Integer count, ButtonActions btnActions, Problem.Test test) {
        this.count = count;
        this.test  = test;
        input = new CaseComponent(" Input #"+count, "Run", width/3, height, btnActions, true, CaseComponentEnums.INPUT,
                Optional.ofNullable(test).map(Problem.Test::getInput).orElse(null)); //Optional.ofNullable(test).map(e -> e.getInput()).orElse(null)
//        input.setPreferredSize(new Dimension(300, 200));

        output = new CaseComponent(" Output #"+count, "**", width/3, height, btnActions, false, CaseComponentEnums.OUTPUT,
                Optional.ofNullable(test).map(Problem.Test::getInput).orElse(null)); //Optional.ofNullable(test).map(e -> e.getInput()).orElse(null)
//        output.setPreferredSize(new Dimension(300, 200));

        expected = new CaseComponent(" Expected #"+count, "Del", width/3, height, btnActions, true, CaseComponentEnums.EXPECTED,
                null);
//        expected.setPreferredSize(new Dimension(300, 200));

        this.setLayout(new GridLayout(1, 3));

        this.add(input);
        this.add(output);
        this.add(expected);
        this.setPreferredSize(new Dimension((int)Math.floor(width), (int)Math.floor(height)));
//        scrollPane = new JScrollPane(this);
    }
}
