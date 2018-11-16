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
            JSONObject results = obj.getJSONObject("results");
            JSONArray bindings = results.getJSONArray("bindings");
            System.out.println("Longitud array: "+bindings.length());
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
