package com.example.attendancemanager.data.retrofit;


import com.example.attendancemanager.data.models.DefaultResponse;
import com.example.attendancemanager.data.models.auth.LoginBody;
import com.example.attendancemanager.data.models.auth.LoginResponse;
import com.example.attendancemanager.data.models.auth.RegisterBody;
import com.example.attendancemanager.data.models.main.student.AllStudentsResponse;
import com.example.attendancemanager.data.models.main.student.StudentBody;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppApiHelper implements APIInterface{

    APIInterface api;

    @Inject
    public AppApiHelper(Retrofit retrofit) {
        api = retrofit.create(APIInterface.class);
    }


    @Override
    public Observable<Response<DefaultResponse>> registerUser(RegisterBody authBody) {
        return api.registerUser(authBody);
    }

    @Override
    public Observable<Response<LoginResponse>> loginUser(LoginBody authBody) {
        return api.loginUser(authBody);
    }

    @Override
    public Observable<Response<DefaultResponse>> addStudent(StudentBody studentBody) {
        return api.addStudent(studentBody);
    }

    @Override
    public Observable<Response<AllStudentsResponse>> getAllStudents() {
        return api.getAllStudents();
    }

    @Override
    public Observable<Response<AllStudentsResponse>> getSingleStudent(String id) {
        return api.getSingleStudent(id);
    }

    @Override
    public Observable<Response<DefaultResponse>> updateSingleStudent(String id, StudentBody body) {
        return api.updateSingleStudent(id,body);
    }

    @Override
    public Observable<Response<DefaultResponse>> deleteSingleStudent(String id) {
        return api.deleteSingleStudent(id);
    }
}
