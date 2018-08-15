package com.mrz.imageadd.listener;

import android.widget.ImageView;

import java.util.List;

/**
 * @author Mrz
 * @date 2018/8/11 01:40
 */
public interface PhotoResourceListener {
    String loadImage(String image, int position, ImageView iv);

    void previewImage(int position, List<String> image);
}
