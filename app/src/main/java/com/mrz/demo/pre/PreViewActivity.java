package com.mrz.demo.pre;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mrz.demo.R;
import com.zhihu.matisse.internal.utils.Platform;

import java.util.List;

public class PreViewActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener,
        View.OnClickListener {

    protected ViewPager mPager;
    private PreViewPagerAdapter mAdapter;
    public static final String IMAGE = "image";
    public static final String POSITION = "position";
    private TextView mTvCount;
    private ImageView mIvDown;
    private int mCurrentPosition;
    private int maxCount;
    private int mPreviousPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_view);
        if (Platform.hasKitKat()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        mPager = findViewById(R.id.pager);
        mTvCount = findViewById(R.id.tv_count);
        mIvDown = findViewById(R.id.iv_down);
        mIvDown.setOnClickListener(this);
        Intent intent = getIntent();
        if (intent == null) return;
        List<String> imageList = intent.getStringArrayListExtra(IMAGE);
        mCurrentPosition = intent.getIntExtra(POSITION, 0);
        maxCount = imageList.size();
        mAdapter = new PreViewPagerAdapter(getSupportFragmentManager(), imageList);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);
        mPager.setCurrentItem(mCurrentPosition);
        changePositionText(mCurrentPosition);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (mPreviousPos != -1 && mPreviousPos != i) {

            ((PreViewContentFragment) mAdapter.instantiateItem(mPager, mPreviousPos)).resetView();
        }
        mPreviousPos = i;
        changePositionText(i);
    }


    @Override
    public void onPageScrollStateChanged(int i) {

    }


    private void changePositionText(int position) {
        mCurrentPosition = position + 1;
        String currentPositionStr = mCurrentPosition + "";
        String maxPositionStr = maxCount + "";
        SpannableString spannableString = new SpannableString(currentPositionStr + " / " +
                maxPositionStr);
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, currentPositionStr.length
                (), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(2f), 0, currentPositionStr.length(),
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0,
                currentPositionStr.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvCount.setText(spannableString);

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "下载功能自己实现哈", Toast.LENGTH_SHORT).show();
    }
}
