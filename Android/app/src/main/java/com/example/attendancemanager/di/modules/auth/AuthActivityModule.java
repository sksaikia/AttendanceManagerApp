package com.example.attendancemanager.di.modules.auth;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.example.attendancemanager.base.ViewModelFactory;
import com.example.attendancemanager.data.AppDataManager;
import com.example.attendancemanager.di.scopes.ActivityContext;
import com.example.attendancemanager.di.scopes.PerActivity;
import com.example.attendancemanager.ui.auth.AuthActivity;
import com.example.attendancemanager.ui.auth.AuthViewModel;
import com.example.attendancemanager.ui.main.MainActivity;
import com.example.attendancemanager.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class AuthActivityModule {
    @Provides
    @PerActivity
    @ActivityContext
    static Context provideAuthActivityModule(AuthActivity activity){
        return activity;
    }


    @Provides
    @PerActivity
    static AuthViewModel provideAuthViewModel(@ActivityContext Context context, Application application, AppDataManager appDataManager){
        AuthViewModel vm = new AuthViewModel(appDataManager, application);
        ViewModelFactory<AuthViewModel> factory = new ViewModelFactory<>(vm,appDataManager,application);
        return ViewModelProviders.of((AuthActivity) context,factory).get(AuthViewModel.class);
    }
}
