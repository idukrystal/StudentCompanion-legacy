package com.crysapp.student.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.crysapp.student.CourseDetailActivity;
import com.crysapp.student.R;
import com.crysapp.student.databinding.FragmentClassListBinding;
import com.crysapp.student.databinding.FragmentNewEditBinding;
import com.crysapp.student.databinding.FragmentRecyclerViewBinding;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.ui.viewmodels.CourseListViewModel;
import java.lang.Exception;
import java.lang.Override;
import java.util.ArrayList;
public class CourseFragment extends Fragment {
    CourseListViewModel model;
    ClassListAdapter<String> adpt ;
    private FragmentRecyclerViewBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
            binding = FragmentRecyclerViewBinding.inflate(inflater);
            return binding.getRoot();
            
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
          binding.todayListRecycler.setAdapter(adpt);
          binding.todayListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
         adpt = new ClassListAdapter<String>(getActivity(),1,R.layout.card_corse,new DiffUtil.ItemCallback<String>(){
           public boolean areContentsTheSame(String p1,String p2){
               return true;
           }
           public boolean areItemsTheSame(String p1,String p2){
               return p1.equals(p2);
           }
        },m->{ Intent in = new Intent(getActivity(),CourseDetailActivity.class);
               in.putExtra("course",m);
               startActivity(in);
        });
        model = new ViewModelProvider(this).get(CourseListViewModel.class);
            model.getData().observe(this,v->{
                adpt.submitList(new ArrayList<String>(v));
        });
    }
}

