package com.zorz.mario.app.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viewcomponents.FontTextView;
import com.zorz.mario.R;


public class NavDrawerButton extends LinearLayout {

    private LinearLayout background;
    private ImageView icon;
    private FontTextView text;


    public NavDrawerButton(Context context) {
        super(context);
        init(null);
    }
    public NavDrawerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.navdrawer_item, this, true);
        background = (LinearLayout) findViewById(R.id.background);
        text = (FontTextView) findViewById(R.id.title);
        icon = (ImageView) findViewById(R.id.icon);

        TypedArray a=getContext().obtainStyledAttributes(
                attrs,
                R.styleable.NavDrawerButton);

        String textval = a.getString(R.styleable.NavDrawerButton_android_text);
        int color = a.getColor(R.styleable.NavDrawerButton_android_textColor, Color.BLACK);
        Drawable ic = a.getDrawable(R.styleable.NavDrawerButton_android_src);
        text.setText(textval);
        text.setTextColor(color);
        icon.setImageDrawable(ic);

        a.recycle();

    }

    public void formatNavDrawerItem(boolean selected) {

        if (selected) {
            background.setBackgroundResource(R.drawable.selected_navdrawer_item_background);
        } else{
            background.setBackgroundResource(0);
        }

        // configure its appearance according to whether or not it's selected
        text.setTextColor(selected ?
                getResources().getColor(R.color.navdrawer_text_color_selected) :
                getResources().getColor(R.color.navdrawer_text_color));
        icon.setColorFilter(selected ?
                getResources().getColor(R.color.navdrawer_icon_tint_selected) :
                getResources().getColor(R.color.navdrawer_icon_tint));
    }


}
