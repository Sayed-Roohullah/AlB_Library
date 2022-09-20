package com.solutiontech.alb_library.activity;

import static com.solutiontech.alb_library.helper.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.solutiontech.alb_library.helper.DBmain;
import com.solutiontech.alb_library.models.Model;
import com.solutiontech.alb_library.adapter.MyAdapter;
import com.solutiontech.alb_library.R;

import java.util.ArrayList;

public class DisplayData extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dBmain=new DBmain(this);
        findId();
        displayData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    private void displayData() {
        sqLiteDatabase=dBmain.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from "+TABLENAME+"",null);
        ArrayList<Model>models=new ArrayList<>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            byte[]avatar=cursor.getBlob(1);
            String name=cursor.getString(2);
            String author = cursor.getString(3);
            String shell = cursor.getString(4);
            String date = cursor.getString(5);
            models.add(new Model(id,avatar,name,author,shell,date));
        }
        cursor.close();
        myAdapter=new MyAdapter(this,R.layout.singel_data,models,sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
    }

    private void findId() {
        recyclerView=findViewById(R.id.rv);
    }
}