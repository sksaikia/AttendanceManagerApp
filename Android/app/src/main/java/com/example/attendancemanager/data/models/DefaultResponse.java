package com.example.attendancemanager.data.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("statusText")
    private String statusText;

    public String getStatus() {
        return status;
    }

    public String getStatusText() {
        return statusText;
    }
}
