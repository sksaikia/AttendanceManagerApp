package com.example.attendancemanager.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.attendancemanager.base.BaseViewModel;
import com.example.attendancemanager.data.AppDataManager;
import com.example.attendancemanager.data.models.DefaultResponse;
import com.example.attendancemanager.data.models.main.classes.AddClassBody;
import com.example.attendancemanager.data.models.main.classes.AllClassResponse;
import com.example.attendancemanager.data.models.main.classes.AllDepartmentsResponse;
import com.example.attendancemanager.data.models.main.student.AllStudentsResponse;
import com.example.attendancemanager.data.models.main.student.StudentBody;
import com.example.attendancemanager.data.models.main.teacher.AllTeachersResponse;
import com.example.attendancemanager.data.models.main.teacher.TeacherBody;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainViewModel extends BaseViewModel {

    private AppDataManager appDataManager;
    public static final String TAG = "MainActivityViewModel";

    // status
    private MutableLiveData<Integer> addStudentStatus;
    private MutableLiveData<Integer> getAllStudentsStatus;
    private MutableLiveData<Integer> getStudentStatus;
    private MutableLiveData<Integer> patchStudentsStatus;
    private MutableLiveData<Integer> deleteStudentStatus;
    private MutableLiveData<Integer> addTeacherStatus;
    private MutableLiveData<Integer> getAllTeachersStatus;
    private MutableLiveData<Integer> getSingleTeacherStatus;
    private MutableLiveData<Integer> patchTeacherStatus;
    private MutableLiveData<Integer> deleteTeacherStatus;
    private MutableLiveData<Integer> getAllDepartmentsStatus;
    private MutableLiveData<Integer> addClassStatus;
    private MutableLiveData<Integer> getSubjectsByDepartment;


    // responses
    private MutableLiveData<AllStudentsResponse> getAllStudents;
    private MutableLiveData<AllStudentsResponse> getOneStudent;
    private MutableLiveData<AllTeachersResponse> getAllTeachers;
    private MutableLiveData<AllTeachersResponse> getOneTeacher;
    private MutableLiveData<AllDepartmentsResponse> getAllDepartments;
    private MutableLiveData<AllClassResponse> getSubjectsByDepartments;

    //Getters
    public LiveData<Integer> getAddStudentStatus(){

        if (addStudentStatus==null)
            addStudentStatus = new MutableLiveData<>();
        return addStudentStatus;

    }
    public LiveData<Integer> getAllStudentsStatus(){
        if (getAllStudentsStatus==null)
            getAllStudentsStatus = new MutableLiveData<>();
        return getAllStudentsStatus;

    }
    public LiveData<Integer> getSingleStudentStatus(){
        if (getStudentStatus==null)
            getStudentStatus = new MutableLiveData<>();
        return getStudentStatus;
    }
    public LiveData<Integer> patchStudentsStatus(){
        if (patchStudentsStatus==null)
            patchStudentsStatus = new MutableLiveData<>();
        return patchStudentsStatus;
    }
    public LiveData<Integer> deleteStudentStatus(){
        if (deleteStudentStatus==null)
            deleteStudentStatus = new MutableLiveData<>();
        return deleteStudentStatus;
    }
    public LiveData<Integer> getAddTeacherStatus(){
        if (addTeacherStatus==null)
            addTeacherStatus = new MutableLiveData<>();
        return addTeacherStatus;
    }
    public LiveData<Integer> getAllTeachersStatus(){
        if (getAllTeachersStatus==null)
            getAllTeachersStatus = new MutableLiveData<>();
        return getAllTeachersStatus;
    }
    public LiveData<Integer> getSingleTeacherStatus(){
        if (getSingleTeacherStatus==null)
            getSingleTeacherStatus = new MutableLiveData<>();
        return getSingleTeacherStatus;
    }
    public LiveData<Integer> patchTeacherStatus(){
        if (patchTeacherStatus==null)
            patchTeacherStatus = new MutableLiveData<>();
        return patchTeacherStatus;
    }
    public LiveData<Integer> deleteTeacherStatus(){
        if (deleteTeacherStatus==null)
            deleteTeacherStatus = new MutableLiveData<>();
        return deleteTeacherStatus;
    }
    public LiveData<Integer> getAllDepartmentsStatus(){
        if (getAllDepartmentsStatus==null)
            getAllDepartmentsStatus = new MutableLiveData<>();
        return getAllDepartmentsStatus;
    }
    public LiveData<Integer> addClassStatus(){
        if (addClassStatus==null)
            addClassStatus = new MutableLiveData<>();
        return addClassStatus;
    }
    public LiveData<Integer> getSubjectsByDepartmentStatus(){

        if (getSubjectsByDepartment==null)
            getSubjectsByDepartment = new MutableLiveData<>();
        return getSubjectsByDepartment;

    }


    //Response getters
    public LiveData<AllStudentsResponse> getAllStudentsResponse(){
        if (getAllStudents==null)
            getAllStudents = new MutableLiveData<AllStudentsResponse>();
        return getAllStudents;
    }
    public LiveData<AllStudentsResponse> getSingleStudentresponse(){
        if (getOneStudent==null)
            getOneStudent = new MutableLiveData<>();
        return getOneStudent;
    }
    public LiveData<AllTeachersResponse> getAllTeachersResponse(){
        if (getAllTeachers==null)
            getAllTeachers = new MutableLiveData<>();
        return getAllTeachers;
    }
    public LiveData<AllTeachersResponse> getSingleTeachersResponse(){
        if (getOneTeacher==null)
            getOneTeacher = new MutableLiveData<>();
        return getOneTeacher;
    }
    public LiveData<AllDepartmentsResponse> getAllDepartmentsResponse(){
        if (getAllDepartments ==null)
            getAllDepartments = new MutableLiveData<>();
        return getAllDepartments;
    }


    public LiveData<AllClassResponse> getAllSubjectsForDepartment(){
        if (getSubjectsByDepartments ==null)
            getSubjectsByDepartments = new MutableLiveData<>();
        return getSubjectsByDepartments;
    }

    @Inject
    public MainViewModel(AppDataManager appDataManager, Application application) {
        super(appDataManager, application);
        this.appDataManager = appDataManager;
    }
    public void addStudent(StudentBody studentBody){

        if (addStudentStatus==null)
            addStudentStatus = new MutableLiveData<>();

        appDataManager.addStudent(studentBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<DefaultResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Response<DefaultResponse> defaultResponseResponse) {

                        addStudentStatus.setValue(defaultResponseResponse.code());
                        Log.d(TAG, "onNext: code " + defaultResponseResponse.code());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        addStudentStatus.setValue(500);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    public void getAllStudents(){
        if (getAllStudents==null)
            getAllStudents = new MutableLiveData<AllStudentsResponse>();

        if (getAllStudentsStatus==null)
            getAllStudentsStatus = new MutableLiveData<>();

        appDataManager.getAllStudents().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllStudentsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllStudentsResponse> allStudentsResponseResponse) {
                getAllStudentsStatus.setValue(allStudentsResponseResponse.code());
                if (allStudentsResponseResponse.code() == 200) {
                    getAllStudents.setValue(allStudentsResponseResponse.body());
                    Log.d(TAG, "onNext: " + allStudentsResponseResponse.body().getList().size());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e);
                getAllStudentsStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });

    }
    public void getOneStudent(String id){
        if (getOneStudent==null)
            getOneStudent = new MutableLiveData<>();
        if (getStudentStatus==null)
            getStudentStatus = new MutableLiveData<>();

        appDataManager.getSingleStudent(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllStudentsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllStudentsResponse> allStudentsResponseResponse) {

                getStudentStatus.setValue(allStudentsResponseResponse.code());

                if (allStudentsResponseResponse.code()==200){
                    getOneStudent.setValue(allStudentsResponseResponse.body());
                }

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);
                getStudentStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });


    }
    public void patchOneStudent(String id,StudentBody body){
        if (patchStudentsStatus==null)
            patchStudentsStatus = new MutableLiveData<>();

        appDataManager.updateSingleStudent(id,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                patchStudentsStatus.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(Throwable e) {
                patchStudentsStatus.setValue(500);
                Log.e(TAG, "onError: ",e );
            }

            @Override
            public void onComplete() {

            }
        });

    }
    public void deleteOneStudent(String id){
        if (deleteStudentStatus==null)
            deleteStudentStatus = new MutableLiveData<>();
        appDataManager.deleteSingleStudent(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                deleteStudentStatus.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ",e );
                deleteStudentStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void addATeacher(TeacherBody body){
        if (addTeacherStatus==null)
            addTeacherStatus = new MutableLiveData<>();

        appDataManager.addTeacher(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                addTeacherStatus.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(Throwable e) {
                addTeacherStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getAllTeachers(){
        if (getAllTeachers==null)
            getAllTeachers = new MutableLiveData<>();
        if (getAllTeachersStatus==null)
            getAllTeachersStatus = new MutableLiveData<>();

        appDataManager.getAllTeachers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllTeachersResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllTeachersResponse> allTeachersResponseResponse) {
                getAllTeachersStatus.setValue(allTeachersResponseResponse.code());
                if (allTeachersResponseResponse.code()==200)
                    getAllTeachers.setValue(allTeachersResponseResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                getAllTeachersStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getSingleTeacher(String id){
        if (getOneTeacher==null)
            getOneTeacher = new MutableLiveData<>();
        if (getSingleTeacherStatus==null)
            getSingleTeacherStatus = new MutableLiveData<>();

        appDataManager.getSingleTeacher(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllTeachersResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllTeachersResponse> allTeachersResponseResponse) {
                getSingleTeacherStatus.setValue(allTeachersResponseResponse.code());
                if (allTeachersResponseResponse.code()==200)
                    getOneTeacher.setValue(allTeachersResponseResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                getSingleTeacherStatus.setValue(500);
                Log.e(TAG, "onError: ",e );
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void patchSingleTeacher(String id,TeacherBody body){
        if (patchTeacherStatus==null)
            patchTeacherStatus = new MutableLiveData<>();

        appDataManager.patchSingleTeacher(id,body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                patchTeacherStatus.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(Throwable e) {
                patchTeacherStatus.setValue(500);
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void deleteSingleTeacher(String id){
        if (deleteTeacherStatus==null)
            deleteTeacherStatus = new MutableLiveData<>();
        appDataManager.deleteSingleTeacher(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                deleteTeacherStatus.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(Throwable e) {
                deleteTeacherStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void getAllDepartments(){
        if (getAllDepartments ==null)
            getAllDepartments = new MutableLiveData<>();
        if (getAllDepartmentsStatus==null)
            getAllDepartmentsStatus = new MutableLiveData<>();
        appDataManager.getAllDepartments().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllDepartmentsResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllDepartmentsResponse> allDepartmentsResponseResponse) {
                getAllDepartmentsStatus.setValue(allDepartmentsResponseResponse.code());
                if (allDepartmentsResponseResponse.code()==200)
                    getAllDepartments.setValue(allDepartmentsResponseResponse.body());
            }

            @Override
            public void onError(Throwable e) {
                getAllDepartmentsStatus.setValue(500);
                Log.e(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
    public void addClass(AddClassBody body){
        if (addClassStatus==null)
            addClassStatus = new MutableLiveData<>();
        appDataManager.addClass(body).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                addClassStatus.setValue(defaultResponseResponse.code());
            }

            @Override
            public void onError(Throwable e) {
                addClassStatus.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void getSubjectsByDepartment(String department){
        if (getSubjectsByDepartments==null)
            getSubjectsByDepartments = new MutableLiveData<>();
        if (getSubjectsByDepartment==null)
            getSubjectsByDepartment = new MutableLiveData<>();
        appDataManager.getSubjectsByDepartment(department).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AllClassResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<AllClassResponse> allDepartmentsResponseResponse) {
                getSubjectsByDepartment.setValue(allDepartmentsResponseResponse.code());
                if (allDepartmentsResponseResponse.code()==200){
                    getSubjectsByDepartments.setValue(allDepartmentsResponseResponse.body());
                }
            }

            @Override
            public void onError(Throwable e) {
                getSubjectsByDepartment.setValue(404);
            }

            @Override
            public void onComplete() {

            }
        });
    }


}
