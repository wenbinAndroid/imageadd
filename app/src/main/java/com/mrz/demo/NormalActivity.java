package com.mrz.demo;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mrz.demo.pre.PreViewActivity;
import com.mrz.imageadd.AddPhotoManage;
import com.mrz.imageadd.AddPhotoView;
import com.mrz.imageadd.PhotoEditActionListener;
import com.squareup.leakcanary.RefWatcher;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.quick.PermissionUtils;
import com.yanzhenjie.permission.quick.listener.PermissionListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class NormalActivity extends AppCompatActivity implements
        PhotoEditActionListener, PermissionListener {

    private AddPhotoView mAddView;

    private AddPhotoManage mAddPhotoManage;
    private final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        mAddView = findViewById(R.id.iv_add);
        mAddPhotoManage = mAddView.getAddPhotoManage();
        mAddPhotoManage.setPhotoEditActionListener(this);

    }


    @Override
    public String showPhoto(String image, int position, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_loading).error(R.mipmap.ic_loading);
        Glide.with(this).load(image).apply(options).into(iv);
        return null;
    }

    @Override
    public void lookImage(int position, List<String> image) {
        Intent intent = new Intent(this, PreViewActivity.class);
        intent.putStringArrayListExtra(PreViewActivity.IMAGE, (ArrayList<String>) image);
        intent.putExtra(PreViewActivity.POSITION, position);
        startActivity(intent);
    }

    private static final String TAG = "NormalActivity";

    @Override
    public void deleteImage(int position) {
        Log.e(TAG, "deleteImage: " + position);
    }


    @Override
    public void addImage(AddPhotoManage manage, int position) {
        PermissionUtils.with(this).permission(Permission.CAMERA, Permission
                .READ_EXTERNAL_STORAGE, Permission.WRITE_EXTERNAL_STORAGE).listener(this).start();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> list = Matisse.obtainPathResult(data);
            mAddPhotoManage.addData(list);
        }

    }

    @Override
    public void onGranted(int requestCode) {
        Matisse.from(this)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.mrz.demo.fileprovider"))
                .maxSelectable(9)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
//                                            .imageEngine(new GlideEngine())  // for glide-V3
                .imageEngine(new Glide4Engine())    // for glide-V4
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(
                            @NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                    }
                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                    }
                })
                .forResult(REQUEST_CODE);
    }

    @Override
    public void onDenied(int requestCode) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
