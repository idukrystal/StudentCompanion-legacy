package com.crysart.student.ui.adapters;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import com.crysapp.student.R;
import com.crysart.student.ui.adapters.ClassSelectAdapter;
import java.time.ZonedDateTime;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import java.util.ArrayList;
import java.util.List;
import android.view.ViewGroup;
public class ClassSelectAdapter extends RecyclerView.Adapter<ClassSelectAdapter.ViewHolder> {
    private Receiver receiver;
    private String title;
    private ArrayList<ZonedDateTime> list = new ArrayList<ZonedDateTime>();
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private CardView cv;
        ViewHolder(View view){
            super(view);
            cv = view.findViewById(R.id.text_card);
            tv = view.findViewById(R.id.class_text);
            }
            public TextView getTv(){
                return tv;
                }
                public CardView getCard(){
                  return cv;
                 }
    }
    public ViewHolder onCreateViewHolder(ViewGroup container,int position){
       
        return new ViewHolder(LayoutInflater.from(container.getContext()).inflate(R.layout.card_select_class,container,false));
    }
    public void onBindViewHolder(ViewHolder holder ,int position){
        ZonedDateTime ld = list.get(position);
        holder.getTv().setText(ld.getDayOfWeek().toString()+"-"+ld.getDayOfMonth()+"-"+ld.getHour()+":"+ld.getMinute());
        holder.getCard().setOnClickListener(m->receiver.onSelect(list.get(position)));
        }
    public int getItemCount(){
        return list.size();
    }
    public void  update(List<ZonedDateTime> al){
        list.clear();
        list.addAll(al);
        notifyDataSetChanged();
    }
    public ClassSelectAdapter(Receiver r){
        receiver = r;
        }
    public static interface Receiver{
        public void onSelect(ZonedDateTime sel);
        }
}





