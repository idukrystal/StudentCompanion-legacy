package com.crysapp.student.ui.viewmodels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.db.StudentDatabase;
import com.crysapp.student.models.Class;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel{
    private List<Class> newlyDeleted ;
    private long lastSaved = -1;
    private LiveData<List<Class>> todayList;
    private long lastEdited = -1;
    public boolean setLastSaved(long s){
        if(s==lastSaved){
            return false;
        }
        lastSaved = s;
        return true;
    }
    public boolean setLastEdited(long s){
        if(s==lastEdited){
            return false;
        }
        lastEdited = s;
        return true;
    }
    public LiveData<List<Class>> getLiveToday(){
       if(todayList == null){
           todayList = StudentCompanionApplication.getDatabase().classDao.getData(StudentDatabase.classDataAccess.today);
           
       }
       return todayList;
   }
    public void setNewlyDeleted(List<Class> nd){
        newlyDeleted = nd;
    }
    public void undoDeleted(){
        StudentCompanionApplication.getDatabase().classDao.insert(new ArrayList<Class>(newlyDeleted));
        newlyDeleted.clear();
    }
 }