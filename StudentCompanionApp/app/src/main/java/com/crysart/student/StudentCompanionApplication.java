package com.crysapp.student;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegate;
import com.crysapp.student.db.StudentDatabase;
import com.crysapp.student.db.StudentDatabaseHelper;
import com.crysapp.student.utill.Action;
import com.itsaky.androidide.logsender.LogSender;
import java.lang.Override;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class StudentCompanionApplication extends Application {
    private static StudentDatabase database;
    @Override
    public void  onCreate(){
        super.onCreate();
        LogSender.startLogging(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        Log.e(getClass().getSimpleName(),"Started");
        database = new StudentDatabase(this);
        
    }
    public static StudentDatabase getDatabase(){
        return database;
    }
    public void onTerminate(){
        database.finish();
    }
}


