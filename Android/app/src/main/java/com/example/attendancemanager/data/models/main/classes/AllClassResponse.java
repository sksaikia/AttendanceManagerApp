package com.example.attendancemanager.data.models.main.classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllClassResponse {

    @SerializedName("result")
    ArrayList<ClassResponse> list;

    public ArrayList<ClassResponse> getList() {
        return list;
    }
}
