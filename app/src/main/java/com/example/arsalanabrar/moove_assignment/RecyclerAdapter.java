package com.example.arsalanabrar.moove_assignment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Data> dataList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, body, time;
        public ImageView user_pic;

        public MyViewHolder(View view) {
            super(view);
            title=(TextView)view.findViewById(R.id.textView);
            body=(TextView)view.findViewById(R.id.textView2);
            time=(TextView)view.findViewById(R.id.textView3);
            user_pic=(ImageView)view.findViewById(R.id.imageView);

                   }
    }

    public RecyclerAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.mcontext=context;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.data_content,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Data data = dataList.get(position);
        holder.title.setText(data.getTitle());
        holder.body.setText(data.getBody());
        holder.time.setText(data.getTime());
        Picasso.with(mcontext).load(data.getAvatar_url()).resize(120, 60).into(holder.user_pic);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
