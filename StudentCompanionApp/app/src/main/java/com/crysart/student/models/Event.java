package com.crysart.student.models;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import java.lang.Comparable;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public abstract class Event implements Parcelable,Comparable<Event>{
    private long id;
    public boolean isSelected = false;
    public static final int BAD_TIME = 10101;
    public static final int NULL_DATE = 10001;
    public static final int VALID = 11010;
    private String title;
    private LocalTime start;
    private LocalTime stop;
    private LocalDate startDate;
    private LocalDate stopDate;
    boolean remind;
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
    public long getId(){
        return id;
    }
    public LocalDate getStartDate(){
        return startDate;
    }
    public void setStart(LocalTime start) {
        this.start = start;
    }
    public LocalTime getStart() {
        return start;
    }
    public void setStop(LocalTime stop) {
        this.stop = stop;
    }
    public LocalTime getStop() {
        return stop;
    }

    public void setRemind(boolean remind) {
        this.remind = remind;
    }
    public boolean isRemind() {
        return remind;
    }
    protected Event(Parcel in){
        id = in.readLong();
        title = in.readString();
        remind = in.readBoolean();
        start = LocalTime.parse(in.readString());
        stop = LocalTime.parse(in.readString());
    }
    @Override
    public void writeToParcel(final Parcel dest,final int flag){
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeBoolean(remind);
        dest.writeString(start.toString());
        dest.writeString(stop.toString());
    }
   @Override
    public int describeContents(){
        return 0;
     }
    
    public int isValid(){
        int i ;
        if(startDate==null)return NULL_DATE;
        else if(start.compareTo(stop)!=-1) return BAD_TIME;
        else return VALID;
    }
    @Override
    public boolean equals(Object obj){
         if(! (obj instanceof Class)) return false;
         else if(getId()==((Event)obj).getId()&&
                 getTitle().equals(((Event)obj).getTitle())&&
                 getStartDate().equals(((Event)obj).getStartDate())&&
                 getStart().equals(((Event)obj).getStart())&&
                 getStop().equals(((Event)obj).getStop())&&
                 isRemind() == (((Event)obj).isRemind())) return true;
         else return false;
    }
    /**public int compareTo(Class c){
        LocalDateTime now = LocalDateTime.now();
       
        if(getDay().equals(c.getDay())){
            if(getDay().equals(now.getDayOfWeek())){
                 return compareRef(getStart(),c.getStart(),LocalTime.now());
            }else{
                return getStart().compareTo(c.getStart());
            }
        }else{
            if((((getDay().compareTo(now.getDayOfWeek()))>0)&&((c.getDay().compareTo(now.getDayOfWeek()))>0))||(((getDay().compareTo(now.getDayOfWeek()))<0)&&((c.getDay().compareTo(now.getDayOfWeek()))<0))){
                return getDay().compareTo(c.getDay());
            }else{
                return compareRef(getDay(),c.getDay(),now.getDayOfWeek());
            }
        }
    }
    public int compareRef(LocalTime compare,LocalTime compareto,LocalTime withrefto){
        if(compare==null&&compareto==null){
            return 0;
        }
        if(compare==null){
            return 1;
        }
        if(compareto==null){
            return -1;
        }
        ArrayList<LocalTime> sorter = new ArrayList<LocalTime>();
        ArrayList<LocalTime> sorted = new ArrayList<LocalTime>();
        sorter.add(compare);
        sorter.add(compareto);
        sorter.add(withrefto);

        Collections.sort(sorter);
        int j =0;
        if(getStart().equals(null)){
            Log.i("its -","null");
            return -1;}
        for(int i= 0;i<sorter.size();i++){
            if(sorter.indexOf(withrefto)+i<sorter.size()){
                sorted.add(i,sorter.get(sorter.indexOf(withrefto)+i));
            }
            else{
                sorted.add(i,sorter.get(j));
                j++;}
        }
        return new Integer(sorted.indexOf(compare)).compareTo(sorted.indexOf(compareto));
    }
    public int compareRef(DayOfWeek compare,DayOfWeek compareto,DayOfWeek withrefto){
        if(compare==null&&compareto==null){
            return 0;
        }
        if(compare==null){
            return 1;
        }
        if(compareto==null){
            return -1;
        }
        ArrayList<DayOfWeek> sorter = new ArrayList<DayOfWeek>();
        ArrayList<DayOfWeek> sorted = new ArrayList<DayOfWeek>();
        sorter.add(compare);
        sorter.add(compareto);
        sorter.add(withrefto);

        Collections.sort(sorter);
        int j =0;
        if(getStart().equals(null)){
            Log.i("its -","null");
            return -1;}
        for(int i= 0;i<sorter.size();i++){
            if(sorter.indexOf(withrefto)+i<sorter.size()){
                sorted.add(i,sorter.get(sorter.indexOf(withrefto)+i));
            }
            else{
                sorted.add(i,sorter.get(j));
                j++;}
        }
        return new Integer(sorted.indexOf(compare)).compareTo(sorted.indexOf(compareto));
    }**/
    public boolean now(){
        LocalDateTime now = LocalDateTime.now();
        if(getStartDate().equals(now.toLocalDate())){
            if(now.toLocalTime().isAfter(getStart())&&now.toLocalTime().isBefore(getStop())){
                return true;
            }
        }
        return false;
    }
    public boolean isNext(){
        LocalDateTime now = LocalDateTime.now();
        if(getStartDate().equals(now.toLocalDate())){
            if(now.toLocalTime().isBefore(getStart())){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode(){
        return stringToCode(title+startDate.toString());
    }
    
    public int stringToCode(String s){
        String st ="";
        char[] x = s.toCharArray();
        for(char c : x ){
            int a = c;
            st += a ;
        }
        return Integer.parseInt(st);
    }
    public  static class callback extends DiffUtil.ItemCallback<Event>{
       public boolean areContentsTheSame(Event p1,Event p2){
           boolean a = p1.equals(p2);
             return a;
       }
       public boolean areItemsTheSame(Event p1,Event p2){
         boolean a = p1.getId() == p2.getId();
           return  a;
       }
   }
   public static callback getCallback(){
       return call;
   }
   private static callback call;
   static{
       call = new callback();
   }
   
}

