package com.example.attendancemanager.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.attendancemanager.R;
import com.example.attendancemanager.ui.auth.login.LoginFragment;
import com.example.attendancemanager.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AuthActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private static final String TAG = "AuthActivity";

    @Inject
    AuthViewModel authViewModel;

    FragmentManager fragmentManager;

    @Inject
    LoginFragment loginFragment;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    AuthViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);


        AndroidInjection.inject(this);

        checkAuth();


        Log.d(TAG, "onCreate: " + authViewModel.demoMethod());

        fragmentManager = getSupportFragmentManager();

        initFrag(loginFragment);

    }

    private void checkAuth() {
        String str = viewModel.getAuthToken();
        Log.d(TAG, "checkAuth: " + str);
        Log.d(TAG, "checkAuth: " + str.length());
        if (str!=null && str.length()>0){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initFrag(Fragment fragment) {

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.frame_layout_main, fragment);
        ft.commit();


    }


    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

}