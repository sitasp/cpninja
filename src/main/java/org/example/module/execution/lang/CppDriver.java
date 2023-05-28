package org.example.module.execution.lang;

import org.example.component.objects.Message;
import org.example.module.execution.common.CodeDriver;
import org.example.module.execution.common.Language;
import org.example.module.execution.common.Program;

public class CppDriver extends CodeDriver {
    public CppDriver() {
        super(Language.CPP);
    }

    @Override
    public String addComments() {
        return null;
    }

    @Override
    public Message compileCode(Program program) {

        return null;
    }

    @Override
    public Message runCode(Program program) {

        return null;
    }
}
