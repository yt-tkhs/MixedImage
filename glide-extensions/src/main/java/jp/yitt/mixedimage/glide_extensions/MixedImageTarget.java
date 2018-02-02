package jp.yitt.mixedimage.glide_extensions;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import jp.yitt.mixedimage.MixedImageLazyRequest;

public class MixedImageTarget extends SimpleTarget<Bitmap> {

    private static final int NO_INDEX = -1;

    private final MixedImageLazyRequest requests;
    private final int index;

    public MixedImageTarget(@NonNull MixedImageLazyRequest requests) {
        this.requests = requests;
        this.index = NO_INDEX;
    }

    public MixedImageTarget(@NonNull MixedImageLazyRequest requests, int index) {
        this.requests = requests;
        this.index = index;
    }

    @Override
    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
        if (index == NO_INDEX) {
            requests.add(resource);
        } else {
            requests.add(index, resource);
        }
    }
}
