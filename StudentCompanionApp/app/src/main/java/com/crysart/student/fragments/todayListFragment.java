package com.crysapp.student.fragments;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.databinding.ActivityHomeBinding;
import com.crysapp.student.databinding.FragmentClassListBinding;
import com.crysapp.student.databinding.FragmentCurentInfoBinding;
import com.crysapp.student.db.StudentDatabaseHelper;
import com.crysapp.student.models.Class;
import androidx.fragment.app.Fragment;
import com.crysapp.student.presenter.ItemPresenter;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.ui.viewmodels.ClassListViewModel;
import java.lang.Exception;
import java.lang.Override;
import java.util.List;

public class  todayListFragment extends Fragment{
        FragmentCurentInfoBinding binding ;
        
        public static final String ITEM_CODE = "itemcode";
       private  Class item ;
         @Override
         public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
             binding = FragmentCurentInfoBinding.inflate(inflater);
             binding.setPresenter(new ItemPresenter());
            return binding.getRoot();
         }
         @Override
         public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
              Bundle arg = getArguments();
              binding.setItem((Class)arg.getParcelable(ITEM_CODE));
         }
        
    }

