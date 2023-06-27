package com.crysapp.student.ui;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.crysapp.student.BR;
import com.crysapp.student.R;
import com.crysapp.student.fragments.ClassListFragment;
import com.crysapp.student.models.Attachment;
import com.crysapp.student.models.Class;
import com.crysapp.student.presenter.ItemPresenter;
import com.crysapp.student.ui.adapters.ClassListAdapter;
import java.lang.Exception;
import android.view.View.OnClickListener;
public class ViewHolder<p,i> extends RecyclerView.ViewHolder implements OnClickListener{
   private final ViewDataBinding binding;
   private final ClickInitiator<i> initiator;
   private ImageView image ;
   private static boolean highligted=false;
   private i item ;
   public ViewHolder(ViewDataBinding binding,ItemPresenter presenter,ClickInitiator<i> initt){
       super(binding.getRoot());
       this.binding = binding;
       image = binding.getRoot().findViewById(R.id.images);
       this.initiator =initt;
       binding.getRoot().setOnClickListener(this);
       this.binding.setVariable(BR.presenter,presenter);
   }public ImageView getVimage(){
         return image;
   }
       public void setItem(i item,boolean sel){
          this.item = item;
          if(item instanceof Class){
              ((Class)item).isSelected = sel;
          }
          if(item instanceof Attachment){
              ((Attachment)item).isSelected = sel;
          }
          binding.setVariable(BR.item,item);
       
       }
       public i getItem(){
           
           return item;
       }
       
   public void setPresenter(p presenter){
      binding.setVariable(BR.presenter,presenter);
    }
    public ItemDetailsLookup.ItemDetails<i> getItemCDetails(){
        return new ItemDetailsLookup.ItemDetails<i>(){
           public int  getPosition(){
               return getAbsoluteAdapterPosition();
           }
           public i getSelectionKey(){
               return (getItem());
           }
           public boolean inSelectionHotspot(MotionEvent mev){
               return highligted;
           }
        };
    }
    public static void setHigh(boolean b){
        highligted = b;
    }
    public void onClick(View v){
        if(initiator!= null){
        initiator.initiateClick(getItem());}
  
    }
    public static interface ClickInitiator<T>{
         public void initiateClick(T t);
    }
}