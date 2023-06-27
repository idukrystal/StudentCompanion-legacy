package com.crysapp.student.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.crysapp.student.db.StudentDatabaseContract;
import java.lang.Exception;
import java.lang.Runnable;

public class StudentDatabaseHelper extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    static Context  cvxt ;
    public static final String DATABASE_NAME = StudentDatabaseContract.Database_Name;
    public StudentDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        cvxt = context;
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StudentDatabaseContract.classTable.Create_Table);
        db.execSQL(StudentDatabaseContract.attachmentTable.Create_Table);
        db.execSQL(StudentDatabaseContract.activityTable.Create_Table);
  
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
    
}
