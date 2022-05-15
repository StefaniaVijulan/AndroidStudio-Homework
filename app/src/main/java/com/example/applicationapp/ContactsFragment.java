package com.example.applicationapp;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class ContactsFragment extends Fragment {

    public static final String EXTRA_INFO = "default";
    private Button btnCapture;
    private ImageView imgCapture;
    private static final int Image_Capture_Code = 1;

    View secondFr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        secondFr = inflater.inflate(R.layout.fragment_contacts, container, false);
        btnCapture = secondFr.findViewById(R.id.bt_open);
        imgCapture = secondFr.findViewById(R.id.image_view);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 100);
        }
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cInt,Image_Capture_Code);
            }
        });
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);



        return secondFr;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                Bitmap bp = (Bitmap) data.getExtras().get("data");
                imgCapture.setImageBitmap(bp);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
    }
}