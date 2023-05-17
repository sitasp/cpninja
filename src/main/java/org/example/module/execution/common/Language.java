package org.example.module.execution.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Language {
    JAVA("java", true, false, ".java");
    private String  value;
    private Boolean isCompiled;
    private Boolean isInterpreted;
    private String  extension;
    Language(String value, Boolean isCompiled, Boolean isInterpreted, String extension) {
        this.value       = value;
        this.isCompiled = isCompiled;
        this.isInterpreted = isInterpreted;
        this.extension  = extension;
    }
    public String getValue() {
        return this.value;
    }
    public String getExtension() {
        return this.extension;
    }
    public Boolean isCompiled() {
        return this.isCompiled;
    }
    public Boolean isInterpreted() {
        return this.isInterpreted;
    }

    public static Language findEnumByValue(String searchValue) {
        for(Language language : Language.values()) {
            if(language.getValue().equalsIgnoreCase(searchValue)) {
                return language;
            }
        }
        return null;
    }
}
