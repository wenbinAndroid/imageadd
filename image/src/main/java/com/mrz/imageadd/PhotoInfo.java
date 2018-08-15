package com.mrz.imageadd;

/**
 * @author Mrz
 * @date 2018/8/11 18:39
 */
public class PhotoInfo {
    public boolean isEdit;
    public int itemCount;
    public int deleteIcon;
    public int addIcon;
    public int maxCount;
    public float margin;
    public float topMargin;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int width;

    public PhotoInfo(boolean isEdit, int itemCount, int deleteIcon, int addIcon, int maxCount,
                     float margin, float topMargin) {
        this.isEdit = isEdit;
        this.itemCount = itemCount;
        this.deleteIcon = deleteIcon;
        this.addIcon = addIcon;
        this.maxCount = maxCount;
        this.margin = margin;
        this.topMargin = topMargin;
    }


}
