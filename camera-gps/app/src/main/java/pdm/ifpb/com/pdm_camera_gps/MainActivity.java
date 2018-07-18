package pdm.ifpb.com.pdm_camera_gps;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.util.Locale.*;

public class MainActivity extends AppCompatActivity {

    private ImageView imagem;
    private Button btFoto;
    private TextView localizacao;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        localizacao = findViewById(R.id.localizacao);
        imagem = findViewById(R.id.imagem);
        btFoto = findViewById(R.id.btFoto);

        localizacao();

        btFoto.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888);
            }
        });
    }


    private void localizacao() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission")
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        localizacao.setText("Latitude = "+latitude + " Longitude = "+ longitude);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imagem.setImageBitmap(photo);

    }

}
