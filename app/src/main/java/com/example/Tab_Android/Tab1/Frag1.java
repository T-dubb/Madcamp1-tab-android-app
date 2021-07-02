package com.example.Tab_Android.Tab1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.Tab_Android.R;

//외부에서 new Frag1 호출 시
public class Frag1 extends Fragment {
    private static final String TAG = "Frag1";

    Fragment contactFragment;
    EditText inputName;
    EditText inputAddress;
    Context context;

    public static NoteDatabase noteDatabase = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.tab1_activity_frag1,container,false);

        contactFragment = new ContactFragment();

        getChildFragmentManager().beginTransaction().replace(R.id.container,contactFragment).commit();
        ImageButton saveButton = v.findViewById(R.id.saveButton);
        inputName = v.findViewById(R.id.inputName);
        inputAddress = v.findViewById(R.id.inputAddress);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = inputName.getText().toString();
                String address = inputAddress.getText().toString();

                String n_a= String.format("이름: %.4s     |      연락처: %.13s",name,address);
                String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (ADDRESS) values (" +
                        "'"+n_a+"')";

                NoteDatabase database = NoteDatabase.getInstance(context);
                database.execSQL(sqlSave);

                inputName.setText("");
                inputAddress.setText("");

                Toast.makeText(view.getContext(),"Add",Toast.LENGTH_SHORT).show();

            }
        });
        openDatabase();
        return v;
    }

    public void openDatabase() {
        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = NoteDatabase.getInstance(getActivity());
        boolean isOpen = noteDatabase.open();
        if (isOpen) {
            Log.d(TAG, "Note database is open.");
        } else {
            Log.d(TAG, "Note database is not open.");
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }
}