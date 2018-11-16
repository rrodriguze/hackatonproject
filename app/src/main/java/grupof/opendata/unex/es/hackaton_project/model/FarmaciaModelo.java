package grupof.opendata.unex.es.hackaton_project.model;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaModelo implements Serializable {

    private int id;
    private double geo_long;
    private double geo_lat;
    private String name;
    private String telephone;
    private String address;
    private String horario_manana_open;
    private String horario_manana_close;
    private String horario_tarde_inv_open;
    private String horario_tarde_inv_close;
    private String horario_tarde_ver_open;
    private String horario_tarde_ver_close;
    private String horario_sabado_open;
    private String horario_sabado_close;

    JSONObject farmaciaObj;
    List<FarmaciaModelo> listaFarmacias = new ArrayList<>();

    public FarmaciaModelo() {

    }

    public FarmaciaModelo(int id, double geo_long, double geo_lat, String name, String telephone,
                          String address, String horario_manana_open, String horario_manana_close,
                          String horario_tarde_inv_open, String horario_tarde_inv_close,
                          String horario_tarde_ver_open, String horario_tarde_ver_close,
                          String horario_sabado_open, String horario_sabado_close) {
        this.id = id;
        this.geo_long = geo_long;
        this.geo_lat = geo_lat;
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.horario_manana_open = horario_manana_open;
        this.horario_manana_close = horario_manana_close;
        this.horario_tarde_inv_open = horario_tarde_inv_open;
        this.horario_tarde_inv_close = horario_tarde_inv_close;
        this.horario_tarde_ver_open = horario_tarde_ver_open;
        this.horario_tarde_ver_close = horario_tarde_ver_close;
        this.horario_sabado_open = horario_sabado_open;
        this.horario_sabado_close = horario_sabado_close;
    }


    public void initFarmaciaJSON(Context context) {
        String farmaciaJSON = "Farmacia.json";
        try {
            farmaciaObj = new JSONObject(readJSONFromAsset(farmaciaJSON, context));
            getDataFarmacia(farmaciaObj);

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
     * Método que se encarga de obtener los datos de la lista de farmacias
     * proporcionada por el JSON
     *
     * @param main_farmacia
     */
    public void getDataFarmacia(JSONObject main_farmacia) {
        try {
            JSONObject results = main_farmacia.getJSONObject("results");
            JSONArray farmacias = results.getJSONArray("bindings");
            String message = "";
            for (int i = 0; i < farmacias.length(); i++) {
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
                if (farmacias.getJSONObject(i).has("Horario_de_manana_Opens")) {
                    horario_manana_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_manana_Opens").get("value"));
                    horario_manana_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_manana_Closes").get("value"));
                    horario_tarde_inv_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_invierno_Opens").get("value"));
                    horario_tarde_inv_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_invierno_Closes").get("value"));
                    horario_tarde_verano_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_verano_Opens").get("value"));
                    horario_tarde_verano_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_de_tarde_verano_Closes").get("value"));
                    horario_sabado_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Sabado_Opens").get("value"));
                    horario_sabado_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Sabado_Closes").get("value"));
                } else if (farmacias.getJSONObject(i).has("Horario_Extendido_Opens")) {
                    horario_manana_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                    horario_manana_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                    horario_tarde_inv_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                    horario_tarde_inv_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                    horario_tarde_verano_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                    horario_tarde_verano_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                    horario_sabado_open = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Opens").get("value"));
                    horario_sabado_close = String.valueOf(farmacias.getJSONObject(i).getJSONObject("Horario_Extendido_Closes").get("value"));
                } else {
                    horario_manana_open = null;
                    horario_manana_close = null;
                    horario_tarde_inv_open = null;
                    horario_tarde_inv_close = null;
                    horario_tarde_verano_open = null;
                    horario_tarde_verano_close = null;
                    horario_sabado_open = null;
                    horario_sabado_close = null;
                }

                listaFarmacias.add(new FarmaciaModelo(id, geo_long, geo_lat, nombreFarmacia, phone, address, horario_manana_open, horario_manana_close, horario_tarde_inv_open, horario_tarde_inv_close, horario_tarde_verano_open, horario_tarde_verano_close, horario_sabado_open, horario_sabado_close));
                System.out.println(listaFarmacias.get(i).toString());

            }
            System.out.println(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    // Getters
    public int getId() {
        return id;
    }

    public double getGeo_long() {
        return geo_long;
    }

    public double getGeo_lat() {
        return geo_lat;
    }

    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public String getHorario_manana_open() {
        return horario_manana_open;
    }

    public String getHorario_manana_close() {
        return horario_manana_close;
    }

    public String getHorario_tarde_inv_open() {
        return horario_tarde_inv_open;
    }

    public String getHorario_tarde_inv_close() {
        return horario_tarde_inv_close;
    }

    public String getHorario_tarde_ver_open() {
        return horario_tarde_ver_open;
    }

    public String getHorario_tarde_ver_close() {
        return horario_tarde_ver_close;
    }

    public String getHorario_sabado_open() {
        return horario_sabado_open;
    }

    public String getHorario_sabado_close() {
        return horario_sabado_close;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setGeo_long(long geo_long) {
        this.geo_long = geo_long;
    }

    public void setGeo_lat(long geo_lat) {
        this.geo_lat = geo_lat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHorario_manana_open(String horario_manana_open) {
        this.horario_manana_open = horario_manana_open;
    }

    public void setHorario_manana_close(String horario_manana_close) {
        this.horario_manana_close = horario_manana_close;
    }

    public void setHorario_tarde_inv_open(String horario_tarde_inv_open) {
        this.horario_tarde_inv_open = horario_tarde_inv_open;
    }

    public void setHorario_tarde_inv_close(String horario_tarde_inv_close) {
        this.horario_tarde_inv_close = horario_tarde_inv_close;
    }

    public void setHorario_tarde_ver_open(String horario_tarde_ver_open) {
        this.horario_tarde_ver_open = horario_tarde_ver_open;
    }

    public void setHorario_tarde_ver_close(String horario_tarde_ver_close) {
        this.horario_tarde_ver_close = horario_tarde_ver_close;
    }

    public void setHorario_sabado_open(String horario_sabado_open) {
        this.horario_sabado_open = horario_sabado_open;
    }

    public void setHorario_sabado_close(String horario_sabado_close) {
        this.horario_sabado_close = horario_sabado_close;
    }

    public String getHorario() {
        return "Horario mañana: " + horario_manana_open + " - " + horario_manana_close + "\n" +
                "Horario tarde invierno: " + horario_tarde_inv_open + " - " + horario_tarde_inv_close + "\n" +
                "Horario tarde verano: " + horario_tarde_ver_open + " - " + horario_tarde_ver_close;
    }

    public String toString() {
        return "ID: "+this.id+"\n Far-nombre: " + this.name + ":\n" +
                "\tLong: " + this.geo_long + "\n" +
                "\tLat: " + this.geo_lat + "\n" +
                "\tTelefono: " + this.telephone + "\n" +
                "\tDireccion: " + this.address + "\n" +
                "\tHorario mañanas: " + this.horario_manana_open + " - " + this.horario_manana_close + "\n" +
                "\tHorario tardes invierno: " + this.horario_tarde_inv_open + " - " + this.horario_tarde_inv_close + "\n" +
                "\tHorario tardes verano: " + this.horario_tarde_ver_open + " - " + this.horario_tarde_ver_close + "\n"+
                "\tHorario sabados: " + this.horario_sabado_open + " - " + this.horario_sabado_close + "\n";
    }

    public List<FarmaciaModelo> getAll(Context context) {
        initFarmaciaJSON(context);
        return listaFarmacias;
    }
}
