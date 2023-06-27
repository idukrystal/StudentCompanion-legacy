package com.crysart.student.fragments;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.crysart.student.ui.adapters.ClassSelectAdapter;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.LocalDate;
import java.time.YearMonth;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import com.crysapp.student.R;
import java.util.TreeSet;
import java.time.ZonedDateTime;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import java.util.EnumSet;
import java.util.ArrayList;
import java.time.Month;
import com.crysapp.student.StudentCompanionApplication;
import java.util.List;
import android.widget.TextView;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.models.Class;
import java.util.Set;
import androidx.lifecycle.MutableLiveData;
import com.crysart.student.ui.adapters.MonthAdapter;
import android.view.View;
import android.os.Bundle;
import android.app.Dialog;
public class ClassPickerDialogFragment extends DialogFragment implements MonthAdapter.Receiver,ClassSelectAdapter.Receiver{
   private String course;
   private Set days = null;
   private MaterialPickerOnPositiveButtonClickListener selector;
   private TextView texxt;
   private RecyclerView monthlist;
   private ClassSelectAdapter adpt;
   private TextView tv;
   private List<Class> lists = new ArrayList<Class>();
    @Override
public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = requireActivity().getLayoutInflater();
    View v = inflater.inflate(R.layout.dialog_class_picker,null);
    tv = v.findViewById(R.id.month_text);
    RecyclerView rv = (RecyclerView)v.findViewById(R.id.class_list);
    
   MutableLiveData<List<Class>> data; data = new MutableLiveData<List<Class>>();
             StudentCompanionApplication.getDatabase().classDao.updaye(data,course);
    monthlist = (RecyclerView)v.findViewById(R.id.month_list);
    
    adpt = new ClassSelectAdapter(this);
    rv.setAdapter(adpt);
    rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    monthlist.setAdapter(new MonthAdapter(this));
    monthlist.setLayoutManager(new LinearLayoutManager(getActivity()));
    tv.setOnClickListener(view->
        monthlist.setVisibility(View.VISIBLE));
    builder.setView(v);
    data.observeForever(m->{
        lists = m;
        select(-1);
        
        });
    return builder.create();
    

    }
    @java.lang.Override
    public void onSelect(java.time.ZonedDateTime sel) {
       selector.onPositiveButtonClick(sel.toInstant().toEpochMilli());
        dismiss();
    }
    
    public void onMonthReceive(int mnt){
        
        monthlist.setVisibility(View.GONE);
        select(mnt);
        }
    public ClassPickerDialogFragment setTitle(String s,MaterialPickerOnPositiveButtonClickListener sel){
        course =s;
        selector = sel;
        return this;
        }
        private void select(int pos){
            String info = (pos==-1)?"THIS MONTH":Month.values()[pos].toString();
            
            tv.setText(info);
            YearMonth temp = YearMonth.now();
            if(pos!=-1){
                int  year = (pos>(temp.getMonthValue()-1))?temp.getYear():temp.getYear()+1;
            temp = YearMonth.of(year,pos+1);
                }
           if(temp.getYear()!=YearMonth.now().getYear())info=info+" "+temp.getYear();
            tv.setText(info);
            adpt.update(getList(temp,lists));
        }
        public static ArrayList<ZonedDateTime> getList(YearMonth ym,List<Class> days){
 LocalDate first = ym.atDay(1);
   Set<ZonedDateTime> dates = new TreeSet <ZonedDateTime> ( );
   for( Class dayOfWeek : days) {
    
    ZonedDateTime ld = first.with ( TemporalAdjusters.dayOfWeekInMonth ( 1 , dayOfWeek.getDay() ) ).atTime(dayOfWeek.getStart().getHour(),dayOfWeek.getStart().getMinute()).atZone(ZoneId.systemDefault());
        
    do { if(ld.isAfter(ZonedDateTime.now()))
        dates.add ( ld);
        ld = ld.plusWeeks ( 1 );
    } while ( YearMonth.from ( ld ).equals ( ym ) );
    
   
}  
   
   
  return new ArrayList(dates);
         
         }
         
}



























