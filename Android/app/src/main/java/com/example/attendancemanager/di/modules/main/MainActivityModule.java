package com.example.attendancemanager.di.modules.main;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.example.attendancemanager.base.ViewModelFactory;
import com.example.attendancemanager.data.AppDataManager;
import com.example.attendancemanager.di.scopes.ActivityContext;
import com.example.attendancemanager.di.scopes.ApplicationContext;
import com.example.attendancemanager.di.scopes.PerActivity;
import com.example.attendancemanager.ui.main.MainActivity;
import com.example.attendancemanager.ui.main.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Provides
    @PerActivity
    @ActivityContext
    static Context provideMainActivityModule(MainActivity activity){
        return activity;
    }


    @Provides
    @PerActivity
    static MainViewModel provideMainViewModel(@ActivityContext Context context, Application application, AppDataManager appDataManager){
        MainViewModel vm = new MainViewModel(appDataManager, application);
        ViewModelFactory<MainViewModel> factory = new ViewModelFactory<>(vm,appDataManager,application);
        return ViewModelProviders.of((MainActivity) context,factory).get(MainViewModel.class);
    }
}
