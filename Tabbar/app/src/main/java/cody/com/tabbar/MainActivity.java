package cody.com.tabbar;

import android.content.Intent;
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
        TabItem tabItem = new TabItem("toolbar", null, null, null, null);
        tabItem.setSelected(true);
        tabItems.add(tabItem);
        tabItems.add(new TabItem("first1", null, null, null, null));
        tabItems.add(new TabItem("first2", null, null, null, null));
        tabItems.add(new TabItem("first3", null, null, null, null));
        tabItems.add(new TabItem("first4", null, null, null, null));
        tabItems.add(new TabItem("first5", null, null, null, null));
        tabbarView.setTabItems(tabItems);
        tabbarView.setOnItemSelectListener(new TabbarView.onItemSelectListener() {
            @Override
            public void onItemSelected(int index) {
                switch (index) {
                    case 0:
                        Intent intent = new Intent(MainActivity.this, ToolBarActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;

                }
            }
        });
    }
}
