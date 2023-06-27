package com.crysapp.student.utill;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Size;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.recyclerview.selection.StorageStrategy;
import com.crysapp.student.R;
import com.crysapp.student.models.Attachment;
import java.lang.Exception;
import java.lang.Object;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
    
public abstract class Action<P,G>{
    private static Handler handler = new Handler(Looper.getMainLooper());
    private static ExecutorService excecutor = Executors.newSingleThreadExecutor();
    abstract protected G doInBackground(final P parameter) throws Exception;
    abstract protected void doOnForeground(final G result);
    protected void doOnError(final Exception e){
        Log.e(e.getClass().getSimpleName(),"Error while performing background task" + e.getMessage(),e);
    }
    public void exec(final P parameter){
        excecutor.execute(new Runner(this,parameter));
    }
   private static class Runner implements Runnable{
        private static final int STATE_BACKGROUND = 1; 
        private static final int STATE_FOREGROUND = 2; 
        private static final int STATE_ERROR = 3;
        private int state = STATE_BACKGROUND;
        private final Action commander ;
        private Object value;
        public Runner(Action action ,Object value){
            commander = action;
            this.value = value;
        }
        void onBacground(){
            try{
                value = commander.doInBackground(value);
                state = STATE_FOREGROUND;
            }catch(final Exception e){
                this.value=e;
                state = STATE_ERROR;
            }
            finally{
                Log.e("background ","before failure");
                handler.post(this);
            }
        }
        void onForeGround(){
            try{
                 commander.doOnForeground(value);
            }catch(final Exception error){
                this.value=error;
                this.state=STATE_ERROR;
                handler.post(this);
                
            }
        }
        void onError(){
               commander.doOnError((Exception)value);
        }

        @Override
        public void run() {
            switch(state){
                case STATE_BACKGROUND:onBacground();break;
                case STATE_FOREGROUND:onForeGround();break;
                case STATE_ERROR:onError();
            }
        }
    }
    public static class Thumbs extends Action<Attachment,Bitmap>{
         private ImageView v;
         private Attachment attach;
         public Thumbs setUp(ImageView view){
             v = view;
             return this;
         }
         public void doOnForeground(Bitmap result){
             if(result  != null)
                v.setImageBitmap(result);
            else{
                if(attach.getType().contains("pdf"))v.setImageDrawable( v.getResources().getDrawable(R.drawable.ic_file_pdf,null));
                else if(attach.getType().contains("word"))v.setImageDrawable( v.getResources().getDrawable(R.drawable.ic_file_word,null));
                else v.setImageDrawable( v.getResources().getDrawable(R.drawable.ic_file,null));
            }
         }
      public Bitmap doInBackground(Attachment attaches) throws Exception{
         
              this.attach=attaches;
            Size size = new Size(128,256);
            if(attaches.getType().contains("video/")){
                  return ThumbnailUtils.createVideoThumbnail(attaches.getFile(),size,null);

            }
            else if(attaches.getType().contains("image/")){
             return ThumbnailUtils.createImageThumbnail(attaches.getFile(),size,null);
              
            }
            else if(attaches.getType().contains("audio/")){
                return ThumbnailUtils.createAudioThumbnail(attaches.getFile(),size,null);
                
            }
            else{
                return null;
            }
           
        }
        
    }
}
