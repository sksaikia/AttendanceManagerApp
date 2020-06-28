package com.example.attendancemanager.data.retrofit;


import com.example.attendancemanager.data.models.DefaultResponse;
import com.example.attendancemanager.data.models.auth.LoginBody;
import com.example.attendancemanager.data.models.auth.LoginResponse;
import com.example.attendancemanager.data.models.auth.RegisterBody;
import com.example.attendancemanager.data.models.main.classes.AddClassBody;
import com.example.attendancemanager.data.models.main.classes.AllClassResponse;
import com.example.attendancemanager.data.models.main.classes.AllDepartmentsResponse;
import com.example.attendancemanager.data.models.main.student.AllStudentsResponse;
import com.example.attendancemanager.data.models.main.student.StudentBody;
import com.example.attendancemanager.data.models.main.teacher.AllTeachersResponse;
import com.example.attendancemanager.data.models.main.teacher.TeacherBody;

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
    @Override
    public Observable<Response<DefaultResponse>> addTeacher(TeacherBody body) {
        return api.addTeacher(body);
    }
    @Override
    public Observable<Response<AllTeachersResponse>> getAllTeachers() {
        return api.getAllTeachers();
    }
    @Override
    public Observable<Response<AllTeachersResponse>> getSingleTeacher(String id) {
        return api.getSingleTeacher(id);
    }
    @Override
    public Observable<Response<DefaultResponse>> patchSingleTeacher(String id, TeacherBody body) {
        return api.patchSingleTeacher(id,body);
    }
    @Override
    public Observable<Response<DefaultResponse>> deleteSingleTeacher(String id) {
        return api.deleteSingleTeacher(id);
    }
    @Override
    public Observable<Response<AllDepartmentsResponse>> getAllDepartments() {
        return api.getAllDepartments();
    }
    @Override
    public Observable<Response<DefaultResponse>> addClass(AddClassBody body) {
        return api.addClass(body);
    }
    @Override
    public Observable<Response<AllClassResponse>> getSubjectsByDepartment(String dep) {
        return api.getSubjectsByDepartment(dep);
    }


}
