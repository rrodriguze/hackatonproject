package grupof.opendata.unex.es.hackaton_project;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import grupof.opendata.unex.es.hackaton_project.adapter.TabsAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabsAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    JSONObject obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setToolbar();

        setTabLayout();

        //JSON PARSER
        initFarmaciaJSON();
    }

    public void initFarmaciaJSON(){
        try {
            obj = new JSONObject(readJSONFromAsset());
            getDataFarmacia(obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setTabLayout() {

        // Set layout
        mTabLayout = findViewById(R.id.tab_layout);

        // Initialize viewerpager
        viewPager = findViewById(R.id.pager);
        mPagerAdapter = new TabsAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        // Add the onTabSelectedListener to the TabLayout
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    /**
     * Método que se encarga de obtener los datos de la lista de farmacias proporcionada por el JSON
     * @param main_farmacia
     */
    public void getDataFarmacia(JSONObject main_farmacia){
        try {
            JSONObject results = main_farmacia.getJSONObject("results");
            JSONArray farmacias = results.getJSONArray("bindings");
            String message = "";
            for (int i = 0; i <farmacias.length() ; i++) {
                JSONObject rdfs_label = (JSONObject) farmacias.getJSONObject(i).get("rdfs_label");
                String nombreFarmacia = String.valueOf(rdfs_label.get("value"));
                message += nombreFarmacia +"\n";
            }
            System.out.println(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("Farmacia.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }




}
