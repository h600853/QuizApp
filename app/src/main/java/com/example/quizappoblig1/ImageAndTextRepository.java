package com.example.quizappoblig1;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;

public class ImageAndTextRepository {

    private static ImageAndTextDAO imageAndTextDAO;
    private LiveData<List<ImageAndText>> allImageAndTexts;

    public ImageAndTextRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        imageAndTextDAO = db.imageAndTextDAO();
        allImageAndTexts = imageAndTextDAO.getAllImageAndTexts();
    }

    LiveData<List<ImageAndText>> getAllImageAndTexts() {
        return allImageAndTexts;
    }
private static class insertAsyncTask extends android.os.AsyncTask<ImageAndText, Void, Void> {
 private final ImageAndTextDAO mAsyncTaskDao;
    insertAsyncTask(ImageAndTextDAO dao) {
        mAsyncTaskDao = dao;
    }
    @Override
    protected Void doInBackground(ImageAndText... imageAndTexts) {
         mAsyncTaskDao.insertImageAndText(imageAndTexts[0]);
        return null;
    }
}
    static void insert(ImageAndText imageAndText) {
       insertAsyncTask task = new insertAsyncTask(imageAndTextDAO);
        task.execute(imageAndText);
    }

}