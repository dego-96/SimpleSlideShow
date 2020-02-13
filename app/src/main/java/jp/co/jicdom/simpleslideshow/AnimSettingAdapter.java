package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnimSettingAdapter extends BaseAdapter {
    private LayoutInflater mInflater;

    private List<AnimSettingData> mSettingDataList;

    AnimSettingAdapter(Context aContext) {
        super();

        String[] label_list = aContext.getResources().getStringArray(R.array.list_items_anim);

        mInflater = (LayoutInflater) aContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSettingDataList = new ArrayList<>();
        for (String label : label_list) {
            AnimSettingData data = new AnimSettingData();
            data.setLabel(label);
            data.setChecked(false);
            mSettingDataList.add(data);
        }
        if (!mSettingDataList.isEmpty()) {
            mSettingDataList.get(0).setChecked(true);
        }
    }

    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
        ViewHolder viewHolder;
        final AnimSettingData data = (AnimSettingData) this.getItem(aPosition);

        if (null == aConvertView) {
            aConvertView = mInflater.inflate(R.layout.list_item_radio, aParent, false);
            viewHolder = new ViewHolder();
            viewHolder.radioButton = aConvertView.findViewById(R.id.list_item_radio);
            viewHolder.textView = aConvertView.findViewById(R.id.list_item_text_view);
            aConvertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) aConvertView.getTag();
        }

        viewHolder.textView.setText(data.getLabel());
        viewHolder.radioButton.setChecked(data.isChecked());
        viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int index = 0; index < mSettingDataList.size(); index++) {
                    mSettingDataList.get(index).setChecked(false);
                }
                data.setChecked(true);
                notifyDataSetChanged();
            }
        });
        return aConvertView;
    }

    @Override
    public Object getItem(int aPosition) {
        return mSettingDataList.get(aPosition);
    }

    @Override
    public long getItemId(int aPosition) {
        return aPosition;
    }

    @Override
    public int getCount() {
        return mSettingDataList.size();
    }

    private class ViewHolder {
        TextView textView;
        RadioButton radioButton;
    }
}
