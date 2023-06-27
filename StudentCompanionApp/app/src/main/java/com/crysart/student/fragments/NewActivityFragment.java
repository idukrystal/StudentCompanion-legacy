package com.crysapp.student.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.crysapp.student.R;
import com.crysapp.student.StudentCompanionApplication;
import com.crysart.student.models.Activities;
import android.content.DialogInterface.OnClickListener;
import com.crysart.student.ui.OptionsSelectWrapper;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.crysapp.student.databinding.AddActivityCardBinding;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.TimePickerWrapper;
import com.crysart.student.fragments.ClassPickerDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.DateValidatorPointForward;
import java.lang.Override;
import android.widget.Toast;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.*;

public class NewActivityFragment extends BottomSheetDialogFragment implements MaterialPickerOnPositiveButtonClickListener<Long> ,OnClickListener,View.OnClickListener,View.OnFocusChangeListener{
    private AddActivityCardBinding binding;
    private String title;
    public static String[] opt1 = {"Do not Repeat","Daily","Weekly","Custom"};
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saved){
        binding = AddActivityCardBinding.inflate(inflater);
       Activities a = new Activities("Test",LocalDate.now().toString(),LocalTime.now().toString(),true,(byte)2);
       binding.setTemp(a);
        binding.saveb.setOnClickListener(this);
        
        binding.dayInputField.setOnFocusChangeListener(this);
        return binding.getRoot();
    }
    @Override
    public void onPositiveButtonClick(Long l){
        LocalDateTime sel = LocalDateTime.ofInstant(Instant.ofEpochMilli(l),ZoneId.systemDefault());
        binding.getTemp().setDate(sel.toLocalDate().toString());
        binding.dayInputField.setText(sel.toLocalDate().format(Activities.dayFormatter));
        binding.startInputField.setText(String.format("%02d",sel.getHour())+":"+String.format("%02d",sel.getMinute()));
    }
    
    @Override
    public void  onViewCreated(View view,Bundle saved){
         binding.dayInputField.setOnClickListener(this);
            new TimePickerWrapper(getFragmentManager()).wrap(binding.startInputField);
            binding.freqInputField.setOnClickListener(this);
            binding.freqInputField.setOnFocusChangeListener(this);
          
     }
    @java.lang.Override
    public void onClick(android.content.DialogInterface arg0, int arg1) {
      binding.freqInputField.setText(opt1[arg1]);
    }
    @java.lang.Override
    public void onFocusChange(android.view.View arg0, boolean arg1) {
        if(arg1){
             switch(arg0.getId()){
            case R.id.freq_input_field:
                 OptionsSelectWrapper.showOptions(opt1,getActivity(),this,1);break;
            case R.id.day_input_field:  
                  pickClass();break;
            }
            
            }
    }
    
    public void onClick(View v){
        switch(v.getId()){
            case R.id.freq_input_field:
                 OptionsSelectWrapper.showOptions(opt1,getActivity(),this,1);break;
            case R.id.day_input_field:  
                  pickClass();break;
            case R.id.saveb:
            Activities a = binding.getTemp();
            StudentCompanionApplication.getDatabase().activityDao.insert(a);
            }
        }
    public static NewActivityFragment getInstance(){
        return  new NewActivityFragment();
    }
    public void setText(String title){
      this.title=title;
    }
    public void pickClass(){
        if(binding.classSwitch.isChecked()){
         new ClassPickerDialogFragment().setTitle(title,this).show(getFragmentManager()
          ,"");}
          else{
           MaterialDatePicker picker = MaterialDatePicker.Builder.datePicker().setTitleText("Select Activity Date").setCalendarConstraints(
            new CalendarConstraints.Builder().setStart(ZonedDateTime.now().toInstant().toEpochMilli()).setEnd(ZonedDateTime.now().plusYears(1).toInstant().toEpochMilli()).setValidator(DateValidatorPointForward.now()).build()
           ).build();
           picker.addOnPositiveButtonClickListener(this);
           picker.show(getFragmentManager(),"DATE_PICKER");
          }
    }
}




























