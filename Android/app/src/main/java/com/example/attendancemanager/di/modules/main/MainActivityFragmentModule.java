package com.example.attendancemanager.di.modules.main;

import com.example.attendancemanager.ui.main.home.HomeFragment;
import com.example.attendancemanager.ui.main.students.addStudent.AddStudentFragment;
import com.example.attendancemanager.ui.main.students.allstudents.AllStudentsFragment;
import com.example.attendancemanager.ui.main.students.home.StudentsHomeFragment;
import com.example.attendancemanager.ui.main.students.student.SingleStudentFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    abstract HomeFragment bindDemoHomeFragment();

    @ContributesAndroidInjector
    abstract StudentsHomeFragment bindStudentsHomeFragment();

    @ContributesAndroidInjector
    abstract AddStudentFragment bindAddStudentFragment();

    @ContributesAndroidInjector
    abstract AllStudentsFragment bindAllStudentsFragment();

    @ContributesAndroidInjector
    abstract SingleStudentFragment bindSingleStudentFragment();


}
