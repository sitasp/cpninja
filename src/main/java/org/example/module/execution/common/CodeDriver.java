package org.example.module.execution.common;

import lombok.Data;
import org.example.component.objects.Message;
import org.example.component.panel.TestPanel;
import org.example.constants.NinjaConstants;
import org.example.entity.Problem;
import org.example.module.execution.lang.CDriver;
import org.example.module.execution.lang.CppDriver;
import org.example.module.execution.lang.JavaDriver;

import java.util.Objects;

@Data
public class CodeDriver {
    private String   name;
    private Language language;
    private Program  program;
    private Problem  problem;

    public CodeDriver(Language lang) {
        this.language = lang;
    }
    public String addComments() {
        return null;
    }

    public Message compileCode(Program program) {
        return null;
    }

    public Message runCode(Program program, TestPanel testPanel){
        return null;
    }

    public static CodeDriver selectDriver() {
        String language = NinjaConstants.DEFAULT_LANGUAGE;
        Language langSelected = Language.findEnumByValue(language);
        switch(Objects.requireNonNull(langSelected)){
            case JAVA -> {
                return new JavaDriver();
            }
            case C -> {
                return new CDriver();
            }
            case CPP -> {
                return new CppDriver();
            }
            default ->{
                return new CodeDriver(null);
            }
        }
    }
}
