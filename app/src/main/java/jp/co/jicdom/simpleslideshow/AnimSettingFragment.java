package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AnimSettingFragment extends Fragment {

    private static final String TAG = "AnimSettingFragment";

    private ListView mListView;

    /**
     * onCreate
     *
     * @param aSavedInstanceState saved instance state
     */
    @Override
    public void onCreate(Bundle aSavedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(aSavedInstanceState);
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
        super.onViewCreated(aView, aSavedInstanceState);

        Context context = getContext();
        if (context != null) {
            AnimSettingAdapter adapter = new AnimSettingAdapter(context);
            mListView.setAdapter(adapter);
        }
    }
}
