package jp.co.jicdom.simpleslideshow;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "ViewPagerAdapter";
    private static final int PAGE_NUM = 3;
    private Context mAppContext;

    private Fragment[] mFragments;

    /**
     * ViewPagerAdapter
     *
     * @param aFragmentManager fragment manager
     */
    ViewPagerAdapter(FragmentManager aFragmentManager) {
        super(aFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragments = new Fragment[PAGE_NUM];
    }

    /**
     * getItem
     *
     * @param aPosition position
     * @return fragment
     */
    @NonNull
    @Override
    public Fragment getItem(int aPosition) {
        Log.d(TAG, "getItem(" + aPosition + ")");

        if (aPosition < PAGE_NUM) {
            if (mFragments[aPosition] == null) {
                switch (aPosition) {
                    case 1:
                        mFragments[1] = new SlideSettingFragment();
                        break;
                    case 2:
                        mFragments[2] = new AnimSettingFragment();
                        break;
                    case 0:
                    default:
                        mFragments[0] = new ContentsSettingFragment();
                        break;
                }
            }
            return mFragments[aPosition];
        } else {
            return mFragments[0];
        }
    }

    /**
     * getCount
     *
     * @return page count
     */
    @Override
    public int getCount() {
        Log.d(TAG, "getCount");
        return PAGE_NUM;
    }

    /**
     * getPageTitle
     *
     * @param aPosition position
     * @return page title
     */
    @Override
    public CharSequence getPageTitle(int aPosition) {
        Log.d(TAG, "getPageTitle");
        final CharSequence[] tab_title = {
                mAppContext.getString(R.string.tab1_content),
                mAppContext.getString(R.string.tab2_slideshow),
                mAppContext.getString(R.string.tab3_animation)
        };
        return tab_title[aPosition];
    }

    /**
     * setContext
     *
     * @param aContext context
     */
    void setContext(Context aContext) {
        Log.d(TAG, "setContext");
        mAppContext = aContext;
    }

    /**
     * getFragment
     */
    Fragment getFragment(int aPosition) {
        return mFragments[aPosition];
    }
}
