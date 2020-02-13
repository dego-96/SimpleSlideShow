package jp.co.jicdom.simpleslideshow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class ContentsSettingFragment extends Fragment {

    private static final String TAG = "ContentsSettingFragment";

    private ListView mListView;

    /**
     * onCreate
     *
     * @param aSavedInstanceState saved instance state
     */
    @Override
    public void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);

        Log.d(TAG, "onCreate()");
    }

    /**
     * onCreateView
     *
     * @param aInflater           inflater
     * @param aContainer          container
     * @param aSavedInstanceState saved insatance state
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater aInflater, ViewGroup aContainer, Bundle aSavedInstanceState) {
        Log.d(TAG, "onCreateView()");
        View view = aInflater.inflate(R.layout.fragment_settings, null);
        mListView = view.findViewById(R.id.list_view_settings);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case ContentsSettingAdapter.ITEM_POS_0_IMAGE_DIR:
                        callGallery();
                        break;
                    case ContentsSettingAdapter.ITEM_POS_1_PLAY_MOVIE:
                        break;
                    case ContentsSettingAdapter.ITEM_POS_2_SELECT_BGM:
                        break;
                    case ContentsSettingAdapter.ITEM_POS_3_MOVIE_MUTE:
                        break;
                    default:
                        break;
                }
            }
        });

        return view;
    }

    /**
     * onViewCreated
     *
     * @param aView               view
     * @param aSavedInstanceState saved insatance state
     */
    @Override
    public void onViewCreated(View aView, Bundle aSavedInstanceState) {
        Log.d(TAG, "onViewCreated()");
        ContentsSettingAdapter adapter = new ContentsSettingAdapter(getContext());
        mListView.setAdapter(adapter);

        super.onViewCreated(aView, aSavedInstanceState);


    }

    private void callGallery() {
//        if (mCanReadExternalStorage) {
//            Intent intentGallery;
//
//            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
//
//            intentGallery.setType("video/*, images/*");
//            startActivityForResult(intentGallery, REQUEST_GALLERY);
//        } else {
//            /* 外部ストレージへのRead権限が無い場合は権限をリクエスト */
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
//                    ContextCompat.checkSelfPermission(
//                            this,
//                            Manifest.permission.READ_EXTERNAL_STORAGE
//                    ) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(
//                        this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_PERMISSION_READ_EXTERNAL_STORAGE
//                );
//            }
//        }
    }
}
