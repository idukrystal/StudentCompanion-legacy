package com.crysapp.student.utill;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import java.lang.Runnable;
import java.util.ArrayList;

public class SingleEventLiveData<T> {
    protected T t;
    protected ArrayList<Pair<Observer<T>,LifecycleOwner>>observers = new ArrayList<Pair<Observer<T>,LifecycleOwner>>();
    
    public void observe(LifecycleOwner own,Observer<T> ob){
        observers.add(new Pair<Observer<T>,LifecycleOwner>(ob,own));
    }
    public T getValue(){
        return t;
    }
    public static class MutableSingleEventLiveData<T> extends SingleEventLiveData<T>{
         public void setValue(T t){
         observers.forEach(o->{
            if(o.second.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)&&!o.second.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.DESTROYED))o.first.onChanged(t);
        });
    }
    public void postValue(T t){
        this.t = t;
        new Handler(Looper.getMainLooper()).post(new Runnable(){
            public void run(){
                observers.forEach(o->{
            if(o.second.getLifecycle().getCurrentState().equals(Lifecycle.State.RESUMED)//&&o.second.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.DESTROYED)
            )o.first.onChanged(t);
        });
            }
        });
    }
  }
}

