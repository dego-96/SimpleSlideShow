package jp.co.jicdom.simpleslideshow;

public class AnimSettingData {

    private String mLabel = null;
    private boolean mIsChecked = false;

    /**
     * setLabel
     *
     * @param aLabel label
     */
    void setLabel(String aLabel) {
        mLabel = aLabel;
    }

    /**
     * setChecked
     *
     * @param aIsChecked is checked
     */
    void setChecked(boolean aIsChecked) {
        this.mIsChecked = aIsChecked;
    }

    /**
     * isChecked
     *
     * @return is checked
     */
    boolean isChecked() {
        return mIsChecked;
    }

    /**
     * getLabel
     *
     * @return label
     */
    String getLabel() {
        return mLabel;
    }
}
