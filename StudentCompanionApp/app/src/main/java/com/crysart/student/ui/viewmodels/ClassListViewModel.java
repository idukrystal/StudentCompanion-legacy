package com.crysapp.student.ui.viewmodels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.db.StudentDatabase;
import com.crysapp.student.models.Class;
import java.util.List;

public  class ClassListViewModel extends ViewModel{
   private LiveData<List<Class>> todayList;
  // private static LiveData<List<Class>> selectClasses = new MutableLiveData<List<Class>>();
   private LiveData<List<Class>> tommorowList;
   private LiveData<List<Class>> otherList;
   public LiveData<List<Class>> getLiveToday(){
       if(todayList == null){
           loadUsers(1);
           
       }
       return todayList;
   }
   public LiveData<List<Class>> getLiveTommorow(){
       if(tommorowList == null){
           loadUsers(2);
       }
       return tommorowList;
   }
   public LiveData<List<Class>> getLiveOther(){
       if(otherList == null){
           loadUsers(3);
       }
       return otherList;
   }
   public void loadUsers(int pp){
       switch(pp){
           case 1 :todayList = StudentCompanionApplication.getDatabase().classDao.getData(StudentDatabase.classDataAccess.today);break;
           case 2 :tommorowList = StudentCompanionApplication.getDatabase().classDao.getData(StudentDatabase.classDataAccess.tommorow);break;
           case 3 :otherList = StudentCompanionApplication.getDatabase().classDao.getData(StudentDatabase.classDataAccess.other);
       }
    }
}