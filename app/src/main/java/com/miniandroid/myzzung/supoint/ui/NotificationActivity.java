package com.miniandroid.myzzung.supoint.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.miniandroid.myzzung.supoint.R;
import com.miniandroid.myzzung.supoint.model.NotificationData;
import com.miniandroid.myzzung.supoint.model.NotificationTitle;
import com.miniandroid.myzzung.supoint.util.NotificationAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by myZZUNG on 2016. 7. 23..
 */
public class NotificationActivity extends AppCompatActivity {

    private final String TAG = "NotificationActivity";
    private final String ACTIONBAR_TITLE = "공지사항";

    private NotificationAdapter mNotificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().setTitle(ACTIONBAR_TITLE);

        NotificationData data1 = new NotificationData("새로운 공지사항입니다.\n 쭝이가 팅해서 걱정입니다.\n 쭝이는 짱짱 귀엽습니다:D");
        NotificationData data2 = new NotificationData("두번째 공지사항입니다.\n 빵이가 쭝해져서 걱정입니다.\n 쭝이는 오늘도 짱짱 귀엽습니다:>");

        NotificationTitle title1 = new NotificationTitle("noti title1", Arrays.asList(data1));
        NotificationTitle title2 = new NotificationTitle("noti title2", Arrays.asList(data2));
        final List<NotificationTitle> titles = Arrays.asList(title1, title2);

        RecyclerView notificationListView = (RecyclerView) findViewById(R.id.notification_recyclerview);
        mNotificationAdapter = new NotificationAdapter(this,titles);
        mNotificationAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {

            }

            @Override
            public void onListItemCollapsed(int position) {

            }
        });

        notificationListView.setAdapter(mNotificationAdapter);
        notificationListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
