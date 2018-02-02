package jp.yitt.mixedimage;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.util.Arrays;

public class MixedImageLazyRequest {

    private static final int NO_IMAGE_COUNT = -1;

    private final Bitmap[] bitmaps;
    private final int imageCount;
    private float outputWidth;
    private float outputHeight;
    private double angle;
    private ResultTarget resultTarget;

    MixedImageLazyRequest(int imageCount, float outputWidth, float outputHeight,
                          double angle, ResultTarget resultTarget) {

        if (imageCount <= 0) {
            throw new IllegalArgumentException("Image Count must be more than 0.");
        }
        this.imageCount = imageCount;
        this.bitmaps = new Bitmap[this.imageCount];
        this.outputWidth = outputWidth;
        this.outputHeight = outputHeight;
        this.angle = angle;
        this.resultTarget = resultTarget;
    }

    /**
     * Add image bitmap based on the index.
     *
     * @param index  index of the bitmap image
     * @param bitmap image bitmap
     */
    public void add(final int index, @NonNull final Bitmap bitmap) {
        if (imageCount == NO_IMAGE_COUNT) {
            throw new IllegalStateException("Image Count must set before add bitmaps.");
        }
        bitmaps[index] = bitmap;
        startIfLoadCompleted();
    }

    /**
     * Add image bitmap with searching for index that doesn't have image.
     *
     * @param bitmap image bitmap
     */
    public void add(@NonNull final Bitmap bitmap) {
        for (int i = 0; i < bitmaps.length; i++) {
            if (bitmaps[i] == null) {
                bitmaps[i] = bitmap;
                startIfLoadCompleted();
                return;
            }
        }
        throw new IllegalStateException("You were trying to add new bitmap, but it has already " + this.imageCount + " bitmaps.");
    }

    /**
     * Create builder to generate images that configurations are changed.
     *
     * @return MixedImageRequestBuilder that configurations are not changed.
     */
    public MixedImageRequestBuilder builder() {
        return new MixedImageRequestBuilder(Arrays.asList(bitmaps))
                .size(outputWidth, outputHeight)
                .angle(angle);
    }

    /**
     * Start generating mixed images if all of bitmaps have been loaded.
     */
    private void startIfLoadCompleted() {
        for (Bitmap bitmap : bitmaps) {
            if (bitmap == null) return;
        }
        Bitmap bitmap = ImageMixer.generate(bitmaps, outputWidth, outputHeight, angle);
        if (this.resultTarget != null) {
            this.resultTarget.onCompleted(bitmap);
        }
    }
}
