package com.miniandroid.myzzung.supoint.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.miniandroid.myzzung.supoint.R;
import com.miniandroid.myzzung.supoint.model.NotificationData;
import com.miniandroid.myzzung.supoint.model.NotificationTitle;

import java.util.List;

/**
 * Created by myZZUNG on 2016. 7. 23..
 */
public class NotificationAdapter extends ExpandableRecyclerAdapter<NotificationAdapter.NotificationParentViewHolder, NotificationAdapter.NotificationChildViewHolder> {

    private LayoutInflater mInflater;

    public NotificationAdapter(Context context, @NonNull List<? extends ParentListItem> parentListItems){
        super(parentListItems);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NotificationParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View parentView = mInflater.inflate(R.layout.item_list_notification_parent,viewGroup,false);
        return new NotificationParentViewHolder(parentView);
    }

    @Override
    public NotificationChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View childView = mInflater.inflate(R.layout.item_list_notification_child,viewGroup,false);
        return new NotificationChildViewHolder(childView);
    }

    @Override
    public void onBindParentViewHolder(NotificationParentViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        NotificationTitle notificationTitle = (NotificationTitle) parentListItem;
        parentViewHolder.bind(notificationTitle);
    }

    @Override
    public void onBindChildViewHolder(NotificationChildViewHolder childViewHolder, int position, Object childListItem) {
        NotificationData notificationData = (NotificationData) childListItem;
        childViewHolder.bind(notificationData);
    }

    public class NotificationParentViewHolder extends ParentViewHolder {
        public TextView mParentTitleTextView;
        public ImageView mParentExpandView;

        public NotificationParentViewHolder(View itemView){
            super(itemView);

            mParentTitleTextView = (TextView) itemView.findViewById(R.id.item_list_notification_parent_title);
            mParentExpandView = (ImageView) itemView.findViewById(R.id.item_list_notification_parent_view);
        }

        public void bind(NotificationTitle notificationTitle) {
            mParentTitleTextView.setText(notificationTitle.getTitle());
        }
    }

    public class NotificationChildViewHolder extends ChildViewHolder {
        public TextView mChildDataTextView;

        public NotificationChildViewHolder(View itemView){
            super(itemView);

            mChildDataTextView = (TextView) itemView.findViewById(R.id.item_list_notification_child_text);
        }

        public void bind(NotificationData notificationData){
            mChildDataTextView.setText(notificationData.getData());
        }
    }
}
