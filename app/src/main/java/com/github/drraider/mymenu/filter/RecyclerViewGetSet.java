package com.github.drraider.mymenu.filter;

public class RecyclerViewGetSet {
    private String text;
    private boolean selected;
    private int pictureResId;


    int getPictureResId() {
        return pictureResId;
    }

    void setPictureResId(int pictureResId) {
        this.pictureResId = pictureResId;
    }

    boolean getSelected() {
        return selected;
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "RecyclerViewGetSet{" +
                "text='" + text + '\'' +
                ", selected=" + selected +
                ", pictureResId=" + pictureResId +
                '}';
    }
}