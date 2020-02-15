package jp.co.jicdom.simpleslideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    /* Request Code */
    private static final int REQUEST_GALLERY = 0;
    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 1;

    private ViewPagerAdapter mPagerAdapter;
    private ArrayList<Uri> mImageUriList;

    /**
     * onCreate
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageUriList = new ArrayList<>();

        ViewPager viewPager = findViewById(R.id.view_pager);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.setContext(getApplicationContext());
        viewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * onActivityResult
     *
     * @param aRequestCode request code
     * @param aResultCode  result code
     * @param aData        data
     */
    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData) {
        super.onActivityResult(aRequestCode, aResultCode, aData);
        Log.d(TAG, "onActivityResult");

        switch (aRequestCode) {
            case REQUEST_PERMISSION_READ_EXTERNAL_STORAGE:
                if (aResultCode == RESULT_OK) {
                    intentGalleryApp();
                }
                break;
            case REQUEST_GALLERY:
                if (aResultCode == RESULT_OK) {
                    Uri uri;
                    String text;
                    if (aData.getData() != null) {
                        mImageUriList.add(aData.getData());
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        ClipData clipData = aData.getClipData();
                        for (int index = 0; clipData != null && index < clipData.getItemCount(); index++) {
                            ClipData.Item item = clipData.getItemAt(index);
                            mImageUriList.add(item.getUri());
                        }
                    }
                    if (mImageUriList.size() > 0) {
                        uri = mImageUriList.get(0);
                        text = getPictPath(uri);
                        if (mImageUriList.size() > 1) {
                            text = text + " + 他" + (mImageUriList.size() - 1) + "件";
                        }
                        Log.d(TAG, "file path : " + text);
                        setDescriptionText(text, ContentsSettingAdapter.TEXTVIEW_ID_CONTENT);
                    }
                }
                break;
            default:
                break;
        }
        if (aRequestCode == REQUEST_PERMISSION_READ_EXTERNAL_STORAGE) {
            if (aResultCode == RESULT_OK) {
                intentGalleryApp();
            }
        }
    }

    /**
     * onSlideShowButtonClicked
     *
     * @param aView view
     */
    public void onSlideShowButtonClicked(View aView) {
        if (mImageUriList != null && mImageUriList.size() > 0) {
            Intent intent = new Intent(getApplication(), SlideShowActivity.class);
            intent.putParcelableArrayListExtra("image_uri", mImageUriList);
            startActivity(intent);
        } else {
            String toast_text = getString(R.string.toast_no_image);
            Toast.makeText(this, toast_text, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * callGallery
     */
    public void callGallery() {
        Log.d(TAG, "callGallery");
        int permission;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        } else {
            permission = PackageManager.PERMISSION_GRANTED;
        }
        if (permission == PackageManager.PERMISSION_GRANTED) {
            /* 外部ストレージへのRead権限が有る場合はギャラリーを呼び出し */
            intentGalleryApp();
        } else {
            /* 外部ストレージへのRead権限が無い場合は権限をリクエスト */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_READ_EXTERNAL_STORAGE
                );
            }
        }
    }

    /**
     * intentGalleryApp
     */
    private void intentGalleryApp() {
        mImageUriList.clear();
        Intent intentGallery;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intentGallery = new Intent();
        }
        intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intentGallery.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intentGallery.setType("*/*");
            String[] mime_types = {"image/*", "video/*"};
            intentGallery.putExtra(Intent.EXTRA_MIME_TYPES, mime_types);
        } else {
            intentGallery.setType("image/*,video/*");
        }
        startActivityForResult(intentGallery, REQUEST_GALLERY);
    }

    /**
     * getPictPath
     *
     * @param aUri uri
     * @return image/video path
     */
    private String getPictPath(Uri aUri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String id = DocumentsContract.getDocumentId(aUri);
            String selection = "_id=?";
            String[] selectionArgs = new String[]{id.split(":")[1]};
            File file = null;

            Cursor cursor = getContentResolver().query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.MediaColumns.DATA},
                    selection,
                    selectionArgs,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                file = new File(cursor.getString(0));
                cursor.close();
            }

            if (file != null) {
                return file.getAbsolutePath();
            }
        } else {
            ContentResolver contentResolver = getContentResolver();
            String[] columns = {MediaStore.Images.Media.DATA};
            Cursor cursor = contentResolver.query(aUri, columns, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String filePath = cursor.getString(0);
                cursor.close();
                return filePath;
            }
        }
        return null;
    }

    private void setDescriptionText(String aText, int aTextViewID) {
        Fragment fragment = mPagerAdapter.getFragment(0);
        if (fragment instanceof ContentsSettingFragment) {
            ((ContentsSettingFragment) fragment).setDescriptionText(aText, aTextViewID);
        }
    }
}
