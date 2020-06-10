package com.example.attendancemanager.data.models.main.student;

import com.google.gson.annotations.SerializedName;

public class StudentBody {

    @SerializedName("student_first_name")
    private String firstName;

    @SerializedName("student_middle_name")
    private String middleName;

    @SerializedName("student_last_name")
    private String lastName;

    @SerializedName("student_phone")
    private String phone;

    @SerializedName("student_roll_no")
    private String rollNo;

    @SerializedName("student_email")
    private String email;

    public StudentBody(String firstName, String middleName, String lastName, String phone, String rollNo, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phone = phone;
        this.rollNo = rollNo;
        this.email = email;
    }

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

    public String getRollNo() {
        return rollNo;
    }

    public String getEmail() {
        return email;
    }
}
