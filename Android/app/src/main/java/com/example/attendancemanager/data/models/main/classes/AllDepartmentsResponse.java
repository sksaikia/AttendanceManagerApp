package com.example.attendancemanager.data.models.main.classes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllDepartmentsResponse {

    @SerializedName("classes")
    ArrayList<DepartmentResponse> list;

    public ArrayList<DepartmentResponse> getList() {
        return list;
    }
}
