package com.example.attendancemanager.ui.auth.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.example.attendancemanager.R;
import com.example.attendancemanager.data.models.auth.LoginBody;
import com.example.attendancemanager.ui.auth.AuthViewModel;
import com.example.attendancemanager.ui.auth.register.RegisterFragment;
import com.example.attendancemanager.ui.main.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class LoginFragment extends Fragment {


    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @BindView(R.id.email)
    TextInputLayout email;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    Button login;

    @Inject
    RegisterFragment registerFragment;

    @Inject
    AuthViewModel authViewModel;
    @BindView(R.id.parent_layout)
    LinearLayout parentLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_login, container, false);

        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);


        subscribeObservers();

        return view;
    }

    private void subscribeObservers() {
        authViewModel.getStatusLogin().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;

                if (x == 200) {
                    showToast("Successful login");
                    progressBar.setVisibility(View.GONE);
                    goToNextActivity();
                } else if (x == 401)
                    showToast("Check your credentials");
                else if (x == 500)
                    showToast("Somewhere,Somehow Something went show");
                    progressBar.setVisibility(View.GONE);
            }
        });
    }


    public void showToast(String msg) {
        Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }


    @OnClick(R.id.register)
    public void goToRegisterFragment() {

        initializeFragments(registerFragment);

    }

    @OnClick(R.id.login)
    public void doLogin() {
        logInUser();
    }

    public void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //  transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout_main, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

    private void goToNextActivity() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


    }

    private void logInUser() {

        progressBar.setVisibility(View.VISIBLE);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        //Check for validity

        LoginBody body = new LoginBody(email, password);

        authViewModel.loginUser(body);


    }

}