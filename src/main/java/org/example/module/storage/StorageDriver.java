package org.example.module.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.StorageException;

import java.util.prefs.Preferences;

public class StorageDriver {

    public static Preferences masterStorage() {
        return Preferences.userRoot();
    }

    public static void keep(String key, String val) {
        masterStorage().put(key, val);
    }

    public static void keep(String key, Object val) throws StorageException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String strValue = mapper.writeValueAsString(val);
            keep(key, strValue);
        }
        catch (Exception ex) {
            throw new StorageException(ex.getMessage());
        }
    }

    public static String fetch(String key) {
        return masterStorage().get(key, null);
    }
}
