package com.example.attendancemanager.ui.main.teachers.allteachers;

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
import com.example.attendancemanager.data.models.main.teacher.AllTeachersResponse;
import com.example.attendancemanager.data.models.main.teacher.TeacherResponse;
import com.example.attendancemanager.ui.main.MainViewModel;
import com.example.attendancemanager.ui.main.students.allstudents.adapter.StudentsAdapter;
import com.example.attendancemanager.ui.main.teachers.allteachers.adapter.TeachersAdapter;
import com.example.attendancemanager.ui.main.teachers.teacher.SingleTeacherFragment;
import com.example.attendancemanager.utils.Constants;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;


public class AllTeachersFragment extends Fragment {

    private static final String TAG = "AllTeachersFragment";
    ArrayList<TeacherResponse> mList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;

    @Inject
    SingleTeacherFragment singleTeacherFragment;

    @Inject
    MainViewModel viewModel;

    @Inject
    TeachersAdapter adapter;


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Log.d(TAG, "onClick: POsition ::: " + position);


            String name = mList.get(position).getId();

            Bundle bundle = new Bundle();
            bundle.putString(Constants.BUNDLE_ALL_TO_SINGLE_CAT, name);
            singleTeacherFragment.setArguments(bundle);

            initializeFragments(singleTeacherFragment);

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


    @Inject
    public AllTeachersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_all_teachers, container, false);
        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        mList = new ArrayList<>();
        viewModel.getAllTeachers();
        setUpRecyclerView(recyclerView, adapter);

        subscribeForStatus();
        subscribeForCategoryData();


        return view;
    }

    private void subscribeForCategoryData() {
        viewModel.getAllTeachersResponse().observe(this, new Observer<AllTeachersResponse>() {
            @Override
            public void onChanged(AllTeachersResponse allTeachersResponse) {
                mList = allTeachersResponse.getList();
                Log.d(TAG, "onChanged: " + mList.size());
                adapter.isShimmer = false;
                adapter.updateListData(mList);
            }
        });
    }

    private void subscribeForStatus() {
        viewModel.getAllTeachersStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int x = integer;
                if (x == 200) {
                    showToast("Teachers data retrieved");
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


    private void setUpRecyclerView(RecyclerView recyclerView, TeachersAdapter adapter) {

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