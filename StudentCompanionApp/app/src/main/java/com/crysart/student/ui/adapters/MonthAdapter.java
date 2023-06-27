package com.crysart.student.ui.adapters;
import androidx.recyclerview.widget.RecyclerView;
import com.crysapp.student.R;
import androidx.cardview.widget.CardView;
import com.crysart.student.ui.adapters.MonthAdapter;
import java.time.Month;
import java.time.LocalDate;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
public class MonthAdapter extends RecyclerView.Adapter<MonthAdapter.ViewHolder> {
    private Receiver receiver;
    public static class ViewHolder extends RecyclerView.ViewHolder{
            private TextView text ;
            
            CardView card ;
            public ViewHolder(View v){
                super(v);
                card = v.findViewById(R.id.text_card);
                text=v.findViewById(R.id.month_text);
                }
            public TextView getTextView(){
                 return text;
                }
        }
        public ViewHolder onCreateViewHolder(ViewGroup container,int position){
          return new ViewHolder(LayoutInflater.from(container.getContext()).inflate(R.layout.month_card,container,false));
            }
       public void onBindViewHolder(ViewHolder vh,int position){
           LocalDate ld = LocalDate.now();
           int cmonth = ld.getMonthValue();
           String info ;
           int mnt;
           if(position==0){
               info = "ThIS MONTH";
               mnt = -1;
           }
          else{
                if((position+cmonth-1)<12){
                    info = Month.values()[position+cmonth-1].toString();
                    mnt=position+cmonth-1;
                }
                else{
                    
                   info = Month.values()[cmonth-(13-position)].toString()+" "+(ld.getYear()+1);
                   mnt = cmonth-(13-position);
                }
                
          }
          vh.getTextView().setText(info);
                vh.card.setOnClickListener(v->{
                    receiver.onMonthReceive(mnt);
                    });
          
       }
           
           public int getItemCount(){
               return 13;}
               public MonthAdapter(Receiver r){
                   receiver = r;
                   }
       public static interface Receiver{
             public void onMonthReceive(int mnt);
      }
}









