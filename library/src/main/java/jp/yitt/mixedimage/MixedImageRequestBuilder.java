package jp.yitt.mixedimage;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.List;

public class MixedImageRequestBuilder extends BaseMixedImageRequestBuilder {

    private Bitmap[] bitmapIterator;

    MixedImageRequestBuilder(List<Bitmap> bitmaps) {
        bitmapIterator = (Bitmap[]) bitmaps.toArray();
    }

    @Override
    public MixedImageRequestBuilder size(float width, float height) {
        super.size(width, height);
        return this;
    }

    @Override
    public MixedImageRequestBuilder angle(double angle) {
        super.angle(angle);
        return this;
    }

    public void into(ImageView imageView) {
        Bitmap mixedBitmap = ImageMixer.generate(bitmapIterator, outputWidth, outputHeight, angle);
        imageView.setImageBitmap(mixedBitmap);
    }

    public Bitmap get() {
        return ImageMixer.generate(bitmapIterator, outputWidth, outputHeight, angle);
    }
}
