package com.example.attendancemanager.ui.main.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attendancemanager.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewholder> {



    private ArrayList<HomeItem> mList;
    private View.OnClickListener onItemClickListener;

    private static final String TAG = "HomeAdapter";



    @Inject
    public HomeAdapter() {
        mList = new ArrayList<>();
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_main_home, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {


           holder.text.setText(mList.get(position).getText());
           holder.image.setImageResource (mList.get(position).getImage());

            //  holder.ItemQuantity.setText(mList.get(position).getItemQuantity());

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public void updateListData(List<HomeItem> data){
        mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();

    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;

        public viewholder(@NonNull View itemView) {

            super(itemView);

            itemView.setTag(this);

            text = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(onItemClickListener);

        }

    }
}
