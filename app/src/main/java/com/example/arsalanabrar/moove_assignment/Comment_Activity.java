package com.example.arsalanabrar.moove_assignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Comment_Activity extends AppCompatActivity {
TextView comemnt,body,createdby;
ImageView user_pic;
String url;
List<Comment_data>comment_data=new ArrayList<Comment_data>();
    private RecyclerView recyclerView;
    private RecyclerAdapter2 mAdapter;

    String created,avatar_url,body1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_);
        Bundle extras = getIntent().getExtras();
        url=extras.getString("comment_url");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mAdapter = new RecyclerAdapter2(comment_data,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        getdatafromserver();
    }
    private void getdatafromserver() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", response.toString());
                String res = response.toString();
                try {
                    JSONArray jsonArray = new JSONArray(res);
                    for (int i=0;i<jsonArray.length();i++) {

                        JSONObject jobj = (JSONObject) response.get(i);
                        created = jobj.getString("created_at");
                        body1 = jobj.getString("body");
                        JSONObject juser=(JSONObject)jobj.getJSONObject("user");
                        avatar_url=juser.getString("avatar_url");
                        Comment_data userdat=new Comment_data(body1,created,avatar_url);
                        comment_data.add(userdat);

                    }
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        VolleyRequest.getInstance(getApplicationContext()).addToRequestQueue(jsonArrayRequest);

    }

}
