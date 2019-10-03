package com.joogat.viewouts;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.joogat.helpers.SoftInputHelper;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomSoftInputEditText extends AppCompatEditText {

    public CustomSoftInputEditText(Context context) {
        super(context);
        init();
    }

    public CustomSoftInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomSoftInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init() {
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            setShowSoftInputOnFocus(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        boolean result = super.onTouchEvent(event);

        if( Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP ) {

            SoftInputHelper.hideSoftInput(getContext(), this);
        }

        return  result;
    }
}
