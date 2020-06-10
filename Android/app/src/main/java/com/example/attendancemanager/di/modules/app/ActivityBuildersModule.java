package com.example.attendancemanager.di.modules.app;

import com.example.attendancemanager.di.modules.auth.AuthActivityFragmentModule;
import com.example.attendancemanager.di.modules.auth.AuthActivityModule;
import com.example.attendancemanager.di.modules.main.MainActivityFragmentModule;
import com.example.attendancemanager.di.modules.main.MainActivityModule;
import com.example.attendancemanager.di.scopes.PerActivity;
import com.example.attendancemanager.ui.auth.AuthActivity;
import com.example.attendancemanager.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainActivityFragmentModule.class})
    @PerActivity
    abstract MainActivity bindMainActivity();


    @ContributesAndroidInjector(modules = {AuthActivityFragmentModule.class, AuthActivityModule.class})
    @PerActivity
    abstract AuthActivity bindAuthActivity();


}
