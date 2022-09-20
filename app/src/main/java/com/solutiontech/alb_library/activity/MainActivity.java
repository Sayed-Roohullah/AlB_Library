package com.solutiontech.alb_library.activity;

import static com.solutiontech.alb_library.helper.DBmain.TABLENAME;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.solutiontech.alb_library.R;
import com.solutiontech.alb_library.helper.DBmain;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView avail, pick, add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            avail = findViewById(R.id.available);
            pick = findViewById(R.id.pickby);
            add = findViewById(R.id.addbook);

            avail.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, DisplayData.class);
                startActivity(i);

            });
            pick.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, Pickby.class);
                startActivity(i);
            });
            add.setOnClickListener(v -> {
                Intent i = new Intent(MainActivity.this, Addbook.class);
                startActivity(i);
            });

        }

}