package com.example.quizappoblig1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ImageAndTextDAO {

    @Insert
    Void insertImageAndText(ImageAndText imageAndText);
    @Delete
    void delete(ImageAndText imageAndText);
    @Query("SELECT * FROM ImageAndText")
    LiveData<List<ImageAndText>> getAllImageAndTexts();
    @Query("DELETE FROM ImageAndText")
    void deleteAll();

}
