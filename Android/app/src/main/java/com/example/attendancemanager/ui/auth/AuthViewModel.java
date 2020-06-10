package com.example.attendancemanager.ui.auth;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.attendancemanager.base.BaseViewModel;
import com.example.attendancemanager.data.AppDataManager;
import com.example.attendancemanager.data.models.DefaultResponse;
import com.example.attendancemanager.data.models.auth.LoginBody;
import com.example.attendancemanager.data.models.auth.LoginResponse;
import com.example.attendancemanager.data.models.auth.RegisterBody;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AuthViewModel extends BaseViewModel {

    private AppDataManager appDataManager;

    public static final String TAG = "AuthActivityViewModel";


    private MutableLiveData<Integer> statusRegister;
    private MutableLiveData<Integer> statusLogin;


    //Getters for status
    public LiveData<Integer> getStatusRegister(){
        if (statusRegister==null)
            statusRegister = new MutableLiveData<>();
        return statusRegister;
    }

    public LiveData<Integer> getStatusLogin(){
        if (statusLogin==null)
            statusLogin = new MutableLiveData<>();
        return statusLogin;
    }



    @Inject
    public AuthViewModel(AppDataManager appDataManager, Application application) {
        super(appDataManager, application);

        this.appDataManager = appDataManager;
    }

    public String demoMethod(){
        return "Working";
    }


    public void registerUser(RegisterBody authBody){

        if (statusRegister==null)
            statusRegister = new MutableLiveData<>();

        appDataManager.registerUser(authBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<DefaultResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<DefaultResponse> defaultResponseResponse) {
                Log.d(TAG, "onNext: code :: " + defaultResponseResponse.code());

                statusRegister.setValue(defaultResponseResponse.code());
                if (defaultResponseResponse.code()==200)
                    loginUser(new LoginBody(authBody.getEmail(),authBody.getPassword()));
            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, "onError: " + e);
                statusRegister.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });


    }

    public void loginUser(LoginBody authBody){

        if (statusLogin==null)
            statusLogin = new MutableLiveData<>();

        appDataManager.loginUser(authBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<LoginResponse>>() {
            @Override
            public void onSubscribe(Disposable d) {
                getCompositeDisposable().add(d);
            }

            @Override
            public void onNext(Response<LoginResponse> defaultResponseResponse) {
                Log.d(TAG, "onNext: code :: " + defaultResponseResponse.code());
                statusLogin.setValue(defaultResponseResponse.code());

                if (defaultResponseResponse.code()==200){

                    appDataManager.setAccessToken(defaultResponseResponse.body().getToken());

                }
            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, "onError: " + e);
                statusLogin.setValue(500);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    public String getAuthToken(){
        return appDataManager.getAccessToken();
    }


}
