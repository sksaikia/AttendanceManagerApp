package com.example.attendancemanager.data.models.main.teacher;

import com.google.gson.annotations.SerializedName;

public class TeacherBody {

    @SerializedName("teacher_first_name")
    private String firstName;

    @SerializedName("teacher_middle_name")
    private String middleName;

    @SerializedName("teacher_last_name")
    private String lastName;

    @SerializedName("teacher_email")
    private String email;

    @SerializedName("teacher_phone")
    private String phone;

    @SerializedName("teacher_department")
    private String department;

    public TeacherBody(String firstName, String middleName, String lastName, String email, String phone,String department) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }
}
