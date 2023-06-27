package com.crysart.student.models;
import com.crysapp.student.models.Class;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.lang.Override;
import com.crysapp.student.fragments.NewActivityFragment;
public  class Activities extends Class{
   private  boolean duringClass;
   public static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEE', 'dd' 'LLL' 'uuuu");
   private byte frequency = 0;
   private String title="";
   private LocalDate date = null;
   private LocalTime time = null;
   private LocalDateTime date2 =null;
   private Class.classId claId=null;
   public Activities(String t,String d1,String t1,boolean b ,byte f){
       title = t;
       setDate(d1);
       setTime(t1);
       duringClass = b;
       frequency = f;
       var  x = 0b100;
   }
   public String getTitle(){
       return title;
   }
   public void setTitle(String title){
       this.title=title;
   }
   public boolean getDuringClass(){
       return duringClass;
   }
   public void setDuringClass(boolean duringClass){
        duringClass=duringClass;
       }
   public void setDate(String date){
        this.date= LocalDate.parse(date);
      }
  public String getDate(){
      return date.format(dayFormatter);
      }
      public byte getFreq(){
          return frequency;
          }
  public String getShortDate(){
         return date.toString();
      }
   public void setTime(String date){
        time = LocalTime.parse(date);
      }
  public String getTime(){
      return String.format("%02d",time.getHour())+":"+String.format("%02d",time.getMinute());
      }     
    public String getFrequency(){
        return NewActivityFragment.opt1[frequency];
    }
    public void setFrequency(String s){
        frequency = (byte)Arrays.asList(NewActivityFragment.opt1).indexOf(s);
    }
}















