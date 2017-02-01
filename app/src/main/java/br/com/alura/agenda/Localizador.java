package br.com.alura.agenda;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Guilherme on 31/01/2017.
 */

public class Localizador implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    private final GoogleApiClient client;
    private final MapaFragment mapaFragment;

    public Localizador(Context context, MapaFragment mapa){
        //solicitando a utilização da localização do aparelho
        client = new GoogleApiClient.Builder(context).addApi(LocationServices.API).addConnectionCallbacks(this).build();

        client.connect();

        this.mapaFragment = mapa;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest request = new LocationRequest();
        request.setSmallestDisplacement(50); //raio minimo que o celular precisa estar fora para buscar novas informações
        request.setInterval(1000); //intervalo das atulizações caso o celular esteja em busca de novas informações constantemente
        //request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); MAIOR PRECISÃO POSSIVEL
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng coordenada = new LatLng(location.getLatitude(), location.getLongitude());
        mapaFragment.centralizaEm(coordenada);
    }
}
















