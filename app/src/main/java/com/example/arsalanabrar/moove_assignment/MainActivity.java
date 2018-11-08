package com.example.arsalanabrar.moove_assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
String title,created,body,avatar_url,comment_url;
List<Data>data=new ArrayList<Data>();
    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mAdapter = new RecyclerAdapter(data,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Data userifo = data.get(position);
                Toast.makeText(getApplicationContext(), userifo.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(MainActivity.this,Comment_Activity.class);
                i.putExtra("comment_url",userifo.getComments_url());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        getdatafromserver();
    }

    private void getdatafromserver() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://api.github.com/repos/square/okhttp/issues", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("response", response.toString());
                String res = response.toString();
                try {
                    JSONArray jsonArray = new JSONArray(res);
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jobj = (JSONObject) response.get(i);
                         title = jobj.getString("title");
                         created = jobj.getString("created_at");
                         body = jobj.getString("body");
                         comment_url=jobj.getString("comments_url");
                        JSONObject juser=(JSONObject)jobj.getJSONObject("user");
                         avatar_url=juser.getString("avatar_url");

                        Data userdat=new Data(title,body,created,avatar_url,comment_url);
                            data.add(userdat);

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
