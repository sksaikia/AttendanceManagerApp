package com.example.attendancemanager.data.models.auth;

import com.google.gson.annotations.SerializedName;

public class RegisterBody {

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;


    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RegisterBody(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
