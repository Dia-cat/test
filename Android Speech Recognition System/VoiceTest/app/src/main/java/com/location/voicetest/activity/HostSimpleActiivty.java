package com.location.voicetest.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.location.voicetest.R;
import com.location.voicetest.adapter.HostSimpleAdapter;
import com.location.voicetest.base.BaseActivity;
import com.location.voicetest.base.TheApplication;
import com.location.voicetest.bean.User;
import com.location.voicetest.db.green.TestInfoDao;
import com.location.voicetest.dbbean.TestInfo;

import java.util.List;

public class HostSimpleActiivty extends BaseActivity {
    private RecyclerView rv;
    private HostSimpleAdapter hostSimpleAdapter;
    private TestInfoDao testInfoDao;
    private List<TestInfo> testInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_host);
        inintView();
        testInfoDao= TheApplication.instance.getDaoSession().getTestInfoDao();
        inintData();
    }

    private void inintData() {
        testInfos= testInfoDao.queryBuilder().where(TestInfoDao.Properties.Username.eq(User.getUserName(this))).build().list();
        hostSimpleAdapter=new HostSimpleAdapter(this,R.layout.iteam_host_simple,testInfos);
        rv.setAdapter(hostSimpleAdapter);
    }

    private void inintView() {
        rv=findViewById(R.id.rv);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutmanager);
    }
}
