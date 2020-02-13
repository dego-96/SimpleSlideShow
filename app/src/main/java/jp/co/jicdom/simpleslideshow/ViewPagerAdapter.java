package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "ViewPagerAdapter";
    private static final int PAGE_NUM = 3;

    ViewPagerAdapter(FragmentManager aFragmentManager) {
        super(aFragmentManager);
    }

    @Override
    public Fragment getItem(int aPosition) {
        Log.d(TAG, "getItem(" + aPosition + ")");

        Fragment fragment;
        switch (aPosition) {
            case 0:
                fragment = new ContentsSettingFragment();
                break;
            case 1:
//                fragment = new SlideSettingFragment();
                fragment = new ContentsSettingFragment();
                break;
            case 2:
//                fragment = new AnimSettingFragment();
                fragment = new ContentsSettingFragment();
                break;
            default:
                fragment = new ContentsSettingFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Override
    public CharSequence getPageTitle(int aPosition) {
        final CharSequence tab_title[] = {
                "aaa",
                "bbb",
                "ccc"
//                context.getString(R.string.tab1_content),
//                context.getString(R.string.tab2_slideshow),
//                context.getString(R.string.tab3_animation)
        };
        return tab_title[aPosition];
    }
}