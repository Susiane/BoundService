package br.com.teste.boundservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

/**
 * Created by Susiane on 18/05/2016.
 */
public class LocalService extends Service {
    private int count;
    private boolean active;
    public static final int ID_NOTIFICACAO = 123456;
    private MediaPlayer mp;
    private static final String PREF_NAME = "MainActivityPreferences";
    private int count1;
    private boolean atividade;
    private int tempoInicial, tempoFinal, tempo, qtdThread;


    // Binder given to clients
    private final IBinder mBinder = (IBinder) new LocalBinder();
    // Random number generator
    private final Random mGenerator = new Random();


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LocalService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LocalService.this;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** method for clients */
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
    public int getCount() {        return count;    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG","onCreate");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG","onStartCommand" + startId + "active :"+ active);
        if(startId == 1){
            Log.d("TAG", "Condicao startId == 1");
            setThred();
        }

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Minha primeira Notificação")
                .setContentText("Que lindo!")
                .setSmallIcon(R.drawable.de_uma_pausa_menu_icon)
                .setContentIntent(pendingIntent)
                .build();


        startForeground(ID_NOTIFICACAO,notification);

        SharedPreferences sp1 = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        count1 = sp1.getInt("count_1",0);
        Log.d("TAG","count1 :"+count1);
        tempoInicial = (int) SystemClock.elapsedRealtime();




        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tempoFinal = (int) SystemClock.elapsedRealtime();
        tempo = tempoFinal-tempoInicial;
        active = false;
        Log.d("TAG","onDestroy");
        Log.d("TAG","Tempo :"+ tempo );
    }

    public void setThred(){
        Log.d("TAG", "iniciou o setThred");
        count = 0;
        active = true;
        atividade = true;
        mp = MediaPlayer.create(this, R.raw.msg);


        new Thread(){
            public void run(){

                /*if(atividade){
                    qtdThread = 5;
                    atividade = false;
                }else{
                    qtdThread = 2;
                    atividade = true;
                }*/
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("TAG", "Primeiro Count :"+ count);

                while(active && count < 100){
                    try {

                        if(atividade){
                            Thread.sleep(1000);
                            atividade =false;
                        }else {
                            Thread.sleep(5000);
                            atividade =true;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count++;
                    //mp.start();

                    Log.d("TAG", "Count :"+ count);
                }

            }

        }.start();
    }
}
