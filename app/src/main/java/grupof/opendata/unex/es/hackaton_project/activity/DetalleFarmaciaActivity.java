package grupof.opendata.unex.es.hackaton_project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import grupof.opendata.unex.es.hackaton_project.R;
import grupof.opendata.unex.es.hackaton_project.model.FarmaciaModelo;

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
        mMap.addMarker(new MarkerOptions().position(far).title("Farmacia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(far));
        mMap.setMinZoomPreference(16);
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


}
