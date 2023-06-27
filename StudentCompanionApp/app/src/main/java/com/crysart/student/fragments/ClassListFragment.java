package com.crysapp.student.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.crysapp.student.R;
import com.crysapp.student.databinding.FragmentClassListBinding;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.ui.adapters.ClasspagerAdapter;
import com.crysapp.student.ui.adapters.UpcommingPagerAdapter;
import com.crysapp.student.ui.viewmodels.ClassListViewModel;
import com.google.android.material.tabs.TabLayoutMediator;
import java.lang.Integer;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Collections;

public class ClassListFragment extends Fragment{
    private FragmentClassListBinding binding;
    private ClassListViewModel model;
    private ClasspagerAdapter pca;
    ArrayList<Integer> ttts = new ArrayList<Integer> ();
    @Override
    public void onCreate(Bundle saved){
         super.onCreate(saved);
      
         pca = new ClasspagerAdapter(this);
         
         model = new ViewModelProvider(this).get(ClassListViewModel.class);
         model.getLiveToday().observe(this,l->{
             
             
             if(l.size()==0){
                 ttts.removeIf(h->h==1);
             }else if(!ttts.contains(1)){
                ttts.add(1);
                Collections.sort(ttts);
             }
             pca.update(ttts);
             
         });
         model.getLiveTommorow().observe(this,l->{
             if(l.size()==0){
                 ttts.removeIf(h->h==2);
             }else if(!ttts.contains(2)){
                ttts.add(2);
                Collections.sort(ttts);
             }
             pca.update(ttts);
             
         });
         model.getLiveOther().observe(this,l->{
             if(l.size()==0){
                 ttts.removeIf(h->h==3);
             }else if(!ttts.contains(3)){
                ttts.add(3);
                Collections.sort(ttts);
                
             }
             pca.update(ttts);
             
         });
    }
    @Override
    public View onCreateView(@ NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
         binding = FragmentClassListBinding.inflate(inflater);
         
         binding.todayListRecycler.setAdapter(pca);
         return binding.getRoot();
    }
    @Override
    public void onViewCreated(View c,Bundle saved){
         new TabLayoutMediator(binding.tabLayout,binding.todayListRecycler,
         (tab,pos)->{ 
            tab.setText(pca.getTitle(pos));
       }
         ).attach();
    }
}
