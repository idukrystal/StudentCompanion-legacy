package com.crysapp.student.ui.viewmodels;
import android.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.crysapp.student.StudentCompanionApplication;
import java.util.ArrayList;
import com.crysapp.student.models.Class;
import java.util.List;

public class TableListViewModel extends ViewModel{
    private LiveData<List<Pair<String,List<Class>>>> mnnn ;
    public LiveData<List<Pair<String,List<Class>>>>  getData(){
        if(mnnn==null){
            mnnn = StudentCompanionApplication.getDatabase().classDao.daysData();
        }
        return mnnn;
    }
}