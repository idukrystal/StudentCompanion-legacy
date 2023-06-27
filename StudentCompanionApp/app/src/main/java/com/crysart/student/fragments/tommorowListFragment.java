package com.crysapp.student.fragments;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.FileProvider;
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
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.ui.viewmodels.ClassListViewModel;
import com.crysapp.student.ui.viewmodels.DetailsViewModel;
import com.crysapp.student.utill.Action;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Override;
import java.util.ArrayList;
import java.util.List;

public  class tommorowListFragment extends Fragment{
    private FragmentRecyclerViewBinding binding;
    private ClassListAdapter<Attachment> adppA;
    private DetailsViewModel model;
    private Bundle arg;
    private ActionMode actionmode;
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        model= new ViewModelProvider(this).get(DetailsViewModel.class);
        arg = getArguments();
        adppA = new ClassListAdapter<Attachment>(getActivity(),1,R.layout.card_attachments,new Attachment.Callback(),n->{
            open(n);
        });
        model.getAttach(arg.getString("val")).observe(this,m->{
            adppA.submitList(m);
        });
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclerViewBinding.inflate(inflater);
        binding.todayListRecycler.setAdapter(adppA);
        binding.todayListRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SelectionTracker<Attachment> ttt = new SelectionTracker.Builder<Attachment>(
             "mysel",
             binding.todayListRecycler,
             new ClassListAdapter.classKeyProvider<Attachment>(adppA),
             new ClassListAdapter.ClassLookup<Attachment>(binding.todayListRecycler),
             StorageStrategy.createParcelableStorage(Attachment.class)
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build();
         adppA.setTracker(ttt);
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
               ArrayList<Attachment> send = new ArrayList<Attachment>();
               ttt.getSelection().forEach(m->send.add(m));
               ttt.clearSelection();
                
               switch(item.getItemId()){
                   case R.id.del :
                        StudentCompanionApplication.getDatabase().attachmentDao.delete(send);
                        
                         return true;
                  default : 
                       // NewEditFragment.getInstance(send.get(0)).show(getActivity().getSupportFragmentManager(),"dd");
                       return true;
               }
                 
                 
       }
        public boolean onPrepareActionMode(ActionMode mode,Menu menu){
                 return false;
        }
         
         };
       SelectionTracker.SelectionObserver<Attachment> obs = new SelectionTracker.SelectionObserver<Attachment>(){
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
    private void open(Attachment att){
        Uri u = FileProvider.getUriForFile(getActivity(),"com.crysapp.student.provider",att.getFile());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(u,att.getType());
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try{
            startActivity(i);
        }catch(Exception a){
            Toast.makeText(getContext(),a.getClass()+"  "+a.getLocalizedMessage()+"!!!"+att.getType(), Toast.LENGTH_SHORT).show();
        }
    }
    
}
    