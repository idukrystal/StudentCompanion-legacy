package com.crysapp.student;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.content.Context;
import androidx.appcompat.view.ActionMode;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.crysapp.student.databinding.ActivityHomeBinding;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.adapters.UpcommingPagerAdapter;
import com.crysapp.student.ui.viewmodels.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.itsaky.androidide.logsender.LogSender;
import fragments.NewEditFragment;
import java.lang.Integer;
import java.lang.Override;
import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private ActionMode actionmode ;
    private UpcommingPagerAdapter upga;
    private HomeViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model =  new ViewModelProvider(this).get(HomeViewModel.class);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        upga = new UpcommingPagerAdapter(this);
        model.getLiveToday().observe(this,l->{
             ArrayList<Class> temp = new ArrayList<Class>();
             for(Class clx : l){
                  if(clx.now())temp.add(clx);
             }
             int i = 0;
             while(temp.size()<=5&&i<l.size()) {
                 if(l.get(i).isNext()) temp.add(l.get(i));
                 i++;
            }
            upga.update(temp)    ;  
         });
        StudentCompanionApplication.getDatabase().classDao.added().observe(
            this, v -> { if(model.setLastSaved(v))Snackbar.make(binding.snacks,v + " Saved",Toast.LENGTH_SHORT).show();}
        );
        StudentCompanionApplication.getDatabase().classDao.edited().observe(
            this, v -> { //if(model.setLastEdited(v))
                Snackbar.make(binding.snacks,v + " Edited",Toast.LENGTH_SHORT).show();}
        );
        StudentCompanionApplication.getDatabase().classDao.newlydeleted().observe(
            this, v -> { model.setNewlyDeleted(v); 
                Snackbar.make(binding.snacks,v.size() + " Deleted",Toast.LENGTH_SHORT).setAction("Undo",x->model.undoDeleted()).show();}
        );
        setContentView(binding.getRoot());
       binding.fab.setOnClickListener(v -> showEdit()
       );
       binding.currentPager.setAdapter(upga);
        setUpNavigation();
        Log.e(getClass().getSimpleName(),"Started");
    }
    public  void showEdit(){
        NewEditFragment nef = NewEditFragment.getInstance();
        nef.show(getSupportFragmentManager(),"");
    }
    public void setUpNavigation(){
        NavHostFragment nhf = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottom,nhf.getNavController());
        nhf.getNavController().addOnDestinationChangedListener((v,h,a)->{if(actionmode!=null)actionmode.finish(); });
       
    }
    @Override
    public void onSupportActionModeStarted(ActionMode am){
       super.onSupportActionModeStarted(am);
       actionmode = am;
    }
    
}

