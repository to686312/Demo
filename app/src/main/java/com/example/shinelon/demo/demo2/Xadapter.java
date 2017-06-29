package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shinelon.demo.R;

import java.util.List;

/**
 * Created by Shinelon on 2016/12/26.
 */

public class Xadapter extends RecyclerView.Adapter<Xadapter.MyViewHolder> {


    private List<String> list;
    private Context context;

    public Xadapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Xadapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.demo2_item_layout, null, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Xadapter.MyViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
