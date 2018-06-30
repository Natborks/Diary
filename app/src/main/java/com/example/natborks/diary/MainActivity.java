package com.example.natborks.diary;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.natborks.diary.Database.AppDatabase;

import static android.widget.GridLayout.VERTICAL;


public class MainActivity extends AppCompatActivity
        implements EntryAdapter.EntryAdapterOnClickHandler{
    private static final int NUM_LIST_ITEMS = 50;
    private EntryAdapter mAdapter;
    private RecyclerView mEntryList;
    private Toast mToast;

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntryList = (RecyclerView) findViewById(R.id.rv_entries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mEntryList.setLayoutManager(linearLayoutManager);

        mEntryList.setHasFixedSize(true);

        mAdapter = new EntryAdapter(this);

        mEntryList.setAdapter(mAdapter);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mEntryList.addItemDecoration(decoration);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(MainActivity.this, CreateEntry.class);
                startActivity(intent);
            }
        });

        mDb = AppDatabase.getsInstance(getApplicationContext());
    }

    @Override
    public void onClick(String entryItem){
        Context context = this;
        Toast.makeText(context,entryItem, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume(){
        super.onResume();

        mAdapter.setEntryData(mDb.entryDao().loadAllEntries());
    }


}
