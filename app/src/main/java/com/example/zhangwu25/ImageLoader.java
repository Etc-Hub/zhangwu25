package com.example.zhangwu25;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;

public class ImageLoader {
    private static final String HTTP_PREFIX = "http://";
    private static final String HTTPS_PREFIX = "http://";
    private static final String FILE_PREFIX = "file://";
    private static final String CONTENT_PREFEIX = "content://";
    private static final String ASSETS_PREFIX = "assets://";
    private static final String DRAWABLE_PREFIX = "drawable://";

    private static ImageLoader sImageLoader;
    protected static WeakReference<Context> sContextWeak;

    public ImageLoader() {}

    public static void init(Context context) {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (sImageLoader == null) {
                    sImageLoader = new ImageLoader();
                }
            }
        }
        sContextWeak = new WeakReference<Context>(context);
    }

    public static void loadImage(ImageView imageView, String imageUrl) {
        loadImage(imageView, imageUrl, 0, 0, 0);
    }

    public static void loadImage(ImageView imageView, String imageUrl, int defaultImageId) {
        loadImage(imageView, imageUrl, defaultImageId, 0, 0);
    }

    public static void loadImage(ImageView imageView, String imageUrl, int defaultImageId, int maxWidth, int maxHeight) {
        loadImage(imageView, imageUrl, defaultImageId, maxWidth, maxHeight, ImageView.ScaleType.CENTER_INSIDE);
    }

    public static void loadImage(ImageView imageView, String imageUrl, int defaultImageId, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {
        isInitedCheck();
        loadImage((Context) sContextWeak.get(), imageView, imageUrl, defaultImageId, maxWidth, maxHeight, scaleType);
    }

    public static void loadImage(final Context context, final ImageView imageView, String imageUrl, int defaultImageId, int maxWidth, int maxHeight, ImageView.ScaleType scaleType) {

        final RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);
        Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .into(imageView);

    }

    public static void clear() {
        isInitedCheck();
        Glide.get((Context) sContextWeak.get()).clearMemory();
    }

    private static void isInitedCheck() {
        if (sContextWeak == null || sContextWeak.get() == null) {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
}
