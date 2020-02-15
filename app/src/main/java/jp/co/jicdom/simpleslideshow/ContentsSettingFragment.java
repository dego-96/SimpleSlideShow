package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ContentsSettingFragment extends Fragment {

    private static final String TAG = "ContentsSettingFragment";

    private ListView mListView;
    private ContentsSettingAdapter mAdapter;
    private Context mContext;

    /**
     * onCreate
     *
     * @param aSavedInstanceState saved instance state
     */
    @Override
    public void onCreate(Bundle aSavedInstanceState) {
        super.onCreate(aSavedInstanceState);

        Log.d(TAG, "onCreate");
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
        Log.d(TAG, "onCreateView");
        View view = aInflater.inflate(R.layout.fragment_settings, null);
        mListView = view.findViewById(R.id.list_view_settings);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case ContentsSettingAdapter.ITEM_POS_0_IMAGE_DIR:
                        ((MainActivity) mContext).callGallery();
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
    public void onViewCreated(@NonNull View aView, Bundle aSavedInstanceState) {
        Log.d(TAG, "onViewCreated");
        Context context = getContext();
        if (context != null) {
            mAdapter = new ContentsSettingAdapter(context);
            mListView.setAdapter(mAdapter);
        }

        super.onViewCreated(aView, aSavedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context aContext) {
        Log.d(TAG, "onAttach");
        super.onAttach(aContext);
        if (aContext instanceof MainActivity) {
            mContext = aContext;
        }
    }

    void setDescriptionText(String aText, int aTextViewID) {
        mAdapter.setDescriptionText(aText, aTextViewID);
    }
}
