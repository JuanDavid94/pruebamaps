package com.example.juand.pruebamaps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String direccion1,direccion2,resultado;
    List<Address> adress1,adress2;
    Address locdir1,locdir2;
    float result[] = new float[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        int status= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if(status== ConnectionResult.SUCCESS){

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else{
            Dialog dialog=GooglePlayServicesUtil.getErrorDialog(status,(Activity)getApplicationContext(),10);
            dialog.show();
        }

        Intent intent= getIntent();
        Bundle extras=intent.getExtras();
        direccion1=extras.getString("direccion1");
        direccion2=extras.getString("direccion2");

        if (direccion1 != null || !direccion1.equals("")){
            Geocoder geocoder1= new Geocoder(this);
            try {

                adress1=geocoder1.getFromLocationName(direccion1,1);

            } catch (IOException e) {
                e.printStackTrace();
            }

            locdir1=adress1.get(0);
        }

        if (direccion2 != null || !direccion2.equals("")){
            Geocoder geocoder2= new Geocoder(this);
            try {
                adress2=geocoder2.getFromLocationName(direccion2,1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            locdir2=adress2.get(0);

            Location localizacion1 = new Location("");
            localizacion1.setLatitude(locdir1.getLatitude());
            localizacion1.setLongitude(locdir2.getLongitude());

            Location localizacion2 = new Location("");
            localizacion2.setLatitude(locdir2.getLatitude());
            localizacion2.setLongitude(locdir2.getLongitude());

            float distancia= localizacion1.distanceTo(localizacion2);

            //Location.distanceBetween(locdir1.getLatitude(),locdir2.getLatitude(),locdir1.getLongitude(),locdir2.getLongitude(),result);
            //No utilice esta funcion ya que me retornaba un dato que no concordaban las unidades de metros.

            resultado= (distancia/1000)+"km";
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        Intent intent1=new Intent(MapsActivity.this,MainActivity.class);
        intent1.putExtra("resultado",resultado);
        startActivity(intent1);


        /*mMap = googleMap;
        //// Add a marker in Sydney and move the camera
        LatLng localizacion = new LatLng(locdir2.getLatitude(), locdir2.getLongitude());
        mMap.addMarker(new MarkerOptions().position(localizacion).title("Destino"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacion));*/

    }
}
