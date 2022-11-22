package com.rutadelcielacoAPP.evaluacion1_android;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rutadelcielacoAPP.evaluacion1_android.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocalizacion();
    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    public void regresarInicio(View view){
        Intent regresarInicio = new Intent(this,SegundoActvity.class);
        startActivity(regresarInicio);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng santiago = new LatLng(-33.44855, -70.71529);
        LatLng alhasgluten = new LatLng(-33.45061, -70.59669);
        LatLng quimeySushi = new LatLng(-33.42912, -70.61789);
        LatLng fajitasVeganas = new LatLng(-33.44900, -70.66318);
        LatLng barItalia = new LatLng(-33.44644, -70.62468);
        LatLng elPueblo = new LatLng(-33.45061, -70.59669);
        LatLng exeptoGluten = new LatLng(-33.46295, -70.59704);
        LatLng emporioSingluten = new LatLng(-33.44409, -70.62643);
        mMap.addMarker(new MarkerOptions().position(santiago).title("Santiago de chile"));
        mMap.addMarker(new MarkerOptions().position(alhasgluten).title("Alhas gluten free - Av condell 1145"));
        mMap.addMarker(new MarkerOptions().position(quimeySushi).title("Quimey sushi - Dr Manuel Barros 160 LOCAL 104"));
        mMap.addMarker(new MarkerOptions().position(fajitasVeganas).title("Fajitas Veganas - Sazie 1968"));
        mMap.addMarker(new MarkerOptions().position(barItalia).title("Bar Italia - Av italia 1423"));
        mMap.addMarker(new MarkerOptions().position(elPueblo).title("El Pueblo - Av portugal 1566"));
        mMap.addMarker(new MarkerOptions().position(exeptoGluten).title("Exepto Gluten - Av Grecia 3234"));
        mMap.addMarker(new MarkerOptions().position(emporioSingluten).title("Emporio sin gluten - Av condell 1150"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(santiago));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(miUbicacion)
                        .zoom(14)
                        .bearing(90)
                        .tilt(45)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);


    }

}
