package com.crysapp.student.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.crysapp.student.databinding.FragmentUpcommingBinding;
import com.crysapp.student.ui.adapters.UpcommingPagerAdapter;
public class UpcomingFragment extends Fragment{
    FragmentUpcommingBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
            binding = FragmentUpcommingBinding.inflate(inflater);
            return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       
       
    }
}
