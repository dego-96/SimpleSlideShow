package jp.co.jicdom.simpleslideshow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

public class SlideShowActivity extends Activity {

    private static final String TAG = "SlideShowActivity";

    private ImageView mImageView;
    private ArrayList<Uri> mImageList;
    private int mImageCount;
    private long mShowTime = 5000; /* 5秒 */

    /**
     * onCreate
     *
     * @param aSavedInstanceState saved Instance state
     */
    @Override
    protected void onCreate(Bundle aSavedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(aSavedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.slideshow_activity);

        View decorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);
        } else {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }

        mImageView = findViewById(R.id.image_view);
        mImageList = getIntent().getParcelableArrayListExtra("image_uri");

        changeImage();
    }

    /**
     * changeImage
     */
    private void changeImage() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap bitmap = getBitmapFromUri(mImageList.get(mImageCount));
                    mImageCount++;
                    if (mImageCount >= mImageList.size()) {
                        mImageCount = 0;
                    }
                    mImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this, mShowTime);
            }
        };
        mImageCount = 0;
        handler.post(runnable);
    }

    /**
     * getBitmapFromUri
     *
     * @param uri uri
     * @return bitmap image
     * @throws IOException i/o exception
     */
    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        if (parcelFileDescriptor != null) {
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return image;
        } else {
            return null;
        }
    }
}
