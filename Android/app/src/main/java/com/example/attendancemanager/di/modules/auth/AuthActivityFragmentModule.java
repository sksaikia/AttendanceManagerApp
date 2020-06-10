package com.example.attendancemanager.di.modules.auth;

import com.example.attendancemanager.ui.auth.login.LoginFragment;
import com.example.attendancemanager.ui.auth.register.RegisterFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@Module
public abstract class AuthActivityFragmentModule {

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract RegisterFragment bindRegisterFragment();


}
