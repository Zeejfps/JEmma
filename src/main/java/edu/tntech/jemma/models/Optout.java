package edu.tntech.jemma.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Optout {

    @SerializedName("timestamp")
    @Expose
    private LocalDateTime timestamp;
    @SerializedName("mailing_id")
    @Expose
    private long mailingId;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public long getMailingId() {
        return mailingId;
    }

    public void setMailingId(long mailingId) {
        this.mailingId = mailingId;
    }
}
