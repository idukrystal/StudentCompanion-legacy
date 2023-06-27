package com.crysapp.student;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import com.crysapp.student.fragments.NewActivityFragment;
import com.crysapp.student.models.Attachment;
import com.google.android.material.tabs.TabLayoutMediator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.crysapp.student.databinding.ActivityCourseDetailsBinding;
import com.crysapp.student.ui.adapters.DetailsAdapter;
import com.crysapp.student.db.StudentDatabaseContract.attachmentTable;
import com.crysapp.student.utill.Action;
import fragments.NewEditFragment;
import java.io.IOException;
import java.lang.Override;
import java.util.ArrayList;
import java.util.logging.LogManager;
public class CourseDetailActivity extends AppCompatActivity {
    private ActivityCourseDetailsBinding binding;
    private static final int REQUEST_ATTACH_PERMISSION = 10001;
    private static final int REQUEST_FILE = 100100;
    String title ;
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        title = getIntent().getStringExtra("course");
        binding = ActivityCourseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.detailsPager.setAdapter(new DetailsAdapter(this,title));
        new TabLayoutMediator(binding.tabLayout,binding.detailsPager,
         (tab,pos)->{ 
             switch(pos){
                 case 0 : tab.setText("Classes");break;
                 case 1 : tab.setText("Attachments");break;
                 case 2 : tab.setText("Activities");
             }
            
       }).attach();
        setSupportActionBar(binding.toolbar);
        setTitle(title);
        View.OnClickListener addC = (m) -> { 
                   NewEditFragment nef = NewEditFragment.getInstance();
                   nef.show(getSupportFragmentManager(),"");
                   nef.setText(title);
        };
        View.OnClickListener addA = (m) -> { 
              onAtt();
     
         };
         View.OnClickListener addAc = (m) -> { 
                   NewActivityFragment nef = NewActivityFragment.getInstance();
                   nef.show(getSupportFragmentManager(),"");
                   nef.setText(title);
        };
        binding.detailsPager.setOnScrollChangeListener(null);
       ViewPager2.OnPageChangeCallback ccv;
       binding.detailsPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
           public void onPageScrollStateChanged (int state){
               super.onPageScrollStateChanged(state);
               switch(state){
                    case ViewPager2.SCROLL_STATE_IDLE : binding.fab.show(); break;
                    case ViewPager2.SCROLL_STATE_DRAGGING : binding.fab.hide();
               }
           }
           public void onPageSelected (int position){
               super.onPageSelected(position);
               switch(position){
                   case 0 : binding.fab.setText("Add Class");
                   binding.fab.setOnClickListener(addC);
                   ;break;
                   case 1 : binding.fab.setText("Add Attachments");
                   binding.fab.setOnClickListener(addA);break;
                   case 2 :binding.fab.setText("Add Activity");
                   binding.fab.setOnClickListener(addAc);
               }
           }
       });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch(requestCode){
            case REQUEST_ATTACH_PERMISSION:if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                
                    onAtt()
                   ;break;
            }
        }
       super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    private void onAtt(){
        final int status = ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        if(status!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                                              new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_ATTACH_PERMISSION);return;
        }
       final Intent attach = new Intent(Intent.ACTION_OPEN_DOCUMENT).addCategory(Intent.CATEGORY_OPENABLE).putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true).setType("*/*");
       startActivityForResult(attach,REQUEST_FILE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(resultCode==RESULT_OK&&data!=null){
        switch(requestCode){
               case REQUEST_FILE : 
                  try {
                       onAttachResults(data);
                
                } catch (IOException e) {
                    Toast.makeText(this,"Error "+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }break;
        }}
    }
    private void onAttachResults(Intent d) throws IOException {
        ArrayList<Uri> ints = new ArrayList<Uri>();
        
         if(d.getClipData()!=null){
             for(int i = 0;i<d.getClipData().getItemCount();i++){
                ints.add(d.getClipData().getItemAt(i).getUri());
             }
         }else{
             ints.add(d.getData());
         }
         
        Uri[] iii = new Uri[ints.size()];
        new AttachmentAddet(title,this).exec(ints.toArray(iii));
        
    }
    public static class AttachmentAddet extends Action<Uri[],String> {
        String title;
        final private Context context;

        public AttachmentAddet(String title, Context context) {
            this.title = title;
            this.context = context;
        }
        
        @Override
        protected String doInBackground(Uri[] p1) {
            try{
               for(Uri i:p1){
                   attach(i);
               }
               return "done";
            }catch(Exception e){
                Log.e("file error",e.getClass().getSimpleName()+" "+e.getLocalizedMessage());
                return "error :"+e.getClass()+" "+e.getLocalizedMessage();
            }
       
        }
        private void attach(Uri d) throws IOException{
            Cursor returnCursor =
            context.getContentResolver().query(d, null, null, null, null);
            
            int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            
            int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
            File f = new File(context.getExternalFilesDir(title),UUID.randomUUID().toString());
            final InputStream  input = context.getContentResolver().openInputStream(d);
            final FileOutputStream  output = new FileOutputStream(f);
            try {

                final byte[] by = new byte [10*1024];
                int byteread = 0;
                while((byteread = input.read(by))!=-1){
                    output.write(by,0,byteread);
                }
                output.flush();

            } catch (IOException e) {
                Log.e("file error",e.getClass().getSimpleName()+" "+e.getLocalizedMessage());
            }
            catch (Exception e) {
                Log.e("error",e.getClass().getSimpleName()+" "+e.getLocalizedMessage());
            }
            finally{
                output.close();
                input.close();

            }
            returnCursor.moveToFirst();
            
            StudentCompanionApplication.getDatabase().attachmentDao.insert(new Attachment(
                    title,returnCursor.getString(nameIndex),f,
                    context.getContentResolver().getType(d)
            ));
        }
        protected void doOnForeground(String s){
            
        }
     }
}




