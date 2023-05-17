package org.example.component.panel;

public enum CaseComponentEnums {
    INPUT("input"),
    OUTPUT("output"),
    EXPECTED("expected");

    private final String type;
    private CaseComponentEnums(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
