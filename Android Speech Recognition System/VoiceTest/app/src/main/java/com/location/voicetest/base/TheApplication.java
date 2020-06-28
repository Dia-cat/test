package com.location.voicetest.base;

import android.app.Application;
import android.app.Service;
import android.database.sqlite.SQLiteDatabase;

import com.iflytek.cloud.SpeechUtility;
import com.location.voicetest.R;
import com.location.voicetest.db.green.DaoMaster;
import com.location.voicetest.db.green.DaoSession;
import com.location.voicetest.db.green.UserInfoDao;
import com.location.voicetest.dbbean.UserInfo;

import java.util.List;

/**
 * 功能:程序的全局数据
 */
public class TheApplication extends Application {
     private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
   private DaoSession mDaoSession;
    public static TheApplication instance = null;
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));
        /***
         * 初始化定位sdk，建议在Application中创建
         */
       setDatabase();
    }

    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        UserInfoDao userInfoDao = mDaoSession.getUserInfoDao();
        List<UserInfo> userInfoList=userInfoDao.loadAll();
        if(userInfoList==null||userInfoList.size()<1){
            UserInfo userInfo=new UserInfo();
            userInfo.setName("admin");
            userInfo.setPwd("123456");
            userInfoDao.insert(userInfo);
        }

    }
    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }
}
