package ru.netology;

import android.graphics.drawable.Drawable;

public class ItemData {

    private Drawable image;
    private String title;
    private String subtitle;
    private boolean checked;

    public ItemData(Drawable image, String title, String subtitle, boolean checked) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.checked = checked;
    }

    public Drawable getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}