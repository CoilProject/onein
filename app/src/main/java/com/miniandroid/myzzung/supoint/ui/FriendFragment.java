package com.miniandroid.myzzung.supoint.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miniandroid.myzzung.supoint.CoilApplication;
import com.miniandroid.myzzung.supoint.R;
import com.miniandroid.myzzung.supoint.model.UserInfo;
import com.miniandroid.myzzung.supoint.util.CoilRequestBuilder;
import com.miniandroid.myzzung.supoint.util.FriendAdapter;
import com.miniandroid.myzzung.supoint.util.SystemMain;
import com.miniandroid.myzzung.supoint.volley.MyVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {

    private final String TAG = "FriendFragment";

    private CoilApplication app;

    List<UserInfo> items;
    FriendAdapter adapter;

    private EditText edit_friend_search;
    private Button btn_friend_search;


    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        app = (CoilApplication) getActivity().getApplicationContext();

        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        final RequestQueue queue = MyVolley.getInstance(getActivity()).getRequestQueue();
        CoilRequestBuilder builder = new CoilRequestBuilder(getActivity());
        builder.setCustomAttribute("user_id", app.user_id);
        Log.d(TAG, "before network : "+builder.build().toString());
        JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST,
                SystemMain.URL.URL_FRIEND_FIND,
                builder.build(),
                networkSuccessListener(),
                networkErrorListener());

        queue.add(myReq);

        items =  new ArrayList<>();

        edit_friend_search = (EditText)rootView.findViewById(R.id.edit_friend_search);
        btn_friend_search = (Button)rootView.findViewById(R.id.btn_friend_search);
        btn_friend_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoilRequestBuilder builder = new CoilRequestBuilder(getActivity());
                builder.setCustomAttribute("user_id", app.user_id)
                        .setCustomAttribute("friend_id", edit_friend_search.getText().toString());
                Log.d(TAG, "before network : "+builder.build().toString());
                JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST,
                        SystemMain.URL.URL_FRIEND_ADD,
                        builder.build(),
                        networkSuccessListener_addfriend(),
                        networkErrorListener());

                queue.add(myReq);
            }
        });



//        UserInfo item;
//        for(int i=0;i<10;i++){
//            item = new UserInfo("ljs93kr"+i, "leejunsu"+i, i, 9-i);
//            items.add(item)
//        }

        adapter = new FriendAdapter(getActivity(), items, R.layout.item_list_friend);

//        StoreSearchAdapter adapter = new StoreSearchAdapter(getActivity(), app.storeAll.getItemList(), R.layout.fragment_search);
//        app.storeAll.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        return rootView;
    }
    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    if(response.getBoolean("find_friends")){
                        JSONArray friend_list = response.getJSONArray("friend_list");
                        UserInfo friend;
                        for(int i=0;i<friend_list.length();i++){
                           friend = new UserInfo(friend_list.getJSONObject(i));
                           items.add(friend);
                        }
                        adapter.notifyDataSetChanged();

                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Intent intent  = new Intent(getApplicationContext(), MainActivity.class);

            }
        };
    }
    private Response.Listener<JSONObject> networkSuccessListener_addfriend() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    if(response.getBoolean("add_friend")){
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //Intent intent  = new Intent(getApplicationContext(), MainActivity.class);

            }
        };
    }
    private Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), R.string.volley_network_fail, Toast.LENGTH_SHORT).show();
            }
        };
    }

}
