package org.example.module.execution.common;

import lombok.Data;
import org.example.entity.Problem;

import java.util.List;

@Data
public class Program {
    private String  name;
    private String  path;
    private String  source;
    private String  content;
    private List<Problem.Test> tests;
}
