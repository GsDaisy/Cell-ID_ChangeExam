package comsuyoung.cellchangetest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class TestService extends Service {
    private PhoneStateListener phoneStateListener;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("서비스", "스타트커맨드");
//        phoneStateListener = new PhoneStateListener(this);
//
//        phoneStateListener.start(this);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onCreate() {
        Log.i("서비스", "온크리에이트");
        super.onCreate();
        phoneStateListener = new PhoneStateListener(this);
        phoneStateListener.start(this);

        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String CHANNEL_ID = "UltraRelDev";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setShowBadge(false);
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            builder = new NotificationCompat.Builder(this, "UltraRelDev");
        }
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentText("초신뢰 Device");
        //.setContentIntent(pendingIntent)
        startForeground(1, builder.build());

    }

    @Override
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }
}
