package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContentsSettingAdapter extends BaseAdapter {

    private static final int LIST_ITEM_COUNT = 4;
    public static final int ITEM_POS_0_IMAGE_DIR = 0;
    public static final int ITEM_POS_1_PLAY_MOVIE = 1;
    public static final int ITEM_POS_2_SELECT_BGM = 2;
    public static final int ITEM_POS_3_MOVIE_MUTE = 3;

    private LayoutInflater mInflater;

    ContentsSettingAdapter(Context aContext) {
        super();

        mInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
        TextView textView;
        ImageView imageView;
        String[] text_str = {
                "list_item_1",
                "list_item_2",
                "list_item_3",
                "list_item_4",
        };

        if (null == aConvertView) {
            switch (aPosition) {
                case ITEM_POS_0_IMAGE_DIR:
                    aConvertView = mInflater.inflate(R.layout.list_item_image, aParent, false);
                    textView = aConvertView.findViewById(R.id.list_item_text_view);
                    textView.setText(text_str[aPosition]);
                    imageView = aConvertView.findViewById(R.id.list_item_image);
                    imageView.setImageResource(R.mipmap.icon_folder);
                    break;
                case ITEM_POS_1_PLAY_MOVIE:
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
                    break;
                case ITEM_POS_3_MOVIE_MUTE:
                    aConvertView = mInflater.inflate(R.layout.list_item_check, aParent, false);
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
        return null;
    }

    @Override
    public long getItemId(int aPosition) {
        return aPosition;
    }

    @Override
    public int getCount() {
        return LIST_ITEM_COUNT;
    }
}
