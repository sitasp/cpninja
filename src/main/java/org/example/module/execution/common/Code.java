package org.example.module.execution.common;

import lombok.Data;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;

@Data
public class Code {
    private String   name;               // maybe we can remove it, Lets leave it for this moment
    private Language language;
    private Program  program;
    private Problem  problem;
}
