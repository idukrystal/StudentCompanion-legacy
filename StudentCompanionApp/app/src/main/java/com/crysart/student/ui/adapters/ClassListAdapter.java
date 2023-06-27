package com.crysapp.student.ui.adapters;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.LayoutRes;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.crysapp.student.BR;
import com.crysapp.student.R;
import com.crysapp.student.models.Attachment;
import com.crysapp.student.models.Class;
import com.crysapp.student.models.Datable;
import com.crysapp.student.presenter.ItemPresenter;
import com.crysapp.student.ui.ViewHolder;
import com.crysapp.student.utill.Action;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassListAdapter<T> extends ListAdapter<T,ViewHolder<ItemPresenter,T>>{
    private final LayoutInflater inflater;
    @LayoutRes private final int layout;
    private SelectionTracker<T> tracker;
    private int type;
    {  
       setHasStableIds(true);
    }
    public void setTracker(SelectionTracker<T> trx){
       tracker = trx;
    }
    private final ItemPresenter presenter;
    private final ViewHolder.ClickInitiator<T> initiator;
    private Context cv;
    public ClassListAdapter(Context context,int type,@LayoutRes int lay,DiffUtil.ItemCallback<T> callback,ViewHolder.ClickInitiator<T> intt){
        super(callback);
        type = type;
        layout = lay;
        cv = context;
        initiator = intt;
        this.inflater = LayoutInflater.from(context);
        this.presenter=new ItemPresenter(context,type);
    }
  
        @Override
    public ViewHolder<ItemPresenter,T> onCreateViewHolder(ViewGroup p1, int p2) {
        return new ViewHolder<ItemPresenter,T>( 
        DataBindingUtil.inflate(inflater,layout,p1,false),presenter,
        initiator);
        
    }
    @Override
    public long getItemId(int position){
        Log.w("getti id",position+" ");
        return getCurrentList().get(position).hashCode();
    }
    @Override
    public void onBindViewHolder(ViewHolder<ItemPresenter,T> p1, final int p2) {
         p1.setItem(getItem(p2),(tracker!= null)?tracker.isSelected(getItem(p2)): false);
         if(getCurrentList().get(p2) instanceof Datable){
             ((Datable)getCurrentList().get(p2)).setImage(p1.getVimage());
         }
     }
   
   public static class classKeyProvider< T extends Datable> extends ItemKeyProvider<T> {
       private final ClassListAdapter<T> adapter;
       {
             Log.e("classkeyprovider","starting");
       }
       public classKeyProvider(ClassListAdapter<T> adp){
           super(SCOPE_CACHED);
             Log.e("classkeyprovider","constructing");
           adapter = adp ;}
           
       public int  getPosition(T l){
           int i = -1;
             Log.e("classkeyprovider","position");
           for(T clx:adapter.getCurrentList()){
               Log.e("classkeyprovider8","try "+clx.getId()+" "+l.getId()+" "+clx.equals(l));
               if(clx.equals(l)){
               i =adapter.getCurrentList().indexOf(clx);break;}
           };
           Log.e("classkeyprovider","post "+i);
           return i;
       }
       public T getKey(int i){
           
           T  kl = adapter.getItem(i);
             Log.e("classkeyprovider","key "+kl);
           return kl;
           
       }
    }
    
    public  static class ClassLookup<T extends Datable> extends ItemDetailsLookup<T>  {
        private final RecyclerView recycler ;
        {
            Log.e("classLookup","starting");
        }
        public ClassLookup(RecyclerView rv){  
            
             Log.e("classLookup","constructing");
             recycler = rv 
            
            
            ;}
        public  ItemDetails<T>  getItemDetails(MotionEvent mev){
             Log.w("ClassLookup","ongetItemdetails");
             View v = recycler.findChildViewUnder(mev.getX(),mev.getY());
             if(v!=null){
                 return ((ViewHolder)recycler.getChildViewHolder(v)).getItemCDetails();
             }
             return null;
        }
    }
    public static interface ClickInitiator<T>{
         public void initiateClick(T t);
    }
    
}





