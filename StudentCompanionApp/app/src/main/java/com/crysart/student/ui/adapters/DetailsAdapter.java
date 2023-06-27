package com.crysapp.student.ui.adapters;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crysapp.student.fragments.ActivitiesFragment;
import com.crysapp.student.fragments.othersListFragment;
import com.crysapp.student.fragments.tommorowListFragment;

public class DetailsAdapter extends FragmentStateAdapter{
    private String val;
    public DetailsAdapter(FragmentActivity fragment,String vl){
        super(fragment);
        val = vl ;
    }
    public Fragment createFragment(int position){
        Bundle args = new Bundle();
        args.putString("val",val);
        switch(position){
             case 0:othersListFragment temp = new othersListFragment();
                    temp.setArguments(args);
                    return temp;
             case 2: ActivitiesFragment act = new ActivitiesFragment();
                     return act;
             default:tommorowListFragment tep = new tommorowListFragment();
                     tep.setArguments(args);
                     return tep;
        }
    }
    public int getItemCount(){
        return 3;
    }
}

