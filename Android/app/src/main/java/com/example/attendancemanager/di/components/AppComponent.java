package com.example.attendancemanager.di.components;

import android.app.Application;

import com.example.attendancemanager.base.BaseApplication;
import com.example.attendancemanager.data.retrofit.APIInterface;
import com.example.attendancemanager.di.modules.app.ActivityBuildersModule;
import com.example.attendancemanager.di.modules.app.AppModule;
import com.example.attendancemanager.di.modules.app.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = { ActivityBuildersModule.class
        ,AndroidInjectionModule.class,
        AppModule.class,RetrofitModule.class})
public interface AppComponent {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }

}
