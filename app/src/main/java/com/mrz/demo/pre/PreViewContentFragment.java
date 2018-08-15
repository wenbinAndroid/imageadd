package com.mrz.demo.pre;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mrz.demo.R;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

/**
 * @author Mrz
 * @date 2018/8/14 17:07
 */
public class PreViewContentFragment extends Fragment {

    public static PreViewContentFragment newInstant(String image) {
        PreViewContentFragment fragment = new PreViewContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PreViewActivity.IMAGE, image);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pre_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        ImageViewTouch image = (ImageViewTouch) view.findViewById(R.id.image_view);
        image.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        String photo = getArguments().getString(PreViewActivity.IMAGE);
        //这边可以通过后缀名来判断图片类型 然后加载不同的
        Glide.with(this).load(photo).into(image);
    }




    public void resetView() {
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(R.id.image_view))
                    .resetMatrix();
        }
    }
}
