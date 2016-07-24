package com.miniandroid.myzzung.supoint.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by myZZUNG on 2016. 7. 18..
 */
public class UserInfo extends Info {

    private String userId;
    private String userName; //
    private int point; //  점수
    private int rank; // 랭킹 순위

    public UserInfo(){

    }

    public UserInfo(String id, String name, int point){
        userId = id;
        userName = name;
        this.point = point;
    }

    public UserInfo(String id, String name, int point, int rank){
        userId = id;
        userName = name;
        this.point = point;
        this.rank = rank;
    }

    public UserInfo(JSONObject obj){
        try {
            userId = obj.getString("friend_id");
            userName = obj.getString("friend_name");
            point = obj.getInt("point");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String show(){
        return "user_id :"+userId+" name : "+userName+" point : "+ point;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
