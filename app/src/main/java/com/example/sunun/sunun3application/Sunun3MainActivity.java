package com.example.sunun.sunun3application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sunun3MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "myapp";
    public static final String REQUEST_TAG = "myrequest";
    private RequestQueue mQueue;
    ProgressDialog pDialog;
    private List<DataModel> datas = new ArrayList<>();
  // A
  // A01 Array data -> A02 Adaptor -> A03 ListView
    //A01 กำหนดตัวแปร ไว้ใน Public class
 //A   String[] datas;
//B




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sunun3_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        String url = "http://itpart.com/android/json/test8records.php";
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading..");
        pDialog.show();

        JsonArrayRequest jsRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Gson gson = new Gson();

                        JSONObject jsObj = null;
                        for (int i=0; i < response.length(); i++ ) {
                            try {
                                jsObj = response.getJSONObject(i);
                                DataModel dataitem = gson.fromJson(String.valueOf(jsObj),DataModel.class);
                                datas.add(dataitem);
//                                Log.d(TAG,"gson "+ dataitem.getTitle() );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        if (datas.size() > 0){
                            displayListview();
                        }

                        pDialog.hide();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d(TAG,error.toString());
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_SHORT).show();
                        pDialog.hide();
                    }
                });  // Request


        mQueue = Volley.newRequestQueue(this);
        jsRequest.setTag(REQUEST_TAG);
        mQueue.add(jsRequest);

    }



    public void displayListview(){
        MyAdapter adapter = new MyAdapter(this,datas);
        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
    }

    // for json listview
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = datas.get(i).getId();
        //    Toast.makeText(this,"You clicked "+ String.valueOf(id)+" ",Toast.LENGTH_SHORT).show();
        Intent itn = new Intent(this, DetailpageActivity.class);
        itn.putExtra("recID", id);
        startActivity(itn);
    }

//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        int id = i; // clicked Item
//        // Log.d(....)  // display on LogCat
//        // Toast.makeText(this,"You clicked "+ String.valueOf(id)+" ",Toast.LENGTH_SHORT).show();
//        Intent itn = new Intent(this, DetailpageActivity.class);
//        itn.putExtra("recID", id);
//        startActivity(itn);
//    }
}

