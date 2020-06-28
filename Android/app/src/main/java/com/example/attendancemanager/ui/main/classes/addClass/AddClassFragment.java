package com.example.attendancemanager.ui.main.classes.addClass;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.attendancemanager.R;
import com.example.attendancemanager.data.models.main.classes.AddClassBody;
import com.example.attendancemanager.data.models.main.classes.AllDepartmentsResponse;
import com.example.attendancemanager.data.models.main.classes.DepartmentResponse;
import com.example.attendancemanager.ui.main.MainViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;


public class AddClassFragment extends Fragment {

    @Inject
    MainViewModel viewModel;
    @BindView(R.id.et_course_name)
    TextInputEditText etCourseName;
    @BindView(R.id.course_name)
    TextInputLayout courseName;
    @BindView(R.id.semester)
    TextView semester;
    @BindView(R.id.spinner_department)
    Spinner spinnerDepartment;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.spinner_semester)
    Spinner spinnerSemester;
    @BindView(R.id.et_subject_code)
    TextInputEditText etSubjectCode;
    @BindView(R.id.subject_code)
    TextInputLayout subjectCode;


    private static final String TAG = "AddClassFragment";
    ArrayList<DepartmentResponse> mList;
    @BindView(R.id.add_class)
    Button addClass;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;


    @Inject
    public AddClassFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_class, container, false);

        mList = new ArrayList<>();
        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);
        setUpSpinner(view);

        viewModel.getAllDepartments();

        subscribeObserversForDepartments();
        subscribeObserverForAddClass();

        return view;
    }

    private void subscribeObserverForAddClass() {
        viewModel.addClassStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                progressBar.setVisibility(View.GONE);
                if (x == 200) {

                    showToast("New class added");

                } else if (x == 401) {

                    showToast("Unauthorized");

                } else {

                    showToast("Something went wrong");

                }
            }
        });
    }

    private void subscribeObserversForDepartments() {
        viewModel.getAllDepartmentsResponse().observe(this, new Observer<AllDepartmentsResponse>() {
            @Override
            public void onChanged(AllDepartmentsResponse allDepartmentsResponse) {

                mList = allDepartmentsResponse.getList();

                Log.d(TAG, "onChanged: " + mList.size());

                setUpDepartmentsSpinner(mList);
            }
        });
    }

    private void setUpDepartmentsSpinner(ArrayList<DepartmentResponse> mList) {

        ArrayList<String> mainList = new ArrayList<>();
        for (DepartmentResponse c : mList)
            mainList.add(c.getDep_name());

        ArrayAdapter<String> bloodGroupAdapter;
        bloodGroupAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                mainList);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(bloodGroupAdapter);

    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void setUpSpinner(View view) {


        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.semesters));
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(bloodGroupAdapter);


    }


    @OnClick(R.id.add_class)
    public void onViewClicked() {

        progressBar.setVisibility(View.VISIBLE);

        String course = etCourseName.getText().toString();
        String code = etSubjectCode.getText().toString();
        String semester = spinnerSemester.getSelectedItem().toString();
        String department = spinnerDepartment.getSelectedItem().toString();

        viewModel.addClass(new AddClassBody(course, semester, department, code));

    }


    private void showToast(String msg) {
        Snackbar snackbar = Snackbar.make(linearLayout, msg, Snackbar.LENGTH_INDEFINITE).
                setDuration(2000);
        snackbar.show();
    }


}