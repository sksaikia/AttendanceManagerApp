package com.example.attendancemanager.data.models.main.classes;

import com.google.gson.annotations.SerializedName;

public class DepartmentResponse {

    @SerializedName("department_id")
    private String dep_id;

    @SerializedName("department_name")
    private String dep_name;


    public String getDep_id() {
        return dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }
}
