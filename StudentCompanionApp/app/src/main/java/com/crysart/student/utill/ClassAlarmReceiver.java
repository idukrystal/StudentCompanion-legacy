package com.crysapp.student.utill;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.crysapp.student.R;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ClassAlarmReceiver extends BroadcastReceiver{
    public static final String CHANNEL_ID = "10000";
    public static final String Time_Code = "11001";
    public static final String Stop_Code =  "1001111";
    public static final String Start_Code = "10011";
    public static final String Class_Code = "10100";
    
    @Override
    public void onReceive(Context context, Intent intent) 
    {   createNotificationChannel(context);
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();
        
        LocalDateTime ldt = LocalDateTime.now();   
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        String title =((zdt.toInstant().toEpochMilli()-intent.getLongExtra(Time_Code,0)<=(1000*60*30)))?context.getString(R.string.now):context.getString(R.string.missed);
        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example
         NotificationCompat.Builder nb = new  NotificationCompat.Builder(context,CHANNEL_ID)
         .setContentTitle(title).setSmallIcon(R.drawable.ic_book_opent).setContentText(intent.getStringExtra(Class_Code)).setPriority(NotificationCompat.PRIORITY_MAX);
          NotificationManagerCompat nm = NotificationManagerCompat.from(context);
          int  not_id = 11;
          nm.notify(not_id,nb.build());
          
    }
    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context,ClassAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 10, pi); // Millisec * Second * Minute
    }
    private void createNotificationChannel(Context context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CharSequence name = context.getString(R.string.class_notification_channel);
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }
}
}