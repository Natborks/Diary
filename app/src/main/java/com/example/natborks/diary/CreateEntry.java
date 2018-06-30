package com.example.natborks.diary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.natborks.diary.Database.AppDatabase;
import com.example.natborks.diary.Database.DiaryEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CreateEntry extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";

    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_ENTRY_ID = -1;
    // Constant for logging
    private static final String TAG = CreateEntry.class.getSimpleName();
    // Fields for views
    EditText mEditTitleText;
    EditText mEditBodyText;
    Button mButton;

    private int mTaskId = DEFAULT_ENTRY_ID;


    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        initViews();

        // TODO (4) Initialize member variable for the data base
        mDb = AppDatabase.getsInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_ENTRY_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton.setText(R.string.update_button);
            if (mTaskId == DEFAULT_ENTRY_ID) {
                // populate the UI
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        mEditTitleText = findViewById(R.id.tv_title);
        mEditBodyText = findViewById(R.id.tv_body);

        mButton = findViewById(R.id.saveButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int editMenuItem = item.getItemId();

        if(editMenuItem == R.id.action_edit){
            Context context = CreateEntry.this;
            String message = "Edit Clicked";
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void populateUI(DiaryEntry entry) {

    }

    public void onSaveButtonClicked() {
        String title = mEditTitleText.getText().toString();
        String body = mEditBodyText.getText().toString();
        Date date = new Date();

        DiaryEntry diaryEntry = new DiaryEntry(title,body,date);

        mDb.entryDao().insertEntry(diaryEntry);

        String toastMsg = "Entry saved";
        Toast.makeText(this,toastMsg,Toast.LENGTH_LONG).show();
        finish();
    }
}
