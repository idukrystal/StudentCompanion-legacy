package com.crysapp.student.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import androidx.recyclerview.widget.DiffUtil;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import com.crysapp.student.utill.Action;
import java.io.File;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;

public class Attachment implements Datable,Parcelable{
    public Action.Thumbs generator;
    private long id;
    private String code;
    private ImageView iiv;
    private String name;
    private File file;
    private String type;
    public  boolean isSelected;
    @Override
    public void setImage(ImageView vv){
        iiv = vv;
      if(generator==null){
          generator = new Action.Thumbs();
      }
      generator.setUp(vv).exec(this);
    }
    public Attachment(long id, String code, String name,String file, String type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.file = new File(file);
        this.type = type;
    }
    public Attachment(String code, String name, File file, String type) {
        this.code = code;
        this.name = name;
        this.file = file;
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public File getFile() {
        return file;
    }


    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }



    public long getId() {
        return this.id;
    }
    
    public  static class Callback extends DiffUtil.ItemCallback<Attachment>{
       public boolean areContentsTheSame(Attachment p1,Attachment p2){
             return true;
       }
       public boolean areItemsTheSame(Attachment p1,Attachment p2){
         boolean a = p1.getId() == p2.getId();
         return  a;
       }
   }
   public void writeToParcel(Parcel parcel,final int i){
       parcel.writeLong(id);
       parcel.writeString(code);
       parcel.writeString(name);
       parcel.writeString(file.getPath());
       parcel.writeString(type);
       
   }
   protected Attachment(Parcel in){
       id = in.readLong();
       code = in.readString();
       name = in.readString();
       file = new File(in.readString());
       type = in.readString();
   }
   public int describeContents(){
       return 0;
   }
   public static final Creator<Attachment> CREATOR = new Creator<Attachment>(){
        @Override
        public Attachment[] newArray(int size){
            
             return new Attachment[size];
         }
         @Override
         public Attachment createFromParcel(Parcel in){
             
             return new Attachment(in);
          }
    };
    @Override
    public boolean equals(Object obs){
        if(! (obs instanceof Attachment)){
            return false;
        }
        return(id==((Attachment)obs).getId()&&file.getAbsolutePath().equals(((Attachment)obs).getFile().getPath())&&code.equals(((Attachment)obs).getCode()));
    }
    @Override
    public int hashCode(){
        return ((int)getId());
    }
}
