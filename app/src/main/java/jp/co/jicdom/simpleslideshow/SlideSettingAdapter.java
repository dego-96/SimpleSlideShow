package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SlideSettingAdapter extends BaseAdapter {

    private static final String TAG = "SlideSettingAdapter";

    private static final int LIST_ITEM_COUNT = 3;
    private static final int ITEM_POS_0_SHOW_TIME = 0;
    private static final int ITEM_POS_1_SCALE = 1;
    private static final int ITEM_POS_2_PLAYBACK_ORDER = 2;

    private LayoutInflater mInflater;
    private Context mContext;

    SlideSettingAdapter(Context aContext) {
        super();

        mContext = aContext;
        mInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
        Log.d(TAG, "getView");
        TextView textView;
        String[] text_str = mContext.getResources().getStringArray(R.array.list_items_slide);

        if (null == aConvertView) {
            switch (aPosition) {
                case ITEM_POS_0_SHOW_TIME:
                case ITEM_POS_1_SCALE:
                case ITEM_POS_2_PLAYBACK_ORDER:
                    aConvertView = mInflater.inflate(R.layout.list_item_text, aParent, false);
                    textView = aConvertView.findViewById(R.id.list_item_text_view);
                    textView.setText(text_str[aPosition]);
                    break;
                default:
                    break;
            }
        }

        return aConvertView;
    }

    @Override
    public Object getItem(int aPosition) {
        Log.d(TAG, "getItem");
        return null;
    }

    @Override
    public long getItemId(int aPosition) {
        Log.d(TAG, "getItemId");
        return aPosition;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount");
        return LIST_ITEM_COUNT;
    }
}
