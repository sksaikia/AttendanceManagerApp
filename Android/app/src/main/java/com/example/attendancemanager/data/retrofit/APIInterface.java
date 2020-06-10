package com.example.attendancemanager.data.retrofit;

import com.example.attendancemanager.data.models.DefaultResponse;
import com.example.attendancemanager.data.models.auth.LoginBody;
import com.example.attendancemanager.data.models.auth.LoginResponse;
import com.example.attendancemanager.data.models.auth.RegisterBody;
import com.example.attendancemanager.data.models.main.student.AllStudentsResponse;
import com.example.attendancemanager.data.models.main.student.StudentBody;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    //Registration
    @POST("api/auth/signup")
    Observable<Response<DefaultResponse>> registerUser(@Body RegisterBody authBody);


    @POST("api/auth/login")
    Observable<Response<LoginResponse>> loginUser(@Body LoginBody authBody);




    //Students
    @POST("api/students/addStudent")
    Observable<Response<DefaultResponse>> addStudent(@Body StudentBody studentBody);

    @GET("api/students/getAllStudents")
    Observable<Response<AllStudentsResponse>> getAllStudents();

    @GET("api/students/getStudent/{id}")
    Observable<Response<AllStudentsResponse>> getSingleStudent(@Path("id") String id);

    @PATCH("api/students/getStudent/{id}")
    Observable<Response<DefaultResponse>> updateSingleStudent(@Path("id") String id,@Body StudentBody body);

    @DELETE("api/students/getStudent/{id}")
    Observable<Response<DefaultResponse>> deleteSingleStudent(@Path("id") String id);


}
