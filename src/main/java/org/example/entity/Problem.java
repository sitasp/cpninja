package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    }

    public static class LanguageTypes {
        private Language java;
    }

    public static class IOType {
        private String type;
    }

    public static class Language {
        private String mainClass;
        private String taskClass;
    }

    public static class Test {
        private String input;
        private String output;
    }
}
