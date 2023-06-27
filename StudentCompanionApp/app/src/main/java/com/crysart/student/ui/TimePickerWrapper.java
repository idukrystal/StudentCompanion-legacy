package com.crysapp.student.ui;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View;
import android.widget.TimePicker;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.crysart.student.fragments.ClassPickerDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import java.lang.Override;
import java.sql.Time;
import java.time.Clock;
import java.time.LocalTime;
public class TimePickerWrapper implements OnClickListener,OnFocusChangeListener,TimePickerDialog.OnTimeSetListener{
     private TextInputEditText currentField;
    private FragmentManager manager;
    private MaterialTimePicker.Builder picker;
    private void setTime(View v){
       
       currentField = (TextInputEditText) v;
       MaterialTimePicker pp = picker.setHour(LocalTime.parse(currentField.getText()).getHour()).setTimeFormat(TimeFormat.CLOCK_24H).setMinute(LocalTime.parse(currentField.getText()).getMinute()).build();
       pp.addOnPositiveButtonClickListener(h ->setMatTime(pp));
       currentField.setTag("");
       pp.show(manager,currentField.getTag().toString());
    }
    @Override
    public void onTimeSet(TimePicker p,int h,int m){
        if(currentField!=null){currentField.setText(String.format("%02d",h)+":"+String.format("%02d",m));}
    }
    public void setMatTime(MaterialTimePicker pp){
        if(currentField!=null){currentField.setText(String.format("%02d",(pp).getHour())+":"+String.format("%02d",pp.getMinute()));}
    }
    @Override
    public void onClick(View v){
        
        setTime(v);
    }
    @Override
    public void onFocusChange(View v,boolean focused){
        if(focused){
            setTime(v);
        }
    }
    public TimePickerWrapper(FragmentManager mn){
        picker = new MaterialTimePicker.Builder();
        manager = mn;
    }
    public void wrap(TextInputEditText edit){
        edit.setOnClickListener(this);
        edit.setOnFocusChangeListener(this);
    }
    public  static class TimePickerFragment extends DialogFragment{
        private LocalTime time;
        TimePickerDialog.OnTimeSetListener listener;
        @Override
        public Dialog onCreateDialog(Bundle saved){
            //return new MaterialTimePicker.Builder().build().show();
            return new TimePickerDialog(getActivity(),listener,time.getHour(),time.getMinute(),DateFormat.is24HourFormat(getActivity()));
        
         }
       public void setTime(final LocalTime t,TimePickerDialog.OnTimeSetListener listener){
          this.time = t;
          this.listener = listener;
       }
       
    }
}







