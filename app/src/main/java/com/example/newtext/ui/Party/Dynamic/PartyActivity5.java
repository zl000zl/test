package com.example.newtext.ui.Party.Dynamic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.newtext.R;

public class PartyActivity5 extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;
    private Button takePhoto;
    private ImageView picture;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party5);
        initView();
    }

    private void initView() {
        takePhoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (ContextCompat.checkSelfPermission(PartyActivity5.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PartyActivity5.this, new String[]{Manifest.permission.CAMERA}, 1);
                        //请求启用相机权限的方法
                    } else {
                        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 999);
                        //请求相机

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(PartyActivity5.this, "当前设备不支持拍照！！！", Toast.LENGTH_SHORT).show();
                    //请求失败的错误方法
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //回调方法
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                picture.setImageBitmap((Bitmap) data.getExtras().get("data"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 999);

            } else Toast.makeText(PartyActivity5.this, "获取权限失败！！！", Toast.LENGTH_SHORT).show();

        }
    }
}