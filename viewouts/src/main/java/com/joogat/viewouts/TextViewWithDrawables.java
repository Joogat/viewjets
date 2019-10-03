package com.joogat.viewouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.CallSuper;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;

public class TextViewWithDrawables extends AppCompatTextView {

    int drawableColor;

    private Drawable drawableLeft, drawableRight, drawableTop, drawableBottom;

    public TextViewWithDrawables(Context context) {
        this(context, null);
    }

    public TextViewWithDrawables(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewWithDrawables(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    @CallSuper
    void init(Context context, AttributeSet attrs, int defStyleAttr) {

        if (attrs != null) {

            TypedArray a = context.obtainStyledAttributes( attrs, R.styleable.TextViewWithDrawables );

            drawableColor = a.getColor(R.styleable.TextViewWithDrawables_drawableColor, 0);

            drawableLeft = getDrawableFor(context, R.styleable.TextViewWithDrawables_drawableLeftCompat, a);
            drawableRight = getDrawableFor(context, R.styleable.TextViewWithDrawables_drawableRightCompat, a);
            drawableTop = getDrawableFor(context, R.styleable.TextViewWithDrawables_drawableTopCompat, a);
            drawableBottom = getDrawableFor(context, R.styleable.TextViewWithDrawables_drawableBottomCompat, a);

            a.recycle();
        }

        setCustomDrawables();
    }


    Drawable getDrawableFor(Context context, int styleableIndex, TypedArray a ){

        Drawable drawable;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            drawable = a.getDrawable( styleableIndex );

        } else {

            int resourceId = a.getResourceId(styleableIndex, -1);

            if (resourceId == -1) return null;

            drawable = AppCompatResources.getDrawable(context, resourceId);
        }

        if(drawable == null) return null;

        drawable = drawable.mutate();

        applyDrawableColor(drawable, drawableColor);

        return drawable;
    }


    public void setCustomDrawables(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            setCompoundDrawablesRelativeWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        }
    }


    public void applyDrawableColor(Drawable drawable, int color){
        if(color != 0){
            drawable.setColorFilter( color, PorterDuff.Mode.SRC_IN );
        }
    }

    public void setDrawableRight(Drawable drawableRight){ this.drawableRight = drawableRight; }
    public void setDrawableBottom(Drawable drawableBottom){ this.drawableBottom = drawableBottom; }
    public void setDrawableLeft(Drawable drawableLeft){ this.drawableLeft = drawableLeft; }
    public void setDrawableTop(Drawable drawableTop){ this.drawableTop = drawableTop; }

    public Drawable getDrawableRight(){ return drawableRight; }
    public Drawable getDrawableBottom(){ return drawableBottom; }
    public Drawable getDrawableLeft(){ return drawableLeft; }
    public Drawable getDrawableTop(){ return drawableTop; }
}