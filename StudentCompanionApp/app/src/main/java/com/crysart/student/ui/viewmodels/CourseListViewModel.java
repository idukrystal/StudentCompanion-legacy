package com.crysapp.student.ui.viewmodels;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.crysapp.student.StudentCompanionApplication;
import java.util.Set;

public class CourseListViewModel extends ViewModel {
    LiveData<Set<String>> allcourse ;
    public LiveData<Set<String>> getData(){
        if(allcourse == null){
            allcourse = StudentCompanionApplication.getDatabase().classDao.allCourse();
        }
        return allcourse;
    }
}