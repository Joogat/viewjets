package com.joogat.viewouts;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.joogat.helpers.SystemBarHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProgressLayout extends FrameLayout {

    private SystemBarHelper systemBarHelper;

    private int backgroundColor = 0x77000000;

    private boolean isActive = false;

    public ProgressLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ProgressLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        setVisibility(INVISIBLE);
        setAlpha(0);
        setClickable(true);

        if(getBackground() instanceof ColorDrawable) backgroundColor = ((ColorDrawable) getBackground()).getColor();

        systemBarHelper = new SystemBarHelper((Activity) getContext());
        systemBarHelper.setDuration(200);

        ProgressBar progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleLarge);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        progressBar.setLayoutParams(params);
        addView(progressBar);

        progressBar.getIndeterminateDrawable().setColorFilter(0xffffffff, PorterDuff.Mode.SRC_IN);
    }


    public void show() {
        if(isActive) return;
        isActive = true;
        setVisibility(VISIBLE);
        setAlpha(0);
        animate().alpha(1).setDuration(200);
        systemBarHelper.animateColorTo(backgroundColor);
    }


    public void show(Runnable runnable) {
        if(isActive) {
            runnable.run();
            return;
        }
        isActive = true;
        setVisibility(VISIBLE);
        setAlpha(0);
        animate().alpha(1).setDuration(200).withEndAction(runnable);
        systemBarHelper.animateColorTo(backgroundColor);
    }


    public void hide() {
        isActive = false;
        setAlpha(0);
        setVisibility(INVISIBLE);
        systemBarHelper.resetColor();
    }
}