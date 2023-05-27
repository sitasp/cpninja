package org.example.component.objects;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Message {
    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private String message;
    private String time;
    private String source;
    private boolean success;

    public Message() {
        this.time = sdf.format(new Date());
        this.success = true;
    }
}
