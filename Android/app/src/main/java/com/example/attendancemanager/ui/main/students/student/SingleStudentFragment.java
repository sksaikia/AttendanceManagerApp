package com.example.attendancemanager.ui.main.students.student;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.attendancemanager.R;
import com.example.attendancemanager.data.models.main.student.AllStudentsResponse;
import com.example.attendancemanager.data.models.main.student.StudentBody;
import com.example.attendancemanager.data.models.main.student.StudentsResponse;
import com.example.attendancemanager.ui.main.MainViewModel;
import com.example.attendancemanager.utils.Constants;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;
import de.hdodenhof.circleimageview.CircleImageView;


public class SingleStudentFragment extends Fragment {

    private static final String TAG = "SingleStudentFragment";

    @Inject
    MainViewModel viewModel;

    String studentId = "1";

    ArrayList<StudentsResponse> mList;
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
    @BindView(R.id.edit_Student)
    Button editStudent;
    @BindView(R.id.first_name)
    TextInputLayout firstName;
    @BindView(R.id.middle_name)
    TextInputLayout middleName;
    @BindView(R.id.last_name)
    TextInputLayout lastName;
    @BindView(R.id.phone_no)
    TextInputLayout phoneNo;
    @BindView(R.id.email)
    TextInputLayout email;
    @BindView(R.id.roll_no)
    TextInputLayout rollNo;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.profile_pic)
    CircleImageView profilePic;
    @BindView(R.id.delete_Student)
    Button deleteStudent;
    @BindView(R.id.update_student)
    Button updateStudent;


    @Inject
    public SingleStudentFragment() {
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
        View view = inflater.inflate(R.layout.fragment_single_student, container, false);

        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);
        mList = new ArrayList<>();

        studentId = getArguments().getString(Constants.BUNDLE_ALL_TO_SINGLE_CAT);
        Log.d(TAG, "onCreateView: Category Id::: " + studentId);


        viewModel.getOneStudent(studentId);
        updateStudent.setEnabled(false);

        subscribeObservers();

        return view;
    }

    private void subscribeObservers() {
        subscribeObserverStatus();
        subscribeObserverResponse();
        subscribeObserverForPatchStudent();
        subscribeObserverForDeleteStudent();
    }

    private void subscribeObserverForDeleteStudent() {
        viewModel.deleteStudentStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if (x == 200) {
                    showToast("Student data updated");
                } else if (x == 401) {
                    showToast("Unauthorized");
                } else {
                    showToast("Something went wrong");
                }
            }
        });
    }

    private void subscribeObserverForPatchStudent() {
        viewModel.patchStudentsStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if (x == 200) {
                    showToast("Student data updated");
                } else if (x == 401) {
                    showToast("Unauthorized");
                } else {
                    showToast("Something went wrong");
                }
            }
        });
    }

    private void subscribeObserverResponse() {
        viewModel.getSingleStudentresponse().observe(this, new Observer<AllStudentsResponse>() {
            @Override
            public void onChanged(AllStudentsResponse allStudentsResponse) {
                mList = allStudentsResponse.getList();
                if (mList.size() >= 1) {
                    String firstName = mList.get(0).getFirstName();
                    String middle = mList.get(0).getMiddleName();
                    String last = mList.get(0).getLastName();
                    String email = mList.get(0).getEmail();
                    String phone = mList.get(0).getPhone();
                    String rollNo = mList.get(0).getRollNo();

                    Log.d(TAG, "onChanged: " + firstName + middle + last);

                    if (firstName != null)
                        etFirstName.setText(firstName);
                    if (middle != null)
                        etMiddleName.setText(middle);
                    if (last != null)
                        etLastName.setText(last);
                    if (email != null)
                        etEmail.setText(email);
                    if (phone != null)
                        etPhone.setText(phone);
                    if (rollNo != null)
                        etRollNo.setText(rollNo);
                    deActiveTextFields();

                }
            }
        });
    }

    private void deActiveTextFields(){
        etFirstName.setEnabled(false);
        etMiddleName.setEnabled(false);
        etLastName.setEnabled(false);
        etEmail.setEnabled(false);
        etPhone.setEnabled(false);
        etRollNo.setEnabled(false);
    }

    private void subscribeObserverStatus() {
        viewModel.getSingleStudentStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if (x == 200) {
                    showToast("Student data retrieved");
                } else if (x == 401) {
                    showToast("Unauthorized");
                } else {
                    showToast("Something went wrong");
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @OnClick(R.id.edit_Student)
    public void onViewClicked() {
        setUpButtonsForEdit();

    }
    private void setUpButtonsForEdit() {
        etFirstName.setEnabled(true);
        etMiddleName.setEnabled(true);
        etLastName.setEnabled(true);
        etEmail.setEnabled(true);
        etPhone.setEnabled(true);
        etRollNo.setEnabled(true);
        updateStudent.setEnabled(true);
        deleteStudent.setEnabled(false);
        editStudent.setEnabled(false);
    }

    private void showToast(String msg) {
        Snackbar snackbar = Snackbar.make(linearLayout, msg, Snackbar.LENGTH_INDEFINITE).
                setDuration(2000);
        snackbar.show();
    }

    @OnClick(R.id.delete_Student)
    public void onDeleteStudentClicked() {
        //Redirect to previous page
        new MaterialAlertDialogBuilder(getActivity())
                .setTitle("Delete a Student")
                .setMessage("Deleting a new student make changes to the database")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO send the database

                        //Show the progress bar
                        viewModel.deleteOneStudent(studentId);
                        getActivity().getSupportFragmentManager().popBackStackImmediate();

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

    @OnClick(R.id.update_student)
    public void onUpdateStudentClicked() {
        editStudent.setEnabled(true);
        deleteStudent.setEnabled(true);
        updateStudent.setEnabled(false);

        String firstName = etFirstName.getText().toString().trim();
        String middleName = etMiddleName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String phoneno = etPhone.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String rollno = etRollNo.getText().toString().trim();

        new MaterialAlertDialogBuilder(getActivity())
                .setTitle("Update a Student")
                .setMessage("updating a new student make changes to the database")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO send the database

                        //Show the progress bar
                        StudentBody body = new StudentBody(firstName, middleName, lastName, phoneno, rollno, email);
                          viewModel.patchOneStudent(studentId,body);

                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showToast("Request cancelled");
                    }
                })
                .show();
        deActiveTextFields();

    }
}