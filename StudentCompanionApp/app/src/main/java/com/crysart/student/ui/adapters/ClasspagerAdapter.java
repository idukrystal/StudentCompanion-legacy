package com.crysapp.student.ui.adapters;
import android.os.Bundle;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crysapp.student.fragments.ClassFragment;
import com.crysapp.student.fragments.ClassListFragment;
import java.lang.Integer;
import java.lang.Override;
import java.util.Arrays;
import java.util.List;

public class ClasspagerAdapter extends FragmentStateAdapter{
    private List<Integer> data;
    public Fragment createFragment(int position){
          ClassFragment frag = new ClassFragment();
          Bundle x = new Bundle();
          x.putInt("type",data.get(position));
          frag.setArguments(x);
          return frag;
    }
    public int getItemCount(){
        
        return (data!=null)?data.size():0;
    }
    public long getItemId(int position){
        return data.get(position);
    }
    @Override
    public boolean containsItem(long id){
       return (data).contains(id);
    }
    public ClasspagerAdapter(Fragment frag){
        super(frag);
    }
    public void update(List<Integer> ll){
        data =ll;
        notifyDataSetChanged();
    }
    public String getTitle(int pos){
        switch(data.get(pos)){
            case 1 : return "Today";
            case 2 : return "Tommmorow";
            default : return "Others";
        }
    }
}
