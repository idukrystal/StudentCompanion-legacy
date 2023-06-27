package com.crysapp.student.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.crysapp.student.R;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.databinding.FragmentRecyclerViewBinding;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.ui.viewmodels.ClassListViewModel;
import fragments.NewEditFragment;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

public class ClassFragment extends Fragment{
    private FragmentRecyclerViewBinding binding;
    private ClassListViewModel model;
    private int yes;
    private ActionMode actionmode;
    private ClassListAdapter adapter;
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        yes = getArguments().getInt("type");
        model = new ViewModelProvider(this).get(ClassListViewModel.class);
        adapter = new ClassListAdapter(getActivity(),yes,R.layout.card_upcomming,Class.getCallback(),null);
        LiveData<List<Class>> ddx ;
        switch(yes){
            case 1 : ddx = model.getLiveToday();break;
            case 2 : ddx = model.getLiveTommorow();break;
            default : ddx = model.getLiveOther();break;
                 
        }ddx.observe(this,f->{
            adapter.submitList(f);
        });
    }
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
          binding = FragmentRecyclerViewBinding.inflate(inflater);
          binding.todayListRecycler.setAdapter(adapter);
          binding.todayListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
          return binding.getRoot();
    }
    public void onViewCreated(View v,Bundle saved){
        SelectionTracker<Class> ttt = new SelectionTracker.Builder<Class>(
             "mysel",
             binding.todayListRecycler,
             new ClassListAdapter.classKeyProvider<Class>(adapter),
             new ClassListAdapter.ClassLookup<Class>(binding.todayListRecycler),
             StorageStrategy.createParcelableStorage(Class.class)
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build();
        adapter.setTracker(ttt);
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