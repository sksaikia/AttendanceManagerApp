package com.example.attendancemanager.ui.main.teachers.allteachers.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;
import com.example.attendancemanager.data.models.main.student.StudentsResponse;
import com.example.attendancemanager.data.models.main.teacher.TeacherResponse;
import com.example.attendancemanager.ui.main.home.adapter.HomeAdapter;
import com.example.attendancemanager.ui.main.home.adapter.HomeItem;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.viewholder> {


    private ArrayList<TeacherResponse> mList;
    private View.OnClickListener onItemClickListener;

    private static final String TAG = "HomeAdapter";

    public boolean isShimmer = true;
    int shimmerNumber = 7;

    @Inject
    public TeachersAdapter() {
        mList = new ArrayList<>();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public TeachersAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_teacher_data, parent, false);
        return new TeachersAdapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeachersAdapter.viewholder holder, int position) {


        if (isShimmer) {
            holder.shimmerFrameLayout.startShimmer();

        }else {

            String first = mList.get(position).getFirstName();
            String middle = mList.get(position).getMiddleName();
            String last = mList.get(position).getLastName();

            String name = "";
            if (first != null)
                name += first;
            if (middle != null)
                name = name +" "  + middle;
            if (last != null)
                name = name +" "  + last;



            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);
            //  holder.ItemImage.setImageResource(mList.get(position).getImages());

            holder.name.setText(name);
            holder.name.setBackground(null);

            holder.rollno.setText(mList.get(position).getDepartment());
            holder.rollno.setBackground(null);

            holder.image.setImageResource(R.drawable.ic_person);
            holder.image.setBackground(null);


        }

    }

    @Override
    public int getItemCount() {

        if (isShimmer)
            return shimmerNumber;
        else
            return mList.size();
    }

    public void updateListData(List<TeacherResponse> data) {
        mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();

    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView name;
        TextView rollno;
        ShimmerFrameLayout shimmerFrameLayout;
        ImageView image;

        public viewholder(@NonNull View itemView) {

            super(itemView);

            itemView.setTag(this);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer_view_container);
            name = itemView.findViewById(R.id.name);
            rollno = itemView.findViewById(R.id.rollno);

            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(onItemClickListener);

        }

    }
}
