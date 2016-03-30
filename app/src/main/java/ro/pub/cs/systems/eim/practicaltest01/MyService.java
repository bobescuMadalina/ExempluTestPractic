package ro.pub.cs.systems.eim.practicaltest01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;

import ro.pub.cs.systems.eim.practicaltest01.practicaltest01.MyThread;

/**
 * Created by mada on 30.03.2016.
 */
public class MyService extends Service {
    MyThread thread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //
        Integer firstValue = Integer.valueOf(intent.getStringExtra("first_value"));
        Integer secondValue = Integer.valueOf(intent.getStringExtra("second_value"));
        thread = new MyThread(getApplicationContext(), firstValue, secondValue);
        thread.start();


        return Service.START_REDELIVER_INTENT;


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        thread.stopThread();
        super.onDestroy();
    }
}
