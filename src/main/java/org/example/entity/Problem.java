package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem implements Serializable {
    private String name;
    private String group;
    private String url;
    private Boolean interactive;
    private Integer memoryLimit;
    private Integer timeLimit;
    private List<Test> tests;
    private String testType;
    private IOType input;
    private IOType output;
    private LanguageTypes languages;
    private Batch batch;

    public static class Batch {
        private String id;
        private Integer size;

        @JsonProperty("id")
        public String getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(String id) {
            this.id = id;
        }

        @JsonProperty("size")
        public Integer getSize() {
            return size;
        }

        @JsonProperty("size")
        public void setSize(Integer size) {
            this.size = size;
        }
    }

    public static class LanguageTypes {
        private Language java;

        @JsonProperty("java")
        public Language getJava() {
            return java;
        }

        @JsonProperty("java")
        public void setJava(Language java) {
            this.java = java;
        }
    }

    public static class IOType {
        private String type;

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }
    }

    public static class Language {
        private String mainClass;
        private String taskClass;

        @JsonProperty("mainClass")
        public String getMainClass() {
            return mainClass;
        }

        @JsonProperty("mainClass")
        public void setMainClass(String mainClass) {
            this.mainClass = mainClass;
        }

        @JsonProperty("taskClass")
        public String getTaskClass() {
            return taskClass;
        }

        @JsonProperty("taskClass")
        public void setTaskClass(String taskClass) {
            this.taskClass = taskClass;
        }
    }

    public static class Test {
        private String input;
        private String output;
        private boolean isActive = true;

        @JsonProperty("input")
        public String getInput() {
            return input;
        }

        @JsonProperty("input")
        public void setInput(String input) {
            this.input = input;
        }

        @JsonProperty("output")
        public String getOutput() {
            return output;
        }

        @JsonProperty("output")
        public void setOutput(String output) {
            this.output = output;
        }

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean isActive) {
            this.isActive = isActive;
        }
    }


    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("group")
    public String getGroup() {
        return group;
    }

    @JsonProperty("group")
    public void setGroup(String group) {
        this.group = group;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("interactive")
    public Boolean getInteractive() {
        return interactive;
    }

    @JsonProperty("interactive")
    public void setInteractive(Boolean interactive) {
        this.interactive = interactive;
    }

    @JsonProperty("memoryLimit")
    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    @JsonProperty("memoryLimit")
    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    @JsonProperty("timeLimit")
    public Integer getTimeLimit() {
        return timeLimit;
    }

    @JsonProperty("timeLimit")
    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    @JsonProperty("tests")
    public List<Test> getTests() {
        return tests;
    }

    @JsonProperty("tests")
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @JsonProperty("testType")
    public String getTestType() {
        return testType;
    }

    @JsonProperty("testType")
    public void setTestType(String testType) {
        this.testType = testType;
    }

    @JsonProperty("input")
    public IOType getInput() {
        return input;
    }

    @JsonProperty("input")
    public void setInput(IOType input) {
        this.input = input;
    }

    @JsonProperty("output")
    public IOType getOutput() {
        return output;
    }

    @JsonProperty("output")
    public void setOutput(IOType output) {
        this.output = output;
    }

    @JsonProperty("languages")
    public LanguageTypes getLanguages() {
        return languages;
    }

    @JsonProperty("languages")
    public void setLanguages(LanguageTypes languages) {
        this.languages = languages;
    }

    @JsonProperty("batch")
    public Batch getBatch() {
        return batch;
    }

    @JsonProperty("batch")
    public void setBatch(Batch batch) {
        this.batch = batch;
    }
}
