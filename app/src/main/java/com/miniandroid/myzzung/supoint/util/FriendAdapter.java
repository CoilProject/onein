package com.miniandroid.myzzung.supoint.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.miniandroid.myzzung.supoint.CoilApplication;
import com.miniandroid.myzzung.supoint.R;
import com.miniandroid.myzzung.supoint.gcm.CoilGcmListenerService;
import com.miniandroid.myzzung.supoint.model.UserInfo;
import com.miniandroid.myzzung.supoint.ui.MainActivity;
import com.miniandroid.myzzung.supoint.volley.MyVolley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by myZZUNG on 2016. 7. 21..
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private final String TAG = "FriendAdapter";

    private Context context;
    private List<UserInfo> list;
    private int item_layout;
    private CoilApplication app;
    public FriendAdapter(){

    }

    public FriendAdapter(Context context, List<UserInfo> items, int item_layout){
        this.context = context;
        this.list = items;
        this.item_layout = item_layout;
        //app = (CoilApplication) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(item_layout, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootView.setLayoutParams(lp);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserInfo item = list.get(position);
        holder.text_id.setText(item.getUserId());
        holder.text_name.setText(item.getUserName());
        holder.text_point.setText(item.getPoint()+"점");
        holder.text_ranking.setText(item.getRank()+"위");
        holder.btn_push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.show(), Toast.LENGTH_SHORT).show();
                final RequestQueue queue = MyVolley.getInstance(context).getRequestQueue();

                CoilRequestBuilder builder = new CoilRequestBuilder(context);
                builder.setCustomAttribute("friend_id", item.getUserId());
                Log.d(TAG, "before network : "+builder.build().toString());
                JsonObjectRequest myReq = new JsonObjectRequest(Request.Method.POST,
                        SystemMain.URL.URL_PUSH_TEST,
                        builder.build(),
                        networkSuccessListener(),
                        networkErrorListener());

                queue.add(myReq);
            }
        });
    }

    private Response.Listener<JSONObject> networkSuccessListener() {
        return new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());


                Toast.makeText(context, "친구에게 푸시를 보냅니다", Toast.LENGTH_SHORT).show();


                //Intent intent  = new Intent(getApplicationContext(), MainActivity.class);

            }
        };
    }
    private Response.ErrorListener networkErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, R.string.volley_network_fail, Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView text_id;
        public TextView text_name;
        public TextView text_point;
        public TextView text_ranking;
        public Button btn_push;


        public ViewHolder(View itemView) {
            super(itemView);
            text_id = (TextView)itemView.findViewById(R.id.text_friend_id);
            text_name = (TextView)itemView.findViewById(R.id.text_friend_name);
            text_point = (TextView)itemView.findViewById(R.id.text_friend_point);
            text_ranking = (TextView)itemView.findViewById(R.id.text_friend_ranking);
            btn_push = (Button)itemView.findViewById(R.id.btn_friend_push);
        }
    }
}
