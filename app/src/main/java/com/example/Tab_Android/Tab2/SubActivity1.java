package com.example.Tab_Android.Tab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.media.Image;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Tab_Android.R;

public class SubActivity1 extends AppCompatActivity {
    Context context;
    AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_view_cat);
        /**
        Point p = getScreenSize(this);
        ImageView c1 = findViewById(R.id.imageView);
        ImageView c2 = findViewById(R.id.imageView2);
        ImageView c3 = findViewById(R.id.imageView3);
        ImageView c4 = findViewById(R.id.imageView4);
        ImageView c5 = findViewById(R.id.imageView5);

        zoomclick(c1,p);
        zoomclick(c2,p);
        zoomclick(c3,p);
        zoomclick(c4,p);
        zoomclick(c5,p);
         */
    }
    public void zoomclick(ImageView c, Point p){
        c.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /**
                c.getLayoutParams().height=p.y;
                c.getLayoutParams().width=p.x;
                c.requestLayout();*/
                popUpImg(c);
            }
        });
    }
    public Point getScreenSize(Activity acitivity){
        Display display = acitivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }
    public void popUpImg(ImageView v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("zoom");
        builder.setView(v);

        builder.setNegativeButton("exit", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                if(dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        dialog = builder.create();
        dialog.show();
    }
}
