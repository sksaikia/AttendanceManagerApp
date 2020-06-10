package com.example.attendancemanager.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.attendancemanager.base.BaseViewModel;
import com.example.attendancemanager.data.AppDataManager;
import com.example.attendancemanager.data.models.DefaultResponse;
import com.example.attendancemanager.data.models.main.student.AllStudentsResponse;
import com.example.attendancemanager.data.models.main.student.StudentBody;

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

    //Students status
    private MutableLiveData<Integer> addStudentStatus;
    private MutableLiveData<Integer> getAllStudentsStatus;
    private MutableLiveData<Integer> getStudentStatus;
    private MutableLiveData<Integer> patchStudentsStatus;
    private MutableLiveData<Integer> deleteStudentStatus;

    //Students responses
    private MutableLiveData<AllStudentsResponse> getAllStudents;
    private MutableLiveData<AllStudentsResponse> getOneStudent;


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

}
