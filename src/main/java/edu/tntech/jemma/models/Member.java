package edu.tntech.jemma.models;

import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName("email")
    private String email;

    public Member(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
