package org.example.module.execution.lang;

import org.apache.commons.lang3.StringUtils;
import org.example.component.objects.Message;
import org.example.component.panel.TerminalPanel;
import org.example.component.panel.TestCasePanel;
import org.example.component.panel.TestPanel;
import org.example.entity.Problem;
import org.example.module.execution.common.CodeDriver;
import org.example.module.execution.common.CompiledProgram;
import org.example.module.execution.common.Language;
import org.example.module.execution.common.Program;

import java.awt.*;
import java.io.*;
import java.util.Objects;

public class JavaDriver extends CodeDriver {
    public static String language_extension = "java";
    public static String compile_extension = "class";
    public static String fileName = "Main";
    public JavaDriver() {
        super(Language.JAVA);
    }

    @Override
    public String addComments() {
        return null;
    }

    @Override
    public Message compileCode(Program program) {
//        if(Objects.isNull(super.getProgram())){
//            CompiledProgram cp1 = programToCompiledMapper(program);
//            program = cp1;
//            super.setProgram(cp1);
//        }
//        else syncPrograms(program);
//        super.getProgram().setContent(program.getContent());
        String folderPath = "tasks" + "/" + program.getName();
        File folder = new File(folderPath);
        File file = new File(folder, fileName + "." + language_extension);
        Message message = new Message();
        message.setSource("Compiler");
        message.setSuccess(true);
        super.setProgram(program);

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
            ((CompiledProgram)super.getProgram()).setCompilePath(folder.getAbsolutePath());
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
    }

    private void syncPrograms(Program program) {
        CompiledProgram cp1 = programToCompiledMapper(program);
        program = cp1;
        super.setProgram(cp1);
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
    public Message runCode(Program program, TestPanel testPanel){
        String compilePath = ((CompiledProgram)program).getCompilePath();
        Message message = new Message();
        message.setSource("Runner");
        message.setSuccess(true);

        int count = 1;
        for(Component component: testPanel.getComponents()) {
            TestCasePanel testCasePanel = ((TestCasePanel) component);
            testCasePanel.getTest().setInput(testCasePanel.getInput().getText().getText());
            testCasePanel.getTest().setOutput(testCasePanel.getOutput().getText().getText());

            if(!testCasePanel.getTest().getInput().isEmpty()) {
                String input = testCasePanel.getTest().getInput();
                String output = "";
                //execute this input
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder(
                            "java",
                            "-cp",
                            ((CompiledProgram) program).getCompilePath(),
                            "Main"
                            );
                    processBuilder.redirectErrorStream(true);
                    Process process = processBuilder.start();

                    // Write the input to the process's standard input
                    process.getOutputStream().write(input.getBytes());
                    process.getOutputStream().flush();
                    process.getOutputStream().close();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        output += line + "\n";
                    }

                    // Wait for the process to complete
                    process.waitFor();
                } catch (IOException | InterruptedException e) {
                    message.setSuccess(false);
                    message.setMessage("Couldn't complete execution of testcase" + count++);
                    e.printStackTrace();
                }

                testCasePanel.getOutput().getText().setText(output);
                if(Objects.equals(StringUtils.trimToEmpty(testCasePanel.getOutput().getText().getText()),
                        StringUtils.trimToEmpty(testCasePanel.getExpected().getText().getText()))) {
                    testCasePanel.getOutput().getButton().setForeground(Color.WHITE);
                    testCasePanel.getOutput().getButton().setBackground(Color.GREEN);
                }
            }
        }
        return message;
    }
}
