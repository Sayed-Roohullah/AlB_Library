package com.solutiontech.alb_library.activity;

import static com.solutiontech.alb_library.helper.DBPick.TABLE_NAME;
import static com.solutiontech.alb_library.helper.DBmain.TABLENAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.solutiontech.alb_library.R;
import com.solutiontech.alb_library.adapter.MyAdapter;
import com.solutiontech.alb_library.adapter.Pickadapter;
import com.solutiontech.alb_library.helper.DBPick;
import com.solutiontech.alb_library.helper.DBmain;
import com.solutiontech.alb_library.models.Model;
import com.solutiontech.alb_library.models.PickModel;

import java.util.ArrayList;
import java.util.List;

public class PickedDisplayed extends AppCompatActivity {

    DBPick dbpick;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    Pickadapter myAdapter;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_displayed);

         dbpick=new DBPick(this);
         recyclerView = findViewById(R.id.pickrecycle);
         displayData();
         recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

     }

    private void displayData() {
         sqLiteDatabase=dbpick.getReadableDatabase();
         Cursor cursor=sqLiteDatabase.rawQuery("select *from "+TABLE_NAME+"",null);
         ArrayList<PickModel> models=new ArrayList<>();
         while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String person=cursor.getString(1);
            String fhater = cursor.getString(2);
            String phone = cursor.getString(3);
            String adress = cursor.getString(4);
            String bookname = cursor.getString(5);
            String date = cursor.getString(6);
            models.add(new PickModel(id,person,fhater,phone,adress,bookname,date));
        }
        cursor.close();
        myAdapter=new Pickadapter(this,R.layout.picked_layout,models,sqLiteDatabase);
        recyclerView.setAdapter(myAdapter);
}
}