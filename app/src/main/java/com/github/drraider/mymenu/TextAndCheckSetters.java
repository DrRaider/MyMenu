package com.github.drraider.mymenu;

public class TextAndCheckSetters {
    private String text;
    private boolean selected;

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
}