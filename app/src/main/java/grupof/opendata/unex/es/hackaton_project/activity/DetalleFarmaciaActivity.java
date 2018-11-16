package grupof.opendata.unex.es.hackaton_project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import grupof.opendata.unex.es.hackaton_project.R;
import grupof.opendata.unex.es.hackaton_project.model.Coordenada;
import grupof.opendata.unex.es.hackaton_project.model.FarmaciaModelo;
import grupof.opendata.unex.es.hackaton_project.model.ParkingModelo;

public class DetalleFarmaciaActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mMapFragment;
    private GoogleMap mMap;
    private FarmaciaModelo far = new FarmaciaModelo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_farmacia);

        setFarmacia();

        bindData();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng far = new LatLng(this.far.getGeo_lat(), this.far.getGeo_long());

        List<Coordenada> coord = getBestParkings();
        System.out.println("Best coords:" + coord.get(0).getX());
        LatLng parkingCoord = new LatLng(coord.get(0).getY(), coord.get(0).getX());
        mMap.addMarker(new MarkerOptions().position(parkingCoord).title("Parking Adaptado"));

        mMap.addMarker(new MarkerOptions().position(far).title("Farmacia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(far));
        mMap.setMinZoomPreference(12);

    }

    private void bindData() {

        // Mostrar la dirección de la farmacia
        TextView mFarmaciaAddress = findViewById(R.id.address_content);
        mFarmaciaAddress.setText(far.getAddress());

        // Mostrar el horario de la farmacia
        TextView mFarmaciaHorario = findViewById(R.id.schedule_content);
        mFarmaciaHorario.setText(far.getHorario());

        // Mostar el telefono de la farmacia
        TextView mFarmaciaTlfn = findViewById(R.id.telefono_content);
        mFarmaciaTlfn.setText(far.getTelephone());

        // Display the Google Maps in a MapFragment
        mMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mMapFragment.getMapAsync(this);

    }

    private void setFarmacia() {
        Intent i = getIntent();
        far = (FarmaciaModelo) i.getSerializableExtra("farObject");
    }

    public List<Coordenada> getBestParkings() {
        List<ParkingModelo> parkings = new ParkingModelo().getAll(getApplicationContext());
        List<Coordenada> bestParkings = new ArrayList<>();
        double x1, y1, x2, y2, total;
        double totalMin = 12123213;
        for (int i = 0; i < parkings.size(); i++) {
            System.out.println("longitud parkings" + parkings.size());
            x1 = far.getGeo_long();
            x2 = parkings.get(i).getGeo_long();
            y1 = far.getGeo_lat();
            y2 = parkings.get(i).getGeo_lat();
            total = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
            Coordenada cord = new Coordenada(x2, y2);
            if (total < totalMin) {
                totalMin = total;
                bestParkings.add(cord);
            }
        }
        return bestParkings;
    }


}
