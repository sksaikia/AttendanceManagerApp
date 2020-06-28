package com.example.attendancemanager.data.models.main.teacher;

import com.google.gson.annotations.SerializedName;

public class TeacherResponse {

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

    @SerializedName("teacher_id")
    private String id;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDepartment() {
        return department;
    }

    public String getId() {
        return id;
    }
}
