package com.blocksolid.okrello;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Dan Buckland on 09/03/2016.
 */
public class MolleFont extends TextView {

    public MolleFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Molle-Regular.ttf"));
    }
}
