package com.crysapp.student.models;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Course implements Parcelable{
    @PrimaryKey(autoGenerate = true)
    public long id;
    @Ignore List<Class> classes = new ArrayList<>();
    String code;
    String title;
    int unit;
    public Course(){}
    public void setCode(String code){
       this.code = code;  
    }
    public String getCode(){
        return code;
    }
    public void setTitle(String title){
       this.title = title;  
    }
      public String getTitle(){
        return title;
    }
    public void setUnit(int unit){
       this.unit = unit;  
    }
      public int getUnit(){
        return unit;
    }
    public void addClass(final Class clax){
        if(clax!=null&&!classes.contains(clax)){
            classes.add(clax);
        }
    }
    public void removeClasd(final Class clax){
        classes.remove(clax);
    }
    public List<Class> getClasses(){
        return Collections.unmodifiableList(classes);
    }
    protected Course(final Parcel in){
        id = in.readLong();
        code = in.readString();
        unit = in.readInt();
        title = in.readString();
        in.readTypedList(classes,Class.CREATOR);
    }
    
     @Override
    public void writeToParcel(final Parcel dest ,final int flag){
        dest.writeLong(id);
        dest.writeString(code);
        dest.writeInt(unit);
        dest.writeString(title);
        dest.writeTypedList(classes);
    }
    @Override
    public int describeContents(){
        return 0;
    }
    
    
    public static final Creator<Course> CREATOR = new Creator<Course>(){
        @Override
        public Course[] newArray(int size){
            return new Course[size];
        }
        @Override
        public Course createFromParcel(Parcel in){
            return new Course(in);
        }
    };
}