# MixedImage
<img src="https://github.com/yt-tkhs/MixedImage/blob/master/art/header.png"/>


 [ ![Download](https://api.bintray.com/packages/yt-tkhs/maven/mixedimage/images/download.svg) ](https://bintray.com/yt-tkhs/maven/mixedimage/_latestVersion)

## Gradle
```groovy
dependencies {
    implementation 'jp.yitt.mixedimage:mixedimage:0.1.1'
}
```

## Example
<img src="https://github.com/yt-tkhs/MixedImage/blob/master/art/preview.gif" width="320" />

## Usase

Below is basic usage.
```kotlin
MixedImage.from(bitmaps)
    .size(1280f, 720f)
    .angle(15.0)
    .into(imageView)
```

### Use with Glide

1. Install the package below.
```groovy
dependencies {
    implementation 'jp.yitt.mixedimage:glide-extensions:0.1.1'
}
```

2. Load each images using Glide like below.
```kotlin
val imageUrls = listOf("image_url1", "image_url2", "image_url3")

val request = MixedImage.lazy(imageUrls.size)
        .size(1280f, 720f)
        .angle(15.0)
        .into(imageView)

imageUrls.forEachIndexed { index, imageUrl ->
    Glide.with(context)
            .asBitmap()
            .load(imageUrl)
            // If you don't gurantee order of images, you can just write "MixedImageTarget(request)".
            .into(MixedImageTarget(request, index))
}
```

3. When all of images are loaded, it generate image and set to ImageView automatically.

## Configurations
- size: set width and height of output image.
- angle: `0 - 45` is recommend. If you set angle too large, it may throw OutOfMemoryException.

## Author
- Yuta Takahashi ([@yt_hizi](https://twitter.com/yt_hizi))

## Licence
```
Copyright 2017 Yuta Takahashi.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```