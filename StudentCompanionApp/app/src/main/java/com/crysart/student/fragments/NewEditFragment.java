package fragments;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.crysapp.student.HomeActivity;
import com.crysapp.student.R;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.databinding.FragmentNewEditBinding;
import com.crysapp.student.db.StudentDatabaseHelper;
import com.crysapp.student.fragments.TimePickerFragment;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.TimePickerWrapper;
import com.crysapp.student.utill.ClassAlarmReceiver;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.Exception;
import java.lang.Override;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;

import android.widget.AdapterView.OnItemSelectedListener;
import java.util.Calendar;
public class NewEditFragment extends BottomSheetDialogFragment implements AdapterView.OnItemClickListener{
    public static final String[] d = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
    private String s ;
    private  TextInputEditText code;
    private static boolean active = false;
    private FragmentNewEditBinding binding ;
    private  TextInputEditText start;
    private TextView warning;
    private TextInputEditText stop;
    private  AutoCompleteTextView day;
    private  CheckBox remind;
    private  Class item;
    private int selectedDay = -1;
    ArrayAdapter<String> adapter;
    public static NewEditFragment getInstance(){
        return new NewEditFragment();
    }
    public static NewEditFragment getInstance(Class c){
        NewEditFragment temp =  new NewEditFragment();
        temp.setItem(c);
        return temp;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // get the views and attach the listener
         
         binding = FragmentNewEditBinding.inflate(inflater);
         adapter = new ArrayAdapter<String>(getContext(),R.layout.texted,d);
         
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(View v,Bundle saved){
           code = binding.codeInputField;
           code.setText(s);
           start = binding.startInputField;
           stop = binding.stopInputField;
           warning = binding.war;
           remind = binding.remind;
           day= binding.dayInputField;
           final  TimePickerWrapper timeWrapper = new TimePickerWrapper(getParentFragmentManager());
           day.setAdapter(adapter);
           day.setOnItemClickListener(this);
           timeWrapper.wrap(start);
           timeWrapper.wrap(stop);
           binding.saveb.setOnClickListener(p->{
                      tryToAdd(createClass());});
           if(item != null){
              code.setText(item.getCourse());
              day.setText(item.getDay().toString(),false);
              day.setListSelection(item.getDay().getValue()-1);
              start.setText(item.getStart().toString());
              stop.setText(item.getStop().toString());
              remind.setChecked(item.isRemind());
           }
    }
    private Class createClass(){
       return new Class(code.getText().toString()
                        ,(Arrays.asList(d).contains(day.getText().toString()))?Arrays.asList(d).indexOf(day.getText().toString()):-1,
                        start.getText().toString(),
                        stop.getText().toString(),
                        ((remind.isChecked())?1:0)
                        );
    }
    public void setText(String nm){
        s = nm;
    }
    @Override
    public void onItemClick(AdapterView v,View b,int i,long id){
         Log.e("day selected",i+"");
         selectedDay = i;
    }
    private void tryToAdd(Class clax){
        binding.dayInput.setError(null);
        binding.startInput.setError(null);
        binding.stopInput.setError(null);
        switch(clax.isValid()){
            case Class.NULL_DAY:binding.dayInput.setError("Pleass select a day") ;return;
            case Class.BAD_TIME: binding.startInput.setError("A class musst start"); binding.stopInput.setError("before it stops");return;
            case Class.VALID:if(item!= null) StudentCompanionApplication.getDatabase().classDao.Edit(clax,item.getId());
                 else  StudentCompanionApplication.getDatabase().classDao.insert(clax);  
                 if(clax.isRemind()){
                 long first = getFirstAlarm(clax);
                 AlarmManager am = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                 Intent intent = new Intent(getActivity(),ClassAlarmReceiver.class );
                 intent.putExtra(ClassAlarmReceiver.Class_Code,clax.getCourse());
                 intent.putExtra(ClassAlarmReceiver.Time_Code,first);
                 intent.putExtra(ClassAlarmReceiver.Start_Code,clax.getStart().getHour()+"/");
                 intent.putExtra(ClassAlarmReceiver.Stop_Code,clax.getCourse());
                 PendingIntent alrmint = PendingIntent.getBroadcast(getActivity(),0,intent,0);
                // am.setRepeating(AlarmManager.RTC_WAKEUP,gseetFirstAlarm(clax)
                //,1000*60*60*24*7,alrmint);
                 am.setWindow(AlarmManager.RTC_WAKEUP,first,0,alrmint);
                
                 }
                
            break;
        }
        dismiss();
    }
    public void setItem(Class c){
        this.item = c ;
       
    }
    @Override
    public void show(FragmentManager fm,String s){
        if(!active) {
            active = true;
            super.show(fm,s);
            
       }
    }
   public void  onDestroy(){
        active = false;
        super.onDestroy();
    }
    public static  long  getFirstAlarm(Class clx){
        LocalDateTime ldt = LocalDateTime.now();   
        ldt = ldt.with(TemporalAdjusters.nextOrSame(clx.getDay()));
        ldt = ldt.with(clx.getStart());
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }   
}

