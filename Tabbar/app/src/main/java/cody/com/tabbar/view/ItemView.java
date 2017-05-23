package cody.com.tabbar.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cody.com.tabbar.R;
import cody.com.tabbar.view.util.DeviceDensity;

/**
 * Created by apple on 2017/5/23.
 */

public class ItemView extends LinearLayout {
    private TabItem tabItem;
    private ImageView imageView;
    private TextView textView;
    private boolean selected = false;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.VERTICAL);
        setGravity(Gravity.CENTER);

        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.drawable.credit_card_more);
        imageView.setLayoutParams(
                new LinearLayout.LayoutParams(DeviceDensity.dip2px(context, 30), DeviceDensity.dip2px(context, 30)));
        textView = new TextView(context);
        textView.setLayoutParams(
                new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        this.addView(imageView);
        this.addView(textView);
    }

    public void setDataSource(TabItem item) {
        this.tabItem = item;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TextView getTextView() {
        return textView;
    }


}
