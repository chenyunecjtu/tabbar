package cody.com.tabbar.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import cody.com.tabbar.view.util.DeviceDensity;

/**
 * Created by apple on 2017/5/23.
 */

public class TabbarView extends LinearLayout {
    private Context context;

    private List<TabItem> tabItems;
    private List<ItemView> itemViews;
    private int width;
    private int height;
    private onItemSelectListener listener;

    public TabbarView(Context context) {
        this(context, null);
    }

    public TabbarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public TabbarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.parseColor("blue"));
        this.context = context;
        setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(i,i,i,i);
        setLayoutParams(layoutParams);

    }


    public void setTabItems(List<TabItem> tabItems) {
        this.tabItems = tabItems;
    }

    public void setOnItemSelectListener(onItemSelectListener l) {
        this.listener = l;
    }


    private void initView(List<TabItem> tabItems) {
        int len = tabItems.size();
        if (itemViews == null) {
            itemViews = new ArrayList<>();
        }
        int subWidth = width / len;
        for (int i = 0; i < len; i++) {
            ItemView view = new ItemView(context);
            view.setDataSource(tabItems.get(i));
            if (tabItems.get(i).getSelected()) {
                view.getTextView().setTextColor(Color.parseColor("white"));
            }
            view.getTextView().setText(tabItems.get(i).getTitle());
            LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(subWidth, LayoutParams.WRAP_CONTENT);
            int px = DeviceDensity.dip2px(context,5);
            view.setPadding(px,px,px,px);
//            layoutParams.setMargins(px,px,px,px);
            view.setLayoutParams(layoutParams);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (ItemView view1:itemViews) {
                        view1.setSelected(false);
                    }
                    v.setSelected(true);
                    refresh();
                }
            });
            addView(view);
            itemViews.add(view);

        }

    }

    public void refresh() {
        if (this.itemViews != null&&this.tabItems != null) {
            int len = itemViews.size();
            for (int i = 0; i < len ; i++) {
                itemViews.get(i).getTextView().setTextColor(Color.parseColor("black"));
                if (itemViews.get(i).isSelected()) {
                    itemViews.get(i).getTextView().setTextColor(Color.parseColor("white"));
                    if (listener != null) {
                        listener.onItemSelected(i);
                    }
                }
            }
        }
    }

    public interface onItemSelectListener{
        void onItemSelected(int index);
    }

    public ItemView getItemView(int pos) {
        if (this.itemViews == null) {
            return null;
        }
        if (pos < this.itemViews.size()) {
            return  itemViews.get(pos);
        }
        return  null;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         width = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        if (this.tabItems != null) {
            initView(this.tabItems);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}
