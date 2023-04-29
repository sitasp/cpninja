package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONPropertyIgnore;

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
}

@Data
class Batch {
    private String id;
    private Integer size;
}

@Data
class LanguageTypes {
    private Language java;
}

@Data
class IOType {
    private String type;
}

@Data
class Language {
    private String mainClass;
    private String taskClass;
}

@Data
class Test {
    private String input;
    private String output;
}
