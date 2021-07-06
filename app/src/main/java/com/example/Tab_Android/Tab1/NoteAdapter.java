package com.example.Tab_Android.Tab1;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Tab_Android.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{

    ArrayList<Note> items = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.tab1_address_item, parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note item = items.get(position);
        holder.setItem(item);
        holder.setLayout();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout layoutAddress;
        CheckBox checkBox;
        ImageButton callbutton;
        ImageButton deleteButton;

        public ViewHolder(View itemView){
            super(itemView);
            layoutAddress = itemView.findViewById(R.id.layoutAddress);
            checkBox = itemView.findViewById(R.id.checkBox);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            callbutton = itemView.findViewById(R.id.callbutton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ADDRESS = (String) checkBox.getText();
                    deleteAddress(ADDRESS);
                    Toast.makeText(v.getContext(),"Delete.",Toast.LENGTH_SHORT).show();
                }

                Context context;

                private void deleteAddress(String ADDRESS){

                    String deleteSql = "delete from " + NoteDatabase.TABLE_NOTE + " where " + "  ADDRESS = '" + ADDRESS +"'";
                    NoteDatabase database = NoteDatabase.getInstance(context);
                    database.execSQL(deleteSql);
                }
            });
            callbutton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    String Address = (String) checkBox.getText();
                    int indexOf = Address.indexOf("연락처: ");
                    String Callars = Address.substring(indexOf+5,indexOf+16);
                    Toast.makeText(v.getContext(),Callars,Toast.LENGTH_SHORT).show();
                    Context c = v.getContext();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+Callars));
                    try{
                        c.startActivity(intent);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }
        public void setItem(Note item){
            checkBox.setText(item.getAddress());
        }
        public void setLayout(){
            layoutAddress.setVisibility(View.VISIBLE);
        }
    }
    public void setItems(ArrayList<Note> items){
        this.items = items;
    }
}
