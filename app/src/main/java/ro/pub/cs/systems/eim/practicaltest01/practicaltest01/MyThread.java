package ro.pub.cs.systems.eim.practicaltest01.practicaltest01;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;

import java.util.Date;

/**
 * Created by mada on 30.03.2016.
 */
public class MyThread extends Thread {
    private boolean isRunning = true;
    private Integer firstValue, secondValue;
    private double arithmeticMean;
    private double geometricMean;
    private Context context;


    public MyThread(Context context, Integer firstValue, Integer secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;

        arithmeticMean = (firstValue + secondValue) /2;
        geometricMean = Math.sqrt(firstValue + secondValue);

        this.context = context;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void sendMessage () {
        Intent intent = new Intent();
        intent.setAction(Constants.MY_ACTION);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + arithmeticMean + " " + geometricMean);
        context.sendBroadcast(intent);

    }

    public void stopThread(){
        isRunning = false;
    }
}
