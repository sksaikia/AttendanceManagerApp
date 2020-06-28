package com.example.attendancemanager.ui.main.teachers.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.attendancemanager.R;
import com.example.attendancemanager.ui.main.home.adapter.HomeAdapter;
import com.example.attendancemanager.ui.main.home.adapter.HomeItem;
import com.example.attendancemanager.ui.main.students.addStudent.AddStudentFragment;
import com.example.attendancemanager.ui.main.students.allstudents.AllStudentsFragment;
import com.example.attendancemanager.ui.main.teachers.addTeacher.AddTeacherFragment;
import com.example.attendancemanager.ui.main.teachers.allteachers.AllTeachersFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class TeachersHomeFragment extends Fragment {


    @Inject
    HomeAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    AddTeacherFragment addTeacherFragment;

    @Inject
    AllTeachersFragment allTeachersFragment;

    ArrayList<HomeItem> mList;

    private static final String TAG = "TeachersHomeFrament";


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Log.d(TAG, "onClick: POsition ::: " + position);

            goToFragment(position);


//
//            String name = mList.get(position);
//
//            Log.d(TAG, "onClick: ID of the object :: " + name);
////
//            Bundle bundle = new Bundle();
//            bundle.putString(Constants.BUNDLE_ALL_TO_SINGLE_CAT, name);
//            singleCategoryFragment.setArguments(bundle);

            //   initializeFragments(singleCategoryFragment);

        }
    };

    @Inject
    public TeachersHomeFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_teachers_home, container, false);
        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        setUpRecyclerView(recyclerView,adapter);


        return view;
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    private void goToFragment(int position) {

        switch (position){

            case 0 :    initializeFragments(allTeachersFragment);
                break;
            case 1 :    initializeFragments(addTeacherFragment);
                break;
        }

    }



    private void setUpRecyclerView(RecyclerView recyclerView, HomeAdapter adapter) {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);

        adapter.updateListData(loadItems());
    }

    private List<HomeItem> loadItems() {
        mList = new ArrayList<>();
        mList.add(new HomeItem(R.drawable.ic_take_attendance,"Teachers Data"));
        mList.add(new HomeItem(R.drawable.ic_routine,"Add a teacher"));

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