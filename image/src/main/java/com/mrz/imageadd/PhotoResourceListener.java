package com.mrz.imageadd;

import android.widget.ImageView;

import java.util.List;

/**
 * @author Mrz
 * @date 2018/8/11 01:40
 */
public interface PhotoResourceListener {
    String showPhoto(String image, int position, ImageView iv);

    void lookImage(int position, List<String> image);
}
