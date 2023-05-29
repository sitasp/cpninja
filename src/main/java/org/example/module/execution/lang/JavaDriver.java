package org.example.module.execution.lang;

import org.apache.commons.lang3.StringUtils;
import org.example.component.objects.Message;
import org.example.component.panel.TerminalPanel;
import org.example.module.execution.common.CodeDriver;
import org.example.module.execution.common.CompiledProgram;
import org.example.module.execution.common.Language;
import org.example.module.execution.common.Program;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class JavaDriver extends CodeDriver {
    public JavaDriver() {
        super(Language.JAVA);
    }

    @Override
    public String addComments() {
        return null;
    }

    @Override
    public Message compileCode(Program program) {
        if(Objects.isNull(super.getProgram())){
            CompiledProgram cp1 = programToCompiledMapper(program);
            program = cp1;
            super.setProgram(cp1);
        }
        else syncPrograms(program);
        String folderPath = "tasks" + "/" + program.getName();
        File folder = new File(folderPath);
        File file = new File(folder, "Main.java");
        Message message = new Message();
        message.setSource("Compiler");
        message.setSuccess(true);


        try {
            if (!folder.exists()) {
                folder.mkdirs();  // Create the folder if it doesn't exist
            }
            if(file.exists())
                file.delete();

            file.createNewFile();
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(super.getProgram().getContent());
            }
            super.getProgram().setName(file.getName());
            super.getProgram().setPath(file.getAbsolutePath());
            ProcessBuilder processBuilder = new ProcessBuilder("javac", file.getAbsolutePath());
            Process compileProcess = processBuilder.start();
            int exitCode = compileProcess.waitFor();

            if(!Objects.equals(exitCode, 0)) {
                String errorOutput = new String(compileProcess.getErrorStream().readAllBytes());
                message.setSuccess(false);

                if(StringUtils.isNotEmpty(errorOutput)) {
                    message.setMessage(errorOutput);
                }
                else {
                    message.setMessage("Code can't be compiled");
                }
                return message;
            }
            ((CompiledProgram)super.getProgram()).setIsCompiled(true);
            ((CompiledProgram)super.getProgram()).setCompilePath(file.getAbsolutePath());
            message.setMessage("Compilation successful");
            return message;
        }
        catch (Exception ex) {
            message.setSuccess(false);
            if(ex instanceof IOException) {
                message.setMessage("File creation error during compilation: " + ex.getMessage());
                return message;
            }
            else {
                message.setMessage("Unhandled error during compilation: " + ex.getMessage());
                return message;
            }
        }
        finally {
            file.deleteOnExit(); // Delete the file when the JVM exits
        }
    }

    private void syncPrograms(Program program) {
        super.getProgram().setContent(program.getContent());
    }

    private CompiledProgram programToCompiledMapper(Program program) {
        CompiledProgram compiledProgram = new CompiledProgram();
        compiledProgram.setPath(program.getPath());
        compiledProgram.setName(program.getName());
        compiledProgram.setContent(program.getContent());
        compiledProgram.setSource(program.getSource());
        return compiledProgram;
    }


    @Override
    public Message runCode(Program program) {
        return null;
    }
}
