package com.example.attendancemanager.data.models.main.student;

import com.google.gson.annotations.SerializedName;

public class StudentsResponse {

    @SerializedName("student_id")
    private String id;

    @SerializedName("student_first_name")
    private String firstName;

    @SerializedName("student_middle_name")
    private String middleName;

    @SerializedName("student_last_name")
    private String lastName;

    @SerializedName("student_phone")
    private String phone;

    @SerializedName("student_email")
    private String email;

    @SerializedName("student_roll_no")
    private String rollNo;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getId() {
        return id;
    }
}
