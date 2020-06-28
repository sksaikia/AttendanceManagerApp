package com.example.attendancemanager.data.models.main.classes;

import com.google.gson.annotations.SerializedName;

public class ClassResponse {
    @SerializedName("course_id")
    private String id;

    @SerializedName("course_name")
    private String name;

    @SerializedName("semester")
    private String semester;

    @SerializedName("department")
    private String department;

    @SerializedName("subject_code")
    private String subject_code;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public String getDepartment() {
        return department;
    }

    public String getSubject_code() {
        return subject_code;
    }
}
