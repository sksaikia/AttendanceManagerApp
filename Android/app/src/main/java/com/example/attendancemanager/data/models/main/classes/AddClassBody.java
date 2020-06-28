package com.example.attendancemanager.data.models.main.classes;

import com.google.gson.annotations.SerializedName;

public class AddClassBody {

    @SerializedName("course_name")
    private String courseName;

    @SerializedName("semester")
    private String semester;

    @SerializedName("department")
    private String department;

    @SerializedName("subject_code")
    private String subject_code;

    public AddClassBody(String courseName, String semester, String department, String subject_code) {
        this.courseName = courseName;
        this.semester = semester;
        this.department = department;
        this.subject_code = subject_code;
    }
}
