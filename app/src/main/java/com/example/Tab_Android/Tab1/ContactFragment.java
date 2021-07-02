package com.example.Tab_Android.Tab1;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Tab_Android.R;

import java.util.ArrayList;

public class ContactFragment extends Fragment {

    RecyclerView recyclerView;
    NoteAdapter adapter;
    Context context;

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab1_fragment, container, false);

        initUI(rootView);

        loadNoteListData();

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNoteListData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        return rootView;
    }

    private void initUI(ViewGroup rootView){
        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
    }

    public int loadNoteListData(){
        String loadSql = "select _id, ADDRESS from " + NoteDatabase.TABLE_NOTE + " order by _id desc";

        int recordCount = -1;
        NoteDatabase database = NoteDatabase.getInstance(context);

        if(database != null){
            Cursor outCursor = database.rawQuery(loadSql);
            recordCount = outCursor.getCount();

            ArrayList<Note> items = new ArrayList<>();

            for(int i = 0; i < recordCount; i++){
                outCursor.moveToNext();

                int _id = outCursor.getInt(0);
                String address = outCursor.getString(1);
                items.add(new Note(_id, address));
            }
            outCursor.close();

            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }

        return recordCount;
    }
}
