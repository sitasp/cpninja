package org.example.module.execution.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Language {
    private String  name;
    private Boolean isCompiled;
    private Boolean isInterpreted;
    private String  extension;

    public Language(String name, Boolean isCompiled, Boolean isInterpreted, String extension) {
        this.name = name;
        this.isCompiled = isCompiled;
        this.isInterpreted = isInterpreted;
        this.extension  = extension;
    }
}
