package com.joogat.viewouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class SelectableTextViewWithDrawables extends TextViewWithDrawables implements View.OnClickListener {

    private View.OnClickListener clickListener;

    private int drawableColorSelected;
    private int backgroundColorSelected;

    private int textColorDefault;
    private int textColorSelected;

    private Drawable backgroundDefault;

    private boolean isSelected = false;

    public SelectableTextViewWithDrawables(Context context) {
        super(context);
    }

    public SelectableTextViewWithDrawables(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectableTextViewWithDrawables(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    void init(Context context, AttributeSet attrs, int defStyleAttr) {

        super.init(context, attrs, defStyleAttr);

        // Settings this as the click listener
        super.setOnClickListener(this);

        if (attrs != null) {

            TypedArray a = context.obtainStyledAttributes( attrs, R.styleable.SelectableTextViewWithDrawables);

            backgroundDefault = getBackground();
            textColorDefault = getCurrentTextColor();

            backgroundColorSelected = a.getColor(R.styleable.SelectableTextViewWithDrawables_backgroundColorSelected, 0);

            drawableColorSelected = a.getColor(R.styleable.SelectableTextViewWithDrawables_drawableColorSelected, 0);
            textColorSelected = a.getColor(R.styleable.SelectableTextViewWithDrawables_textColorSelected, 0);

            a.recycle();
        }
    }



    public void setSelected(){
        isSelected = true;
        if(getDrawableLeft() != null) applyDrawableColor(getDrawableLeft(), drawableColorSelected);
        if(getDrawableRight() != null) applyDrawableColor(getDrawableRight(), drawableColorSelected);
        if(getDrawableTop() != null) applyDrawableColor(getDrawableTop(), drawableColorSelected);
        if(getDrawableBottom() != null) applyDrawableColor(getDrawableBottom(), drawableColorSelected);
        setTextColor(textColorSelected);
        setBackgroundColor(backgroundColorSelected);
    }


    public void setUnselected(){
        isSelected = false;
        if(getDrawableLeft() != null) applyDrawableColor(getDrawableLeft(), drawableColor);
        if(getDrawableRight() != null) applyDrawableColor(getDrawableRight(), drawableColor);
        if(getDrawableTop() != null) applyDrawableColor(getDrawableTop(), drawableColor);
        if(getDrawableBottom() != null) applyDrawableColor(getDrawableBottom(), drawableColor);
        setTextColor(textColorDefault);
        setBackground(backgroundDefault);
    }


    @Override
    public void onClick(View v) {

        if(isSelected){
            setUnselected();
        } else {
            setSelected();
        }

        if(clickListener != null){
            clickListener.onClick(this);
        }
    }


    @Override
    public void setOnClickListener(@Nullable View.OnClickListener l) {
        clickListener = l;
        // Not forwarding this listener to super as pageActivity listener is already set inside init(Context context, AttributeSet attrs, int defStyleAttr)
    }


    @Override
    public void setTextColor(int textColor) {
        if(textColor == 0) { super.setTextColor(textColorDefault);}
        else { super.setTextColor(textColor); }
    }


    @Override
    public void setBackground(Drawable background) {
        if(background == null) super.setBackground(backgroundDefault);
        else super.setBackground(background);
    }

    @Override
    public void setBackgroundColor(int color) {
        if(color == 0) { super.setBackground(backgroundDefault);}
        else{ super.setBackgroundColor(color); }
    }
}