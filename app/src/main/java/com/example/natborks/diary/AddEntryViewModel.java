package com.example.natborks.diary;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.natborks.diary.Database.AppDatabase;
import com.example.natborks.diary.Database.DiaryEntry;

public class AddEntryViewModel extends ViewModel {
    private LiveData<DiaryEntry> entry;

    public AddEntryViewModel(AppDatabase database, ){

    }

    public LiveData<DiaryEntry> getEntry() {
        return entry;
    }
}
