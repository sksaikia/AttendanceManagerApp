package com.example.attendancemanager.di.modules.main;

import com.example.attendancemanager.ui.main.classes.addClass.AddClassFragment;
import com.example.attendancemanager.ui.main.classes.home.ClassHomeFragment;
import com.example.attendancemanager.ui.main.home.HomeFragment;
import com.example.attendancemanager.ui.main.routine.addRoutine.AddRoutineFragment;
import com.example.attendancemanager.ui.main.routine.home.RoutineHomeFragment;
import com.example.attendancemanager.ui.main.students.addStudent.AddStudentFragment;
import com.example.attendancemanager.ui.main.students.allstudents.AllStudentsFragment;
import com.example.attendancemanager.ui.main.students.home.StudentsHomeFragment;
import com.example.attendancemanager.ui.main.students.student.SingleStudentFragment;
import com.example.attendancemanager.ui.main.teachers.addTeacher.AddTeacherFragment;
import com.example.attendancemanager.ui.main.teachers.allteachers.AllTeachersFragment;
import com.example.attendancemanager.ui.main.teachers.home.TeachersHomeFragment;
import com.example.attendancemanager.ui.main.teachers.teacher.SingleTeacherFragment;

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

    @ContributesAndroidInjector
    abstract TeachersHomeFragment bindTeacherHomeFragment();

    @ContributesAndroidInjector
    abstract AddTeacherFragment bindAddTeacherFragment();

    @ContributesAndroidInjector
    abstract AllTeachersFragment bindAllteachersFragment();

    @ContributesAndroidInjector
    abstract SingleTeacherFragment bindSingleTeacherFragment();

    @ContributesAndroidInjector
    abstract AddClassFragment bindAddClassFragment();

    @ContributesAndroidInjector
    abstract ClassHomeFragment bindClassHomeFragment();

    @ContributesAndroidInjector
    abstract AddRoutineFragment bindAddRoutineFragment();

    @ContributesAndroidInjector
    abstract RoutineHomeFragment bindRoutineHomeFragment();


}
