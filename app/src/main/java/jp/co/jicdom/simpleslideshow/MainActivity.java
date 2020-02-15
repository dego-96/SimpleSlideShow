package jp.co.jicdom.simpleslideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /* Request Code */
    private static final int REQUEST_GALLERY = 0;
    private static final int REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 1;

    private ViewPagerAdapter mPagerAdapter;
    private List<Uri> mImageUriList;

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
                    if (aData.getData() != null) {
                        uri = aData.getData();
                        mImageUriList.add(uri);
                        Log.d(TAG, "URI : " + uri.getPath());
                        setDescriptionText(uri.getPath(), ContentsSettingAdapter.TEXTVIEW_ID_CONTENT);
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        ClipData clipData = aData.getClipData();
                        String text = "";
                        for (int index = 0; clipData != null && index < clipData.getItemCount(); index++) {
                            if (index == 0) {
                                text = clipData.getItemAt(index).getUri().getPath();
                                text = text + " + 他" + (clipData.getItemCount() - 1) + "件";
                            }
                            ClipData.Item item = clipData.getItemAt(index);
                            uri = item.getUri();
                            Log.d(TAG, "URI : " + uri.getPath());
                        }
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

    private void setDescriptionText(String aText, int aTextViewID) {
        Fragment fragment = mPagerAdapter.getFragment(0);
        if (fragment instanceof ContentsSettingFragment) {
            ((ContentsSettingFragment) fragment).setDescriptionText(aText, aTextViewID);
        }
    }
}
