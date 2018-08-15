package com.mrz.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mrz.demo.pre.PreViewActivity;
import com.mrz.imageadd.AddPhotoManage;
import com.mrz.imageadd.AddPhotoView;
import com.mrz.imageadd.listener.PhotoResourceListener;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoadActivity extends AppCompatActivity implements PhotoResourceListener {
    private AddPhotoView mAddPhotoView;

    private List<String> imageList = Arrays.asList("https://img11.360buyimg" +
                    ".com/n1/s450x450_jfs/t14848/365/2076510540/93902/e5883831" +
                    "/5a6947e5N39e16ed8.jpg",
            "https://img11.360buyimg.com/n1/s450x450_jfs/t14848/365/2076510540/93902/e5883831" +
                    "/5a6947e5N39e16ed8.jpg", "https://img14.360buyimg" +
                    ".com/n0/jfs/t15184/284/2018136970/96074/e870bb03/5a694955N21107862.jpg",
            "https://img14.360buyimg" +
                    ".com/n0/jfs/t15184/284/2018136970/96074/e870bb03/5a694955N21107862.jpg",
            "https://img14.360buyimg" +
                    ".com/n0/jfs/t2380/357/871339911/44566/b968e8a5/5631d221Nde64bf21.jpg",
            "https://img14.360buyimg" +
                    ".com/n0/jfs/t2380/357/871339911/44566/b968e8a5/5631d221Nde64bf21.jpg");
    private AddPhotoManage mAddPhotoManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        mAddPhotoView = findViewById(R.id.add_photo);
        mAddPhotoManage = mAddPhotoView.getAddPhotoManage();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAddPhotoManage.setData(imageList.size(), LoadActivity.this);
            }
        }, 500);
    }

    @Override
    public String showPhoto(String image, int position, ImageView iv) {
        //这里的image是空的,要从数据源自己获取
        String reallyImage = imageList.get(position);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_loading).error(R.mipmap.ic_loading);
        Glide.with(this).load(reallyImage).apply(options).into(iv);
        return reallyImage;
    }

    @Override
    public void lookImage(int position, List<String> image) {
        Intent intent = new Intent(this, PreViewActivity.class);
        intent.putStringArrayListExtra(PreViewActivity.IMAGE, (ArrayList<String>) image);
        intent.putExtra(PreViewActivity.POSITION, position);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
