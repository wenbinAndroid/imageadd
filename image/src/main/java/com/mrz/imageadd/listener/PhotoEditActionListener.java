package com.mrz.imageadd.listener;

import android.widget.ImageView;

import com.mrz.imageadd.AddPhotoManage;

import java.util.List;

/**
 * @author Mrz
 * @date 2018/8/9 15:22
 */
public interface PhotoEditActionListener {
    void deleteImage(int position);

    void previewImage(int position, List<String> image);

    void addImage(AddPhotoManage manage, int position);

    void loadImage(String image, int position, ImageView iv);

}
