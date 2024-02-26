package com.example.quizappoblig1;

import android.net.Uri;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static String fromUri(Uri uri) {
        return uri == null ? null : uri.toString();
    }
    @TypeConverter
    public static Uri toUri(String uri) {
        return uri == null ? null : Uri.parse(uri);
    }
}
