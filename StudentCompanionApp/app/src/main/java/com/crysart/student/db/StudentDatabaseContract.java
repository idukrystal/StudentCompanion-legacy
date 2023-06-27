package com.crysapp.student.db;
import android.provider.BaseColumns;
import java.lang.String;

public class StudentDatabaseContract {
    private StudentDatabaseContract(){}
    public static final String Database_Name = "student.db";
    public static class classTable implements BaseColumns{
        public static final String Table_Name = "Class_Table";
        public static final String Column_Code = "Code";
        public static final String Column_Start = "Start";
        public static final String Column_Day = "Day";
        public static final String Column_Stop = "Stop";
        public static final String Column_Remind = "Remind";
        public static final String[] Projection_All = {Column_Code,Column_Day,Column_Start,Column_Stop,Column_Remind};
        public static final String[] Projection_Code = {Column_Code};
        public static final String[] Projection_Remind = {Column_Remind};
        public static final String Create_Table =
                    "CREATE TABLE "+Table_Name+" ("+
                     _ID+" INTEGER PRIMARY KEY,"+
                     Column_Code+" TEXT,"+
                     Column_Day+" TEXT,"+
                     Column_Start+" TEXT,"+
                     Column_Stop+" TEXT,"+
                     Column_Remind+" INTEGER)";
    }
    public static class attachmentTable implements BaseColumns{
        public static final String Table_Name = "attachment_Table";
        public static final String Column_Code = "Code";
        public static final String Column_Name = "Name";
        public static final String Column_File = "File";
        public static final String Column_Type = "Type";
        public static final String Create_Table =
                    "CREATE TABLE "+Table_Name+" ("+
                     _ID+" INTEGER PRIMARY KEY,"+
                     Column_Code+" TEXT,"+
                     Column_Name+" TEXT,"+
                     Column_File+" TEXT,"+
                     Column_Type+" TEXT)";
    }
    public static class activityTable implements BaseColumns{
        public static final String Table_Name = "activity_Table";
        public static final String Column_Title = "titl";
        public static final String Column_Class_ID = "Class_id";
        public static final String Column_Date_1 = "date1";
        public static final String Column_Date_2 = "date2";
        public static final String Column_Time_1 = "time1";
        public static final String Column_Time_2 = "time2";
        public static final String Column_During_Class = "remind";
        public static final String Column_Frequency = "freq";
        public static final String Create_Table =
                    "CREATE TABLE "+Table_Name+" ("+
                     _ID+" INTEGER PRIMARY KEY,"+
                     Column_Title+" TEXT,"+
                     Column_Class_ID+" INTEGER,"+
                     Column_Date_1+" TEXT,"+
                     Column_Date_2+" TEXT,"+
                     Column_Time_1+" TEXT,"+
                     Column_Time_2+" TEXT,"+
                     Column_Frequency+"INTEGER,"+
                     Column_During_Class+" INTEGER)";
        
        
    }
}


