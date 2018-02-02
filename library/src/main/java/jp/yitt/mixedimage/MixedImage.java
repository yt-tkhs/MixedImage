package jp.yitt.mixedimage;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MixedImage {

    /**
     * Create builder with existing bitmaps as array.
     */
    public static MixedImageRequestBuilder from(Bitmap... bitmaps) {
        return new MixedImageRequestBuilder(Arrays.asList(bitmaps));
    }

    /**
     * Create builder with existing bitmaps as iterator.
     */
    public static MixedImageRequestBuilder from(Iterator<Bitmap> bitmaps) {
        ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();
        while (bitmaps.hasNext()) {
            Bitmap nextElement = bitmaps.next();
            bitmapArrayList.add(nextElement);
        }
        return new MixedImageRequestBuilder(bitmapArrayList);
    }

    /**
     * Create lazy builder with not existing images for now.
     */
    public static MixedImageLazyRequestBuilder lazy(int imageCount) {
        return new MixedImageLazyRequestBuilder(imageCount);
    }
}
