package com.example.attendancemanager.data;

import android.content.Context;

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
import com.example.attendancemanager.data.prefs.AppPreferencesHelper;
import com.example.attendancemanager.data.retrofit.AppApiHelper;
import com.example.attendancemanager.di.scopes.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Response;

@Singleton
public class AppDataManager implements AppDataManagerHelper{

    private Context context;
    private AppPreferencesHelper preferencesHelper;
    private AppApiHelper apiHelper;
    private static final String TAG = "AppDataManager";

    @Inject
    public AppDataManager(@ApplicationContext Context context,AppPreferencesHelper appPreferencesHelper
    ,AppApiHelper apiHelper){
        this.context = context;
        this.preferencesHelper = appPreferencesHelper;
        this.apiHelper = apiHelper;
    }
    @Override
    public String getAccessToken() {
        return preferencesHelper.getAccessToken();
    }
    @Override
    public void setAccessToken(String token) {
        preferencesHelper.setAccessToken(token);
    }
    @Override
    public String getUserId() {
        return preferencesHelper.getUserId();
    }
    @Override
    public Observable<Response<DefaultResponse>> registerUser(RegisterBody authBody) {
        return apiHelper.registerUser(authBody);
    }
    @Override
    public Observable<Response<LoginResponse>> loginUser(LoginBody authBody) {
        return apiHelper.loginUser(authBody);
    }
    @Override
    public Observable<Response<DefaultResponse>> addStudent(StudentBody studentBody) {
        return apiHelper.addStudent(studentBody);
    }
    @Override
    public Observable<Response<AllStudentsResponse>> getAllStudents() {
        return apiHelper.getAllStudents();
    }
    @Override
    public Observable<Response<AllStudentsResponse>> getSingleStudent(String id) {
        return apiHelper.getSingleStudent(id);
    }
    @Override
    public Observable<Response<DefaultResponse>> updateSingleStudent(String id, StudentBody body) {
        return apiHelper.updateSingleStudent(id,body);
    }
    @Override
    public Observable<Response<DefaultResponse>> deleteSingleStudent(String id) {
        return apiHelper.deleteSingleStudent(id);
    }
    @Override
    public Observable<Response<DefaultResponse>> addTeacher(TeacherBody body) {
        return apiHelper.addTeacher(body);
    }
    @Override
    public Observable<Response<AllTeachersResponse>> getAllTeachers() {
        return apiHelper.getAllTeachers();
    }
    @Override
    public Observable<Response<AllTeachersResponse>> getSingleTeacher(String id) {
        return apiHelper.getSingleTeacher(id);
    }
    @Override
    public Observable<Response<DefaultResponse>> patchSingleTeacher(String id, TeacherBody body) {
        return apiHelper.patchSingleTeacher(id,body);
    }
    @Override
    public Observable<Response<DefaultResponse>> deleteSingleTeacher(String id) {
        return apiHelper.deleteSingleTeacher(id);
    }
    @Override
    public Observable<Response<AllDepartmentsResponse>> getAllDepartments() {
        return apiHelper.getAllDepartments();
    }
    @Override
    public Observable<Response<DefaultResponse>> addClass(AddClassBody body) {
        return apiHelper.addClass(body);
    }
    @Override
    public Observable<Response<AllClassResponse>> getSubjectsByDepartment(String dep) {
        return apiHelper.getSubjectsByDepartment(dep);
    }


}
