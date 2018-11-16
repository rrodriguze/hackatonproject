package grupof.opendata.unex.es.hackaton_project.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import grupof.opendata.unex.es.hackaton_project.R;
import grupof.opendata.unex.es.hackaton_project.adapter.TabsAdapter;
import grupof.opendata.unex.es.hackaton_project.model.FarmaciaModelo;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabsAdapter mPagerAdapter;
    private TabLayout mTabLayout;
    JSONObject parkingObj;
    /**
     * Lista de farmacias parseadas del JSON
     */
    List<FarmaciaModelo> listaFarmacias = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setTabLayout();

    }

    public void initParkingJSON(){
        String parkingJSON = "PlazaMovilidadReducida.json";

        try {
            parkingObj = new JSONObject(readJSONFromAsset(parkingJSON));
            getDataParking(parkingObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Método que se encarga de obtener los datos de la lista de parking de
     * minusvalidos proporcionadas por el JSON
     * @param main_parking
     */
    public void getDataParking(JSONObject main_parking) {
        JSONObject results = null;
        try {
            results = main_parking.getJSONObject("results");
            JSONArray aparcamientos = results.getJSONArray("bindings");
            for (int i = 0; i <aparcamientos.length() ; i++) {
               /*
                int id = 0;
                double geo_long = Double.parseDouble(String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("geo_long").get("value")));
                double geo_lat = Double.parseDouble(String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("geo_lat").get("value")));
                String nombreFarmacia = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("rdfs_label").get("value"));
                String phone = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("schema_telephone").get("value"));
                String address = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("schema_address_streetAddress").get("value"));
                String horario_manana_open = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_de_manana_Opens").get("value"));
                String horario_manana_close = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_de_manana_Closes").get("value"));
                String horario_tarde_inv_open = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_de_tarde_invierno_Opens").get("value"));
                String horario_tarde_inv_close = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_de_tarde_invierno_Closes").get("value"));
                String horario_tarde_verano_open = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_de_tarde_verano_Opens").get("value"));
                String horario_tarde_verano_close = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_de_tarde_verano_Closes").get("value"));
                String horario_sabado_open = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_Sabado_Opens").get("value"));
                String horario_sabado_close = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("Horario_Sabado_Closes").get("value"));
                listaFarmacias.add(new FarmaciaModelo(id,geo_long,geo_lat,nombreFarmacia,"324234234",address,horario_manana_open,horario_manana_close,horario_tarde_inv_open,horario_tarde_inv_close,horario_tarde_verano_open,horario_tarde_verano_close,horario_sabado_open,horario_sabado_close));
                System.out.println(listaFarmacias.get(i).toString());
            */
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    /**
     * Método que se encarga de obtener los datos de la lista de farmacias
     * proporcionada por el JSON
     * @param main_farmacia
     */
    public void getDataFarmacia(JSONObject main_farmacia){
        try {
            JSONObject results = main_farmacia.getJSONObject("results");
            JSONArray farmacias = results.getJSONArray("bindings");
            String message = "";
            for (int i = 0; i <farmacias.length() ; i++) {
                int id = i;
                double geo_long = Double.parseDouble(String.valueOf(farmacias.getJSONObject(i).getJSONObject("geo_long").get("value")));
                double geo_lat = Double.parseDouble(String.valueOf(farmacias.getJSONObject(i).getJSONObject("geo_lat").get("value")));
                String nombreFarmacia = String.valueOf(farmacias.getJSONObject(i).getJSONObject("rdfs_label").get("value"));
                String phone = String.valueOf(farmacias.getJSONObject(i).getJSONObject("schema_telephone").get("value"));
                String address = String.valueOf(farmacias.getJSONObject(i).getJSONObject("schema_address_streetAddress").get("value"));
                String horario_manana_open;
                String horario_manana_close;
                String horario_tarde_inv_open;
                String horario_tarde_inv_close;
                String horario_tarde_verano_open;
                String horario_tarde_verano_close;
                String horario_sabado_open;
                String horario_sabado_close;
                if(farmacias.getJSONObject(i).has("Horario_de_manana_Opens")) {
                     horario_manana_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_manana_Opens").get("value"));
                     horario_manana_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_manana_Closes").get("value"));
                     horario_tarde_inv_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_invierno_Opens").get("value"));
                     horario_tarde_inv_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_invierno_Closes").get("value"));
                     horario_tarde_verano_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_verano_Opens").get("value"));
                     horario_tarde_verano_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_verano_Closes").get("value"));
                     horario_sabado_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Sabado_Opens").get("value"));
                     horario_sabado_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Sabado_Closes").get("value"));
                }else if (farmacias.getJSONObject(i).has("Horario_Extendido_Opens")){
                     horario_manana_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                     horario_manana_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                     horario_tarde_inv_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                     horario_tarde_inv_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                     horario_tarde_verano_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                     horario_tarde_verano_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                     horario_sabado_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                     horario_sabado_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                }else {
                     horario_manana_open = null;
                     horario_manana_close = null;
                     horario_tarde_inv_open = null;
                     horario_tarde_inv_close = null;
                     horario_tarde_verano_open = null;
                     horario_tarde_verano_close = null;
                     horario_sabado_open = null;
                     horario_sabado_close = null;
                }

                listaFarmacias.add(new FarmaciaModelo(id,geo_long,geo_lat,nombreFarmacia,phone,address,horario_manana_open,horario_manana_close,horario_tarde_inv_open,horario_tarde_inv_close,horario_tarde_verano_open,horario_tarde_verano_close,horario_sabado_open,horario_sabado_close));
                System.out.println(listaFarmacias.get(i).toString());

            }
            System.out.println(message);
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
    public String readJSONFromAsset(String jsonFile) {
        String json = null;
        try {
            InputStream is = getAssets().open(jsonFile);
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
