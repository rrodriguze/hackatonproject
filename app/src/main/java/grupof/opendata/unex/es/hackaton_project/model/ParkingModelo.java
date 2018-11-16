package grupof.opendata.unex.es.hackaton_project.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParkingModelo {
    private int id;
    private double geo_long;
    private double geo_lat;
    private String name;
    JSONObject parkingObj;
    List<ParkingModelo> listaParkings = new ArrayList<>();


    public ParkingModelo() {

    }

    public ParkingModelo(int pid, double p_geo_long, double p_geo_lat, String pname) {
        this.id = pid;
        this.geo_long = p_geo_long;
        this.geo_lat = p_geo_lat;
        this.name = pname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGeo_long() {
        return geo_long;
    }

    public void setGeo_long(double geo_long) {
        this.geo_long = geo_long;
    }

    public double getGeo_lat() {
        return geo_lat;
    }

    public void setGeo_lat(double geo_lat) {
        this.geo_lat = geo_lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "parking name: " + name + "\n" +
                "geo_long: " + geo_long + "\n" +
                "geo_lat: " + geo_lat + "\n";

    }

    public void initParkingJSON(Context context) {
        String parkingJSON = "PlazaMovilidadReducida.json";

        try {
            parkingObj = new JSONObject(readJSONFromAsset(parkingJSON, context));
            getDataParking(parkingObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String readJSONFromAsset(String jsonFile, Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFile);
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

    /**
     * Método que se encarga de obtener los datos de la lista de parking de
     * minusvalidos proporcionadas por el JSON
     *
     * @param main_parking
     */
    public void getDataParking(JSONObject main_parking) {
        JSONObject results = null;
        try {
            results = main_parking.getJSONObject("results");
            JSONArray aparcamientos = results.getJSONArray("bindings");
            for (int i = 0; i < aparcamientos.length(); i++) {

                int id = i;
                double geo_long = Double.parseDouble(String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("geo_long").get("value")));
                double geo_lat = Double.parseDouble(String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("geo_lat").get("value")));
                String nombreParking = String.valueOf(aparcamientos.getJSONObject(i).getJSONObject("rdfs_label").get("value"));
                listaParkings.add(new ParkingModelo(id, geo_long, geo_lat, nombreParking));
                System.out.println(listaParkings.get(i).toString());

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public List<ParkingModelo> getAll(Context context) {
        initParkingJSON(context);
        return this.listaParkings;
    }
}
