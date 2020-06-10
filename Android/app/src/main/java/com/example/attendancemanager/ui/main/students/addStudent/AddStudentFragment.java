package com.example.attendancemanager.ui.main.students.addStudent;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.attendancemanager.R;
import com.example.attendancemanager.data.models.main.student.StudentBody;
import com.example.attendancemanager.ui.main.MainViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;


public class AddStudentFragment extends Fragment {

    @BindView(R.id.et_first_name)
    TextInputEditText etFirstName;
    @BindView(R.id.et_middle_name)
    TextInputEditText etMiddleName;
    @BindView(R.id.et_last_name)
    TextInputEditText etLastName;
    @BindView(R.id.et_phone)
    TextInputEditText etPhone;
    @BindView(R.id.et_email)
    TextInputEditText etEmail;
    @BindView(R.id.et_roll_no)
    TextInputEditText etRollNo;
    @BindView(R.id.add_Student)
    Button addStudent;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @Inject
    MainViewModel viewModel;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Inject
    public AddStudentFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);

        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        subscribeObserver();

        return view;
    }

    private void subscribeObserver() {

        viewModel.getAddStudentStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if(x==200){
                    showToast("Student added");
                    progressBar.setVisibility(View.GONE);
                }else if(x==401){
                    showToast("Unauthorized");
                    progressBar.setVisibility(View.GONE);
                }else {
                    showToast("Something went wrong");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @OnClick(R.id.add_Student)
    public void onViewClicked() {

        String firstName = etFirstName.getText().toString().trim();
        String middleName = etMiddleName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String phoneno = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String rollno = etRollNo.getText().toString().trim();

        new MaterialAlertDialogBuilder(getActivity())
                .setTitle("Add a Student")
                .setMessage("Adding a new student make changes to the database")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO send the database

                        //Show the progress bar
                        StudentBody body = new StudentBody(firstName, middleName, lastName, phoneno, rollno, email);

                        viewModel.addStudent(body);

                        progressBar.setVisibility(View.VISIBLE);


                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("Request cancelled");
                    }
                })
                .show();


    }

    private void showToast(String msg) {
        Snackbar snackbar = Snackbar.make(linearLayout, msg, Snackbar.LENGTH_INDEFINITE).
                setDuration(2000);
        snackbar.show();
    }
}