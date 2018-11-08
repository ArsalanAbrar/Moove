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

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder> {
    private List<Comment_data> commentDataList;
    Context mcontext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView body, time;
        public ImageView user_pic;

        public MyViewHolder(View view) {
            super(view);

            body=(TextView)view.findViewById(R.id.textView2);
            time=(TextView)view.findViewById(R.id.textView3);
            user_pic=(ImageView)view.findViewById(R.id.imageView1);

        }
    }

    public RecyclerAdapter2(List<Comment_data> commentDataList, Context context) {
        this.commentDataList = commentDataList;
        this.mcontext=context;
    }



    @NonNull
    @Override
    public RecyclerAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview=LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_content,parent,false);
        return new RecyclerAdapter2.MyViewHolder(itemview);
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter2.MyViewHolder holder, int position) {

        Comment_data data = commentDataList.get(position);
        holder.body.setText(data.getBody());
        holder.time.setText(data.getTime());
        Picasso.with(mcontext).load(data.getAvatar_url()).resize(120, 60).into(holder.user_pic);

    }

    @Override
    public int getItemCount() {
        return commentDataList.size();
    }
}
