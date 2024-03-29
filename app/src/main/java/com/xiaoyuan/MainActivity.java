package com.xiaoyuan;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.xiaoyuan.adapter.CommonTabPagerAdapter;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements CommonTabPagerAdapter.TabPagerListener {

    TabLayout tabLayout;
    AppBarLayout appbar;
    ViewPager viewpager;

    private CommonTabPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        adapter = new CommonTabPagerAdapter(getSupportFragmentManager()
                , 10, Arrays.asList("美食", "电影", "玩乐", "评价","美食", "电影", "玩乐", "评价","美食", "电影"), this);
        adapter.setListener(this);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    @Override
    public Fragment getFragment(int position) {
        return DemoFragment.newInstance(position);
    }
}
