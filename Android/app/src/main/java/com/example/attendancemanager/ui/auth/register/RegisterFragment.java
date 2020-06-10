package com.example.attendancemanager.ui.auth.register;

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
import com.example.attendancemanager.data.models.auth.RegisterBody;
import com.example.attendancemanager.ui.auth.AuthViewModel;
import com.example.attendancemanager.ui.auth.login.LoginFragment;
import com.example.attendancemanager.ui.main.MainActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class RegisterFragment extends Fragment {

    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @BindView(R.id.email)
    TextInputLayout email;
    @BindView(R.id.et_first_name)
    TextInputEditText etFirstName;
    @BindView(R.id.first_name)
    TextInputLayout firstName;
    @BindView(R.id.et_last_name)
    TextInputEditText etLastName;
    @BindView(R.id.last_name)
    TextInputLayout lastName;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.login)
    TextView login;

    @Inject
    AuthViewModel viewModel;
    @BindView(R.id.parent_layout)
    LinearLayout parentLayout;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        AndroidSupportInjection.inject(this);

        ButterKnife.bind(this, view);

        subscribeObserver();


        return view;
    }

    private void subscribeObserver() {
        viewModel.getStatusRegister().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                int x = integer;
                if (x == 200) {
                    showToast("Successfully Registered");
                    progressBar.setVisibility(View.GONE);
                    goToNextActivity();
                } else if (x == 403)
                    showToast("User Already exists");
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

    @OnClick({R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                registerUser();
                break;
            case R.id.login:
                initializeFragments(new LoginFragment());
                break;
        }
    }

    private void registerUser() {

        progressBar.setVisibility(View.VISIBLE);

        String email = etEmail.getText().toString();
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String password = etPassword.getText().toString();

        //Check for validity

        RegisterBody body = new RegisterBody(firstName, lastName, email, password);

        viewModel.registerUser(body);


    }


    private void goToNextActivity() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);


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
}