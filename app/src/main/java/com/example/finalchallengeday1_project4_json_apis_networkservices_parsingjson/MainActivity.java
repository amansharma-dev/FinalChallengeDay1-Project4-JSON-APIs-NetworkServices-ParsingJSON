package com.example.finalchallengeday1_project4_json_apis_networkservices_parsingjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    //JSON-Object::  https://jsonplaceholder.typicode.com/todos/1
    //JSON-Array:: http://jsonplaceholder.typicode.com/todos

    public static final String TAG = "MainActivity";
    public static final String JSON_OBJECT_URL = "https://jsonplaceholder.typicode.com/todos/1";
    public static final String JSON_ARRAY_URL = "https://jsonplaceholder.typicode.com/todos";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        //fetching json object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, JSON_OBJECT_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: "+response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //fetching json array
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_ARRAY_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i<response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Log.d(TAG, "onResponse: "+jsonObject.getString("id")+"---"+jsonObject.getBoolean("completed"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.getMessage());
            }
        });
//        requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonArrayRequest);
    }
}