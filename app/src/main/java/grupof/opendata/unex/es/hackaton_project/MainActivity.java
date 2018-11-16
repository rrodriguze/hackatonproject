package grupof.opendata.unex.es.hackaton_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    JSONObject obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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
