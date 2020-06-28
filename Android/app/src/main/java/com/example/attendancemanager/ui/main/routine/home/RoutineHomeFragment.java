package com.example.attendancemanager.ui.main.routine.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.ui.main.home.adapter.HomeAdapter;
import com.example.attendancemanager.ui.main.home.adapter.HomeItem;
import com.example.attendancemanager.ui.main.routine.addRoutine.AddRoutineFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class RoutineHomeFragment extends Fragment {

    ArrayList<HomeItem> mList;
    @Inject
    HomeAdapter adapter;

    private static final String TAG = "RoutineHomeFragment";

    @Inject
    AddRoutineFragment addRoutineFragment;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            Log.d(TAG, "onClick: POsition ::: " + position);

            goToFragment(position);


        }
    };


    @Inject
    public RoutineHomeFragment() {
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
        View view = inflater.inflate(R.layout.fragment_routine_home, container, false);
        AndroidSupportInjection.inject(this);
        ButterKnife.bind(this, view);

        mList = new ArrayList<>();

        setUpRecyclerView(recyclerView,adapter);


        return view;
    }

    @Override
    public void onAttach(Context context) {

        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    private void goToFragment(int position) {

        switch (position) {

            case 0:
                initializeFragments(addRoutineFragment);
                break;


        }

    }


    private void setUpRecyclerView(RecyclerView recyclerView, HomeAdapter adapter) {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(onClickListener);

        adapter.updateListData(loadItems());
    }

    private List<HomeItem> loadItems() {
        mList = new ArrayList<>();
        mList.add(new HomeItem(R.drawable.ic_take_attendance, "Add subject to routine"));
        mList.add(new HomeItem(R.drawable.ic_routine, "Edit Routine"));

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