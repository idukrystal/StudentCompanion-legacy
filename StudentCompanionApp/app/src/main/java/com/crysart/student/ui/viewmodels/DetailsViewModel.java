package com.crysapp.student.ui.viewmodels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.models.Attachment;
import com.crysapp.student.models.Class;
import java.util.List;

public class DetailsViewModel extends ViewModel{
    MutableLiveData<List<Class>> data ;
    LiveData<List<Attachment>> attdata ;
    public LiveData<List<Class>> getData(String bop){
        if(data == null){
             data = new MutableLiveData<List<Class>>();
             StudentCompanionApplication.getDatabase().classDao.updaye(data,bop);
        }
        return data;
       
    }
    public LiveData<List<Attachment>> getAttach(String bop){
        if(attdata == null){
             attdata = 
             StudentCompanionApplication.getDatabase().attachmentDao.get(bop);
        }
        return attdata;
       
    }
}
