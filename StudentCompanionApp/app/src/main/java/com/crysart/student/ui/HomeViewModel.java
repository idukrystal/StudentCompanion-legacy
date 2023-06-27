package com.crysapp.student.ui;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.crysapp.student.models.Class;
import java.util.List;

public class HomeViewModel extends ViewModel{
   private MutableLiveData<List<Class>> liveClasses;
   public MutableLiveData<List<Class>> getUsers(){
       if(liveClasses == null ){
           liveClasses = new MutableLiveData<List<Class>>();
           loadUsers();
       }
       return liveClasses;
   }
   private void loadUsers(){
       
   }
   
}