package com.solutiontech.alb_library.adapter;

 import static com.solutiontech.alb_library.helper.DBPick.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solutiontech.alb_library.R;
import com.solutiontech.alb_library.activity.MainActivity;
import com.solutiontech.alb_library.activity.Pickby;
import com.solutiontech.alb_library.helper.DBPick;
import com.solutiontech.alb_library.models.PickModel;

import java.util.ArrayList;

public class Pickadapter extends RecyclerView.Adapter<Pickadapter.ViewHolder> {
        Context context;
        int singledata;
        ArrayList<PickModel> modelArrayList;
        SQLiteDatabase sqLiteDatabase;
//generate constructor

public Pickadapter(Context context, int singledata, ArrayList<PickModel> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
        }

@NonNull
@Override
public Pickadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.picked_layout,null);
        return new ViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull Pickadapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
final PickModel model=modelArrayList.get(position);


        holder.person.setText(model.getPerson());
        holder.adress.setText(model.getAdress());
        holder.father.setText(model.getFather());
        holder.contact.setText(model.getPhone());
        holder.bookname.setText(model.getBookname());
        holder.date.setText(model.getDates());

        //flow menu
        holder.flowmenu.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        PopupMenu popupMenu=new PopupMenu(context,holder.flowmenu);
        popupMenu.inflate(R.menu.flow_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
@Override
public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
        case R.id.edit_menu:
        ///////
        //edit operation
        Bundle bundle=new Bundle();
        bundle.putInt("pid",model.getId());
        bundle.putString("pickbook",model.getBookname());
        bundle.putString("person",model.getPerson());
        bundle.putString("father",model.getFather());
        bundle.putString("phone",model.getPhone());
        bundle.putString("adress",model.getAdress());
        Intent intent=new Intent(context, Pickby.class);
        intent.putExtra("userdata",bundle);
        context.startActivity(intent);
        break;
        case R.id.delete_menu:
        ///delete operation
        DBPick dbpick=new DBPick(context);
        sqLiteDatabase=dbpick.getReadableDatabase();
        long recdelete=sqLiteDatabase.delete(TABLE_NAME,"id="+model.getId(),null);
        if (recdelete!=-1){
        Toast.makeText(context, "data deleted", Toast.LENGTH_SHORT).show();
        //remove position after deleted
        modelArrayList.remove(position);
        //update data
        notifyDataSetChanged();
        }
        break;
        default:
           return false;
        }
           return false;
        }
        });
        //display menu
        popupMenu.show();
        }
        });
        }

@Override
public int getItemCount() {
        return modelArrayList.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView bookname, person,father,contact,adress, date;
    ImageButton flowmenu;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        bookname=(TextView)itemView.findViewById(R.id.bookpick);
        person=(TextView)itemView.findViewById(R.id.personname);
        father=(TextView)itemView.findViewById(R.id.fathername);
        contact=(TextView)itemView.findViewById(R.id.contact);
        adress=(TextView)itemView.findViewById(R.id.adress);
        date=(TextView)itemView.findViewById(R.id.date);

        flowmenu=(ImageButton)itemView.findViewById(R.id.flowmenus);
    }

    }
}
