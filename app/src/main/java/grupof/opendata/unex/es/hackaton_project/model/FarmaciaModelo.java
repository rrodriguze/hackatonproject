package grupof.opendata.unex.es.hackaton_project.model;


public class FarmaciaModelo {

    private int id;
    private int geo_long;
    private int geo_lat;
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

    public FarmaciaModelo() {
    }

    public FarmaciaModelo(int id, int geo_long, int geo_lat, String name, String telephone,
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

    // Getters

    public int getId() {
        return id;
    }

    public int getGeo_long() {
        return geo_long;
    }

    public int getGeo_lat() {
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

    public void setGeo_long(int geo_long) {
        this.geo_long = geo_long;
    }

    public void setGeo_lat(int geo_lat) {
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
}
