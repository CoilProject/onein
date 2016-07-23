package com.miniandroid.myzzung.supoint.model;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

/**
 * Created by myZZUNG on 2016. 7. 23..
 */
public class NotificationTitle implements ParentListItem{

    private String mTitle;
    private List<NotificationData> mDatas;

    public NotificationTitle(String title, List<NotificationData> datas) {
        mTitle = title;
        mDatas = datas;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public List<?> getChildItemList() {
        return mDatas;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
