package com.crysapp.student.presenter;
import android.content.Context;
import android.view.View;
import com.crysapp.student.models.Class;

public class ItemPresenter {
    private int type;
    public ItemPresenter(Context context,int type){
        this.type =type;
    }
    public String getSecondaryText(Class  c){
        switch(type){
            case 3 : return c.getDay().toString();
            default : return c.getStart().toString()+" - "+c.getStop().toString();
        }
    }
    public String getPrimaryText(Class c){
        switch(type){
            case 4 : return c.getDay().toString();
            default : return c.getCourse();
        }
    }
    public int ifSelected(Class c){
        if(c.isSelected){
            return View.GONE;
        }
        return View.VISIBLE;
    }
    public String getCurrentHeader(Class c){
        if(c.now()) return "NOW";
        if(c.isNext()) return "UPNEXT";
        return "ANOTHER DAY";
    }
    public String getTimming(Class c){
        if(c.now()) return "Ends by "+c.getStop().toString();
        if(c.isNext()) return "Starts by "+c.getStart().toString();
        return "On"+c.getDay().toString();
    }
    public ItemPresenter(){
        
    }
}