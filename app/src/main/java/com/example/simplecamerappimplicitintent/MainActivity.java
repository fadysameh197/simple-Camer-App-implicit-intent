package com.example.simplecamerappimplicitintent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static int CAPTURE_REQUEST_CODE=1;
    ImageView imageView_photoView;
    ImageButton btn_capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView_photoView = findViewById(R.id.imageView_photo_view);
        btn_capture = findViewById(R.id.btn_capture);
        btn_capture.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null)
            startActivityForResult(intent,CAPTURE_REQUEST_CODE);
            else Toast.makeText(getBaseContext(), "There is no available app in your device"
                    , Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAPTURE_REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView_photoView.setImageBitmap(bitmap);
        }
        else{
            Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_LONG).show();
        }
    }
}