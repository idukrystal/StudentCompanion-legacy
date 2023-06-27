package com.crysapp.student.ui.adapters;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.databinding.adapters.LinearLayoutBindingAdapter;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.crysapp.student.R;
import com.crysapp.student.StudentCompanionApplication;
import com.crysapp.student.models.Class;
import com.crysapp.student.ui.ViewHolder;
import fragments.NewEditFragment;
import java.util.ArrayList;
import java.util.List;


public class ListInListAdapter extends ListAdapter<Pair<String,List<Class>>,ListInListAdapter.ViewHolder>  {
   private SelectionTracker.SelectionObserver<Class> obs;
   private ActionMode actionmode ;
   private List<SelectionTracker<Class>> tracks = new ArrayList<SelectionTracker<Class>>();
   public  static class ViewHolder extends RecyclerView.ViewHolder{
     private TextView title;
     private RecyclerView content;
     private ClassListAdapter adapt;
     private View v;
       public ViewHolder(View v){
           super(v);
           this.v = v;
           title = (TextView) v.findViewById(R.id.title);
           content = (RecyclerView)v.findViewById(R.id.content);
           adapt = new ClassListAdapter(v.getContext(),1,R.layout.card_contents_table,Class.getCallback(),null);
           content.setAdapter(adapt);
           content.setLayoutManager(new GridLayoutManager(v.getContext(),2));
       }
   }
   
   public void onBindViewHolder(ViewHolder vh,int position){
      vh.title.setText(getCurrentList().get(position).first);
      vh.adapt.submitList(getCurrentList().get(position).second);
   }
   public ViewHolder onCreateViewHolder(ViewGroup container,int position){
        ViewHolder vhx = new ViewHolder(LayoutInflater.from(container.getContext()).inflate(R.layout.card_table_content,container,false));
        SelectionTracker<Class> ttt = new SelectionTracker.Builder<Class>(
         "mysel",
         vhx.content,
         new ClassListAdapter.classKeyProvider(vhx.adapt),
         new ClassListAdapter.ClassLookup(vhx.content),
         StorageStrategy.createParcelableStorage(Class.class)
         ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build();
         vhx.adapt.setTracker(ttt);
         tracks.add(ttt);
         ttt.addObserver(obs);
         return vhx;
   }
   public long getItemId(int position){
       return position;
   }
   public ListInListAdapter(AppCompatActivity act){
       super(new DiffUtil.ItemCallback<Pair<String,List<Class>>>(){
         public boolean areContentsTheSame(Pair<String,List<Class>> p1,Pair<String,List<Class>> p2){
             return p1.second.equals(p2.second);
         }
        public boolean areItemsTheSame(Pair<String,List<Class>> p1,Pair<String,List<Class>> p2){
            return p1.first.equals(p2.first);
        }
       });
       ActionMode.Callback acb =  new ActionMode.Callback(){
                  
                  public boolean onCreateActionMode(ActionMode mode,Menu menu){
                  act.getMenuInflater().inflate(R.menu.cotextual_attachment_menu,menu);
                 actionmode =mode;
                 return true;
      }
       public void onDestroyActionMode(ActionMode mode){
                
                 actionmode = null; 
                 com.crysapp.student.ui.ViewHolder.setHigh(false);
                 tracks.forEach(x->x.clearSelection());
       }
       public boolean onActionItemClicked(ActionMode mode,MenuItem item){
               ArrayList<Class> send = getSelection();
               tracks.forEach(x->x.clearSelection());
                
               switch(item.getItemId()){
                   case R.id.del :
                        StudentCompanionApplication.getDatabase().classDao.Delete(send);
                        
                         return true;
                  default : 
                        NewEditFragment.getInstance(send.get(0)).show(act.getSupportFragmentManager(),"dd");
                       return true;
               }
                 
                 
       }
        public boolean onPrepareActionMode(ActionMode mode,Menu menu){
                 return false;
        }
         
         };
       obs = new SelectionTracker.SelectionObserver<Class>(){
              public void onSelectionChanged(){
                  super.onSelectionChanged();
                 if(getSelection().size()==0){
                     com.crysapp.student.ui.ViewHolder.setHigh(false);
                     if(actionmode!=null)actionmode.finish();
                     
                 }
                 else{
                     com.crysapp.student.ui.ViewHolder.setHigh(true);
                     
                   if(actionmode==null) act.startSupportActionMode(acb);
                   actionmode.setTitle(getSelection().size()+" Selected");
                   if(getSelection().size()>1){
                       actionmode.getMenu().getItem(0).setVisible(false);
                       
                   }
                   else{
                       actionmode.getMenu().getItem(0).setVisible(true);
                   }
                 }
              
              }
              @Override
              public void onSelectionCleared(){
                  super.onSelectionCleared();
                  tracks.forEach(v->v.clearSelection());
                  com.crysapp.student.ui.ViewHolder.setHigh(false);
                  if(actionmode!=null)  actionmode.finish();
                
              }
          };
   }
   @Override
   public int getItemCount(){
       Log.e("listinlist iems",getCurrentList().size()+"");
       return super.getItemCount();
   }
   private ArrayList<Class> getSelection(){
       ArrayList<Class> list = new ArrayList<Class>();
       tracks.forEach(v->
            v.getSelection().forEach(t->
                 list.add(t)
            )
       );
       return list;
   }
 
}


