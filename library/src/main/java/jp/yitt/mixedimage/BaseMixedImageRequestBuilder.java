package jp.yitt.mixedimage;

public abstract class BaseMixedImageRequestBuilder {

    float outputWidth;
    float outputHeight;
    double angle;

    public BaseMixedImageRequestBuilder size(float width, float height) {
        this.outputWidth = width;
        this.outputHeight = height;
        return this;
    }

    public BaseMixedImageRequestBuilder angle(double angle) {
        this.angle = angle;
        return this;
    }
}
