package com.crysapp.student.fragments;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.crysapp.student.databinding.FragmentRecyclerViewBinding;
import com.crysapp.student.databinding.FragmentNewEditBinding;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.ui.adapters.ListInListAdapter;
import com.crysapp.student.ui.viewmodels.TableListViewModel;
import java.util.ArrayList;
import java.util.List;

public class TableFragment extends Fragment {
    private FragmentRecyclerViewBinding binding;
    ListInListAdapter ad;
    private TableListViewModel model;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
            binding = FragmentRecyclerViewBinding.inflate(inflater);
            return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        
        binding.todayListRecycler.setAdapter(ad);
        binding.todayListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
     
    }
    public void onCreate(Bundle b){
        super.onCreate(b);
        ad = new ListInListAdapter((AppCompatActivity)getActivity());
        model = new ViewModelProvider(this).get(TableListViewModel.class);
        model.getData().observe(this,v->ad.submitList(v));
    }
}
