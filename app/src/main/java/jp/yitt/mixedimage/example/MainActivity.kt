package jp.yitt.mixedimage.example

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.SeekBar
import jp.yitt.mixedimage.MixedImage


class MainActivity : AppCompatActivity() {

    companion object {
        private const val resultWidth = 1280f
        private const val resultHeight = 720f
        private const val angle = 15.0
    }

    val imageView: ImageView by lazy { findViewById<ImageView>(R.id.imageView) }
    private lateinit var bitmaps: List<Bitmap>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val widthSeekBar = findViewById<SeekBar>(R.id.widthSeekBar)
        val heightSeekBar = findViewById<SeekBar>(R.id.heightSeekBar)
        val angleSeekBar = findViewById<SeekBar>(R.id.angleSeekBar)

        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.example1)
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.example2)
        val bitmap3 = BitmapFactory.decodeResource(resources, R.drawable.example3)
        val bitmap4 = BitmapFactory.decodeResource(resources, R.drawable.example4)
        bitmaps = listOf(bitmap1, bitmap2, bitmap3, bitmap4)

        widthSeekBar.progress = resultWidth.toInt()
        heightSeekBar.progress = resultHeight.toInt()
        angleSeekBar.progress = angle.toInt()

        widthSeekBar.setOnSeekBarChangeListener(onProgressChanged = { _, progress, _ ->
            if (progress == 0) return@setOnSeekBarChangeListener
            MixedImage.from(*bitmaps.toTypedArray())
                    .size(progress.toFloat(), heightSeekBar.progress.toFloat())
                    .angle(angleSeekBar.progress.toDouble())
                    .into(imageView)
        })

        heightSeekBar.setOnSeekBarChangeListener(onProgressChanged = { _, progress, _ ->
            if (progress == 0) return@setOnSeekBarChangeListener
            MixedImage.from(*bitmaps.toTypedArray())
                    .size(widthSeekBar.progress.toFloat(), progress.toFloat())
                    .angle(angleSeekBar.progress.toDouble())
                    .into(imageView)
        })

        angleSeekBar.setOnSeekBarChangeListener(onProgressChanged = { _, progress, _ ->
            MixedImage.from(*bitmaps.toTypedArray())
                    .size(widthSeekBar.progress.toFloat(), heightSeekBar.progress.toFloat())
                    .angle(progress.toDouble())
                    .into(imageView)
        })

        MixedImage.from(*bitmaps.toTypedArray())
                .size(resultWidth, resultHeight)
                .angle(angle)
                .into(imageView)
    }

    private fun SeekBar.setOnSeekBarChangeListener(
            onProgressChanged: ((SeekBar, Int, Boolean) -> Unit)? = null,
            onStartTrackingTouch: ((SeekBar) -> Unit)? = null,
            onStopTrackingTouch: ((SeekBar) -> Unit)? = null
    ) {
        setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                onProgressChanged?.invoke(seekBar, progress, fromUser)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                onStartTrackingTouch?.invoke(seekBar)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                onStopTrackingTouch?.invoke(seekBar)
            }

        })
    }
}
