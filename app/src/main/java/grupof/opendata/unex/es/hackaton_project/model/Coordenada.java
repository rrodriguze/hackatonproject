package grupof.opendata.unex.es.hackaton_project.model;

public class Coordenada {

    private double x;
    private double y;

    public Coordenada(double px, double py) {
        this.x = px;
        this.y = py;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
