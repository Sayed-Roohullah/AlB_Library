package com.solutiontech.alb_library.activity;

import static com.solutiontech.alb_library.helper.DBPick.TABLE_NAME;
import static com.solutiontech.alb_library.helper.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.solutiontech.alb_library.R;
import com.solutiontech.alb_library.helper.DBPick;
import com.solutiontech.alb_library.helper.DBmain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pickby extends AppCompatActivity {
    DBPick dBpick;
    SQLiteDatabase sqLiteDatabase;
    EditText Ename,Efname,Econtact,Eadress,Ebookname;
    int id=0;
    Button submit,display,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickby);

        dBpick = new DBPick(this);

        inits();
        editData();
        insertData();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                cv.put("person",Ename.getText().toString());
                cv.put("father",Efname.getText().toString());
                cv.put("phone",Econtact.getText().toString());
                cv.put("adress",Eadress.getText().toString());
                cv.put("bookname",Ebookname.getText().toString());
                cv.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(new Date()));
                sqLiteDatabase=dBpick.getWritableDatabase();
                sqLiteDatabase=dBpick.getWritableDatabase();
                long recedit=sqLiteDatabase.update(TABLENAME,cv,"id="+id,null);
                if (recedit!=-1){
                    Toast.makeText(Pickby.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    //clear data after submit
                    Ename.setText("");
                    Efname.setText("");
                    Econtact.setText("");
                    Eadress.setText("");
                    Ebookname.setText("");

                    edit.setVisibility(View.GONE);
                    submit.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void inits() {
            Ename = findViewById(R.id.editpname);
            Efname = findViewById(R.id.editfname);
            Econtact = findViewById(R.id.editphone);
            Eadress = findViewById(R.id.editadr);
            Ebookname = findViewById(R.id.editbname);
            edit = findViewById(R.id.editdata);
            submit = findViewById(R.id.submitdata);
            display = findViewById(R.id.view);

    }

    public void onview(View view) {
        Intent i = new Intent(Pickby.this,PickedDisplayed.class);
        startActivity(i);

    }
    private void editData() {
        if (getIntent().getBundleExtra("userdata")!=null){
            Bundle bundle=getIntent().getBundleExtra("userdata");
            id=bundle.getInt("pid");

            Ename.setText(bundle.getString("person"));

            Efname.setText(bundle.getString("father"));

            Econtact.setText(bundle.getString("phone"));
            Eadress.setText(bundle.getString("adress"));
            Ebookname.setText(bundle.getString("pickbook"));
             //visible edit button and hide submit button
            submit.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
        }
    }
    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                cv.put("person",Ename.getText().toString());
                cv.put("father",Efname.getText().toString());
                cv.put("phone",Econtact.getText().toString());
                cv.put("adress",Eadress.getText().toString());
                cv.put("bookname",Ebookname.getText().toString());
                cv.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(new Date()));
                sqLiteDatabase=dBpick.getWritableDatabase();
                Long recinsert=sqLiteDatabase.insert(TABLE_NAME,null,cv);
                if (recinsert!=null){
                    Toast.makeText(Pickby.this, "inserted successfully", Toast.LENGTH_SHORT).show();
                    //clear when click on submit button
                    Ename.setText("");
                    Efname.setText("");
                    Econtact.setText("");
                    Eadress.setText("");
                    Ebookname.setText("");
                }
            }
        });

    }
}
