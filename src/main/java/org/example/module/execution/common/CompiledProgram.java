package org.example.module.execution.common;

import lombok.Data;

@Data
public class CompiledProgram extends Program {
    private Boolean isCompiled;
    private String  compilePath;
}
