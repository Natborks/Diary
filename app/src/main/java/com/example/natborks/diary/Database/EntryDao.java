package com.example.natborks.diary.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EntryDao {

    @Query("SELECT * FROM Entry")
    public List<DiaryEntry> loadAllEntries();

    @Insert
    void insertEntry(DiaryEntry diaryEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    public void updateEntry(DiaryEntry diaryEntry);

    @Delete
    void deleteEntry(DiaryEntry diaryEntry);

}
