package com.crysapp.student.models;
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

@Entity(indices = @Index("courseId"))
public class Class implements Parcelable,Comparable<Class>,Datable{
    @PrimaryKey(autoGenerate = true)
    private long id;
    private classId claxid;
    public boolean isSelected = false;
    public static final int BAD_TIME = 10101;
    public static final int NULL_DAY = 10001;
    public static final int VALID = 11011;
    public static final int Today = 4;
    public static final int Tommorow = 5;
    public static final int Other = 6;
    public long courseId;
    private String code;
    private DayOfWeek day ;
    private LocalTime start;
    private LocalTime stop;
    boolean remind;
    public Class(){}
    @Ignore public Class(String cd,int d,String s,String st,int rd){
        if(d> -1)day = DayOfWeek.values()[d];
        code = cd;
        start = LocalTime.parse(s);
        stop = LocalTime.parse(st);
        remind = (rd==1)?true:false;
    }
    public void setImage(ImageView vv){
        
    }
    @Ignore public Class(long id,String cd,int d,String s,String st,int rd){
        if(d> -1)day = DayOfWeek.values()[d];
        code = cd;
        this.id = id;
        start = LocalTime.parse(s);
        stop = LocalTime.parse(st);
        remind = (rd==1)?true:false;
    }
    public void setCourse(String course) {
        this.code = course;
        
    }

    public String getCourse() {
        return code;
    }
    public long getId(){
        return id;
    }
    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public DayOfWeek getDay() {
        
        return day;
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
    protected Class(Parcel in){
        Log.d("parcelling ","saving from parcel");
        id = in.readLong();
        code = in.readString();
        day = DayOfWeek.values()[in.readInt()];
        remind = in.readBoolean();
        start = LocalTime.parse(in.readString());
        stop = LocalTime.parse(in.readString());
    }
    @Override
    public void writeToParcel(final Parcel dest,final int flag){
        Log.d("parcelling ","writting to parcel");
        dest.writeLong(id);
        dest.writeString(code);
        dest.writeInt(day.getValue());
        dest.writeBoolean(remind);
        dest.writeString(start.toString());
        dest.writeString(stop.toString());
        Log.d("parcelling "," ending writting to parcel");
    }
   @Override
    public int describeContents(){
        Log.d("parcelling "," describing contents  to parcel");
        return 0;
     }
    public static final Creator<Class> CREATOR = new Creator<Class>(){
        @Override
        public Class[] newArray(int size){
            Log.d("parcelling ","saving from parcel");
             return new Class[size];
         }
         @Override
         public Class createFromParcel(Parcel in){
             Log.d("parcelling ","saving from parcel");
             return new Class(in);
          }
    };
    public int isValid(){
        int i ;
        if(day==null)return NULL_DAY;
        else if(start.compareTo(stop)!=-1) return BAD_TIME;
        else return VALID;
    }
    public  int getDayCat(){
        LocalDate today = LocalDate.now();
        if(getDay() == today.getDayOfWeek()){
            return Today;
        }
        else if(getDay() == today.getDayOfWeek().plus(1)){
            return Tommorow;
        }
        else{
            return Other;
            
        }
    }
    @Override
    public boolean equals(Object obj){
         if(! (obj instanceof Class)) return false;
         else if(getId()==((Class)obj).getId()&&
                 getCourse().equals(((Class)obj).getCourse())&&
                 getDay().equals(((Class)obj).getDay())&&
                 getStart().equals(((Class)obj).getStart())&&
                 getStop().equals(((Class)obj).getStop())&&
                 isRemind() == (((Class)obj).isRemind())) return true;
         else return false;
    }
    public int compareTo(Class c){
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
    }
    public boolean now(){
        LocalDateTime now = LocalDateTime.now();
        if(getDay().equals(now.getDayOfWeek())){
            if(now.toLocalTime().isAfter(getStart())&&now.toLocalTime().isBefore(getStop())){
                return true;
            }
        }
        return false;
    }
    public boolean isNext(){
        LocalDateTime now = LocalDateTime.now();
        if(getDay().equals(now.getDayOfWeek())){
            if(now.toLocalTime().isBefore(getStart())){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode(){
        return ((int)getId());
    }
    public long generateUniqueId(){
        //try{ 
           String all = ""+getCourse().hashCode()+getId()+getDay().getValue()+getStart().getHour()+getStart().getMinute()+getStop().getHour()+getStop().getMinute()+(isRemind()?1:0);
       return Long.parseLong(all);
       // }
      /* catch(Exception e){
           Log.e("generating id",e.getClass().getSimpleName()+" "+e.getLocalizedMessage());
           return getId();
       }*/
    }
    public long stringToCode(String s){
        String st ="";
        char[] x = s.toCharArray();
        for(char c : x ){
            long a = c;
            st += a ;
        }
        return Long.parseLong(st);
    }
    public  static class callback extends DiffUtil.ItemCallback<Class>{
       public boolean areContentsTheSame(Class p1,Class p2){
           boolean a = p1.equals(p2);
            
            Log.d("callback c "+p1.getId()+" - "+p2.getId(),a+"");
             return a;
       }
       public boolean areItemsTheSame(Class p1,Class p2){
         boolean a = p1.getId() == p2.getId();
         Log.d("calback i "+p1.getId()+" - "+p2.getId(),a+"");
           
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
    public static class classId {
       private final String className;
       private final long classId;
       private classId(Class c){
           className = c.getCourse();
           classId = c.getId();
       }
       public String getClassName(){
           return className;
       }
       public long getClassId(){
           return classId;
       }
       public static classId of(String s){
           return new classId(s);
       }
       private classId(String s){
            className = "";
            classId = 111;
       }
   }
   public classId classId(){
       claxid = (claxid!=null)?claxid:new classId(this);
       return claxid;
   }
}






