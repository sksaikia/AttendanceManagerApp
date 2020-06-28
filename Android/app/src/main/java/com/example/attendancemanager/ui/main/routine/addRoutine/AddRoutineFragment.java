package com.example.attendancemanager.ui.main.routine.addRoutine;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.attendancemanager.R;
import com.example.attendancemanager.data.models.main.classes.AllClassResponse;
import com.example.attendancemanager.data.models.main.classes.AllDepartmentsResponse;
import com.example.attendancemanager.data.models.main.classes.ClassResponse;
import com.example.attendancemanager.data.models.main.classes.DepartmentResponse;
import com.example.attendancemanager.ui.main.MainViewModel;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;

public class AddRoutineFragment extends Fragment {

    TimePickerDialog startTimeDialog;
    TimePickerDialog endTimeDialog;

    ArrayList<DepartmentResponse> mList;
    ArrayList<ClassResponse> subjectList;

    private static final String TAG = "AddRoutineFragment";
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.spinner_department)
    Spinner spinnerDepartment;
    @BindView(R.id.session)
    TextView session;
    @BindView(R.id.spinner_session)
    Spinner spinnerSession;
    @BindView(R.id.teacher)
    TextView teacher;
    @BindView(R.id.spinner_teacher)
    Spinner spinnerTeacher;
    @BindView(R.id.semester)
    TextView semester;
    @BindView(R.id.spinner_semester)
    Spinner spinnerSemester;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.spinner_day)
    Spinner spinnerDay;
    @BindView(R.id.add_class)
    Button addClass;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.linear)
    LinearLayout linear;
    @BindView(R.id.linear_layout)
    ScrollView linearLayout;
    @BindView(R.id.et_start_time)
    TextView etStartTime;
    @BindView(R.id.et_end_time)
    TextView etEndTime;

    @Inject
    MainViewModel viewModel;
    @BindView(R.id.subject_name)
    TextView subjectName;
    @BindView(R.id.spinner_subject)
    Spinner spinnerSubject;

    @Inject
    public AddRoutineFragment() {
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
        View view = inflater.inflate(R.layout.fragment_add_routine, container, false);


        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);
        mList = new ArrayList<>();

        setUpSpinner(view);


        viewModel.getAllDepartments();
        subscribeObservers();

        return view;
    }

    private void subscribeObservers() {
        subscribeObserversForDepartments();
        subscribeObsserverForSubjectByDepartment();
    }

    private void subscribeObsserverForSubjectByDepartment() {
        viewModel.getAllSubjectsForDepartment().observe(this, new Observer<AllClassResponse>() {
            @Override
            public void onChanged(AllClassResponse allDepartmentsResponse) {
                subjectList = allDepartmentsResponse.getList();

                Log.d(TAG, "onChanged: " + subjectList.size());

                setUpSubjectsSpinner(subjectList);
            }
        });
    }

    private void setUpSubjectsSpinner(ArrayList<ClassResponse> subjectList) {

        ArrayList<String> mainList = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();
        for (ClassResponse c : subjectList) {
            mainList.add(c.getName());
            idList.add(c.getId());
        }

        ArrayAdapter<String> bloodGroupAdapter;
        bloodGroupAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                mainList);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSubject.setAdapter(bloodGroupAdapter);

        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
                Log.d(TAG, "onItemClick: dep name : " + mainList.get(position));
                Log.d(TAG, "onItemSelected: dep id : " + idList.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @OnClick(R.id.et_start_time)
    public void onStartTimeClicked() {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        startTimeDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        etStartTime.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
        startTimeDialog.show();
    }

    @OnClick(R.id.et_end_time)
    public void onEndTimeClicked() {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        endTimeDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        etEndTime.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
        endTimeDialog.show();
    }

    private void setUpSpinner(View view) {


        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.semesters));
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(bloodGroupAdapter);


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
        ArrayList<String> idList = new ArrayList<>();
        for (DepartmentResponse c : mList) {
            mainList.add(c.getDep_name());
            idList.add(c.getDep_id());
        }

        ArrayAdapter<String> bloodGroupAdapter;
        bloodGroupAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                mainList);
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(bloodGroupAdapter);

        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
                Log.d(TAG, "onItemClick: dep name : " + mainList.get(position));
                Log.d(TAG, "onItemSelected: dep id : " + idList.get(position));
                String s = mainList.get(position);

                viewModel.getSubjectsByDepartment(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}