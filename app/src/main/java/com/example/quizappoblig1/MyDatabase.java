package com.example.quizappoblig1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ImageAndText.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MyDatabase extends RoomDatabase {

    public abstract ImageAndTextDAO imageAndTextDAO();
    private static MyDatabase INSTANCE;

    static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    MyDatabase.class,
                                    "product_database").build();
                }
            }
        }
        return INSTANCE;
    }

}
