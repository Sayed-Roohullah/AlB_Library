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

public class Addbook extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    ImageView avatar;
    EditText name ,author, shell;
    Button submit,display,edit;
    int id=0;

    Bitmap ProfilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        dBmain=new DBmain(this);
        findid();
        insertData();
        imagePick();
        editData();
    }
    private void editData() {
        if (getIntent().getBundleExtra("userdata")!=null){
            Bundle bundle=getIntent().getBundleExtra("userdata");
            id=bundle.getInt("id");
            //for image
            byte[]bytes=bundle.getByteArray("avatar");
            Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            avatar.setImageBitmap(bitmap);
            //for set name
            name.setText(bundle.getString("name"));

            author.setText(bundle.getString("author"));

            shell.setText(bundle.getString("shell"));
            //visible edit button and hide submit button
            submit.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);
        }
    }

    private void imagePick() {
        avatar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });
    }


    private void pickFromGallery() {
        // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", 256);
        intent.putExtra("outputY", 256);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 1);    }



    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                cv.put("avatar",ImageViewToByte(avatar));
                cv.put("name",name.getText().toString());
                cv.put("author",author.getText().toString());
                cv.put("shell",shell.getText().toString());
                cv.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(new Date()));
                sqLiteDatabase=dBmain.getWritableDatabase();
                Long recinsert=sqLiteDatabase.insert(TABLENAME,null,cv);
                if (recinsert!=null){
                    Toast.makeText(Addbook.this, "inserted successfully", Toast.LENGTH_SHORT).show();
                    //clear when click on submit button
                    avatar.setImageResource(R.mipmap.ic_launcher);
                    name.setText("");
                    author.setText("");
                    shell.setText("");
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Addbook.this, DisplayData.class));
            }
        });
        //for storing new data or update data
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv=new ContentValues();
                cv.put("avatar",ImageViewToByte(avatar));
                cv.put("name",name.getText().toString());
                cv.put("author",author.getText().toString());
                cv.put("shell",shell.getText().toString());
                cv.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(new Date()));

                sqLiteDatabase=dBmain.getWritableDatabase();
                sqLiteDatabase=dBmain.getWritableDatabase();
                long recedit=sqLiteDatabase.update(TABLENAME,cv,"id="+id,null);
                if (recedit!=-1){
                    Toast.makeText(Addbook.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    //clear data after submit
                    avatar.setImageResource(R.mipmap.ic_launcher);
                    name.setText("");
                    author.setText("");
                    shell.setText("");
                    //edit hide and submit visible
                    edit.setVisibility(View.GONE);
                    submit.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private byte[] ImageViewToByte(ImageView avatar) {
        Bitmap bitmap=((BitmapDrawable)avatar.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream);
        byte[]bytes=stream.toByteArray();
        return bytes;
    }

    private void findid() {
        avatar=(ImageView)findViewById(R.id.avatar);
        name=(EditText)findViewById(R.id.edit_name);
        author=(EditText)findViewById(R.id.edit_author);
        shell=(EditText)findViewById(R.id.edit_shell);
        submit=(Button)findViewById(R.id.btn_submit);
        display=(Button)findViewById(R.id.btn_display);
        edit=(Button)findViewById(R.id.btn_edit);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1 ) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                ProfilePic = extras.getParcelable("data");
                avatar.setImageBitmap(ProfilePic);
            }
        }
    }
}