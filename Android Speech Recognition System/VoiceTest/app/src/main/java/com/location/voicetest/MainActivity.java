package com.location.voicetest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.location.voicetest.fragment.DubbingFragement;
import com.location.voicetest.fragment.FirstFragement;
import com.location.voicetest.fragment.MyFragement;
import com.location.voicetest.fragment.PracticeFragement;
import com.location.voicetest.fragment.TwoFragement;
import com.location.voicetest.utils.ToastUtils;
import com.location.voicetest.view.NoScrollViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //Tab 文字
    private final String[] TAB_TITLES = {"首页", "练习","配音","我的"};
    //Fragment 数组
    private final Fragment[] TAB_FRAGMENTS = new Fragment[]{new FirstFragement(), new PracticeFragement(),new DubbingFragement(), new MyFragement()};
    //Tab 数目
    private final int COUNT = TAB_TITLES.length;
    private MyViewPagerAdapter mAdapter;
    public NoScrollViewPager viewpager;
    //Tab 数目
    private TabLayout tablayout;

    private int keyBackClickCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTab();
    }

    private void initTab() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (NoScrollViewPager) findViewById(R.id.viewpager);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        setTableLayOut(tablayout);
    }

    public void setTableLayOut(TabLayout tablayout) {
        setTabs(tablayout, this.getLayoutInflater(), TAB_TITLES);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
    }

    /**
     * @description: 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, String[] tabTitlees) {
        for (int i = 0; i < tabTitlees.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_indicator, null);
            tab.setCustomView(view);
            TextView title = (TextView) view.findViewById(R.id.tv_title);
            title.setText(tabTitlees[i]);
            tabLayout.addTab(tab);
            //利用ContextCompat工具类获取drawable图片资源
        }
    }

    /**
     * @description: ViewPager 适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (keyBackClickCount++) {
                case 0:
                    ToastUtils.showToast(this, "再按一次退出");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            keyBackClickCount = 0;
                        }
                    }, 3000);
                    break;
                case 1:
                    finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
