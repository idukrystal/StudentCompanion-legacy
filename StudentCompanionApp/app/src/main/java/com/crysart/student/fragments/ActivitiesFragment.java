package com.crysapp.student.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.crysapp.student.R;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.crysapp.student.databinding.FragmentRecyclerViewBinding;
import com.crysart.student.models.Activities;
import java.lang.Override;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ActivitiesFragment extends Fragment{
   private FragmentRecyclerViewBinding binding;
   private ClassListAdapter<Activities> adapter;
   @Override
   public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saved){
       binding = FragmentRecyclerViewBinding.inflate(inflater);
      // adapter = new ClassListAdapter<Activities>(getActivity(),4,R.layout.card_upcomming,Class.getCallback(),null);
       binding.todayListRecycler.setAdapter(adapter);
       binding.todayListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
       return binding.getRoot();
   }
}




