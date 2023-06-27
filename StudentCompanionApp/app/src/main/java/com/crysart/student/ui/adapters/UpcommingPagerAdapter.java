package com.crysapp.student.ui.adapters;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.crysapp.student.fragments.todayListFragment;
import com.crysapp.student.fragments.tommorowListFragment;
import com.crysapp.student.fragments.othersListFragment;
import com.crysapp.student.models.Class;
import fragments.NewEditFragment;
import java.lang.Exception;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;
public class UpcommingPagerAdapter extends FragmentStateAdapter{
    private List<Class> currents = new ArrayList<Class>();
    public UpcommingPagerAdapter(FragmentActivity fragment){
        super(fragment);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
         Fragment temp ;
         Bundle args = new Bundle();
         temp = new todayListFragment();
         args.putParcelable(todayListFragment.ITEM_CODE,currents.get(position));
         temp.setArguments(args);
         return  temp;
     }
    @Override
    public int getItemCount() {
      return (currents != null)?currents.size():0;
        
    }
    @Override
    public long getItemId(int position){
        return currents.get(position).generateUniqueId();
    }
    @Override
    public boolean  containsItem(long id){
        for(Class x : currents){
            if(x.generateUniqueId()==id){
                return true;
            }
        }
        return false;
    }
   public void update(List<Class> cur){
       Log.d("upcpager",cur.size()+"");
   if(currents != null){
           currents.clear();
           currents.addAll(cur);
       }
       else{
           currents = new ArrayList<Class>();
           currents.clear();
           currents.addAll(cur);
          
       }
      notifyDataSetChanged();
   }

}