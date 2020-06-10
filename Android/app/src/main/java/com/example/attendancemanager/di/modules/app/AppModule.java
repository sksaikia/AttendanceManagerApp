package com.example.attendancemanager.di.modules.app;

import android.app.Application;
import android.content.Context;

import com.example.attendancemanager.data.prefs.PreferencesInfo;
import com.example.attendancemanager.data.retrofit.APIInterface;
import com.example.attendancemanager.di.scopes.ApplicationContext;
import com.example.attendancemanager.utils.Constants;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class AppModule {

    @Binds
    @Singleton
    @ApplicationContext
    abstract Context provideContext(Application application);

    @Provides
    @Singleton
    @PreferencesInfo
    static String providePrefFileName(){
        return Constants.PREF_FILE_NAME;
    }


}
