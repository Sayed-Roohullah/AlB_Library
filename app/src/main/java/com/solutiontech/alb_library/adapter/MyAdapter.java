package com.solutiontech.alb_library.adapter;

import static com.solutiontech.alb_library.helper.DBmain.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.solutiontech.alb_library.activity.Addbook;
import com.solutiontech.alb_library.activity.MainActivity;
import com.solutiontech.alb_library.R;
import com.solutiontech.alb_library.helper.DBmain;
import com.solutiontech.alb_library.models.Model;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    int singledata;
    ArrayList<Model> modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    //generate constructor

    public MyAdapter(Context context, int singledata, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.singel_data,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Model model=modelArrayList.get(position);
        byte[]image=model.getProavatar();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageavatar.setImageBitmap(bitmap);
        holder.bookname.setText(model.getBookname());
        holder.author.setText(model.getAuthorname());
        holder.shell.setText(model.getShell());
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
                                bundle.putInt("id",model.getId());
                                bundle.putByteArray("avatar",model.getProavatar());
                                bundle.putString("name",model.getBookname());
                                bundle.putString("author",model.getAuthorname());
                                bundle.putString("shell",model.getShell());
                                Intent intent=new Intent(context, Addbook.class);
                                intent.putExtra("userdata",bundle);
                                context.startActivity(intent);
                                break;
                            case R.id.delete_menu:
                                ///delete operation
                                DBmain dBmain=new DBmain(context);
                                sqLiteDatabase=dBmain.getReadableDatabase();
                                long recdelete=sqLiteDatabase.delete(TABLENAME,"id="+model.getId(),null);
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
        ImageView imageavatar;
        TextView bookname, author,shell, date;
        ImageButton flowmenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageavatar=(ImageView)itemView.findViewById(R.id.viewavatar);
            bookname=(TextView)itemView.findViewById(R.id.txt_name);
            author=(TextView)itemView.findViewById(R.id.bookauthor);
            shell=(TextView)itemView.findViewById(R.id.bookshell);
            date=(TextView)itemView.findViewById(R.id.addate);

            flowmenu=(ImageButton)itemView.findViewById(R.id.flowmenu);
        }
    }
}