package com.crysapp.student.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.crysapp.student.R;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.databinding.ActivityHomeBinding;
import com.crysapp.student.databinding.FragmentClassListBinding;
import com.crysapp.student.databinding.FragmentRecyclerViewBinding;
import com.crysapp.student.db.StudentDatabaseHelper;
import com.crysapp.student.models.Attachment;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.ui.viewmodels.ClassListViewModel;
import com.crysapp.student.ui.viewmodels.DetailsViewModel;
import fragments.NewEditFragment;
import java.lang.Exception;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;
public  class othersListFragment extends Fragment{
    private FragmentRecyclerViewBinding binding;
    private DetailsViewModel model;
    private ClassListAdapter<Class> adpp;
    private Bundle arg;
    private ActionMode actionmode;
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        model= new ViewModelProvider(this).get(DetailsViewModel.class);
        arg = getArguments();
        adpp = new ClassListAdapter<Class>(getActivity(),4,R.layout.card_upcomming,Class.getCallback(),null);
        model.getData(arg.getString("val")).observe(this,m->{
            adpp.submitList(m);
                
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup comtainer,Bundle saved){
        binding = FragmentRecyclerViewBinding.inflate(inflater);
        binding.todayListRecycler.setAdapter(adpp);
        binding.todayListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View v,Bundle saved){
        SelectionTracker<Class> ttt = new SelectionTracker.Builder<Class>(
             "mysel",
             binding.todayListRecycler,
             new ClassListAdapter.classKeyProvider<Class>(adpp),
             new ClassListAdapter.ClassLookup<Class>(binding.todayListRecycler),
             StorageStrategy.createParcelableStorage(Class.class)
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build();
        adpp.setTracker(ttt);
        ActionMode.Callback acb =  new ActionMode.Callback(){
              public boolean onCreateActionMode(ActionMode mode,Menu menu){
              getActivity().getMenuInflater().inflate(R.menu.cotextual_attachment_menu,menu);
              actionmode =mode;
                 return true;
      }
       public void onDestroyActionMode(ActionMode mode){
                
                 actionmode = null; 
                 ttt.clearSelection();
       }
       public boolean onActionItemClicked(ActionMode mode,MenuItem item){
               ArrayList<Class> send = new ArrayList<Class>();
               ttt.getSelection().forEach(m->send.add(m));
               ttt.clearSelection();
                
               switch(item.getItemId()){
                   case R.id.del :
                        StudentCompanionApplication.getDatabase().classDao.Delete(send);
                        
                         return true;
                  default : 
                        NewEditFragment.getInstance(send.get(0)).show(getActivity().getSupportFragmentManager(),"dd");
                       return true;
               }
                 
                 
       }
        public boolean onPrepareActionMode(ActionMode mode,Menu menu){
                 return false;
        }
         
         };
       SelectionTracker.SelectionObserver<Class> obs = new SelectionTracker.SelectionObserver<Class>(){
              public void onSelectionChanged(){
                  super.onSelectionChanged();
                 if(ttt.getSelection().size()==0){
                     if(actionmode!=null)actionmode.finish();
      
                 }
                 else{
                   if(actionmode==null) ((AppCompatActivity)getActivity()).startSupportActionMode(acb);
                   actionmode.setTitle(ttt.getSelection().size()+" Selected");
                   if(ttt.getSelection().size()>1){
                       actionmode.getMenu().getItem(0).setVisible(false);
                       
                   }
                   else{
                       actionmode.getMenu().getItem(0).setVisible(true);
                   }
                 }
              
              }
              @Override
              public void onSelectionCleared(){
                  super.onSelectionCleared();
                  ttt.clearSelection();
                  if(actionmode!=null)  actionmode.finish();
                
              }
          };ttt.addObserver(obs);
    }
}



