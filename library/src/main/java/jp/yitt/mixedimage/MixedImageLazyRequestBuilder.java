package jp.yitt.mixedimage;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

public class MixedImageLazyRequestBuilder extends BaseMixedImageRequestBuilder {

    private int imageCount;

    MixedImageLazyRequestBuilder(int imageCount) {
        if (imageCount <= 0) {
            throw new IllegalArgumentException("Image Count must be more than 0.");
        }
        this.imageCount = imageCount;
    }

    @Override
    public MixedImageLazyRequestBuilder size(float width, float height) {
        super.size(width, height);
        return this;
    }

    @Override
    public MixedImageLazyRequestBuilder angle(double angle) {
        super.angle(angle);
        return this;
    }


    public MixedImageLazyRequest into(@NonNull final ImageView imageView) {
        ResultTarget resultTarget = new ResultTarget() {
            @Override
            public void onCompleted(Bitmap result) {
                imageView.setImageBitmap(result);
            }
        };
        return into(resultTarget);
    }

    public MixedImageLazyRequest into(@NonNull final ResultTarget resultTarget) {
        return new MixedImageLazyRequest(this.imageCount, outputWidth, outputHeight, angle, resultTarget);
    }
}
