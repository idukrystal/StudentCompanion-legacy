package com.crysapp.student.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import com.crysapp.student.db.StudentDatabaseContract.*;
import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.crysapp.student.models.Attachment;
import com.crysapp.student.models.Class;
import com.crysart.student.models.Activities;
import com.crysapp.student.utill.Action;
import com.crysapp.student.utill.SingleEventLiveData;
import com.crysapp.student.utill.SingleEventLiveData.MutableSingleEventLiveData;
import java.lang.Exception;
import java.lang.Runnable;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.lang.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.crysapp.student.db.StudentDatabaseContract.classTable;

public class StudentDatabase {
    
    Handler handler = new Handler(Looper.getMainLooper());
    public final classDataAccess classDao;
    public final activityDataAccess activityDao;
    public final attachmentDataAccess attachmentDao;
    private SQLiteDatabase database;
    private Context cxtv;
    private ExecutorService worker = Executors.newSingleThreadExecutor();
    private runner classRunner;
    public  StudentDatabase(Context coxt){
        classDao = new classDataAccess();
        activityDao = new activityDataAccess();
        attachmentDao = new attachmentDataAccess();
        cxtv = coxt;
        classRunner = new runner() ;
        classRunner.setState(runner.getDatabase);
        worker.execute(classRunner);
    }
    public void getDatabase(){
        database = new StudentDatabaseHelper(cxtv).getWritableDatabase();
    }
    public void finish(){
        database.close();
    }
    public class attachmentDataAccess{
        private Runnable inserter;
        private Runnable deleter;
        private List<Attachment> todel;
        private Attachment current;
        private String val ;
        private MutableLiveData<List<Attachment>> attaches = new MutableLiveData<List<Attachment>>();
        public MutableLiveData<List<Attachment>> getData(){
             return attaches;
         }
        public void insert(Attachment att){
            this.current = att;
            if(inserter ==null){
                inserter = () ->{ 
                    database.insert(attachmentTable.Table_Name,null,toCv(current));
                    querry();
               };
           
            }
            worker.execute(inserter);
            handler.post(()->{
                Log.d(" addinv atttaches",current.getCode()+" "+current.getName());
            });
        }
        public LiveData<List<Attachment>> get(String s){
                attaches = new MutableLiveData<List<Attachment>>();
                val = s;
                worker.execute(()->querry());
                return attaches;
       }
       public void delete(List<Attachment> list){
           todel = list;
           if(deleter == null){
               deleter = () -> {
                  for(Attachment aty:todel){
                       database.delete(StudentDatabaseContract.attachmentTable.Table_Name,StudentDatabaseContract.attachmentTable._ID+"=?",new String[] {Long.toString(aty.getId())}) ;
                        
                  }
                  querry();
               };
           }
           worker.execute(deleter);
       }
        private void querry(){
            Cursor cs = database.query(attachmentTable.Table_Name,null,attachmentTable.Column_Code+" =?",new String[]{val},null,null,null);
            if(cs!=null){
                List<Attachment> te = new ArrayList<Attachment>();
                 while(cs.moveToNext()){
                        te.add(
                            new Attachment(cs.getLong(0),cs.getString(1),cs.getString(2),cs.getString(3),cs.getString(4))
                        );
                 }
             attaches.postValue(te);
            }
        }
  
    }
    public class classDataAccess{
        public  static final int other = 1; 
        public static final int today = 2;
        public static final int tommorow = 3; 
        private boolean singleMode = false;
        private String detailc ;
        private MutableLiveData<List<Class>> currentDetails ;
        private List<Pair<String,List<Class>>> mnnn = new ArrayList<Pair<String,List<Class>>>();
        private MutableLiveData<List<Pair<String,List<Class>>>> daysData = new MutableLiveData<List<Pair<String,List<Class>>>> ();
        private MutableLiveData<List<Class>> todayClass = new MutableLiveData<List<Class>>();
        private MutableLiveData<Set<String>> allCourse = new MutableLiveData<Set<String>>();
        private MutableLiveData<List<Class>> tommorowClass  = new MutableLiveData<List<Class>>();
        private MutableLiveData<List<Class>> otherClass  = new MutableLiveData<List<Class>>();
        private SingleEventLiveData.MutableSingleEventLiveData<Long> newlyadded = new SingleEventLiveData.MutableSingleEventLiveData<Long>();
        private SingleEventLiveData.MutableSingleEventLiveData<Long> newlyedited = new SingleEventLiveData.MutableSingleEventLiveData<Long>();
        private SingleEventLiveData.MutableSingleEventLiveData<List<Class>> newlydeleted = new SingleEventLiveData.MutableSingleEventLiveData<List<Class>>();
        private List<Class> temptoday = new ArrayList<Class>();
        private List<Class> temptommorow = new ArrayList<Class>();
        private List<Class> tempother = new ArrayList<Class>();
        private ArrayList<Class> todaydata = new ArrayList<Class>();
        private ArrayList<Class> tommorowdata= new ArrayList<Class>();
        private ArrayList<Class> otherdata = new ArrayList<Class>();
        public void insert(Class c){
            classRunner.setState(runner.insertClass);
            classRunner.setSubject(c);
            worker.execute(classRunner);
              
        }
        private classDataAccess(){
             
        }
        public void updaye(MutableLiveData<List<Class>> list, String  type){
              currentDetails = list;
              singleMode=true;
              detailc=type;
              classRunner.setState(0);
              worker.execute(classRunner);
        }
        public LiveData<Set<String>> allCourse(){
            return allCourse;
        }
        private void repay(){
            mnnn.add(new Pair<String,List<Class>>("MONDAY",new ArrayList<Class>()));
            mnnn.add(new Pair<String,List<Class>>("TUESDAY",new ArrayList<Class>()));
            mnnn.add(new Pair<String,List<Class>>("WEDNESDAY",new ArrayList<Class>()));
            mnnn.add(new Pair<String,List<Class>>("THURSDAY",new ArrayList<Class>()));
            mnnn.add(new Pair<String,List<Class>>("FRIDAY",new ArrayList<Class>()));
            mnnn.add(new Pair<String,List<Class>>("SATURDAY",new ArrayList<Class>()));
            mnnn.add(new Pair<String,List<Class>>("SUNDAY",new ArrayList<Class>()));
        }
        public  LiveData<List<Pair<String,List<Class>>>> daysData(){
            return daysData;
        }
        public void insert(List<Class> c){
            classRunner.setState(runner.insertMutiple);
            classRunner.setDel(c);
            worker.execute(classRunner);
        }
        public LiveData<List<Class>> getData(int i){
            switch(i){ 
                case today:return todayClass;
                case tommorow : return tommorowClass;
                default : return otherClass;
                
            }
             
       }
        public SingleEventLiveData<Long> added(){
            return newlyadded;
        }
        public SingleEventLiveData<Long> edited(){
            return newlyedited;
        }
        public SingleEventLiveData<List<Class>> newlydeleted(){
            return newlydeleted;
        }
        void queryS(){
            if(currentDetails!=null){
               Cursor cs = database.query(StudentDatabaseContract.classTable.Table_Name,null,StudentDatabaseContract.classTable.Column_Code+" =?",new String[]{detailc},null,null,null);
               if(cs!=null){ 
                   ArrayList<Class> sjmp = new ArrayList<Class>();
                   while(cs.moveToNext()){
                       Class temp = new Class(cs.getLong(0),
                    cs.getString(1),cs.getInt(2),cs.getString(3),cs.getString(4),cs.getInt(5));
                    sjmp.add(temp);
                   }
                   currentDetails.postValue(sjmp);
                }
            }
        }
        void query(){
            Cursor cs = database.query(StudentDatabaseContract.classTable.Table_Name,null,null,null,null,null,null);
            if(cs!=null){
                Log.w("query class","cs is not null its "+cs.getCount());
                Set<String> crset = new TreeSet<String>();
                tempother.clear();
                temptommorow.clear();
                temptoday.clear();
                mnnn.clear();
                repay();
                while(cs.moveToNext()){
                    Class temp = new Class(cs.getLong(0),
                    cs.getString(1),cs.getInt(2),cs.getString(3),cs.getString(4),cs.getInt(5));
                    switch(temp.getDayCat()){
                        case Class.Today : temptoday.add(temp);Log.d("today","1 added "+temp.getDay().toString());break;
                        case Class.Tommorow : temptommorow.add(temp);Log.d("tommorow","1 added "+temp.getDay().toString());break;
                        case Class.Other : tempother.add(temp);Log.d("other","1 added "+temp.getDay().toString());
                         
                    }
                    crset.add(temp.getCourse());
                    switch(temp.getDay().getValue()){
                        case 1: mnnn.get(0).second.add(temp);break;
                        case 2: mnnn.get(1).second.add(temp); break;
                        case 3: mnnn.get(2).second.add(temp); break;
                        case 4: mnnn.get(3).second.add(temp);   break;
                        case 5: mnnn.get(4).second.add(temp);  break;
                        case 6: mnnn.get(5).second.add(temp); break;
                        case 7: mnnn.get(6).second.add(temp);
                    }
                }
                List<Pair<String,List<Class>>> nawa = new ArrayList<Pair<String,List<Class>>>();
                mnnn.forEach(v->{
                    if(v.second.size()!=0){
                        Collections.sort(v.second);
                        nawa.add(v);
                    }
                });
                Collections.sort(todaydata);
                Collections.sort(tommorowdata);
                Collections.sort(otherdata);
                Collections.sort(temptoday);
                Collections.sort(temptommorow);
                Collections.sort(tempother);
                if(!(temptoday.equals(todaydata))){
                    todaydata.clear();
                    todaydata.addAll(temptoday);
                    todayClass.postValue(new ArrayList<Class>(todaydata));
                }
                if(!(temptommorow.equals(tommorowdata))){
                    tommorowdata.clear();
                    tommorowdata.addAll(temptommorow);
                    tommorowClass.postValue(new ArrayList<Class>(tommorowdata));
                }
                if(!(tempother.equals(otherdata))){
                    otherdata.clear();
                    otherdata.addAll(tempother);
                    otherClass.postValue(new ArrayList<Class>(otherdata));
                }
                 daysData.postValue(nawa); 
                 allCourse.postValue(crset) ;
            }
            else{
            }
             
        }
        public void Delete(List<Class> todel){
            classRunner.setState(runner.deleteClass);
            classRunner.setDel(todel);
            worker.execute(classRunner);
        }
        public void Edit(Class c,long i){
            classRunner.setSubject(c);
            classRunner.setUid(i);
            classRunner.setState(runner.editClass);
            worker.execute(classRunner);
        }
    }  
    static ContentValues convertClass(Class c,boolean ided){
        ContentValues cv = new ContentValues();
        if(ided){
             cv.put(StudentDatabaseContract.classTable._ID,c.getId());
        }
        cv.put(StudentDatabaseContract.classTable.Column_Code,c.getCourse());
        cv.put(StudentDatabaseContract.classTable.Column_Day,c.getDay().getValue()-1);
        cv.put(StudentDatabaseContract.classTable.Column_Start,c.getStart().toString());
        cv.put(StudentDatabaseContract.classTable.Column_Stop,c.getStop().toString());
        cv.put(StudentDatabaseContract.classTable.Column_Remind,c.isRemind());
        return cv;
    }
    
    
    public  class activityDataAccess implements Runnable{
          final byte insert = 1;
          final byte delete = 2;
          MutableLiveData allActivities= new MutableLiveData<>();
          MutableSingleEventLiveData<Long> added = new MutableSingleEventLiveData<>();
          private Activities current ;
          byte state = 0;
          final byte getAll = 3;
          final byte getSingle=4;
          final byte getCourse=5;
          public synchronized void run(){
              switch(state){
                  case insert :
                    added.postValue(database.insert(StudentDatabaseContract.activityTable.Table_Name,null,toCv(current)));
                    quary();
                    break;
                  }
          }
          public  SingleEventLiveData<Long> insert(Activities acc){
               state = insert;
               current = acc;
               worker.execute(this);
               return added;
          }
          public LiveData<List<Activities>> getAll(){
               return allActivities;
          }
          private void quary(){
              Cursor cs = database.query(StudentDatabaseContract.activityTable.Table_Name,null,null,null,null,null,null);
              ArrayList<Activities> temp = new ArrayList<>();
              if(cs!=null){
                  cs.moveToFirst();
                  while(cs.moveToNext()){
                      Activities t = new Activities(cs.getString(1),cs.getString(3),cs.getString(5),(cs.getInt(8)==1)?true:false,(byte)cs.getShort(7));
                      temp.add(t);
                  }
                  allActivities.postValue(temp);
              }
          }
          private ContentValues toCv(Activities acc){
              var cv = new ContentValues();
              cv.put(activityTable.Column_Title,acc.getTitle());
              cv.put(activityTable.Column_Date_1,acc.getShortDate());
              cv.put(activityTable.Column_During_Class,acc.getDuringClass()?1:0);
              cv.put(activityTable.Column_Time_1,acc.getTime());
              cv.put(activityTable.Column_Frequency,acc.getFreq());
              return cv;
          }
      }
      
      
    private class runner implements Runnable{
        static final int getDatabase = 1; 
        static final int getClasses = 2;
        static final int insertMutiple =6;
        static final int insertClass = 3; 
        static final int deleteClass = 4; 
        static final int editClass = 5;
        private int state;
        private Class subject;
        private long uid;
        private List<Class> del;
        public void run(){
            switch(state){
                case getDatabase : getDatabase() ;break;
                case insertClass:Log.e("inserting class","trying");long e = database.insert(StudentDatabaseContract.classTable.Table_Name,null,convertClass(subject,false)); Log.e("inserting class","done") ;classDao.newlyadded.postValue(e);break;
                case deleteClass : ArrayList<Class> lic = new ArrayList<Class>();
                    for(int i = 0;i<del.size();i++){
                        database.delete(StudentDatabaseContract.classTable.Table_Name,StudentDatabaseContract.classTable._ID+"=?",new String[] {Long.toString(del.get(i).getId())} );
                        lic.add(del.get(i));
                    }classDao.newlydeleted.postValue(lic);break;
                case editClass : long l = database.update(StudentDatabaseContract.classTable.Table_Name,convertClass(subject,false),StudentDatabaseContract.classTable._ID+"=?",new String[]{Long.toString(uid)});classDao.newlyedited.postValue(l);break;
                case insertMutiple:Log.d("inserting multiple","Starting");
                    for(int i = 0;i<del.size();i++){
                        database.insert(StudentDatabaseContract.classTable.Table_Name,null,convertClass(del.get(i),true));
                     };
            }
            if(classDao.singleMode){
                 classDao.queryS();
            }
            classDao.query();
        }
        void setState(int state){
            this.state = state;
        }
        void setSubject(Class c){
            subject = c;
        }
        void setDel(List<Class> dic){
            del = dic;
        }
        void setUid(long l){
            uid = l;
        }
    }
    public static  enum dataType{
         Class ,Attachment;
         private String name;
         public void setName(String n){
             name = n;
         }
    }
    public interface accesibleType{
        
    }
    public ContentValues toCv(Attachment current){
        ContentValues cv = new ContentValues();            
                     cv.put(attachmentTable.Column_Code,current.getCode());
                     cv.put(attachmentTable.Column_Name,current.getName());      
                    cv.put(attachmentTable.Column_File,current.getFile().getAbsolutePath());
                    cv.put(attachmentTable.Column_Type,current.getType());          
            return cv;
    }
}











