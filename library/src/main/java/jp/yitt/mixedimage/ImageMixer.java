package jp.yitt.mixedimage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Image mixing engine
 */
class ImageMixer {

    static Bitmap generate(Bitmap[] bitmaps, float outputWidth, float outputHeight, Double angle) {
        float verticalDistance = (float) (outputHeight * Math.tan(Math.toRadians(angle)));
        float baseDistance = (outputWidth + verticalDistance) / bitmaps.length;

        Bitmap outputBitmap = Bitmap.createBitmap((int) outputWidth, (int) outputHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        canvas.drawRGB(0, 0, 0);
        Float centerIndex = (bitmaps.length - 1f) / 2f;
        for (int i = 0; i < bitmaps.length; i++) {
            Bitmap bitmap = bitmaps[i];
            Bitmap croppedBitmap = cropParallelogram(bitmap, verticalDistance, baseDistance, outputHeight);
            Float x = ((i - centerIndex) * baseDistance) + (outputBitmap.getWidth() - croppedBitmap.getWidth()) / 2f;
            Float y = (outputBitmap.getHeight() - croppedBitmap.getHeight()) / 2f;
            canvas.drawBitmap(croppedBitmap, x, y, paint);
        }
        return outputBitmap;
    }

    private static Bitmap cropParallelogram(Bitmap bitmap, float verticalDistance, float baseDistance, float outputHeight) {
        float requiredWidth = baseDistance + verticalDistance;
        float scale = outputHeight / (float) bitmap.getHeight();
        if (scale * bitmap.getWidth() < requiredWidth) {
            scale = requiredWidth / bitmap.getWidth();
        }

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                bitmap, (int) (bitmap.getWidth() * scale), (int) (bitmap.getHeight() * scale), true);
        Bitmap outputBitmap = Bitmap.createBitmap(
                scaledBitmap.getWidth(), scaledBitmap.getHeight(), scaledBitmap.getConfig());

        Path path = new Path();
        path.lineTo(0f, 0f);
        path.lineTo(baseDistance, 0f);
        path.lineTo(-verticalDistance + baseDistance, outputHeight);
        path.lineTo(-verticalDistance, outputHeight);

        Float offsetX = (scaledBitmap.getWidth() - baseDistance + verticalDistance) / 2f;
        Float offsetY = (scaledBitmap.getHeight() - outputHeight) / 2f;
        path.offset(offsetX, offsetY);

        Canvas canvas = new Canvas(outputBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledBitmap, 0f, 0f, paint);
        scaledBitmap.recycle();

        return outputBitmap;
    }
}
