package com.crysapp.student.ui.widget;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.Exception;
import java.lang.Override;

public class NoKeyboardEditText extends TextInputEditText{
    private OnClickListener listener=null;
    public NoKeyboardEditText(Context context){
        super(context);
    }
    public NoKeyboardEditText(Context context ,AttributeSet attr){
        super(context,attr);
    }
    public NoKeyboardEditText(Context context,AttributeSet attr,int defSyle){
        super(context,attr,defSyle);
        
    }
    @Override
    public void onFocusChanged(boolean b,int i,Rect t){
         if(getOnFocusChangeListener()!=null)
         getOnFocusChangeListener().onFocusChange(this,b);
    }
    public boolean onTouchEvent(MotionEvent me){
     try{    
            if(hasFocus()){
             if(listener!=null){
                 switch(me.getAction()){
                     case MotionEvent.ACTION_DOWN:
                             listener.onClick(this);break;
                      default :super.onTouchEvent(me);
                 }
                 
             }
            return false;
        }
        else{
            return super.onTouchEvent(me);
        }
     }catch(Exception r){
         Log.e("nokeyboard",r.getClass().getName()+"  "+r.getLocalizedMessage());
         return true;
     }
    }
    public void setOnClickListener(OnClickListener on){
        super.setOnClickListener(on);
        this.listener = on ;
    }
    @Override
    public void onSelectionChanged(int i ,int j){
         
    }   
    
}