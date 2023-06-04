package org.example.component.panel;

import lombok.Data;
import org.example.component.ButtonActions;
import org.example.component.CaseComponent;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.module.execution.common.Program;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

@Data
public class TestCasePanel extends JPanel {
    private CaseComponent           input;
    private CaseComponent           output;
    private CaseComponent           expected;
    private ButtonActions           btnActions;
    private Integer                 count;
    private JScrollPane             scrollPane;
    private Double                  tpwidth  = NinjaConstants.TestCase.PANEL_WIDTH;
    private Double                  tpheight = NinjaConstants.TestCase.PANEL_HEIGHT;
    private Problem.Test            test;
    private Program program;
    public TestCasePanel(Integer count, ButtonActions btnActions, Program program, Problem.Test test) {
        this.count = count;
        this.test  = test;
        this.program = program;
        input = new CaseComponent(" Input #"+count, "Run", tpwidth/3, tpheight, btnActions, true, CaseComponentEnums.INPUT, program,
                Optional.ofNullable(test).map(Problem.Test::getInput).orElse(null)); //Optional.ofNullable(test).map(e -> e.getInput()).orElse(null)
//        input.setPreferredSize(new Dimension(300, 200));

        output = new CaseComponent(" Output #"+count, "**", tpwidth/3, tpheight, btnActions, false, CaseComponentEnums.OUTPUT, program,null); //Optional.ofNullable(test).map(e -> e.getInput()).orElse(null)
//        output.setPreferredSize(new Dimension(300, 200));

        expected = new CaseComponent(" Expected #"+count, "Del", tpwidth/3, tpheight, btnActions, true, CaseComponentEnums.EXPECTED, program,
                Optional.ofNullable(test).map(Problem.Test::getOutput).orElse(null));
//        expected.setPreferredSize(new Dimension(300, 200));

        this.setLayout(new GridLayout(1, 3));

        this.add(input);
        this.add(output);
        this.add(expected);
        this.setPreferredSize(new Dimension((int)Math.floor(tpwidth), (int)Math.floor(tpheight)));
    }
}
