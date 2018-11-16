package grupof.opendata.unex.es.hackaton_project;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TableLayout;

import grupof.opendata.unex.es.hackaton_project.adapter.TabsAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private AdapterTabs mPagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTabLayout();

    }

    private void setTabLayout() {

        // Set layout
        mTabLayout = findViewById(R.id.tab_layout);

        // Initialize viewerpager
        viewPager = findViewById(R.id.pager);
        mPagerAdapter = new TabsAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());


    }

}
