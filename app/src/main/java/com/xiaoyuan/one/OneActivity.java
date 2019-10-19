package com.xiaoyuan.one;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoyuan.R;

public class OneActivity extends AppCompatActivity implements StickyScrollView.OnScrollChangedListener {

    private StickyScrollView stickyScrollView;
    private int height;
    private LinearLayout llContent;
    private ViewPager tab_viewpager;
    private TabLayout tabLayout = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initView();
        initListeners();

    }

    /**
     * 初始化View
     */
    private void initView() {
        stickyScrollView = (StickyScrollView) findViewById(R.id.scrollView);
        tab_viewpager = findViewById(R.id.tab_viewpager);
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        stickyScrollView.setOnScrollListener(this);
        tabLayout = findViewById(R.id.tablayout);
        tab_viewpager.setAdapter(new MorePagerAdapter());
        tabLayout.setupWithViewPager(tab_viewpager);

    }


    private void initListeners() {
        //获取内容总高度
        final ViewTreeObserver vto = llContent.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = llContent.getHeight();
                //注意要移除
                llContent.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);

            }
        });

        //获取Fragment高度
        ViewTreeObserver viewTreeObserver = tab_viewpager.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                height = height - tab_viewpager.getHeight();
                //注意要移除
                tab_viewpager.getViewTreeObserver()
                        .removeGlobalOnLayoutListener(this);
            }
        });




    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {

    }

    final class MorePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(OneActivity.this);
            tv.setText("布局" + position);
            tv.setTextSize(30.0f);
            tv.setGravity(Gravity.CENTER);
            (container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            (container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "选项" + position;
        }
    }
}
