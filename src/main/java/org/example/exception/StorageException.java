package org.example.exception;

import org.apache.commons.lang3.StringUtils;

public class StorageException extends Exception {
    private String persistingData;

    public StorageException(String message) {
        this(null, message);
    }

    public StorageException(String persistingData, String message) {
        super(String.format("Exception occurred while storing data {%s}: %s", StringUtils.trimToEmpty(persistingData), message));
        this.persistingData = persistingData;
    }

    public StorageException(String persistingData, String message, Throwable cause) {
        super(String.format("Exception occurred while storing data {%s}: %s", StringUtils.trimToEmpty(persistingData), message), cause);
        this.persistingData = persistingData;
    }

    public String getPersistingData() {
        return persistingData;
    }

    public void setPersistingData(String str) {
        this.persistingData = str;
    }
}
