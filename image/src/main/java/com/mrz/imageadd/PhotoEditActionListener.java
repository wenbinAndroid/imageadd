package com.mrz.imageadd;

import android.widget.ImageView;

import java.util.List;

/**
 * @author Mrz
 * @date 2018/8/9 15:22
 */
public interface PhotoEditActionListener {
    void deleteImage(int position);

    void lookImage(int position, List<String> image);

    void addImage(AddPhotoManage manage, int position);

    String showPhoto(String image, int position, ImageView iv);

}
