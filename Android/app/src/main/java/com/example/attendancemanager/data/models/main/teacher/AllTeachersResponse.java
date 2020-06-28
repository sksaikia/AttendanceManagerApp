package com.example.attendancemanager.data.models.main.teacher;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllTeachersResponse {

    @SerializedName("result")
    private ArrayList<TeacherResponse> list;

    public ArrayList<TeacherResponse> getList() {
        return list;
    }

    public void setList(ArrayList<TeacherResponse> list) {
        this.list = list;
    }
}
