package com.example.attendancemanager.ui.main.students.allstudents;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.data.models.main.student.AllStudentsResponse;
import com.example.attendancemanager.data.models.main.student.StudentsResponse;
import com.example.attendancemanager.ui.main.MainViewModel;
import com.example.attendancemanager.ui.main.students.allstudents.adapter.StudentsAdapter;
import com.example.attendancemanager.ui.main.students.student.SingleStudentFragment;
import com.example.attendancemanager.utils.Constants;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;


public class AllStudentsFragment extends Fragment {

    private static final String TAG = "AllStudentsFragment";

    @Inject
    StudentsAdapter adapter;

    ArrayList<StudentsResponse> mList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    MainViewModel viewModel;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @Inject
    SingleStudentFragment singleStudentFragment;

    @Inject
    public AllStudentsFragment() {
        // Required empty public constructor
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Log.d(TAG, "onClick: POsition ::: " + position);


            String name = mList.get(position).getId();

            Bundle bundle = new Bundle();
            bundle.putString(Constants.BUNDLE_ALL_TO_SINGLE_CAT, name);
            singleStudentFragment.setArguments(bundle);

            initializeFragments(singleStudentFragment);

        }
    };

    private void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_students, container, false);

        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);


        mList = new ArrayList<>();

        viewModel.getAllStudents();

        setUpRecyclerView(recyclerView, adapter);

        //
        subscribeForStatus();
        subscribeForCategoryData();


        return view;
    }

    private void subscribeForStatus() {
        viewModel.getAllStudentsStatus().observe(this, new Observer<Integer>() {
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

    private void subscribeForCategoryData() {
        viewModel.getAllStudentsResponse().observe(this, new Observer<AllStudentsResponse>() {
            @Override
            public void onChanged(AllStudentsResponse allStudentsResponse) {
                mList = allStudentsResponse.getList();
                Log.d(TAG, "onChanged: " + mList.size());
                adapter.isShimmer = false;
                adapter.updateListData(mList);

            }
        });
    }


    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void setUpRecyclerView(RecyclerView recyclerView, StudentsAdapter adapter) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);


    }

    private void showToast(String msg) {
        Snackbar snackbar = Snackbar.make(linearLayout, msg, Snackbar.LENGTH_INDEFINITE).
                setDuration(2000);
        snackbar.show();
    }

}