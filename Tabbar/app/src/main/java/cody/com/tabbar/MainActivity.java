package cody.com.tabbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cody.com.tabbar.view.TabItem;
import cody.com.tabbar.view.TabbarView;

public class MainActivity extends AppCompatActivity {
    private TabbarView tabbarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabbarView = (TabbarView) findViewById(R.id.tabbar_view);
        List<TabItem> tabItems = new ArrayList<>();
        TabItem tabItem = new TabItem("first0", null,null,null,null);
        tabItem.setSelected(true);
        tabItems.add(tabItem);
        tabItems.add(new TabItem("first1", null,null,null,null));
        tabItems.add(new TabItem("first2", null,null,null,null));
        tabItems.add(new TabItem("first3", null,null,null,null));
        tabItems.add(new TabItem("first4", null,null,null,null));
        tabbarView.setTabItems(tabItems);
    }
}
