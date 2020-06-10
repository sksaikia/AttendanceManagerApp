package com.example.attendancemanager.data.models.main.student;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllStudentsResponse {

    @SerializedName("total_count")
    private int no;

    @SerializedName("result")
    private ArrayList<StudentsResponse> list;

    public int getNo() {
        return no;
    }

    public ArrayList<StudentsResponse> getList() {
        return list;
    }
}
