package com.example.attendancemanager.ui.main.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.ui.main.classes.home.ClassHomeFragment;
import com.example.attendancemanager.ui.main.home.adapter.HomeAdapter;
import com.example.attendancemanager.ui.main.home.adapter.HomeItem;
import com.example.attendancemanager.ui.main.routine.home.RoutineHomeFragment;
import com.example.attendancemanager.ui.main.students.home.StudentsHomeFragment;
import com.example.attendancemanager.ui.main.teachers.home.TeachersHomeFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class HomeFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    ArrayList<HomeItem> mList;
    FragmentManager fragmentManager;

    private static final String TAG = "HomeFragment";

    @Inject
    HomeAdapter adapter;

    @Inject
    StudentsHomeFragment studentsHomeFragment;

    @Inject
    TeachersHomeFragment teachersHomeFragment;

    @Inject
    ClassHomeFragment classHomeFragment;

    @Inject
    RoutineHomeFragment routineHomeFragment;

    @Inject
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Log.d(TAG, "onClick: POsition ::: " + position);

            goToFragment(position);

        }
    };

    private void goToFragment(int position) {

        switch (position){

            case 0 :
                break;
            case 1 : initializeFragments(routineHomeFragment);
                break;
            case 2 : initializeFragments(studentsHomeFragment);
                break;
            case 3 :  initializeFragments(teachersHomeFragment);
                break;
            case 4 :    initializeFragments(classHomeFragment);
                break;
            case 5 :
                break;
            case 6 :
                break;
            case 7 :
                break;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_demo, container, false);


        AndroidSupportInjection.inject(this);

        ButterKnife.bind(this,view);


        setUpRecyclerView(recyclerView,adapter);
        fragmentManager = getActivity().getSupportFragmentManager();



        return view;
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }



    private void setUpRecyclerView(RecyclerView recyclerView, HomeAdapter adapter) {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);

        adapter.updateListData(loadItems());
    }

    private List<HomeItem> loadItems() {
        mList = new ArrayList<>();
        mList.add(new HomeItem(R.drawable.ic_take_attendance,"Take Attendance"));
        mList.add(new HomeItem(R.drawable.ic_routine,"Routine"));
        mList.add(new HomeItem(R.drawable.ic_student_center,"Students"));
        mList.add(new HomeItem(R.drawable.ic_teacher,"Teachers"));
        mList.add(new HomeItem(R.drawable.ic_class,"Class"));
        mList.add(new HomeItem(R.drawable.ic_allocation,"Allocation"));
        mList.add(new HomeItem(R.drawable.ic_session,"Session"));



        return mList;

    }


    private void initializeFragments(Fragment frag) {
        String backStateName = frag.getClass().toString();
        //Log.d(TAG, "onBtnOtpLoginClicked: " + backStateName);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
     //   transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        transaction.replace(R.id.frame_layout, frag);
        transaction.addToBackStack(backStateName);
        transaction.commit();
    }

}