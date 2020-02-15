package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentsSettingAdapter extends BaseAdapter {

    private static final String TAG = "ContentsSettingAdapter";

    private static final int LIST_ITEM_COUNT = 4;
    static final int ITEM_POS_0_IMAGE_DIR = 0;
    static final int ITEM_POS_1_PLAY_MOVIE = 1;
    static final int ITEM_POS_2_SELECT_BGM = 2;
    static final int ITEM_POS_3_MOVIE_MUTE = 3;

    /* TextView ID*/
    static final int TEXTVIEW_ID_CONTENT = 0;
    private static final int TEXTVIEW_ID_BGM = 1;
    private static final int TEXTVIEW_ID_COUNT = 2;

    private LayoutInflater mInflater;
    private Context mContext;
    private TextView[] mDescriptionTextViews;

    /**
     * ContentsSettingAdapter
     *
     * @param aContext context
     */
    ContentsSettingAdapter(Context aContext) {
        super();
        mContext = aContext;

        mInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mDescriptionTextViews = new TextView[TEXTVIEW_ID_COUNT];
    }

    /**
     * getView
     *
     * @param aPosition    position
     * @param aConvertView convert view
     * @param aParent      parent view group
     * @return convert view
     */
    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
        Log.d(TAG, "getView");
        TextView textView;
        ImageView imageView;
        String[] text_str = mContext.getResources().getStringArray(R.array.list_items_contents);

        if (null == aConvertView) {
            switch (aPosition) {
                case ITEM_POS_0_IMAGE_DIR:
                    aConvertView = mInflater.inflate(R.layout.list_item_image, aParent, false);
                    textView = aConvertView.findViewById(R.id.list_item_text_view);
                    textView.setText(text_str[aPosition]);
                    imageView = aConvertView.findViewById(R.id.list_item_image);
                    imageView.setImageResource(R.mipmap.icon_folder);
                    mDescriptionTextViews[TEXTVIEW_ID_CONTENT] = aConvertView.findViewById(R.id.list_item_text_view_description);
                    break;
                case ITEM_POS_1_PLAY_MOVIE:
                case ITEM_POS_3_MOVIE_MUTE:
                    aConvertView = mInflater.inflate(R.layout.list_item_check, aParent, false);
                    textView = aConvertView.findViewById(R.id.list_item_text_view);
                    textView.setText(text_str[aPosition]);
                    break;
                case ITEM_POS_2_SELECT_BGM:
                    aConvertView = mInflater.inflate(R.layout.list_item_image, aParent, false);
                    textView = aConvertView.findViewById(R.id.list_item_text_view);
                    textView.setText(text_str[aPosition]);
                    imageView = aConvertView.findViewById(R.id.list_item_image);
                    imageView.setImageResource(R.mipmap.icon_file);
                    mDescriptionTextViews[TEXTVIEW_ID_BGM] = aConvertView.findViewById(R.id.list_item_text_view_description);
                    break;
                default:
                    break;
            }
        }
        return aConvertView;
    }

    /**
     * getItem
     *
     * @param aPosition position
     * @return null
     */
    @Override
    public Object getItem(int aPosition) {
        Log.d(TAG, "getItem");
        return null;
    }

    /**
     * getItemId
     *
     * @param aPosition position
     * @return position
     */
    @Override
    public long getItemId(int aPosition) {
        Log.d(TAG, "getItemId");
        return aPosition;
    }

    /**
     * getCount
     *
     * @return list item count
     */
    @Override
    public int getCount() {
        Log.d(TAG, "getCount");
        return LIST_ITEM_COUNT;
    }

    /**
     * setDescriptionText
     *
     * @param aText       text
     * @param aTextViewID TextView ID
     */
    void setDescriptionText(String aText, int aTextViewID) {
        if (aTextViewID < TEXTVIEW_ID_COUNT) {
            mDescriptionTextViews[aTextViewID].setText(aText);
        }
    }
}
